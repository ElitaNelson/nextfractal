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
package com.nextbreakpoint.nextfractal.mandelbrot.extensions.paletteRenderer;

import java.util.List;

import org.w3c.dom.Element;

import com.nextbreakpoint.nextfractal.core.extensionPoints.extensionConfigXMLImporter.ExtensionConfigXMLImporterExtensionRuntime;
import com.nextbreakpoint.nextfractal.core.xml.XMLImportException;
import com.nextbreakpoint.nextfractal.core.xml.XMLImporter;
import com.nextbreakpoint.nextfractal.mandelbrot.common.RenderedPaletteElement;
import com.nextbreakpoint.nextfractal.mandelbrot.common.RenderedPaletteElementXMLImporter;

/**
 * @author Andrea Medeghini
 */
public class DefaultRendererConfigXMLImporterRuntime extends ExtensionConfigXMLImporterExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.extensionPoints.extensionConfigXMLImporter.ExtensionConfigXMLImporterExtensionRuntime#createXMLImporter()
	 */
	@Override
	public XMLImporter<DefaultRendererConfig> createXMLImporter() {
		return new DefaultRendererConfigXMLImporter();
	}

	private class DefaultRendererConfigXMLImporter extends AbstractPaletteRendererConfigXMLImporter<DefaultRendererConfig> {
		/**
		 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensions.paletteRenderer.AbstractPaletteRendererConfigXMLImporter#createExtensionConfig()
		 */
		@Override
		protected DefaultRendererConfig createExtensionConfig() {
			return new DefaultRendererConfig();
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensions.paletteRenderer.AbstractPaletteRendererConfigXMLImporter#getConfigElementClassId()
		 */
		@Override
		protected String getConfigElementClassId() {
			return "DefaultRendererConfig";
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensions.paletteRenderer.AbstractPaletteRendererConfigXMLImporter#getPropertiesSize()
		 */
		@Override
		protected int getPropertiesSize() {
			return 1;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensions.paletteRenderer.AbstractPaletteRendererConfigXMLImporter#importProperties(com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.paletteRenderer.PaletteRendererExtensionConfig, java.util.List)
		 */
		@Override
		protected void importProperties(final DefaultRendererConfig config, final List<Element> propertyElements) throws XMLImportException {
			importRenderedPalette(config, propertyElements.get(0));
		}

		/**
		 * @param config
		 * @param element
		 * @throws XMLImportException
		 */
		protected void importRenderedPalette(final DefaultRendererConfig config, final Element element) throws XMLImportException {
			final List<Element> elements = this.getElements(element, RenderedPaletteElement.CLASS_ID);
			if (elements.size() == 1) {
				config.getRenderedPaletteElement().setValue(new RenderedPaletteElementXMLImporter().importFromElement(elements.get(0)).getValue());
			}
		}
	}
}
