//$HeadURL$
/*----------------    FILE HEADER  ------------------------------------------
 This file is part of deegree.
 Copyright (C) 2001-2007 by:
 Department of Geography, University of Bonn
 http://www.giub.uni-bonn.de/deegree/
 lat/lon GmbH
 http://www.lat-lon.de

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.
 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 Lesser General Public License for more details.
 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 Contact:

 Andreas Poth
 lat/lon GmbH
 Aennchenstr. 19
 53177 Bonn
 Germany
 E-Mail: poth@lat-lon.de

 Prof. Dr. Klaus Greve
 Department of Geography
 University of Bonn
 Meckenheimer Allee 166
 53115 Bonn
 Germany
 E-Mail: greve@giub.uni-bonn.de
 ---------------------------------------------------------------------------*/
package org.deegree.model.geometry;

import org.deegree.model.geometry.primitive.Point;


/**
 * 
 * 
 * 
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth</a>
 * @author last edited by: $Author$
 * 
 * @version. $Revision$, $Date$
 */
public interface Envelope extends Geometry {

    /**
     * Must always return {@link Geometry.GeometryType#ENVELOPE}.
     * 
     * @return {@link Geometry.GeometryType#ENVELOPE}.
     */
    @Override
    public GeometryType getGeometryType();    
    
    /**
     * returns the minimum coordinate of an envelope
     * @return minimum coordinate of an envelope
     */
    public Point getMin();
    
    /**
     * returns the maximum coordinate of an envelope
     * @return maximum coordinate of an envelope
     */
    public Point getMax();
    
    /**
     * merges two envelopes into one
     * 
     * @param other
     * @return merged envelope
     */
    public Envelope merge(Envelope other);
    
    /**
     * returns the width of an envelope
     * @return width of an envelope
     */
    public double getWidth();
    
    /**
     * returns the height of an envelope
     * @return height of an envelope
     */
    public double getHeight();
    
    /**
     * returns centroid of an envelope
     * @return centroid of an envelope
     */
    public Point getCentroid();
    
}