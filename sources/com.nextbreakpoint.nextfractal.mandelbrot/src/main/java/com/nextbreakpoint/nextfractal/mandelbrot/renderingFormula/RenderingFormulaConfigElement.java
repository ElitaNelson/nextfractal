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
package com.nextbreakpoint.nextfractal.mandelbrot.renderingFormula;

import com.nextbreakpoint.nextfractal.core.common.ConfigurableExtensionReferenceElement;
import com.nextbreakpoint.nextfractal.core.config.AbstractConfigElement;
import com.nextbreakpoint.nextfractal.core.config.ConfigContext;
import com.nextbreakpoint.nextfractal.core.config.ConfigElement;
import com.nextbreakpoint.nextfractal.core.extension.ConfigurableExtensionReference;
import com.nextbreakpoint.nextfractal.mandelbrot.renderingFormula.extension.RenderingFormulaExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class RenderingFormulaConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "RenderingFormula";
	private final ConfigurableExtensionReferenceElement<RenderingFormulaExtensionConfig> extensionElement = new ConfigurableExtensionReferenceElement<RenderingFormulaExtensionConfig>();

	/**
	 * Constructs a new element.
	 */
	public RenderingFormulaConfigElement() {
		super(RenderingFormulaConfigElement.CLASS_ID);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.common.ConfigurableExtensionReferenceElement#setContext(com.nextbreakpoint.nextfractal.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		extensionElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public RenderingFormulaConfigElement clone() {
		final RenderingFormulaConfigElement element = new RenderingFormulaConfigElement();
		if (getReference() != null) {
			element.setReference(getReference().clone());
		}
		return element;
	}
	
	/**
	 * @see com.nextbreakpoint.nextfractal.core.config.ConfigElement#copyFrom(com.nextbreakpoint.nextfractal.core.config.ConfigElement)
	 */
	public void copyFrom(ConfigElement source) {
		RenderingFormulaConfigElement element = (RenderingFormulaConfigElement) source;
		if (element.getReference() != null) {
			setReference(element.getReference().clone());
		}
	}

	/**
	 * @return
	 */
	public ConfigurableExtensionReference<RenderingFormulaExtensionConfig> getReference() {
		return extensionElement.getReference();
	}

	/**
	 * @param reference
	 */
	public void setReference(final ConfigurableExtensionReference<RenderingFormulaExtensionConfig> reference) {
		extensionElement.setReference(reference);
	}

	/**
	 * @return
	 */
	public ConfigurableExtensionReferenceElement<RenderingFormulaExtensionConfig> getExtensionElement() {
		return extensionElement;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		final RenderingFormulaConfigElement other = (RenderingFormulaConfigElement) obj;
		if (extensionElement == null) {
			if (other.extensionElement != null) {
				return false;
			}
		}
		else if (!extensionElement.equals(other.extensionElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.config.AbstractConfigElement#dispose()
	 */
	@Override
	public void dispose() {
		extensionElement.dispose();
		super.dispose();
	}
}
