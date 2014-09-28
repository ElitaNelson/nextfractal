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
package com.nextbreakpoint.nextfractal.mandelbrot.extensions.orbitTrap;

import org.w3c.dom.Element;

import com.nextbreakpoint.nextfractal.core.common.DoubleElementXMLExporter;
import com.nextbreakpoint.nextfractal.core.extensionConfigXMLExporter.extension.ExtensionConfigXMLExporterExtensionRuntime;
import com.nextbreakpoint.nextfractal.core.xml.XMLExportException;
import com.nextbreakpoint.nextfractal.core.xml.XMLExporter;
import com.nextbreakpoint.nextfractal.core.xml.XMLNodeBuilder;
import com.nextbreakpoint.nextfractal.mandelbrot.common.CriteriaElementXMLExporter;

/**
 * @author Andrea Medeghini
 */
public class LineTrapConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.extensionConfigXMLExporter.extension.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<LineTrapConfig> createXMLExporter() {
		return new LineTrapConfigXMLExporter();
	}

	private class LineTrapConfigXMLExporter extends AbstractOrbitTrapConfigXMLExporter<LineTrapConfig> {
		/**
		 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensions.incolouringFormula.AbstractOrbitTrapConfigXMLExporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "LineTrapConfig";
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensions.incolouringFormula.AbstractOrbitTrapConfigXMLExporter#exportProperties(com.nextbreakpoint.nextfractal.mandelbrot.incolouringFormula.extension.OrbitTrapExtensionConfig, org.w3c.dom.Element, com.nextbreakpoint.nextfractal.core.xml.XMLNodeBuilder)
		 */
		@Override
		protected void exportProperties(final LineTrapConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			exportThreshold(config, createProperty(builder, element, "threshold"), builder);
			exportRotation(config, createProperty(builder, element, "rotation"), builder);
			exportLength(config, createProperty(builder, element, "length"), builder);
			exportCriteria(config, createProperty(builder, element, "criteria"), builder);
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportThreshold(final LineTrapConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new DoubleElementXMLExporter().exportToElement(config.getThresholdElement(), builder));
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportLength(final LineTrapConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new DoubleElementXMLExporter().exportToElement(config.getLengthElement(), builder));
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportRotation(final LineTrapConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new DoubleElementXMLExporter().exportToElement(config.getRotationElement(), builder));
		}

		/**
		 * @param config
		 * @param element
		 * @param builder
		 * @throws XMLExportException
		 */
		protected void exportCriteria(final LineTrapConfig config, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new CriteriaElementXMLExporter().exportToElement(config.getCriteriaElement(), builder));
		}
	}
}
