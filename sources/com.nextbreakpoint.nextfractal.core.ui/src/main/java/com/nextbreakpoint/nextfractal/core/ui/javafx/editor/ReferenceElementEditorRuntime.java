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
package com.nextbreakpoint.nextfractal.core.ui.javafx.editor;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import com.nextbreakpoint.nextfractal.core.runtime.extension.Extension;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionReference;
import com.nextbreakpoint.nextfractal.core.runtime.extension.NullExtension;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeValue;
import com.nextbreakpoint.nextfractal.core.ui.javafx.CoreUIResources;
import com.nextbreakpoint.nextfractal.core.ui.javafx.NodeEditorComponent;
import com.nextbreakpoint.nextfractal.core.ui.javafx.extensionPoints.editor.EditorExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class ReferenceElementEditorRuntime extends EditorExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.twister.javafx.editor.extension.EditorExtensionRuntime#createEditor(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEditor)
	 */
	@Override
	public NodeEditorComponent createEditor(final NodeEditor nodeEditor) {
		return new EditorComponent(nodeEditor);
	}

	/**
	 * @return
	 */
	protected abstract NodeValue<?> createChildValue();

	/**
	 * @param reference
	 * @return the node value.
	 */
	protected abstract NodeValue<?> createNodeValue(ExtensionReference reference);

	/**
	 * @return
	 */
	protected abstract List<Extension<?>> getExtensionList();

	/**
	 * @return
	 */
	protected abstract String getInsertBeforeLabel();

	/**
	 * @return
	 */
	protected abstract String getInsertAfterLabel();

	/**
	 * @return
	 */
	protected abstract String getRemoveLabel();

	/**
	 * @return
	 */
	protected abstract String getInsertBeforeTooltip();

	/**
	 * @return
	 */
	protected abstract String getInsertAfterTooltip();

	/**
	 * @return
	 */
	protected abstract String getRemoveTooltip();

	private class EditorComponent extends VBox implements NodeEditorComponent {
		private final ComboBox<Extension<?>> extensionComboBox;

		/**
		 * @param nodeEditor
		 */
		public EditorComponent(final NodeEditor nodeEditor) {
			Label label = new Label(CoreUIResources.getInstance().getString("label.extension"));
			extensionComboBox = new ComboBox<>();
			List<Extension<?>> extensions = getExtensionList();
			extensionComboBox.getItems().add(NullExtension.getInstance());
			extensionComboBox.getItems().addAll(extensions);
			setAlignment(Pos.CENTER_LEFT);
			setSpacing(10);
			if (nodeEditor.isParentMutable()) {
				getChildren().add(label);
				getChildren().add(extensionComboBox);
			}
//			combo.setRenderer(new ExtensionListCellRenderer());
//			if (nodeEditor.isParentMutable()) {
//				final JButton insertBeforeButton = GUIFactory.createButton(new InsertBeforeAction(combo, nodeEditor), getInsertBeforeTooltip());
//				final JButton insertAfterButton = GUIFactory.createButton(new InsertAfterAction(combo, nodeEditor), getInsertAfterTooltip());
//				final JButton removeButton = GUIFactory.createButton(new RemoveAction(nodeEditor), getRemoveTooltip());
//				this.add(GUIFactory.createLabel(CoreUIResources.getInstance().getString("label.extension"), SwingConstants.CENTER));
//				this.add(Box.createVerticalStrut(8));
//				this.add(combo);
//				this.add(Box.createVerticalStrut(8));
//				this.add(insertBeforeButton);
//				this.add(Box.createVerticalStrut(8));
//				this.add(insertAfterButton);
//				this.add(Box.createVerticalStrut(8));
//				this.add(removeButton);
//			}
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.twister.javafx.NodeEditorComponent#getComponent()
		 */
		@Override
		public Node getComponent() {
			return this;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.twister.javafx.NodeEditorComponent#reloadValue()
		 */
		@Override
		public void reloadValue() {
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.twister.javafx.NodeEditorComponent#dispose()
		 */
		@Override
		public void dispose() {
		}
	}

//	private class InsertAfterAction extends AbstractAction {
//		private static final long serialVersionUID = 1L;
//		private final NodeEditor nodeEditor;
//		private final JComboBox combo;
//
//		/**
//		 * @param combo
//		 * @param nodeEditor
//		 */
//		public InsertAfterAction(final JComboBox combo, final NodeEditor nodeEditor) {
//			super(getInsertAfterLabel());
//			this.nodeEditor = nodeEditor;
//			this.combo = combo;
//		}
//
//		/**
//		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//		 */
//		@Override
//		public void actionPerformed(final ActionEvent e) {
//			final Extension<?> extension = (Extension<?>) combo.getSelectedItem();
//			if (extension instanceof NullExtension) {
//				nodeEditor.getParentNodeEditor().insertChildNodeAfter(nodeEditor.getIndex(), createNodeValue(null));
//			}
//			else {
//				final ExtensionReference reference = extension.getExtensionReference();
//				nodeEditor.getParentNodeEditor().insertChildNodeAfter(nodeEditor.getIndex(), createNodeValue(reference));
//			}
//		}
//	}
//
//	private class InsertBeforeAction extends AbstractAction {
//		private static final long serialVersionUID = 1L;
//		private final NodeEditor nodeEditor;
//		private final JComboBox combo;
//
//		/**
//		 * @param combo
//		 * @param nodeEditor
//		 */
//		public InsertBeforeAction(final JComboBox combo, final NodeEditor nodeEditor) {
//			super(getInsertBeforeLabel());
//			this.nodeEditor = nodeEditor;
//			this.combo = combo;
//		}
//
//		/**
//		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//		 */
//		@Override
//		public void actionPerformed(final ActionEvent e) {
//			final Extension<?> extension = (Extension<?>) combo.getSelectedItem();
//			if (extension instanceof NullExtension) {
//				nodeEditor.getParentNodeEditor().insertChildNodeBefore(nodeEditor.getIndex(), createNodeValue(null));
//			}
//			else {
//				final ExtensionReference reference = extension.getExtensionReference();
//				nodeEditor.getParentNodeEditor().insertChildNodeBefore(nodeEditor.getIndex(), createNodeValue(reference));
//			}
//		}
//	}
//
//	private class RemoveAction extends AbstractAction {
//		private static final long serialVersionUID = 1L;
//		private final NodeEditor nodeEditor;
//
//		/**
//		 * @param nodeEditor
//		 */
//		public RemoveAction(final NodeEditor nodeEditor) {
//			super(getRemoveLabel());
//			this.nodeEditor = nodeEditor;
//		}
//
//		/**
//		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//		 */
//		@Override
//		public void actionPerformed(final ActionEvent e) {
//			nodeEditor.getParentNodeEditor().removeChildNode(nodeEditor.getIndex());
//		}
//	}
}
