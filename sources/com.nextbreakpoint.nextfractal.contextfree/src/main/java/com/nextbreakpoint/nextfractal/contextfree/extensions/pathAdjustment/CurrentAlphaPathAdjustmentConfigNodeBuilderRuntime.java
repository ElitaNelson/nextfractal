/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.extensions.pathAdjustment;

import com.nextbreakpoint.nextfractal.contextfree.extensions.ContextFreeExtensionResources;
import com.nextbreakpoint.nextfractal.core.elements.BooleanElementNode;
import com.nextbreakpoint.nextfractal.core.elements.FloatElementNode;
import com.nextbreakpoint.nextfractal.core.extensionPoints.nodeBuilder.NodeBuilderExtensionRuntime;
import com.nextbreakpoint.nextfractal.core.runtime.common.AbstractExtensionConfigNodeBuilder;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionConfig;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeBuilder;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject;

/**
 * @author Andrea Medeghini
 */
public class CurrentAlphaPathAdjustmentConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.extensionPoints.nodeBuilder.NodeBuilderExtensionRuntime#createNodeBuilder(com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((CurrentAlphaPathAdjustmentConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<CurrentAlphaPathAdjustmentConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final CurrentAlphaPathAdjustmentConfig config) {
			super(config);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.common.AbstractExtensionConfigNodeBuilder#createNodes(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject)
		 */
		@Override
		public void createNodes(final NodeObject parentNode) {
			parentNode.appendChildNode(new ValueElementNode(getConfig()));
			parentNode.appendChildNode(new TargetElementNode(getConfig()));
		}

		private class ValueElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public ValueElementNode(final CurrentAlphaPathAdjustmentConfig config) {
				super(config.getExtensionId() + ".value", config.getValueElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.ValueElement"));
			}
		}
		private class TargetElementNode extends BooleanElementNode {
			/**
			 * @param config
			 */
			public TargetElementNode(final CurrentAlphaPathAdjustmentConfig config) {
				super(config.getExtensionId() + ".target", config.getTargetElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.TargetElement"));
			}
		}
	}
}
