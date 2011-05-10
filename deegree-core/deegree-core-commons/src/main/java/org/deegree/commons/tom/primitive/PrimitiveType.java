//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2011 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package org.deegree.commons.tom.primitive;

import org.apache.xerces.xs.XSSimpleTypeDefinition;

/**
 * Defines a primitive type.
 * 
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @author last edited by: $Author$
 * 
 * @version $Revision$, $Date$
 */
public class PrimitiveType {

    private final BaseType baseType;

    private final XSSimpleTypeDefinition xsType;

    /**
     * Creates a new {@link PrimitiveType} instance for the given {@link BaseType}.
     * 
     * @param baseType
     *            base type, must not be <code>null</code>
     */
    public PrimitiveType( BaseType baseType ) {
        this.baseType = baseType;
        xsType = null;
    }

    /**
     * Creates a new {@link PrimitiveType} instance for the given {@link XSSimpleTypeDefinition}.
     * 
     * @param xsType
     *            XML schema type, must not be <code>null</code>
     */
    public PrimitiveType( XSSimpleTypeDefinition xsType ) {
        this.xsType = xsType;
        this.baseType = BaseType.valueOf( xsType );
    }

    /**
     * Returns the base type.
     * 
     * @return base type, never <code>null</code>
     */
    public BaseType getBaseType() {
        return baseType;
    }

    /**
     * Returns the XML schema simple type definition.
     * 
     * @return XML schema simple type definition, can be <code>null</code>
     */
    public XSSimpleTypeDefinition getXSType() {
        return xsType;
    }
}