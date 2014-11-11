/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.extensions.action;

import com.nextbreakpoint.nextfractal.contextfree.shapeReplacement.ShapeReplacementConfigElement;
import com.nextbreakpoint.nextfractal.contextfree.shapeReplacement.ShapeReplacementConfigElementXMLExporter;
import com.nextbreakpoint.nextfractal.core.runtime.common.AbstractConfigElementNodeActionXMLExporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class ShapeReplacementElementNodeActionXMLExporterRuntime extends AbstractConfigElementNodeActionXMLExporterRuntime<ShapeReplacementConfigElement> {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.common.AbstractConfigElementNodeActionXMLExporterRuntime#createExporter()
	 */
	@Override
	protected ShapeReplacementConfigElementXMLExporter createExporter() {
		return new ShapeReplacementConfigElementXMLExporter();
	}
}
