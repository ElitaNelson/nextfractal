/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.shapeAdjustment.extension;

import com.nextbreakpoint.nextfractal.contextfree.renderer.support.CFModification;
import com.nextbreakpoint.nextfractal.core.extension.ConfigurableExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class ShapeAdjustmentExtensionRuntime<T extends ShapeAdjustmentExtensionConfig> extends ConfigurableExtensionRuntime<T> {
	/**
	 * @param state
	 */
	public abstract void apply(CFModification mod);

	public boolean isSizeChange() {
		return false;
	}

	public float getSize() {
		return 1f;
	}
}
