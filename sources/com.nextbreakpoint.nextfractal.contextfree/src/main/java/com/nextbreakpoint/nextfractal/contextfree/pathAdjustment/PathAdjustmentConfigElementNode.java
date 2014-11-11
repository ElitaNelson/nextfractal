/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.pathAdjustment;

import com.nextbreakpoint.nextfractal.contextfree.ContextFreeResources;
import com.nextbreakpoint.nextfractal.contextfree.extensionPoints.pathAdjustment.PathAdjustmentExtensionConfig;
import com.nextbreakpoint.nextfractal.core.elements.ConfigurableExtensionReferenceElementNode;
import com.nextbreakpoint.nextfractal.core.runtime.common.AbstractConfigElementNode;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ConfigurableExtensionReference;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeValue;

/**
 * @author Andrea Medeghini
 */
public class PathAdjustmentConfigElementNode extends AbstractConfigElementNode<PathAdjustmentConfigElement> {
	private static final String NODE_ID = PathAdjustmentConfigElement.CLASS_ID;
	private static final String NODE_CLASS = "node.class.PathAdjustmentElement";
	private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.PathAdjustmentElement");
	private final PathAdjustmentConfigElement pathAdjustment;

	/**
	 * Constructs a new effect node.
	 * 
	 * @param pathAdjustment the pathAdjustment element.
	 */
	public PathAdjustmentConfigElementNode(final PathAdjustmentConfigElement pathAdjustment) {
		super(PathAdjustmentConfigElementNode.NODE_ID);
		if (pathAdjustment == null) {
			throw new IllegalArgumentException("pathAdjustment is null");
		}
		this.pathAdjustment = pathAdjustment;
		setNodeLabel(PathAdjustmentConfigElementNode.NODE_LABEL);
		setNodeClass(PathAdjustmentConfigElementNode.NODE_CLASS);
		setNodeValue(new PathAdjustmentConfigElementNodeValue(pathAdjustment));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof PathAdjustmentConfigElementNode) {
			return (pathAdjustment == ((PathAdjustmentConfigElementNode) o).pathAdjustment);
		}
		return false;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.common.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public PathAdjustmentConfigElement getConfigElement() {
		return pathAdjustment;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject#addDescription(java.lang.StringBuilder)
	 */
	@Override
	protected void addDescription(final StringBuilder builder) {
		if (getChildNodeCount() > 0) {
			builder.append(getChildNode(0).getLabel());
		}
		else {
			super.addDescription(builder);
		}
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject#updateNode()
	 */
	@Override
	protected void updateChildNodes() {
		createChildNodes((PathAdjustmentConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new PathAdjustmentNodeEditor(this);
	}

	/**
	 * @param value
	 */
	protected void createChildNodes(final PathAdjustmentConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new ExtensionElementNode(PathAdjustmentConfigElementNode.NODE_ID + ".extension", value.getValue()));
	}

	private static class PathAdjustmentNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public PathAdjustmentNodeEditor(final NodeObject node) {
			super(node);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor#createChildNode(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeValue)
		 */
		@Override
		protected NodeObject createChildNode(final NodeValue<?> value) {
			return null;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor#createNodeValue(Object)
		 */
		@Override
		public NodeValue<?> createNodeValue(final Object value) {
			return new PathAdjustmentConfigElementNodeValue((PathAdjustmentConfigElement) value);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return PathAdjustmentConfigElementNodeValue.class;
		}
	}

	private static class ExtensionElementNode extends ConfigurableExtensionReferenceElementNode<PathAdjustmentExtensionConfig> {
		public static final String NODE_CLASS = "node.class.PathAdjustmentReference";

		/**
		 * @param nodeId
		 * @param pathAdjustment
		 */
		public ExtensionElementNode(final String nodeId, final PathAdjustmentConfigElement pathAdjustment) {
			super(nodeId, pathAdjustment.getExtensionElement());
			setNodeClass(ExtensionElementNode.NODE_CLASS);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.elements.ConfigurableExtensionReferenceElementNode#createNodeValue(com.nextbreakpoint.nextfractal.core.runtime.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<PathAdjustmentExtensionConfig> value) {
			return new PathAdjustmentExtensionReferenceNodeValue(value);
		}
	}
}
