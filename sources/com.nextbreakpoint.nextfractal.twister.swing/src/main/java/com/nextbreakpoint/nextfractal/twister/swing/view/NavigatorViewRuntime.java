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
package com.nextbreakpoint.nextfractal.twister.swing.view;

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
import com.nextbreakpoint.nextfractal.core.extension.Extension;
import com.nextbreakpoint.nextfractal.core.extension.ExtensionConfig;
import com.nextbreakpoint.nextfractal.core.extension.ExtensionException;
import com.nextbreakpoint.nextfractal.core.nodeBuilder.extension.NodeBuilderExtensionRuntime;
import com.nextbreakpoint.nextfractal.core.swing.NavigatorPanel;
import com.nextbreakpoint.nextfractal.core.swing.NavigatorTree;
import com.nextbreakpoint.nextfractal.core.swing.ViewContext;
import com.nextbreakpoint.nextfractal.core.swing.util.GUIUtil;
import com.nextbreakpoint.nextfractal.core.tree.Node;
import com.nextbreakpoint.nextfractal.core.tree.NodeAction;
import com.nextbreakpoint.nextfractal.core.tree.NodeBuilder;
import com.nextbreakpoint.nextfractal.core.tree.NodeEvent;
import com.nextbreakpoint.nextfractal.core.tree.NodeListener;
import com.nextbreakpoint.nextfractal.core.tree.NodeSession;
import com.nextbreakpoint.nextfractal.core.tree.NodeSessionListener;
import com.nextbreakpoint.nextfractal.core.tree.RootNode;
import com.nextbreakpoint.nextfractal.core.tree.Tree;
import com.nextbreakpoint.nextfractal.core.util.RenderContext;
import com.nextbreakpoint.nextfractal.twister.swing.ViewPanel;
import com.nextbreakpoint.nextfractal.twister.swing.view.extension.ViewExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class NavigatorViewRuntime extends ViewExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.twister.swing.view.extension.ViewExtensionRuntime#createView(com.nextbreakpoint.nextfractal.core.extension.ExtensionConfig, com.nextbreakpoint.nextfractal.core.swing.ViewContext, com.nextbreakpoint.nextfractal.core.util.RenderContext)
	 */
	@Override
	public ViewPanel createView(final ExtensionConfig config, final ViewContext viewContext, final RenderContext context, final NodeSession session) {
		try {
			final Extension<NodeBuilderExtensionRuntime> extension = CoreRegistry.getInstance().getNodeBuilderExtension(config.getExtensionId());
			final NodeBuilder nodeBuilder = extension.createExtensionRuntime().createNodeBuilder(config);
			final Tree tree = new Tree(new RootNode("navigator.root", extension.getExtensionName() + " extension"));
			nodeBuilder.createNodes(tree.getRootNode());
			tree.getRootNode().setContext(config.getContext());
			tree.getRootNode().setSession(new NavigatorNodeSession());
			return new NavigatorViewPanel(viewContext, context, tree);
		}
		catch (final ExtensionException e) {
			e.printStackTrace();
		}
		return new EmptyViewPanel();
	}

	private class EmptyViewPanel extends ViewPanel {
		private static final long serialVersionUID = 1L;

		/**
		 * @see com.nextbreakpoint.nextfractal.twister.swing.ViewPanel#dispose()
		 */
		@Override
		public void dispose() {
		}
	}

	private class NavigatorViewPanel extends ViewPanel {
		private static final long serialVersionUID = 1L;
		private final NavigatorPanelSelectionListener panelSelectionListener;
		private final NavigatorTreeSelectionListener treeSelectionListener;
		private final NavigatorTreeModelListener treeModelListener;
		private final NavigatorTreeListener navigatorTreeListener;
		private final NavigatorPanel navigatorPanel;
		private final NavigatorTree navigatorTree;
		private final RenderContext context;
		private final Tree tree;

		/**
		 * @param viewContext
		 * @param context
		 * @param tree
		 */
		public NavigatorViewPanel(final ViewContext viewContext, final RenderContext context, final Tree tree) {
			this.tree = tree;
			this.context = context;
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), BorderFactory.createLineBorder(Color.DARK_GRAY)));
			navigatorTree = new NavigatorTree(tree.getRootNode());
			navigatorPanel = new NavigatorPanel(viewContext, tree.getRootNode());
			navigatorTree.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.DARK_GRAY), BorderFactory.createEmptyBorder(2, 2, 2, 2)));
			add(navigatorTree, BorderLayout.WEST);
			add(navigatorPanel, BorderLayout.CENTER);
			panelSelectionListener = new NavigatorPanelSelectionListener();
			treeSelectionListener = new NavigatorTreeSelectionListener();
			treeModelListener = new NavigatorTreeModelListener();
			navigatorTreeListener = new NavigatorTreeListener();
			tree.getRootNode().addNodeListener(navigatorTreeListener);
			navigatorPanel.addChangeListener(panelSelectionListener);
			navigatorTree.getModel().addTreeModelListener(treeModelListener);
			navigatorTree.getSelectionModel().addTreeSelectionListener(treeSelectionListener);
			navigatorTree.expandAll();
		}

		private class NavigatorTreeListener implements NodeListener {
			/**
			 * @see com.nextbreakpoint.nextfractal.core.tree.NodeListener#nodeChanged(com.nextbreakpoint.nextfractal.core.tree.NodeEvent)
			 */
			public void nodeChanged(final NodeEvent e) {
			}

			/**
			 * @see com.nextbreakpoint.nextfractal.core.tree.NodeListener#nodeAdded(com.nextbreakpoint.nextfractal.core.tree.NodeEvent)
			 */
			public void nodeAdded(final NodeEvent e) {
			}

			/**
			 * @see com.nextbreakpoint.nextfractal.core.tree.NodeListener#nodeRemoved(com.nextbreakpoint.nextfractal.core.tree.NodeEvent)
			 */
			public void nodeRemoved(final NodeEvent e) {
			}

			/**
			 * @see com.nextbreakpoint.nextfractal.core.tree.NodeListener#nodeAccepted(com.nextbreakpoint.nextfractal.core.tree.NodeEvent)
			 */
			public void nodeAccepted(final NodeEvent e) {
				GUIUtil.executeTask(new Runnable() {
					public void run() {
						context.refresh();
					}
				}, true);
			}

			/**
			 * @see com.nextbreakpoint.nextfractal.core.tree.NodeListener#nodeCancelled(com.nextbreakpoint.nextfractal.core.tree.NodeEvent)
			 */
			public void nodeCancelled(final NodeEvent e) {
			}
		}

		private class NavigatorPanelSelectionListener implements ChangeListener {
			/**
			 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
			 */
			public void stateChanged(final ChangeEvent e) {
				GUIUtil.executeTask(new Runnable() {
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
			public void valueChanged(final TreeSelectionEvent e) {
				GUIUtil.executeTask(new Runnable() {
					public void run() {
						navigatorPanel.removeChangeListener(panelSelectionListener);
						if (navigatorTree.getSelectionPath() != null) {
							final Node node = (Node) ((DefaultMutableTreeNode) navigatorTree.getSelectionPath().getLastPathComponent()).getUserObject();
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
			public void treeNodesChanged(final TreeModelEvent e) {
				GUIUtil.executeTask(new Runnable() {
					public void run() {
						navigatorTree.expandPath(e.getTreePath());
					}
				}, true);
			}

			/**
			 * @see javax.swing.event.TreeModelListener#treeNodesInserted(javax.swing.event.TreeModelEvent)
			 */
			public void treeNodesInserted(final TreeModelEvent e) {
				GUIUtil.executeTask(new Runnable() {
					public void run() {
						navigatorTree.expandPath(e.getTreePath());
					}
				}, true);
			}

			/**
			 * @see javax.swing.event.TreeModelListener#treeNodesRemoved(javax.swing.event.TreeModelEvent)
			 */
			public void treeNodesRemoved(final TreeModelEvent e) {
			}

			/**
			 * @see javax.swing.event.TreeModelListener#treeStructureChanged(javax.swing.event.TreeModelEvent)
			 */
			public void treeStructureChanged(final TreeModelEvent e) {
			}
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.twister.swing.ViewPanel#dispose()
		 */
		@Override
		public void dispose() {
			tree.getRootNode().removeTreeListener(navigatorTreeListener);
			navigatorPanel.removeChangeListener(panelSelectionListener);
			navigatorTree.getModel().removeTreeModelListener(treeModelListener);
			navigatorTree.getSelectionModel().removeTreeSelectionListener(treeSelectionListener);
			tree.getRootNode().setContext(null);
			tree.getRootNode().setSession(null);
			tree.getRootNode().dispose();
		}
	}

	private class NavigatorNodeSession implements NodeSession {
		/**
		 * 
		 */
		public NavigatorNodeSession() {
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.tree.NodeSession#appendAction(com.nextbreakpoint.nextfractal.core.tree.NodeAction)
		 */
		public void appendAction(final NodeAction action) {
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.tree.NodeSession#getActions()
		 */
		public List<NodeAction> getActions() {
			return null;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.tree.NodeSession#getSessionName()
		 */
		public String getSessionName() {
			return "Navigator";
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.tree.NodeSession#getTimestamp()
		 */
		public long getTimestamp() {
			return 0;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.tree.NodeSession#isAcceptImmediatly()
		 */
		public boolean isAcceptImmediatly() {
			return true;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.tree.NodeSession#setAcceptImmediatly(boolean)
		 */
		public void setAcceptImmediatly(final boolean isApplyImmediatly) {
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.tree.NodeSession#setTimestamp(long)
		 */
		public void setTimestamp(final long timestamp) {
		}

		public void fireSessionAccepted() {
		}

		public void fireSessionCancelled() {
		}

		public void fireSessionChanged() {
		}

		public void addSessionListener(NodeSessionListener listener) {
		}

		public void removeSessionListener(NodeSessionListener listener) {
		}
	}
}
