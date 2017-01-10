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

/**
 *
 * @author brunomnsilva
 */
public interface DrawableGraphElement {
    
    /**
     * Returns ID of element. Must be unique.
     * @return the ID
     */
    public String getId();
    
    /**
     * Checks whether the element is selected.
     * @return selected state
     */
    public boolean isSelected();
    
}
