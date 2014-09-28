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

import java.util.List;

import org.w3c.dom.Element;

import com.nextbreakpoint.nextfractal.core.common.StringElement;
import com.nextbreakpoint.nextfractal.core.common.StringElementXMLImporter;
import com.nextbreakpoint.nextfractal.core.xml.XMLImportException;
import com.nextbreakpoint.nextfractal.twister.layerFilter.LayerFilterConfigElement;
import com.nextbreakpoint.nextfractal.twister.layerFilter.LayerFilterConfigElementXMLImporter;

/**
 * @author Andrea Medeghini
 */
public class GroupLayerConfigElementXMLImporter extends AbstractLayerConfigElementXMLImporter<GroupLayerConfigElement> {
	/**
	 * @see com.nextbreakpoint.nextfractal.twister.layer.AbstractLayerConfigElementXMLImporter#createConfigElement()
	 */
	@Override
	protected GroupLayerConfigElement createConfigElement() {
		return new GroupLayerConfigElement();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.layer.AbstractLayerConfigElementXMLImporter#getConfigElementId()
	 */
	@Override
	protected String getConfigElementId() {
		return GroupLayerConfigElement.CLASS_ID;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public GroupLayerConfigElement importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, getConfigElementId());
		final GroupLayerConfigElement configElement = createConfigElement();
		final List<Element> propertyElements = getProperties(element);
		if (isVersion(element, 1) && (propertyElements.size() == getPropertiesSize() + 3)) {
			importProperties1(configElement, propertyElements);
		}
		else if (isVersion(element, 0) && (propertyElements.size() == getPropertiesSize() + 2)) {
			importProperties0(configElement, propertyElements);
		}
		return configElement;
	}

	protected void importProperties0(final GroupLayerConfigElement configElement, final List<Element> propertyElements) throws XMLImportException {
		super.importProperties(configElement, propertyElements);
		importFilters(configElement, propertyElements.get(getPropertiesSize() + 0));
		importLayers(configElement, propertyElements.get(getPropertiesSize() + 1));
	}

	protected void importProperties1(final GroupLayerConfigElement configElement, final List<Element> propertyElements) throws XMLImportException {
		super.importProperties(configElement, propertyElements);
		importFilters(configElement, propertyElements.get(getPropertiesSize() + 0));
		importLayers(configElement, propertyElements.get(getPropertiesSize() + 1));
		importLabel(configElement, propertyElements.get(getPropertiesSize() + 2));
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.layer.AbstractLayerConfigElementXMLImporter#importProperties(com.nextbreakpoint.nextfractal.twister.layer.LayerConfigElement, java.util.List)
	 */
	@Override
	protected void importProperties(final GroupLayerConfigElement configElement, final List<Element> propertyElements) throws XMLImportException {
		super.importProperties(configElement, propertyElements);
	}

	private void importFilters(final GroupLayerConfigElement configElement, final Element element) throws XMLImportException {
		final LayerFilterConfigElementXMLImporter filterImporter = new LayerFilterConfigElementXMLImporter();
		final List<Element> filterElements = this.getElements(element, LayerFilterConfigElement.CLASS_ID);
		for (int i = 0; i < filterElements.size(); i++) {
			configElement.appendFilterConfigElement(filterImporter.importFromElement(filterElements.get(i)));
		}
	}

	private void importLayers(final GroupLayerConfigElement configElement, final Element element) throws XMLImportException {
		final ImageLayerConfigElementXMLImporter imageLayerImporter = new ImageLayerConfigElementXMLImporter();
		final List<Element> layerElements = this.getElements(element, ImageLayerConfigElement.CLASS_ID);
		for (int i = 0; i < layerElements.size(); i++) {
			configElement.appendLayerConfigElement(imageLayerImporter.importFromElement(layerElements.get(i)));
		}
	}

	private void importLabel(final GroupLayerConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> stringElements = this.getElements(element, StringElement.CLASS_ID);
		if (stringElements.size() == 1) {
			configElement.setLabel(new StringElementXMLImporter().importFromElement(stringElements.get(0)).getValue());
		}
	}
}
