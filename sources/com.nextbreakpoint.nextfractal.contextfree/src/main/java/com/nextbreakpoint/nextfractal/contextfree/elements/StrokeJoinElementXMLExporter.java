/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.elements;

import com.nextbreakpoint.nextfractal.core.runtime.common.ValueConfigElementXMLExporter;

/**
 * @author Andrea Medeghini
 */
public class StrokeJoinElementXMLExporter extends ValueConfigElementXMLExporter<String, StrokeJoinElement> {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.common.ValueConfigElementXMLExporter#formatValue(java.io.Serializable)
	 */
	@Override
	protected String formatValue(final String value) {
		return value;
	}
}