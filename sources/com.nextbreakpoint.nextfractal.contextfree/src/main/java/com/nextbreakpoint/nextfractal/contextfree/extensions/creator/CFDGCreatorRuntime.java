/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.extensions.creator;

import com.nextbreakpoint.nextfractal.contextfree.cfdg.CFDGConfigElement;
import com.nextbreakpoint.nextfractal.core.creator.extension.CreatorExtensionRuntime;
import com.nextbreakpoint.nextfractal.core.scripting.JSException;

/**
 * @author Andrea Medeghini
 */
public class CFDGCreatorRuntime extends CreatorExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.creator.extension.element.CreatorExtensionRuntime#create(java.lang.Object[])
	 */
	@Override
	public Object create(final Object... args) throws JSException {
		if ((args != null) && (args.length > 0)) {
			throw new JSException("CFDGConfigElement creator requires no arguments");
		}
		return new CFDGConfigElement();
	}
}