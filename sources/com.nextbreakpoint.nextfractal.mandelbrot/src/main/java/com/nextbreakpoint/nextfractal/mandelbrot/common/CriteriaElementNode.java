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
package com.nextbreakpoint.nextfractal.mandelbrot.common;

import com.nextbreakpoint.nextfractal.core.config.ValueChangeEvent;
import com.nextbreakpoint.nextfractal.core.config.ValueChangeListener;
import com.nextbreakpoint.nextfractal.core.config.ValueConfigElement;
import com.nextbreakpoint.nextfractal.core.tree.AttributeNode;
import com.nextbreakpoint.nextfractal.core.tree.Node;
import com.nextbreakpoint.nextfractal.core.tree.NodeAction;
import com.nextbreakpoint.nextfractal.core.tree.NodeEditor;
import com.nextbreakpoint.nextfractal.core.tree.NodeSession;
import com.nextbreakpoint.nextfractal.core.tree.NodeValue;
import com.nextbreakpoint.nextfractal.mandelbrot.MandelbrotResources;

/**
 * @author Andrea Medeghini
 */
public class CriteriaElementNode extends AttributeNode {
	private static final String NODE_LABEL = MandelbrotResources.getInstance().getString("node.label.CriteriaElement");
	public static final String NODE_CLASS = "node.class.OrbitTrapCriteriaElement";
	private final ConfigElementListener listener;
	private final CriteriaElement configElement;

	/**
	 * @param nodeId
	 */
	public CriteriaElementNode(final String nodeId, final CriteriaElement configElement) {
		super(nodeId);
		setNodeLabel(CriteriaElementNode.NODE_LABEL);
		setNodeClass(CriteriaElementNode.NODE_CLASS);
		this.configElement = configElement;
		listener = new ConfigElementListener();
		setNodeValue(new CriteriaElementNodeValue(configElement.getValue()));
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.tree.Node#dispose()
	 */
	@Override
	public void dispose() {
		if (configElement != null) {
			configElement.removeChangeListener(listener);
		}
		super.dispose();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.tree.Node#setSession(com.nextbreakpoint.nextfractal.core.tree.NodeSession)
	 */
	@Override
	public void setSession(final NodeSession session) {
		if (session != null) {
			configElement.addChangeListener(listener);
		}
		else {
			configElement.removeChangeListener(listener);
		}
		super.setSession(session);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.tree.Node#nodeAdded()
	 */
	@Override
	protected void nodeAdded() {
		setNodeValue(new CriteriaElementNodeValue(configElement.getValue()));
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.tree.Node#nodeRemoved()
	 */
	@Override
	protected void nodeRemoved() {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.tree.Node#isEditable()
	 */
	@Override
	public boolean isEditable() {
		return true;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.tree.DefaultNode#getValueAsString()
	 */
	@Override
	public String getValueAsString() {
		if (getNodeValue() != null) {
			return String.valueOf(getNodeValue().getValue());
		}
		else {
			return "";
		}
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new CriteriaNodeEditor(this);
	}

	protected class CriteriaNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public CriteriaNodeEditor(final AttributeNode node) {
			super(node);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.tree.NodeEditor#doSetValue(java.lang.NodeValue)
		 */
		@Override
		protected void doSetValue(final NodeValue<?> value) {
			configElement.removeChangeListener(listener);
			configElement.setValue(((CriteriaElementNodeValue) value).getValue());
			configElement.addChangeListener(listener);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.tree.NodeEditor#createChildNode(com.nextbreakpoint.nextfractal.core.tree.NodeValue)
		 */
		@Override
		protected Node createChildNode(final NodeValue<?> value) {
			return null;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return CriteriaElementNodeValue.class;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.tree.NodeEditor#createNodeValue(Object)
		 */
		@Override
		public NodeValue<?> createNodeValue(final Object value) {
			return new CriteriaElementNodeValue((Integer) value);
		}
	}

	protected class ConfigElementListener implements ValueChangeListener {
		public void valueChanged(final ValueChangeEvent e) {
			cancel();
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setNodeValue(new CriteriaElementNodeValue((Integer) e.getParams()[0]));
					getSession().appendAction(new NodeAction(getNodeClass(), NodeAction.ACTION_SET_VALUE, e.getTimestamp(), getNodePath(), e.getParams()[0], e.getParams()[1]));
					break;
				}
				default: {
					break;
				}
			}
		}
	}
}
