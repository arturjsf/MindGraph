/*
*    GraphDraw for JavaFX
*    Copyright (C) 2016  brunomnsilva@gmail.com
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    any later version.
*
*   This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package javafxgraphs.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafxgraphs.tad.iVertex;
import javafxgraphs.tad.iGraph;
import javafxgraphs.tad.iEdge;

/**
 * This class represents a pane that can be used to draw a iGraph structure.
 * Object-types for vertices and edges must implement the DrawableGraphElement interface.
 * 
 * Text of vertices and edges is obtained through the toString() method of the stored 
 * elements.
 * 
 * Used references:
 * http://fxexperience.com/2014/05/resizable-grid-using-canvas/
 * http://stackoverflow.com/questions/16670393/java-draw-arc-between-2-points
 *
 * @author brunomnsilva
 * @param <V> type of vertex stored elements
 * @param <E> type of edge stored elements
 */
public class GraphDraw<V extends DrawableGraphElement, E extends DrawableGraphElement> extends Pane {

    //GLOBAL CONFIGURATIONS.////////////////////////////////////////////////////
    public static String CONFIG_BACKGROUND = "white"; //pixels //deprecated

    public static int CONFIG_NODE_DIAMETER = 30; //pixels
    public static int CONFIG_NODE_STROKE_WIDTH = 3; //pixels
    public static String CONFIG_NODE_DRAW = "black"; 
    public static String CONFIG_NODE_FILL = "yellow"; 
    public static Font CONFIG_NODE_FONT = new Font("sans", 12);

    public static double CONFIG_EDGE_STROKE_WIDTH = 0.5;
    public static double CONFIG_EDGE_STROKE_WIDTH_SELECTED = 1;
    public static String CONFIG_EDGE_DRAW = "black";
    public static String CONFIG_EDGE_DRAW_SELECTED = "red";
    public static Font CONFIG_EDGE_FONT = new Font("sans", 10);
    
    private static final double OVERLAP_OFFSET_ADJUSTMENT = 15;
    private static final double OVERLAP_THRESHOLD_DISTANCE = 5; // do not change this one
    
    //TODO: text rotation is experimental. Problems with multiple edges between vertices. 
    //Can be improved.
    public static  boolean rotateText = false;
    
    ////////////////////////////////////////////////////////////////////////////
    
    /** the canvas to draw on */
    private final Canvas canvas = new Canvas();
    /** reference to the graph to be drawn */
    private final iGraph<V, E> graph;    
    
    /** list of vertices locations on the canvas */
    private final List<VertexLocation> locations;
    /** map of locations and their indices in the <i>locations</i> list */
    private final HashMap<String, Integer> locationIndices;

    /** list of 2d points used to stroke text. Used to draw multiple edges's text */
    private final List<Point2D> edgeTextMidPoints;
    
    //only used in alternate edge connections. Uncomment, if needed.
    //private Random randGenerator = new Random();
    
    /**
     * Constructor.
     * @param graph the graph to be drawn 
     */
    public GraphDraw(iGraph<V, E> graph) {
        this.graph = graph;

        locations = new ArrayList<>();
        locationIndices = new HashMap<>();
        edgeTextMidPoints = new LinkedList<>();
        
        getChildren().add(canvas);
        generatePositions();
    }

    /**
     * Generates the positions of the vertices using a circular structure.
     * Vertices and place equidistant to each other.
     */
    private void generatePositions() {
        ////////////////////////////////////////////////////////////////////////
        //TODO: Improvement: generate positions based on different strategies, 
        //e.g., enclosed in a circle, in a rectangle, etc.
        ////////////////////////////////////////////////////////////////////////
        
        if (graph == null || graph.numVertices() == 0) {
            return;
        }

        //clear previous positions (they may change when canvas is resized)
        locations.clear();
        locationIndices.clear();
        edgeTextMidPoints.clear();

        int N = graph.numVertices();

        double width = this.getWidth();
        double height = this.getHeight();

        //System.out.println(String.format("W= %f | H = %f", width, height));
        Point2D center = new Point2D(width / 2, height / 2);

        double angleIncrement = 360f / N;

        Iterable<iVertex<V>> vertices = graph.vertices();
        ArrayList<iVertex<V>> listVertices = new ArrayList<>(N);
        for (iVertex<V> v : vertices) {
            listVertices.add(v);
        }

        ////////////////////////////////////////////////////////////////////////
        //TODO: Improvement: reorder listVertices to minimize edge crossings        
        ////////////////////////////////////////////////////////////////////////
        
        //place first vertice north position, others in clockwise manner
        boolean first = true;
        Point2D p = null;
        int index = 0;
        for (iVertex<V> v : listVertices) {

            if (first) {
                //verifiy smaller width and height. Responsiveness to resize.
                if(width > height)
                    p = new Point2D(center.getX(),
                            center.getY() - height / 2 + CONFIG_NODE_DIAMETER * 2);
                else
                    p = new Point2D(center.getX(),
                            center.getY() - width / 2 + CONFIG_NODE_DIAMETER * 2);
                
                first = false;
            } else {
                p = rotate(p, center, angleIncrement);
            }

            locations.add(index, new VertexLocation(v, p) );
            locationIndices.put(v.element().getId(), index);
            //System.out.println(v.element().getId() + " --- " + index);
            index++;
        }
    }

