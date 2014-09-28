/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.common;

import com.nextbreakpoint.nextfractal.core.config.ValueConfigElementXMLImporter;

/**
 * @author Andrea Medeghini
 */
public class FillRuleElementXMLImporter extends ValueConfigElementXMLImporter<String, FillRuleElement> {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.config.ValueConfigElementXMLImporter#parseValue(java.lang.String)
	 */
	@Override
	protected String parseValue(final String value) {
		return String.valueOf(value);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.config.ValueConfigElementXMLImporter#createDefaultConfigElement()
	 */
	@Override
	protected FillRuleElement createDefaultConfigElement() {
		return new FillRuleElement("non-zero");
	}
}
