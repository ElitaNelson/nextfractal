/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.extensions.action;

import com.nextbreakpoint.nextfractal.contextfree.shapeReplacement.ShapeReplacementConfigElement;
import com.nextbreakpoint.nextfractal.contextfree.shapeReplacement.ShapeReplacementConfigElementXMLImporter;
import com.nextbreakpoint.nextfractal.core.runtime.common.AbstractConfigElementListNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class ShapeReplacementElementListNodeActionXMLImporterRuntime extends AbstractConfigElementListNodeActionXMLImporterRuntime<ShapeReplacementConfigElement> {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.common.AbstractConfigElementListNodeActionXMLImporterRuntime#createImporter()
	 */
	@Override
	protected ShapeReplacementConfigElementXMLImporter createImporter() {
		return new ShapeReplacementConfigElementXMLImporter();
	}
}
