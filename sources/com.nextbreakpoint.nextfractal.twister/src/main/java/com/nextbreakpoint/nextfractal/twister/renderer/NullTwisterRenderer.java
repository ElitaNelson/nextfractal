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
package com.nextbreakpoint.nextfractal.twister.renderer;

import java.awt.Graphics2D;
import java.util.Map;

import com.nextbreakpoint.nextfractal.core.util.Surface;
import com.nextbreakpoint.nextfractal.core.util.Tile;
import com.nextbreakpoint.nextfractal.twister.TwisterRuntime;

/**
 * @author Andrea Medeghini
 */
public class NullTwisterRenderer implements TwisterRenderer {
	private Tile tile;

	/**
	 * 
	 */
	public NullTwisterRenderer() {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#startRenderer()
	 */
	public void startRenderer() {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#stopRenderer()
	 */
	public void stopRenderer() {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#abortRenderer()
	 */
	public void abortRenderer() {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#joinRenderer()
	 */
	public void joinRenderer() {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#drawImage(java.awt.Graphics2D)
	 */
	public void drawImage(final Graphics2D g) {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#drawImage(java.awt.Graphics2D, int, int)
	 */
	public void drawImage(final Graphics2D g, final int x, final int y) {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#drawImage(java.awt.Graphics2D, int, int, int, int)
	 */
	public void drawImage(final Graphics2D g, final int x, final int y, final int w, final int h) {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#drawImage(java.awt.Graphics2D, int, int, int, int, int, int)
	 */
	public void drawImage(final Graphics2D g, final int x, final int y, final int w, final int h, final int bx, final int by) {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#getRuntime()
	 */
	public TwisterRuntime getRuntime() {
		return null;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#setRenderingHints(java.util.Map)
	 */
	public void setRenderingHints(final Map<Object, Object> hints) {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#getTile()
	 */
	public Tile getTile() {
		return tile;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#setTile(com.nextbreakpoint.nextfractal.core.util.Tile)
	 */
	public void setTile(final Tile tile) {
		this.tile = tile;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#render()
	 */
	public void render() {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#prepareImage(boolean)
	 */
	public void prepareImage(final boolean isDynamicRequired) {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#dispose()
	 */
	public void dispose() {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#drawSurface(java.awt.Graphics2D)
	 */
	public void drawSurface(final Graphics2D graphics2D) {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#drawSurface(java.awt.Graphics2D, int, int)
	 */
	public void drawSurface(Graphics2D graphics2D, int x, int y) {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.TwisterRenderer#loadSurface(com.nextbreakpoint.nextfractal.core.util.Surface)
	 */
	public void loadSurface(final Surface surface) {
	}
}