    /**
     * Checks if two vertices are drawn side-by-side
     * @param vla first vertex location
     * @param vlb second vertex location
     * @return vertices are drawn side-by-side
     */
    private boolean sideByside(VertexLocation vla, VertexLocation vlb) {
        int indexA = locationIndices.get(vla.vertex.element().getId());
        int indexB = locationIndices.get(vlb.vertex.element().getId());
        
        //checks if indices are consecutive or first/last
        int indexDist = Math.abs( indexA - indexB) % ( locationIndices.size() - 1);
        
        return (indexDist <= 1);
    }
    
    /**
     * Method to draw the graph using 2D primitives. The method is called
     * automatically whenever any update is needed. Can be called externally
     * to forced updates, e.g., when selected edges change.
     */
    public void redraw() {

        generatePositions();

        double width = canvas.getWidth();
        double height = canvas.getHeight();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //fill background. Deprecated. Now transparent
        //gc.setFill(Paint.valueOf(CONFIG_BACKGROUND));
        
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.clearRect(0, 0, width, height);

        //!important so things are aligned correctly.
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.BOTTOM); 
        gc.setGlobalBlendMode(BlendMode.DIFFERENCE);

        //DRAW EDGES    
        gc.setFont(CONFIG_EDGE_FONT);
        
        Iterable<iEdge<E, V>> edges = graph.edges();
        
        for (iEdge<E, V> edge : edges) {

            iVertex<V>[] vertices = edge.vertices();

            iVertex<V> a = vertices[0];
            iVertex<V> b = vertices[1];

            VertexLocation vla = locations.get(locationIndices.get(a.element().getId()));
            VertexLocation vlb = locations.get(locationIndices.get(b.element().getId()));

            //System.out.println(String.format("SIDE-BY-SIDE %s & %s ? %s", a.element().getId(), b.element().getId(), sideByside(vla,vlb)));
            
            if(sideByside(vla,vlb)) {
                Point2D center = new Point2D(width / 2, height / 2);

                connectArc(gc, vla.location, vlb.location, 
                        center, 
                        edge.element());
            } else {
                
                //commented alternate ways do draw edges. Just for for future reference.
//                gc.beginPath();
//                gc.moveTo(vla.location.getX(), vla.location.getY());
////                gc.quadraticCurveTo( 
////                        width / 2, height / 2, 
////                        vlb.location.getX(), vlb.location.getY());
//                
//                gc.bezierCurveTo(width / 2, height / 2, 
//                        randGenerator.nextInt((int)width), randGenerator.nextInt((int)height), 
//                        vlb.location.getX(), vlb.location.getY());
//                
//                gc.stroke();
                
                connectLine(gc, vla.location, vlb.location, edge.element());
            }
            
            
        }

        //DRAW NODES
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.setFill(Paint.valueOf(CONFIG_NODE_FILL));
        gc.setStroke(Paint.valueOf(CONFIG_NODE_DRAW));
        //gc.setEffect(new Reflection());
        gc.setFont(CONFIG_NODE_FONT);

