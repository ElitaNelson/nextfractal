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
package com.nextbreakpoint.nextfractal.mandelbrot.renderer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadFactory;

import com.nextbreakpoint.nextfractal.mandelbrot.core.Color;
import com.nextbreakpoint.nextfractal.mandelbrot.core.Number;
import com.nextbreakpoint.nextfractal.mandelbrot.core.Orbit;
import com.nextbreakpoint.nextfractal.mandelbrot.renderer.xaos.XaosRenderer;
import com.nextbreakpoint.nextfractal.render.RenderFactory;
import com.nextbreakpoint.nextfractal.render.RenderGraphicsContext;

/**
 * @author Andrea Medeghini
 */
public class RendererCoordinator implements RendererDelegate {
	public static final String KEY_TYPE = "TYPE";
	public static final Integer VALUE_REALTIME = 1;
	private final HashMap<String, Integer> hints = new HashMap<>();
	private final ThreadFactory threadFactory;
	private final RenderFactory renderFactory;
	private volatile boolean pixelsChanged;
	private volatile float progress;
	private Renderer renderer;
	
	/**
	 * @param threadFactory
	 * @param renderFactory
	 * @param tile
	 * @param hints
	 */
	public RendererCoordinator(ThreadFactory threadFactory, RenderFactory renderFactory, RendererTile tile, Map<String, Integer> hints) {
		this.threadFactory = threadFactory;
		this.renderFactory = renderFactory;
		this.hints.putAll(hints);
		renderer = createRenderer(tile);
		renderer.setRendererDelegate(this);
	}

	/**
	 * @see java.lang.Object#finalize()
	 */
	@Override
	public void finalize() throws Throwable {
		dispose();
		super.finalize();
	}

	/**
	 * 
	 */
	public final void dispose() {
		free();
	}
	
	/**
	 * 
	 */
	public void abort() {
		renderer.abortTasks();
	}
	
	/**
	 * 
	 */
	public void waitFor() {
		renderer.waitForTasks();
	}
	
	/**
	 * 
	 */
	public void run() {
		renderer.runTask();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.renderer.RendererDelegate#didChanged(float)
	 */
	@Override
	public void didChanged(float progress) {
		this.progress = progress;
		this.pixelsChanged = true;
	}

	/**
	 * @return
	 */
	public boolean isPixelsChanged() {
		boolean result = pixelsChanged;
		pixelsChanged = false;
		return result;
	}

	/**
	 * @return
	 */
	public float getProgress() {
		return progress;
	}

	/**
	 * @return
	 */
	public RendererSize getSize() {
		return renderer.getSize();
	}

	/**
	 * @param point
	 */
	public void setConstant(Number constant) {
		renderer.setPoint(constant);
	}

	/**
	 * @param julia
	 */
	public void setJulia(final boolean julia) {
		renderer.setJulia(julia);
	}

	/**
	 * @param orbit
	 * @param color
	 */
	public void setOrbitAndColor(Orbit orbit, Color color) {
		renderer.setOrbit(orbit);
		renderer.setColor(color);
	}

	/**
	 * @param color
	 */
	public void setColor(Color color) {
		renderer.setColor(color);
	}

	/**
	 * 
	 */
	public void init() {
		renderer.init();
	}

	/**
	 * @return
	 */
	public boolean isTileSupported() {
		return true;
	}

	/**
	 * @param gc
	 */
	public void drawImage(final RenderGraphicsContext gc) {
		renderer.drawImage(gc);
	}

	/**
	 * @param gc
	 * @param x
	 * @param y
	 */
	public void drawImage(final RenderGraphicsContext gc, final int x, final int y) {
		renderer.drawImage(gc, x, y);
	}

	/**
	 * @param gc
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawImage(final RenderGraphicsContext gc, final int x, final int y, final int w, final int h) {
		renderer.drawImage(gc, x, y, w, h);
	}

	/**
	 * 
	 */
	protected void free() {
		if (renderer != null) {
			renderer.dispose();
			renderer = null;
		}
	}

	/**
	 * @param tile 
	 * @return
	 */
	protected Renderer createRenderer(RendererTile tile) {
		Integer type = hints.get(KEY_TYPE);
		if (type != null && type.equals(VALUE_REALTIME)) {
			return new XaosRenderer(threadFactory, renderFactory, tile);
		} else {
			return new Renderer(threadFactory, renderFactory, tile);
		}
	}

	public Number getInitialCenter() {
		return renderer.getInitialRegion().getCenter();
	}

	public Number getInitialSize() {
		return renderer.getInitialRegion().getSize();
	}

	public RendererTile getTile() {
		return renderer.getTile();
	}

	public void setView(RendererView view) {
		renderer.setView(view);
	}
}