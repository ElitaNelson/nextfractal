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
package com.nextbreakpoint.nextfractal.twister.ui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.nextbreakpoint.nextfractal.core.CoreRegistry;
import com.nextbreakpoint.nextfractal.core.extensionPoints.nodeBuilder.NodeBuilderExtensionRuntime;
import com.nextbreakpoint.nextfractal.core.runtime.extension.Extension;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionConfig;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionException;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeAction;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeBuilder;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEvent;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeListener;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeObject;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeSession;
import com.nextbreakpoint.nextfractal.core.runtime.tree.NodeSessionListener;
import com.nextbreakpoint.nextfractal.core.runtime.tree.RootNode;
import com.nextbreakpoint.nextfractal.core.ui.swing.NavigatorPanel;
import com.nextbreakpoint.nextfractal.core.ui.swing.NavigatorTree;
import com.nextbreakpoint.nextfractal.core.ui.swing.ViewContext;
import com.nextbreakpoint.nextfractal.core.ui.swing.util.GUIUtil;
import com.nextbreakpoint.nextfractal.core.util.RenderContext;
import com.nextbreakpoint.nextfractal.twister.ui.swing.extensionPoints.view.ViewExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class DefaultViewRuntime extends ViewExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.twister.ui.swing.extensionPoints.view.ViewExtensionRuntime#createView(com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionConfig, com.nextbreakpoint.nextfractal.core.ui.swing.ViewContext, com.nextbreakpoint.nextfractal.core.util.RenderContext)
	 */
	@Override
	public ViewPanel createView(final ExtensionConfig config, final ViewContext viewContext, final RenderContext context, final NodeSession session) {
		try {
			final Extension<NodeBuilderExtensionRuntime> extension = CoreRegistry.getInstance().getNodeBuilderExtension(config.getExtensionId());
			final NodeBuilder nodeBuilder = extension.createExtensionRuntime().createNodeBuilder(config);
			final RootNode rootNode = new RootNode("navigator.root", extension.getExtensionName() + " extension");
			nodeBuilder.createNodes(rootNode);
			rootNode.setContext(config.getContext());
			rootNode.setSession(new NavigatorNodeSession());
			return new DefaultViewPanel(viewContext, context, rootNode);
		}
		catch (final ExtensionException e) {
			e.printStackTrace();
		}
		return new EmptyViewPanel();
	}

	private class EmptyViewPanel extends ViewPanel {
		private static final long serialVersionUID = 1L;

		/**
		 * @see com.nextbreakpoint.nextfractal.twister.ui.swing.ViewPanel#dispose()
		 */
		@Override
		public void dispose() {
		}
	}

	private class DefaultViewPanel extends ViewPanel {
		private static final long serialVersionUID = 1L;
		private final NavigatorPanelSelectionListener panelSelectionListener;
		private final NavigatorTreeSelectionListener treeSelectionListener;
		private final NavigatorTreeModelListener treeModelListener;
		private final NavigatorTreeListener navigatorTreeListener;
		private final NavigatorPanel navigatorPanel;
		private final NavigatorTree navigatorTree;
		private final RenderContext context;
		private final RootNode rootNode;

		/**
		 * @param viewContext
		 * @param context
		 * @param tree
		 */
		public DefaultViewPanel(final ViewContext viewContext, final RenderContext context, final RootNode rootNode) {
			this.rootNode = rootNode;
			this.context = context;
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), BorderFactory.createLineBorder(Color.DARK_GRAY)));
			navigatorTree = new NavigatorTree(rootNode);
			navigatorPanel = new NavigatorPanel(viewContext, rootNode);
			navigatorTree.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.DARK_GRAY), BorderFactory.createEmptyBorder(2, 2, 2, 2)));
			add(navigatorTree, BorderLayout.WEST);
			add(navigatorPanel, BorderLayout.CENTER);
			panelSelectionListener = new NavigatorPanelSelectionListener();
			treeSelectionListener = new NavigatorTreeSelectionListener();
			treeModelListener = new NavigatorTreeModelListener();
			navigatorTreeListener = new NavigatorTreeListener();
			rootNode.addNodeListener(navigatorTreeListener);
			navigatorPanel.addChangeListener(panelSelectionListener);
			navigatorTree.getModel().addTreeModelListener(treeModelListener);
			navigatorTree.getSelectionModel().addTreeSelectionListener(treeSelectionListener);
			navigatorTree.expandAll();
		}

		private class NavigatorTreeListener implements NodeListener {
			/**
			 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeListener#nodeChanged(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEvent)
			 */
			@Override
			public void nodeChanged(final NodeEvent e) {
			}

			/**
			 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeListener#nodeAdded(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEvent)
			 */
			@Override
			public void nodeAdded(final NodeEvent e) {
			}

			/**
			 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeListener#nodeRemoved(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEvent)
			 */
			@Override
			public void nodeRemoved(final NodeEvent e) {
			}

			/**
			 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeListener#nodeAccepted(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEvent)
			 */
			@Override
			public void nodeAccepted(final NodeEvent e) {
				GUIUtil.executeTask(new Runnable() {
					@Override
					public void run() {
						context.refresh();
					}
				}, true);
			}

			/**
			 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeListener#nodeCancelled(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeEvent)
			 */
			@Override
			public void nodeCancelled(final NodeEvent e) {
			}
		}

		private class NavigatorPanelSelectionListener implements ChangeListener {
			/**
			 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
			 */
			@Override
			public void stateChanged(final ChangeEvent e) {
				GUIUtil.executeTask(new Runnable() {
					@Override
					public void run() {
						if (navigatorPanel.getEditorNode() != null) {
							navigatorTree.getSelectionModel().removeTreeSelectionListener(treeSelectionListener);
							final TreePath path = navigatorTree.creareTreePath(navigatorPanel.getEditorNode().getNodePath());
							navigatorTree.expandPath(path.getParentPath());
							navigatorTree.setSelectionPath(path);
							navigatorTree.getSelectionModel().addTreeSelectionListener(treeSelectionListener);
						}
					}
				}, true);
			}
		}

		private class NavigatorTreeSelectionListener implements TreeSelectionListener {
			/**
			 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
			 */
			@Override
			public void valueChanged(final TreeSelectionEvent e) {
				GUIUtil.executeTask(new Runnable() {
					@Override
					public void run() {
						navigatorPanel.removeChangeListener(panelSelectionListener);
						if (navigatorTree.getSelectionPath() != null) {
							final NodeObject node = (NodeObject) ((DefaultMutableTreeNode) navigatorTree.getSelectionPath().getLastPathComponent()).getUserObject();
							navigatorPanel.loadNode(node);
						}
						else {
							navigatorPanel.loadNode(null);
						}
						navigatorPanel.addChangeListener(panelSelectionListener);
					}
				}, true);
			}
		}

		private class NavigatorTreeModelListener implements TreeModelListener {
			/**
			 * @see javax.swing.event.TreeModelListener#treeNodesChanged(javax.swing.event.TreeModelEvent)
			 */
			@Override
			public void treeNodesChanged(final TreeModelEvent e) {
				GUIUtil.executeTask(new Runnable() {
					@Override
					public void run() {
						navigatorTree.expandPath(e.getTreePath());
					}
				}, true);
			}

			/**
			 * @see javax.swing.event.TreeModelListener#treeNodesInserted(javax.swing.event.TreeModelEvent)
			 */
			@Override
			public void treeNodesInserted(final TreeModelEvent e) {
				GUIUtil.executeTask(new Runnable() {
					@Override
					public void run() {
						navigatorTree.expandPath(e.getTreePath());
					}
				}, true);
			}

			/**
			 * @see javax.swing.event.TreeModelListener#treeNodesRemoved(javax.swing.event.TreeModelEvent)
			 */
			@Override
			public void treeNodesRemoved(final TreeModelEvent e) {
			}

			/**
			 * @see javax.swing.event.TreeModelListener#treeStructureChanged(javax.swing.event.TreeModelEvent)
			 */
			@Override
			public void treeStructureChanged(final TreeModelEvent e) {
			}
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.twister.ui.swing.ViewPanel#dispose()
		 */
		@Override
		public void dispose() {
			rootNode.removeTreeListener(navigatorTreeListener);
			navigatorPanel.removeChangeListener(panelSelectionListener);
			navigatorTree.getModel().removeTreeModelListener(treeModelListener);
			navigatorTree.getSelectionModel().removeTreeSelectionListener(treeSelectionListener);
			rootNode.setContext(null);
			rootNode.setSession(null);
			rootNode.dispose();
		}
	}

	private class NavigatorNodeSession implements NodeSession {
		/**
		 * 
		 */
		public NavigatorNodeSession() {
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeSession#appendAction(com.nextbreakpoint.nextfractal.core.runtime.tree.NodeAction)
		 */
		@Override
		public void appendAction(final NodeAction action) {
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeSession#getActions()
		 */
		@Override
		public List<NodeAction> getActions() {
			return null;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeSession#getSessionName()
		 */
		@Override
		public String getSessionName() {
			return "Navigator";
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeSession#getTimestamp()
		 */
		@Override
		public long getTimestamp() {
			return 0;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeSession#isAcceptImmediatly()
		 */
		@Override
		public boolean isAcceptImmediatly() {
			return true;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeSession#setAcceptImmediatly(boolean)
		 */
		@Override
		public void setAcceptImmediatly(final boolean isApplyImmediatly) {
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.tree.NodeSession#setTimestamp(long)
		 */
		@Override
		public void setTimestamp(final long timestamp) {
		}

		@Override
		public void fireSessionAccepted() {
		}

		@Override
		public void fireSessionCancelled() {
		}

		@Override
		public void fireSessionChanged() {
		}

		@Override
		public void addSessionListener(NodeSessionListener listener) {
		}

		@Override
		public void removeSessionListener(NodeSessionListener listener) {
		}
	}
}