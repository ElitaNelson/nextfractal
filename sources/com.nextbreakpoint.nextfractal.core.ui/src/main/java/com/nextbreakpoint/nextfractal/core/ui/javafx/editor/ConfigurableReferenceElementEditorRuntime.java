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

import com.nextbreakpoint.nextfractal.core.extension.ConfigurableExtension;
import com.nextbreakpoint.nextfractal.core.extension.ConfigurableExtensionReference;
import com.nextbreakpoint.nextfractal.core.extension.NullConfigurableExtension;
import com.nextbreakpoint.nextfractal.core.tree.NodeEditor;
import com.nextbreakpoint.nextfractal.core.tree.NodeValue;
import com.nextbreakpoint.nextfractal.core.ui.javafx.CoreUIResources;
import com.nextbreakpoint.nextfractal.core.ui.javafx.NodeEditorComponent;
import com.nextbreakpoint.nextfractal.core.ui.javafx.extensionPoints.editor.EditorExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class ConfigurableReferenceElementEditorRuntime extends EditorExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.twister.javafx.editor.extension.EditorExtensionRuntime#createEditor(com.nextbreakpoint.nextfractal.core.tree.NodeEditor)
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
	protected abstract NodeValue<?> createNodeValue(ConfigurableExtensionReference<?> reference);

	/**
	 * @return
	 */
	protected abstract List<ConfigurableExtension<?, ?>> getExtensionList();

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
	protected abstract String getMoveUpLabel();

	/**
	 * @return
	 */
	protected abstract String getMoveDownLabel();

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

	/**
	 * @return
	 */
	protected abstract String getMoveUpTooltip();

	/**
	 * @return
	 */
	protected abstract String getMoveDownTooltip();

	private class EditorComponent extends VBox implements NodeEditorComponent {
		private final ComboBox<ConfigurableExtension<?, ?>> extensionComboBox;

		/**
		 * @param nodeEditor
		 */
		public EditorComponent(final NodeEditor nodeEditor) {
			Label label = new Label(CoreUIResources.getInstance().getString("label.extension"));
			extensionComboBox = new ComboBox<>();
			List<ConfigurableExtension<?, ?>> extensions = getExtensionList();
			extensionComboBox.getItems().add(NullConfigurableExtension.getInstance());
			extensionComboBox.getItems().addAll(extensions);
			setAlignment(Pos.CENTER_LEFT);
			setSpacing(10);
			if (nodeEditor.isParentMutable()) {
				getChildren().add(label);
				getChildren().add(extensionComboBox);
			}
//			if (nodeEditor.isParentMutable()) {
//				final JButton insertBeforeButton = GUIFactory.createButton(new InsertBeforeAction(combo, nodeEditor), getInsertBeforeTooltip());
//				final JButton insertAfterButton = GUIFactory.createButton(new InsertAfterAction(combo, nodeEditor), getInsertAfterTooltip());
//				final JButton removeButton = GUIFactory.createButton(new RemoveAction(nodeEditor), getRemoveTooltip());
//				final JButton moveUpButton = GUIFactory.createButton(new MoveUpAction(nodeEditor), getMoveUpTooltip());
//				final JButton moveDownButton = GUIFactory.createButton(new MoveDownAction(nodeEditor), getMoveDownTooltip());
//				this.add(GUIFactory.createLabel(CoreUIResources.getInstance().getString("label.extension"), SwingConstants.CENTER));
//				this.add(Box.createVerticalStrut(8));
//				this.add(combo);
//				this.add(Box.createVerticalStrut(8));
//				this.add(insertBeforeButton);
//				this.add(Box.createVerticalStrut(8));
//				this.add(insertAfterButton);
//				this.add(Box.createVerticalStrut(8));
//				this.add(removeButton);
//				this.add(Box.createVerticalStrut(8));
//				this.add(moveUpButton);
//				this.add(Box.createVerticalStrut(8));
//				this.add(moveDownButton);
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
//			try {
//				final ConfigurableExtension<?, ?> extension = (ConfigurableExtension<?, ?>) combo.getSelectedItem();
//				if (extension instanceof NullConfigurableExtension) {
//					nodeEditor.getParentNodeEditor().insertChildNodeAfter(nodeEditor.getIndex(), createNodeValue(null));
//				}
//				else {
//					final ConfigurableExtensionReference<?> reference = extension.createConfigurableExtensionReference();
//					nodeEditor.getParentNodeEditor().insertChildNodeAfter(nodeEditor.getIndex(), createNodeValue(reference));
//				}
//			}
//			catch (final ExtensionException x) {
//				x.printStackTrace();
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
//			try {
//				final ConfigurableExtension<?, ?> extension = (ConfigurableExtension<?, ?>) combo.getSelectedItem();
//				if (extension instanceof NullConfigurableExtension) {
//					nodeEditor.getParentNodeEditor().insertChildNodeBefore(nodeEditor.getIndex(), createNodeValue(null));
//				}
//				else {
//					final ConfigurableExtensionReference<?> reference = extension.createConfigurableExtensionReference();
//					nodeEditor.getParentNodeEditor().insertChildNodeBefore(nodeEditor.getIndex(), createNodeValue(reference));
//				}
//			}
//			catch (final ExtensionException x) {
//				x.printStackTrace();
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
//
//	private class MoveUpAction extends AbstractAction {
//		private static final long serialVersionUID = 1L;
//		private final NodeEditor nodeEditor;
//
//		/**
//		 * @param nodeEditor
//		 */
//		public MoveUpAction(final NodeEditor nodeEditor) {
//			super(getMoveUpLabel());
//			this.nodeEditor = nodeEditor;
//		}
//
//		/**
//		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//		 */
//		@Override
//		public void actionPerformed(final ActionEvent e) {
//			nodeEditor.getParentNodeEditor().moveUpChildNode(nodeEditor.getIndex());
//		}
//	}
//
//	private class MoveDownAction extends AbstractAction {
//		private static final long serialVersionUID = 1L;
//		private final NodeEditor nodeEditor;
//
//		/**
//		 * @param nodeEditor
//		 */
//		public MoveDownAction(final NodeEditor nodeEditor) {
//			super(getMoveDownLabel());
//			this.nodeEditor = nodeEditor;
//		}
//
//		/**
//		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//		 */
//		@Override
//		public void actionPerformed(final ActionEvent e) {
//			nodeEditor.getParentNodeEditor().moveDownChildNode(nodeEditor.getIndex());
//		}
//	}
}