        for( VertexLocation vl : locations ) {
            gc.setLineWidth(CONFIG_NODE_STROKE_WIDTH);

            //draw node
            gc.strokeOval(vl.location.getX() - CONFIG_NODE_DIAMETER / 2,
                    vl.location.getY() - CONFIG_NODE_DIAMETER / 2,
                    CONFIG_NODE_DIAMETER, CONFIG_NODE_DIAMETER);

            //fill node
            gc.fillOval(vl.location.getX() - CONFIG_NODE_DIAMETER / 2,
                    vl.location.getY() - CONFIG_NODE_DIAMETER / 2,
                    CONFIG_NODE_DIAMETER, CONFIG_NODE_DIAMETER);

            //draw node name
            gc.setLineWidth(1);
            gc.strokeText(vl.vertex.element().toString(),
                    vl.location.getX(),
                    vl.location.getY() + CONFIG_NODE_DIAMETER);
        }

        
    }

    /**
     * Draws a line between two points with a label mid-point.
     * @param gc graphics context of canvas to draw on
     * @param a origin point
     * @param b destination point
     * @param label label to write
     */
    private void connectLine(GraphicsContext gc, Point2D a, Point2D b, E edge) {
        
        if (edge.isSelected()) {
            gc.setLineWidth(CONFIG_EDGE_STROKE_WIDTH_SELECTED);
            gc.setStroke(Paint.valueOf(CONFIG_EDGE_DRAW_SELECTED));
            gc.setFill(Paint.valueOf(CONFIG_EDGE_DRAW_SELECTED));
        } else {
            gc.setLineWidth(CONFIG_EDGE_STROKE_WIDTH);
            gc.setStroke(Paint.valueOf(CONFIG_EDGE_DRAW));
            gc.setFill(Paint.valueOf(CONFIG_EDGE_DRAW));
        }

        //draw straight line
        gc.strokeLine(a.getX(), a.getY(),
                b.getX(), b.getY());

        //draw edge text midpoint
        Point2D mid = a.midpoint(b);
        
        //hack! :( 
        if(checkTextOverlap(mid, edgeTextMidPoints)) {
            mid = mid.add(0, OVERLAP_OFFSET_ADJUSTMENT);
        }         
        edgeTextMidPoints.add(mid);
        //end hack :)
        
        gc.setLineWidth(1);
        if (!rotateText) {
            gc.fillText(edge.toString(), mid.getX(), mid.getY());
        } else {
            //rotate text so that it is parallel to the line
            double angle = -angle(a, b);
            strokeRotatedText(gc, mid, angle, edge.toString());
        }
    }
    
    /**
     * Strokes the text in a specified rotation
     * @param gc graphics context of canvas
     * @param p the mid point of the text
     * @param angleDegrees rotation angle in degrees
     * @param text the text to stroke
     */
    private void strokeRotatedText(GraphicsContext gc, Point2D p, double angleDegrees, String text) 
    {    
        //to avoid stroking upside down text, correct angle. We only want angleDegrees \in [-90, 90]
        int sign = angleDegrees >= 0 ? 1 : -1;
        angleDegrees = Math.abs(angleDegrees);
        while(angleDegrees > 90) {
            angleDegrees -= 180;
        }
        angleDegrees *= sign;
        //end-fix angle
        
        gc.save(); //save current transform
        
        //apply transform rotation
        Rotate r = new Rotate(angleDegrees, p.getX(), p.getY());
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        
        //stroke the text
        gc.strokeText(text, p.getX(), p.getY());
                
        gc.restore(); //restore original transform
    }  

    /**
     * Draws an arc between two points with a label at the center of the arc.
     * @param gc graphics context of canvas to draw on
     * @param a origin point
     * @param b destination point
     * @param pivot center point
     * @param label label to write
     */
    private void connectArc(GraphicsContext gc, Point2D a, Point2D b, Point2D pivot, E edge) {
      
        if (edge.isSelected()) {
            gc.setLineWidth(CONFIG_EDGE_STROKE_WIDTH_SELECTED);
            gc.setStroke(Paint.valueOf(CONFIG_EDGE_DRAW_SELECTED));
            gc.setFill(Paint.valueOf(CONFIG_EDGE_DRAW_SELECTED));
        } else {
            gc.setLineWidth(CONFIG_EDGE_STROKE_WIDTH);
            gc.setStroke(Paint.valueOf(CONFIG_EDGE_DRAW));
            gc.setFill(Paint.valueOf(CONFIG_EDGE_DRAW));
        }
        
        double radius = a.distance(pivot);
        
        double angle_a_pivot = angle( a, pivot);
        double angle_b_pivot = angle( b, pivot) ;
        
        gc.strokeArc(pivot.getX() - radius,
                pivot.getY() - radius,
                2 * radius,
                2 * radius,
                angle_a_pivot,
                angleDiff(angle_a_pivot, angle_b_pivot),
                ArcType.OPEN);
    
        Point2D mid = rotate(a, pivot, -angleDiff(angle_a_pivot, angle_b_pivot) / 2);
        
        //hack! :(
        if(checkTextOverlap(mid, edgeTextMidPoints)) {
            mid = mid.add(0, OVERLAP_OFFSET_ADJUSTMENT);
        }         
        edgeTextMidPoints.add(mid);
        //end hack :)
        
        gc.setLineWidth(1);
        if (!rotateText) {
            gc.fillText(edge.toString(), mid.getX(), mid.getY());
        } else {
            //rotate text so that it is parallel to the line
            double angle = -angle(a, b);
            strokeRotatedText(gc, mid, angle, edge.toString());
        }
        
        //System.out.println("ARC---");
        //System.out.println("X = " + (pivot.getX() - radius));
        //System.out.println("Y = " + (pivot.getY() - radius));
        //System.out.println("width = " + 2 * radius);
        //System.out.println("radius = " + radius);
        //System.out.println("angle a = " + angle_a_pivot);
        //System.out.println("angle b = " + angle_b_pivot);
        //System.out.println("angle diff = " + angleDiff(angle_a_pivot, angle_b_pivot));
        //System.out.println("------");
    }
    
    /**
     * 
     * @param p
     * @param existing
     * @return 
     */ 
    private static boolean checkTextOverlap(Point2D p, List<Point2D> existing) {
        
        for (Point2D p2 : existing) {
            //Comparing double types for equality is wrong. Use a threshold distance.
            if(p.distance(p2) < OVERLAP_THRESHOLD_DISTANCE) {
                //System.out.println("Overlap detect at: " + p.toString());
                return true; //found an overlap, no need to check more
            }
        }
        
        //no overlap found
        return false;
    }
    
    /**
     * Return polar angle of any point relative to arc center.
     * @param p point to compute angle
     * @param pivot pivot point
     * @return polar angle
     */
    private static double angle(Point2D p, Point2D pivot) {
        //return Math.toDegrees(Math.atan2(pivot.getY() - p.getY(), p.getX() - pivot.getX()));
        double angle = Math.toDegrees(Math.atan2(pivot.getY() - p.getY(), p.getX() - pivot.getX()));
        if (angle < 0) {
            angle += 360;
        }

        return angle;
    }
    
    /**
     * Return angle different between two angles in [-180, 180[
     * @param angle_a
     * @param angle_b
     * @return 
     */
    private static double angleDiff(double angle_a, double angle_b) {
        //double 360 - angle_a - 
        
        double d = angle_b - angle_a;
        while (d >= 180) { d -= 360; }
        while (d < -180) { d += 360; }
        return d;
    }

    /**
     * Rotate a point around a pivot point by a specific degrees amount
     * @param point point to rotate
     * @param pivot pivot point
     * @param angle_degrees rotation degrees
     * @return rotated point
     */
    private static Point2D rotate(final Point2D point, final Point2D pivot, double angle_degrees) {
        double angle = Math.toRadians(angle_degrees); //angle_degrees * (Math.PI/180); //to radians
        
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        //translate to origin
        Point2D result = point.subtract(pivot);
        
        // rotate point
        Point2D rotatedOrigin = new Point2D(
                result.getX() * cos - result.getY() * sin,
                result.getX() * sin + result.getY() * cos);

        // translate point back
        result = rotatedOrigin.add(pivot);

        return result;
    }

    @Override
    protected void layoutChildren() {
        final int top = (int) snappedTopInset();
        final int right = (int) snappedRightInset();
        final int bottom = (int) snappedBottomInset();
        final int left = (int) snappedLeftInset();
        final int w = (int) getWidth() - left - right;
        final int h = (int) getHeight() - top - bottom;
        canvas.setLayoutX(left);
        canvas.setLayoutY(top);
        if (w != canvas.getWidth() || h != canvas.getHeight()) {
            canvas.setWidth(w);
            canvas.setHeight(h);
            redraw();
        }
    }

    /**
     * Helper class that stores a vertex and its generated location
     */
    private class VertexLocation {

        iVertex<V> vertex;
        Point2D location;

        public VertexLocation(iVertex<V> vertex, Point2D location) {
            this.vertex = vertex;
            this.location = location;
        }

    }
   
    //can use this class to represent points and then use equals() to compare
    //overlapping points. For future implementation.
    
//    private class MyPoint2D extends Point2D {
//
//        private static final double THRESHOLD_EQUALITY = 5;
//        public MyPoint2D(double x, double y) {
//            super(x, y);
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if(obj instanceof Point2D)
//                return false;
//            
//            Point2D other = (Point2D)obj;
//            
//            return (this.distance(other) < THRESHOLD_EQUALITY) ? true : false;
//        }
//        
//        
//    }

}
