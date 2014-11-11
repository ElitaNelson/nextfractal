/*
 * NextFractal 7.0 
 * http://www.nextbreakpoint.com
 *
 * Copyright 2001, 2015 Andrea Medeghini
 * andrea@nextbreakpoint.com
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
package com.nextbreakpoint.nextfractal.core.extensions.action;

import org.w3c.dom.Element;

import com.nextbreakpoint.nextfractal.core.elements.LongElement;
import com.nextbreakpoint.nextfractal.core.elements.LongElementXMLExporter;
import com.nextbreakpoint.nextfractal.core.runtime.common.AbstractActionXMLExporterRuntime;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeActionValue;
import com.nextbreakpoint.nextfractal.core.runtime.xml.XMLExportException;
import com.nextbreakpoint.nextfractal.core.runtime.xml.XMLNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class LongNodeActionXMLExporterRuntime extends AbstractActionXMLExporterRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.common.twister.util.AbstractActionXMLExporterRuntime#exportParams(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeActionValue, org.w3c.dom.Element, com.nextbreakpoint.nextfractal.core.runtime.xml.XMLNodeBuilder)
	 */
	@Override
	protected void exportParams(final NodeActionValue action, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final LongElementXMLExporter exporter = new LongElementXMLExporter();
		final LongElement configElement0 = new LongElement((Long) action.getActionParams()[0]);
		final LongElement configElement1 = new LongElement((Long) action.getActionParams()[1]);
		element.appendChild(exporter.exportToElement(configElement0, builder));
		element.appendChild(exporter.exportToElement(configElement1, builder));
	}
}
