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
public abstract class ConfigurableReferenceElementListEditorRuntime extends EditorExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.twister.javafx.editor.extension.EditorExtensionRuntime#createEditor(com.nextbreakpoint.nextfractal.core.tree.NodeEditor)
	 */
	@Override
	public NodeEditorComponent createEditor(final NodeEditor nodeEditor) {
		return new EditorComponent(nodeEditor);
	}

	/**
	 * @param reference
	 * @return the node value.
	 */
	protected abstract NodeValue<?> createNodeValue(ConfigurableExtensionReference<?> reference);

	/**
	 * @return
	 */
	protected abstract String getAppendLabel();

	/**
	 * @return
	 */
	protected abstract String getRemoveAllLabel();

	/**
	 * @return
	 */
	protected abstract String getAppendTooltip();

	/**
	 * @return
	 */
	protected abstract String getRemoveAllTooltip();

	/**
	 * @return
	 */
	protected abstract List<ConfigurableExtension<?, ?>> getExtensionList();

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
//			final JButton appendButton = GUIFactory.createButton(new AppendAction(combo, nodeEditor), getAppendTooltip());
//			final JButton removeButton = GUIFactory.createButton(new RemoveAllAction(nodeEditor), getRemoveAllTooltip());
//			this.add(Box.createVerticalStrut(8));
//			this.add(appendButton);
//			this.add(Box.createVerticalStrut(8));
//			this.add(removeButton);
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

//	private class AppendAction extends AbstractAction {
//		private static final long serialVersionUID = 1L;
//		private final JComboBox combo;
//		private final NodeEditor nodeEditor;
//
//		/**
//		 * @param combo
//		 * @param nodeEditor
//		 */
//		public AppendAction(final JComboBox combo, final NodeEditor nodeEditor) {
//			super(getAppendLabel());
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
//					nodeEditor.appendChildNode(createNodeValue(null));
//				}
//				else {
//					final ConfigurableExtensionReference<?> reference = extension.createConfigurableExtensionReference();
//					nodeEditor.appendChildNode(createNodeValue(reference));
//				}
//			}
//			catch (final ExtensionException x) {
//				x.printStackTrace();
//			}
//		}
//	}
//
//	private class RemoveAllAction extends AbstractAction {
//		private static final long serialVersionUID = 1L;
//		private final NodeEditor nodeEditor;
//
//		/**
//		 * @param nodeEditor
//		 */
//		public RemoveAllAction(final NodeEditor nodeEditor) {
//			super(getRemoveAllLabel());
//			this.nodeEditor = nodeEditor;
//		}
//
//		/**
//		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//		 */
//		@Override
//		public void actionPerformed(final ActionEvent e) {
//			nodeEditor.removeAllChildNodes();
//		}
//	}
}