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
package com.nextbreakpoint.nextfractal.mandelbrot.extensions.orbitTrap;

import com.nextbreakpoint.nextfractal.core.elements.DoubleElementNode;
import com.nextbreakpoint.nextfractal.core.extensionPoints.nodeBuilder.NodeBuilderExtensionRuntime;
import com.nextbreakpoint.nextfractal.core.runtime.common.AbstractExtensionConfigNodeBuilder;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionConfig;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeBuilder;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject;
import com.nextbreakpoint.nextfractal.mandelbrot.elements.CriteriaElementNode;
import com.nextbreakpoint.nextfractal.mandelbrot.extensions.MandelbrotExtensionResources;

/**
 * @author Andrea Medeghini
 */
public class RectangleTrapConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.extensionPoints.nodeBuilder.NodeBuilderExtensionRuntime#createNodeBuilder(com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((RectangleTrapConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<RectangleTrapConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final RectangleTrapConfig config) {
			super(config);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.common.AbstractExtensionConfigNodeBuilder#createNodes(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject)
		 */
		@Override
		public void createNodes(final NodeObject parentNode) {
			parentNode.appendChildNode(new WidthNode(getConfig()));
			parentNode.appendChildNode(new HeightNode(getConfig()));
			parentNode.appendChildNode(new RotationNode(getConfig()));
			parentNode.appendChildNode(new CriteriaNode(getConfig()));
		}

		private class WidthNode extends DoubleElementNode {
			private final String NODE_LABEL = MandelbrotExtensionResources.getInstance().getString("node.label.WidthElement");

			/**
			 * @param config
			 * @param index
			 */
			public WidthNode(final RectangleTrapConfig config) {
				super(config.getExtensionId() + ".width", config.getWidthElement());
				setNodeLabel(NODE_LABEL);
			}
		}

		private class HeightNode extends DoubleElementNode {
			private final String NODE_LABEL = MandelbrotExtensionResources.getInstance().getString("node.label.HeightElement");

			/**
			 * @param config
			 * @param index
			 */
			public HeightNode(final RectangleTrapConfig config) {
				super(config.getExtensionId() + ".height", config.getHeightElement());
				setNodeLabel(NODE_LABEL);
			}
		}

		private class RotationNode extends DoubleElementNode {
			private final String NODE_LABEL = MandelbrotExtensionResources.getInstance().getString("node.label.RotationElement");

			/**
			 * @param config
			 * @param index
			 */
			public RotationNode(final RectangleTrapConfig config) {
				super(config.getExtensionId() + ".rotation", config.getRotationElement());
				setNodeLabel(NODE_LABEL);
			}
		}

		private class CriteriaNode extends CriteriaElementNode {
			private final String NODE_LABEL = MandelbrotExtensionResources.getInstance().getString("node.label.CriteriaElement");

			/**
			 * @param config
			 * @param index
			 */
			public CriteriaNode(final RectangleTrapConfig config) {
				super(config.getExtensionId() + ".criteria", config.getCriteriaElement());
				setNodeLabel(NODE_LABEL);
			}
		}
	}
}
