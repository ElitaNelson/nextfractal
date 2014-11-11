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
package com.nextbreakpoint.nextfractal.mandelbrot.extensions.outcolouringFormula;

import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject;
import com.nextbreakpoint.nextfractal.mandelbrot.extensions.MandelbrotExtensionResources;
import com.nextbreakpoint.nextfractal.twister.elements.PercentageElementNode;

/**
 * @author Andrea Medeghini
 */
public class BinaryAndPotentialConfigNodeBuilderRuntime extends AbstractOutcolouringPaletteConfigNodeBuilderRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensions.outcolouringFormula.AbstractOutcolouringPaletteConfigNodeBuilderRuntime#createNodes(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject, com.nextbreakpoint.nextfractal.mandelbrot.extensions.outcolouringFormula.AbstractOutcolouringPaletteConfig)
	 */
	@Override
	public void createNodes(final NodeObject parentNode, final AbstractOutcolouringPaletteConfig config) {
		parentNode.appendChildNode(new OffsetElementNode((BinaryAndPotentialConfig) config));
	}

	private static class OffsetElementNode extends PercentageElementNode {
		public static final String NODE_LABEL = MandelbrotExtensionResources.getInstance().getString("node.label.OffsetElement");

		/**
		 * @param config
		 */
		public OffsetElementNode(final BinaryAndPotentialConfig config) {
			super(config.getExtensionId() + ".offset", config.getOffsetElement());
			setNodeLabel(OffsetElementNode.NODE_LABEL);
		}
	}
}
