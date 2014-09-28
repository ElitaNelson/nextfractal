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
package com.nextbreakpoint.nextfractal.mandelbrot.outcolouringFormula.extension;

import com.nextbreakpoint.nextfractal.core.extension.sl.SLConfigurableExtensionBuilder;
import com.nextbreakpoint.nextfractal.core.extension.sl.SLConfigurableExtensionRegistry;
import com.nextbreakpoint.nextfractal.mandelbrot.outcolouringFormula.extension.OutcolouringFormulaExtensionConfig;
import com.nextbreakpoint.nextfractal.mandelbrot.outcolouringFormula.extension.OutcolouringFormulaExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class OutcolouringFormulaExtensionRegistry extends SLConfigurableExtensionRegistry<OutcolouringFormulaExtensionRuntime<? extends OutcolouringFormulaExtensionConfig>, OutcolouringFormulaExtensionConfig> {
	/**
	 * the extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "com.nextbreakpoint.nextfractal.mandelbrot.extensions";
	/**
	 * the configuration element name.
	 */
	public static final String CONFIGURATION_ELEMENT_NAME = "OutcolouringFormula";
	/**
	 * the extension descriptor class.
	 */
	public static final Class<? extends OutcolouringFormulaExtensionDescriptor> EXTENSION_DESCRIPTOR_CLASS = OutcolouringFormulaExtensionDescriptor.class;

	/**
	 * Constructs a new registry.
	 */
	public OutcolouringFormulaExtensionRegistry() {
		super(OutcolouringFormulaExtensionRegistry.EXTENSION_DESCRIPTOR_CLASS, OutcolouringFormulaExtensionRegistry.EXTENSION_POINT_NAME, new SLConfigurableExtensionBuilder<OutcolouringFormulaExtensionRuntime<? extends OutcolouringFormulaExtensionConfig>, OutcolouringFormulaExtensionConfig>(OutcolouringFormulaExtensionRegistry.CONFIGURATION_ELEMENT_NAME));
	}
}
