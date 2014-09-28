/*
 * NextFractal 6.1 
 * http://nextfractal.sourceforge.net
 *
 * Copyright 2001, 2010 Andrea Medeghini
 * http://andreamedeghini.users.sourceforge.net
 *
 * This file is part of NextFractal.
 *
 * NextFractal is an application for creating fractals and other graphics artifacts.
 *
 * NextFractal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NextFractal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NextFractal.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.nextbreakpoint.nextfractal.twister.common;

import org.w3c.dom.Element;

import com.nextbreakpoint.nextfractal.core.config.ValueConfigElementXMLImporter;
import com.nextbreakpoint.nextfractal.core.xml.XMLExporter;
import com.nextbreakpoint.nextfractal.core.xml.XMLImportException;

/**
 * @author Andrea Medeghini
 */
public class PercentageElementXMLImporter extends ValueConfigElementXMLImporter<Integer, PercentageElement> {
	/**
	 * @param element
	 * @param expectedClassId
	 * @throws XMLImportException
	 */
	@Override
	protected void checkClassId(final Element element, final String expectedClassId) throws XMLImportException {
		final String classId = getClassId(element);
		if (!"Percent".equals(classId) && !classId.equals(expectedClassId)) {
			throw new XMLImportException("Invalid value: " + XMLExporter.ATTRIBUTE_CLASS_ID + " = " + expectedClassId);
		}
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.config.ValueConfigElementXMLImporter#parseValue(java.lang.String)
	 */
	@Override
	protected Integer parseValue(final String value) {
		return Integer.valueOf(value);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.config.ValueConfigElementXMLImporter#createDefaultConfigElement()
	 */
	@Override
	protected PercentageElement createDefaultConfigElement() {
		return new PercentageElement(100);
	}
}
