//$HeadURL: svn+ssh://mschneider@svn.wald.intevation.org/deegree/deegree3/commons/trunk/src/org/deegree/model/feature/Feature.java $
/*----------------    FILE HEADER  ------------------------------------------

 This file is part of deegree.
 Copyright (C) 2001-2008 by:
 EXSE, Department of Geography, University of Bonn
 http://www.giub.uni-bonn.de/deegree/
 lat/lon GmbH
 http://www.lat-lon.de

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 Contact:

 Andreas Poth  
 lat/lon GmbH 
 Aennchenstr. 19
 53115 Bonn
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
package org.deegree.model.gml;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.deegree.commons.xml.XMLProcessingException;
import org.deegree.model.feature.Feature;
import org.deegree.model.feature.Property;
import org.deegree.model.feature.types.PropertyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Allows the lookup of GML objects by the value of their <code>gml:id</code> attribute.
 * <p>
 * This functionality is essential for resolving local xlink-references at the end of the parsing process of a GML
 * instance document.
 * 
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider </a>
 * @author last edited by: $Author:$
 * 
 * @version $Revision:$, $Date:$
 */
public class GMLIdContext {

    private static final Logger LOG = LoggerFactory.getLogger( GMLIdContext.class );

    private Map<String, Feature> idToFeature = new HashMap<String, Feature>();

    private List<XLinkProperty> xlinkProperties = new ArrayList<XLinkProperty>();

    public void addFeature( Feature feature ) {
        idToFeature.put( feature.getId(), feature );
    }

    public Feature getFeature( String fid ) {
        return idToFeature.get (fid);
    }    
    
    public Property addXLinkProperty( String featureId, PropertyType pt, int occurence, String targetId ) {
        XLinkProperty prop = new XLinkProperty( featureId, pt, occurence, targetId) ;
        xlinkProperties.add( prop );
        return prop;
    }

    /**
     * @throws XMLProcessingException 
     */
    public void resolveXLinks()
                            throws XMLProcessingException {
        for ( XLinkProperty prop : xlinkProperties ) {
            LOG.debug( "Resolving xlink-property with reference to '" + prop.targetId + "'" );
            Object targetObject = idToFeature.get( prop.targetId );
            if ( targetObject == null ) {
                String msg = "Cannot resolve reference to object with id '" + prop.targetId
                             + "'. There is no such object in the document.";
                throw new XMLProcessingException ( msg);
            }
            prop.feature.setPropertyValue( prop.getName(), prop.occurence, targetObject );
        }
    }

    /**
     * Used to identify a (certain occurrence of a) {@link Property} of a {@link Feature}.
     * 
     * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider </a>
     * @author last edited by: $Author:$
     * 
     * @version $Revision:$, $Date:$
     */
    class XLinkProperty implements Property<Object>{

        String featureId;
        
        Feature feature;

        PropertyType pt;

        int occurence;

        String targetId;

        private XLinkProperty( String featureId, PropertyType pt, int occurence, String targetId ) {
            this.featureId = featureId;
            this.pt = pt;
            this.occurence = occurence;
            this.targetId = targetId;
        }

        void setFeature(Feature feature) {
            this.feature = feature;
        }
        
        @Override
        public QName getName() {
            return pt.getName();
        }

        @Override
        public PropertyType getType() {
            return pt;
        }

        @Override
        public Object getValue() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
