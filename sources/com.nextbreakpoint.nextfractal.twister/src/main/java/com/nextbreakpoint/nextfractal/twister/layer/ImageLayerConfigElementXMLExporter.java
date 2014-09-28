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
package com.nextbreakpoint.nextfractal.twister.layer;

import org.w3c.dom.Element;

import com.nextbreakpoint.nextfractal.core.common.StringElementXMLExporter;
import com.nextbreakpoint.nextfractal.core.xml.XMLExportException;
import com.nextbreakpoint.nextfractal.core.xml.XMLNodeBuilder;
import com.nextbreakpoint.nextfractal.twister.image.ImageConfigElementXMLExporter;
import com.nextbreakpoint.nextfractal.twister.layerFilter.LayerFilterConfigElementXMLExporter;

/**
 * @author Andrea Medeghini
 */
public class ImageLayerConfigElementXMLExporter extends AbstractLayerConfigElementXMLExporter<ImageLayerConfigElement> {
	/**
	 * @see com.nextbreakpoint.nextfractal.twister.layer.AbstractLayerConfigElementXMLExporter#exportProperties(com.nextbreakpoint.nextfractal.twister.layer.LayerConfigElement, org.w3c.dom.Element, com.nextbreakpoint.nextfractal.core.xml.XMLNodeBuilder)
	 */
	@Override
	protected void exportProperties(final ImageLayerConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		super.exportProperties(configElement, element, builder);
		exportFilters(configElement, createProperty(builder, element, "filterList"), builder);
		exportImage(configElement, createProperty(builder, element, "image"), builder);
		exportLabel(configElement, createProperty(builder, element, "label"), builder);
	}

	private void exportFilters(final ImageLayerConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final LayerFilterConfigElementXMLExporter filterExporter = new LayerFilterConfigElementXMLExporter();
		for (int i = 0; i < configElement.getFilterConfigElementCount(); i++) {
			element.appendChild(filterExporter.exportToElement(configElement.getFilterConfigElement(i), builder));
		}
	}

	private void exportImage(final ImageLayerConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ImageConfigElementXMLExporter().exportToElement(configElement.getImageConfigElement(), builder));
	}

	private void exportLabel(final ImageLayerConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new StringElementXMLExporter().exportToElement(configElement.getLabelElement(), builder));
	}
}
