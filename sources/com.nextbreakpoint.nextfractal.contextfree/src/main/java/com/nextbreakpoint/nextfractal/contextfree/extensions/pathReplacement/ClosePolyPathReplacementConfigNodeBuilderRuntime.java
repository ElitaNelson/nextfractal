/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.extensions.pathReplacement;

import com.nextbreakpoint.nextfractal.contextfree.extensions.ContextFreeExtensionResources;
import com.nextbreakpoint.nextfractal.core.common.BooleanElementNode;
import com.nextbreakpoint.nextfractal.core.extension.ExtensionConfig;
import com.nextbreakpoint.nextfractal.core.extensionPoints.nodeBuilder.NodeBuilderExtensionRuntime;
import com.nextbreakpoint.nextfractal.core.tree.NodeObject;
import com.nextbreakpoint.nextfractal.core.tree.NodeBuilder;
import com.nextbreakpoint.nextfractal.core.util.AbstractExtensionConfigNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class ClosePolyPathReplacementConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.extensionPoints.nodeBuilder.NodeBuilderExtensionRuntime#createNodeBuilder(com.nextbreakpoint.nextfractal.core.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((ClosePolyPathReplacementConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<ClosePolyPathReplacementConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final ClosePolyPathReplacementConfig config) {
			super(config);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.util.AbstractExtensionConfigNodeBuilder#createNodes(com.nextbreakpoint.nextfractal.core.tree.NodeObject)
		 */
		@Override
		public void createNodes(final NodeObject parentNode) {
			parentNode.appendChildNode(new AlignElementNode(getConfig()));
		}

		private class AlignElementNode extends BooleanElementNode {
			/**
			 * @param config
			 */
			public AlignElementNode(final ClosePolyPathReplacementConfig config) {
				super(config.getExtensionId() + ".align", config.getAlignElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.AlignElement"));
			}
		}
	}
}
