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
package com.nextbreakpoint.nextfractal.contextfree;

import org.w3c.dom.Element;

import com.nextbreakpoint.nextfractal.contextfree.cfdg.CFDGConfigElementXMLExporter;
import com.nextbreakpoint.nextfractal.core.xml.XMLExportException;
import com.nextbreakpoint.nextfractal.core.xml.XMLExporter;
import com.nextbreakpoint.nextfractal.core.xml.XMLNodeBuilder;
import com.nextbreakpoint.nextfractal.twister.common.SpeedElementXMLExporter;
import com.nextbreakpoint.nextfractal.twister.common.ViewElementXMLExporter;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeConfigXMLExporter extends XMLExporter<ContextFreeConfig> {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.xml.XMLExporter#exportToElement(java.lang.Object, com.nextbreakpoint.nextfractal.core.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final ContextFreeConfig config, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, ContextFreeConfig.CLASS_ID, 0, 0);
		exportProperties(config, element, builder);
		return element;
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportProperties(final ContextFreeConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		exportCFDG(config, createProperty(builder, element, "cdfg"), builder);
		exportView(config, createProperty(builder, element, "view"), builder);
		exportSpeed(config, createProperty(builder, element, "speed"), builder);
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportCFDG(final ContextFreeConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new CFDGConfigElementXMLExporter().exportToElement(config.getCFDG(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportView(final ContextFreeConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ViewElementXMLExporter().exportToElement(config.getViewElement(), builder));
	}

	/**
	 * @param config
	 * @param element
	 * @param builder
	 * @throws XMLExportException
	 */
	protected void exportSpeed(final ContextFreeConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new SpeedElementXMLExporter().exportToElement(config.getSpeedElement(), builder));
	}
}
