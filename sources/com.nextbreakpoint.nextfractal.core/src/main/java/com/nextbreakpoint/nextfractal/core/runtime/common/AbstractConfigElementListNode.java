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
package com.nextbreakpoint.nextfractal.core.runtime.common;

import com.nextbreakpoint.nextfractal.core.CoreResources;
import com.nextbreakpoint.nextfractal.core.runtime.ConfigElement;
import com.nextbreakpoint.nextfractal.core.runtime.ElementChangeEvent;
import com.nextbreakpoint.nextfractal.core.runtime.ElementChangeListener;
import com.nextbreakpoint.nextfractal.core.runtime.ListConfigElement;
import com.nextbreakpoint.nextfractal.core.runtime.tree.DefaultNode;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeAction;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeSession;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeValue;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractConfigElementListNode<T extends ConfigElement> extends DefaultNode {
	protected ConfigElementListener listListener;
	protected ListConfigElement<T> listElement;

	/**
	 * Constructs a new list node.
	 * 
	 * @param listElement the frame element.
	 */
	public AbstractConfigElementListNode(final String nodeId, final ListConfigElement<T> listElement) {
		super(nodeId);
		if (listElement == null) {
			throw new IllegalArgumentException("listElement is null");
		}
		this.listElement = listElement;
		listListener = new ConfigElementListener();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject#isEditable()
	 */
	@Override
	public boolean isEditable() {
		return false;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject#isMutable()
	 */
	@Override
	public final boolean isMutable() {
		return true;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.DefaultNode#getValueAsString()
	 */
	@Override
	public String getValueAsString() {
		return isEditable() ? super.getValueAsString() : getChildNodeCount() + " " + CoreResources.getInstance().getString("label.elements");
	}

	/**
	 * @param value
	 * @return
	 */
	protected abstract AbstractConfigElementNode<? extends T> createChildNode(T value);

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject#dispose()
	 */
	@Override
	public void dispose() {
		if (listElement != null) {
			listElement.removeChangeListener(listListener);
		}
		listElement = null;
		listListener = null;
		super.dispose();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject#setSession(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeSession)
	 */
	@Override
	public void setSession(final NodeSession session) {
		if (session != null) {
			listElement.addChangeListener(listListener);
		}
		else {
			listElement.removeChangeListener(listListener);
		}
		super.setSession(session);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject#nodeAdded()
	 */
	@Override
	protected void nodeAdded() {
		createChildNodes();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject#nodeRemoved()
	 */
	@Override
	protected void nodeRemoved() {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject#addLabel(java.lang.StringBuilder)
	 */
	@Override
	protected void addLabel(final StringBuilder builder) {
		super.addLabel(builder);
		if (listElement.getElementCount() == 1) {
			builder.append(" (" + listElement.getElementCount() + " " + CoreResources.getInstance().getString("label.element") + ")");
		}
		else {
			builder.append(" (" + listElement.getElementCount() + " " + CoreResources.getInstance().getString("label.elements") + ")");
		}
	}

	protected void createChildNodes() {
		createConfigElementNodes(listElement);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public boolean equals(final Object o) {
		if (o instanceof AbstractConfigElementListNode) {
			return (listElement == ((AbstractConfigElementListNode) o).listElement);
		}
		return false;
	}

	/**
	 * Creates the nodes.
	 * 
	 * @param listElement the frame element.
	 */
	protected void createConfigElementNodes(final ListConfigElement<T> listElement) {
		for (int i = 0; i < listElement.getElementCount(); i++) {
			final T configElement = listElement.getElement(i);
			final NodeObject configElementNode = createChildNode(configElement);
			appendChildNode(configElementNode);
		}
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.ListNode#getChildValueType()
	 */
	public abstract Class<?> getChildValueType();

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new ListNodeEditor();
	}

	/**
	 * @param value
	 * @return
	 */
	public abstract NodeValue<?> createNodeValue(final Object value);

	protected class ListNodeEditor extends NodeEditor {
		/**
		 * 
		 */
		public ListNodeEditor() {
			super(AbstractConfigElementListNode.this);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor#createChildNode(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeValue)
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		protected NodeObject createChildNode(final NodeValue value) {
			final T configElement = ((NodeValue<T>) value).getValue();
			configElement.setContext(getContext());
			return AbstractConfigElementListNode.this.createChildNode(configElement);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor#doAppendNode(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject)
		 */
		@SuppressWarnings("unchecked")
		@Override
		protected void doAppendNode(final NodeObject node) {
			listElement.removeChangeListener(listListener);
			listElement.appendElement(((AbstractConfigElementNode<T>) node).getConfigElement());
			listElement.addChangeListener(listListener);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor#doInsertNodeAfter(int, com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject)
		 */
		@SuppressWarnings("unchecked")
		@Override
		protected void doInsertNodeAfter(final int index, final NodeObject node) {
			listElement.removeChangeListener(listListener);
			listElement.insertElementAfter(index, ((AbstractConfigElementNode<T>) node).getConfigElement());
			listElement.addChangeListener(listListener);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor#doInsertNodeBefore(int, com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject)
		 */
		@SuppressWarnings("unchecked")
		@Override
		protected void doInsertNodeBefore(final int index, final NodeObject node) {
			listElement.removeChangeListener(listListener);
			listElement.insertElementBefore(index, ((AbstractConfigElementNode<T>) node).getConfigElement());
			listElement.addChangeListener(listListener);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor#doRemoveNode(int)
		 */
		@Override
		protected void doRemoveNode(final int nodeIndex) {
			listElement.removeChangeListener(listListener);
			listElement.removeElement(nodeIndex);
			listElement.addChangeListener(listListener);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor#doRemoveNode(int)
		 */
		@Override
		protected void doMoveUpNode(final int nodeIndex) {
			listElement.removeChangeListener(listListener);
			listElement.moveElementUp(nodeIndex);
			listElement.addChangeListener(listListener);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor#doRemoveNode(int)
		 */
		@Override
		protected void doMoveDownNode(final int nodeIndex) {
			listElement.removeChangeListener(listListener);
			listElement.moveElementDown(nodeIndex);
			listElement.addChangeListener(listListener);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return ConfigElementListNodeValue.class;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor#createNodeValue(Object)
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public NodeValue createNodeValue(final Object value) {
			return AbstractConfigElementListNode.this.createNodeValue(value);
		}
	}

	protected class ConfigElementListener implements ElementChangeListener {
		@Override
		@SuppressWarnings("unchecked")
		public void valueChanged(final ElementChangeEvent e) {
			cancel();
			switch (e.getEventType()) {
				case ListConfigElement.ELEMENT_ADDED: {
					final AbstractConfigElementNode<? extends T> configElementNode = createChildNode((T) e.getParams()[0]);
					appendChildNode(configElementNode);
					getSession().appendAction(new NodeAction(getNodeClass(), NodeAction.ACTION_APPEND_NODE, e.getTimestamp(), getNodePath(), e.getParams()[1], configElementNode.getConfigElement().clone()));
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_AFTER: {
					final AbstractConfigElementNode<? extends T> configElementNode = createChildNode((T) e.getParams()[0]);
					insertNodeAfter((Integer) e.getParams()[1], configElementNode);
					getSession().appendAction(new NodeAction(getNodeClass(), NodeAction.ACTION_INSERT_NODE_AFTER, e.getTimestamp(), getNodePath(), e.getParams()[1], configElementNode.getConfigElement().clone()));
					break;
				}
				case ListConfigElement.ELEMENT_INSERTED_BEFORE: {
					final AbstractConfigElementNode<? extends T> configElementNode = createChildNode((T) e.getParams()[0]);
					insertNodeBefore((Integer) e.getParams()[1], configElementNode);
					getSession().appendAction(new NodeAction(getNodeClass(), NodeAction.ACTION_INSERT_NODE_BEFORE, e.getTimestamp(), getNodePath(), e.getParams()[1], configElementNode.getConfigElement().clone()));
					break;
				}
				case ListConfigElement.ELEMENT_REMOVED: {
					final AbstractConfigElementNode<? extends T> configElementNode = createChildNode((T) e.getParams()[0]);
					removeChildNode((Integer) e.getParams()[1]);
					getSession().appendAction(new NodeAction(getNodeClass(), NodeAction.ACTION_REMOVE_NODE, e.getTimestamp(), getNodePath(), e.getParams()[1], configElementNode.getConfigElement().clone()));
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_UP: {
					moveUpChildNode((Integer) e.getParams()[1]);
					getSession().appendAction(new NodeAction(getNodeClass(), NodeAction.ACTION_MOVE_UP_NODE, e.getTimestamp(), getNodePath(), e.getParams()[1]));
					break;
				}
				case ListConfigElement.ELEMENT_MOVED_DOWN: {
					moveDownChildNode((Integer) e.getParams()[1]);
					getSession().appendAction(new NodeAction(getNodeClass(), NodeAction.ACTION_MOVE_DOWN_NODE, e.getTimestamp(), getNodePath(), e.getParams()[1]));
					break;
				}
				case ListConfigElement.ELEMENT_CHANGED: {
					final AbstractConfigElementNode<? extends T> configElementNode = createChildNode((T) e.getParams()[0]);
					setChildNode((Integer) e.getParams()[1], configElementNode);
					getSession().appendAction(new NodeAction(getNodeClass(), NodeAction.ACTION_CHANGE_NODE, e.getTimestamp(), getNodePath(), e.getParams()[1], configElementNode.getConfigElement().clone()));
					break;
				}
				default: {
					break;
				}
			}
		}
	}
}