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

import java.util.concurrent.ThreadFactory;

import com.nextbreakpoint.nextfractal.core.Worker;
import com.nextbreakpoint.nextfractal.mandelbrot.core.Color;
import com.nextbreakpoint.nextfractal.mandelbrot.core.MutableNumber;
import com.nextbreakpoint.nextfractal.mandelbrot.core.Number;
import com.nextbreakpoint.nextfractal.mandelbrot.core.Orbit;
import com.nextbreakpoint.nextfractal.mandelbrot.renderer.strategy.JuliaRendererStrategy;
import com.nextbreakpoint.nextfractal.mandelbrot.renderer.strategy.MandelbrotRendererStrategy;

/**
 * @author Andrea Medeghini
 */
public class Renderer {
	protected final RendererFractal rendererFractal;
	protected final ThreadFactory threadFactory;
	protected final RendererData rendererData;
	protected final Worker rendererWorker;
	protected volatile RendererDelegate rendererDelegate;
	protected volatile RendererStrategy rendererStrategy;
	protected volatile boolean aborted;
	protected volatile boolean orbitChanged;
	protected volatile boolean colorChanged;
	protected volatile boolean regionChanged;
	protected volatile float progress;
	protected int width;
	protected int height;
	protected boolean julia;
	protected boolean continuous;
	protected Number constant;
	protected RendererRegion region;
	protected RendererRegion initialRegion;

	/**
	 * @param rendererDelegate
	 * @param rendererFractal
	 * @param width
	 * @param height
	 */
	public Renderer(ThreadFactory threadFactory, int width, int height) {
		this.threadFactory = threadFactory;
		this.rendererWorker = new Worker(threadFactory);
		this.rendererData = createRendererData();
		this.rendererFractal = new RendererFractal();
		this.width = width;
		this.height = height;
		start();
	}
	
	/**
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @return
	 */
	public boolean isInterrupted() {
		return aborted || Thread.currentThread().isInterrupted();
	}

	/**
	 * 
	 */
	public void dispose() {
		stop();
		free();
	}

	/**
	 * @return
	 */
	public float getProgress() {
		return progress;
	}

	/**
	 * 
	 */
	public void stopRender() {
		rendererWorker.abortTasks();
		rendererWorker.waitTasks();
	}

	/**
	 * 
	 */
	public void abortRender() {
		rendererWorker.abortTasks();
	}

	/**
	 * 
	 */
	public void joinRender() {
		rendererWorker.waitTasks();
	}

	/**
	 * 
	 */
	public void startRender() {
		rendererWorker.addTask(new Runnable() {
			@Override
			public void run() {
				doRender();
			}
		});
	}
	
	/**
	 * @param julia
	 */
	public void setJulia(boolean julia) {
		this.julia = julia;
	}

	/**
	 * @param constant
	 */
	public void setConstant(Number constant) {
		this.constant = constant;
	}

	/**
	 * @return
	 */
	public RendererDelegate getRendererDelegate() {
		return rendererDelegate;
	}

	/**
	 * @param rendererDelegate
	 */
	public void setRendererDelegate(RendererDelegate rendererDelegate) {
		this.rendererDelegate = rendererDelegate;
	}

	/**
	 * @return
	 */
	protected RendererData createRendererData() {
		return new RendererData();
	}

	/**
	 * @param orbit
	 */
	public void setOrbit(Orbit orbit) {
		rendererFractal.setOrbit(orbit);
		orbitChanged = true;
	}

	/**
	 * @param color
	 */
	public void setColor(Color color) {
		rendererFractal.setColor(color);
		colorChanged = true;
	}

	/**
	 * @param continuous
	 */
	public void setContinuous(boolean continuous) {
		this.continuous = continuous;
	}

	/**
	 * 
	 */
	public void init() {
		rendererFractal.initialize();
		initialRegion = new RendererRegion(rendererFractal.getOrbit().getInitialRegion());
	}

	/**
	 * 
	 */
	protected void free() {
		rendererData.free();
	}

	/**
	 * 
	 */
	protected void start() {
		rendererWorker.start();
	}

	/**
	 * 
	 */
	protected void stop() {
		rendererWorker.stop();
	}

	/**
	 * @return
	 */
	public RendererRegion getInitialRegion() {
		return initialRegion;
	}

	/**
	 * @return
	 */
	public RendererRegion getRegion() {
		return region;
	}

	/**
	 * @param region
	 */
	public void setRegion(RendererRegion region) {
		this.region = region;
		regionChanged = true; 
	}

	/**
	 * @param dynamic
	 */
	protected void doRender() {
		if (rendererFractal == null) {
			progress = 1;
			return;
		}
		final boolean redraw = orbitChanged || regionChanged || regionChanged;
		orbitChanged = false;
		colorChanged = false;
		regionChanged = false;
		aborted = false;
		progress = 0;
		rendererFractal.clearScope();
		rendererFractal.setConstant(constant);
		if (julia) {
			rendererStrategy = new JuliaRendererStrategy(rendererFractal);
		} else {
			rendererStrategy = new MandelbrotRendererStrategy(rendererFractal);
		}
		rendererStrategy.prepare();
		rendererData.setSize(width, height, rendererFractal.getStateSize());
		rendererData.setRegion(region);
		rendererData.initPositions();
		rendererData.swap();
		rendererData.clearPixels();
		if (rendererDelegate != null) {
			rendererDelegate.didChanged(progress, rendererData.getPixels());
		}
		final MutableNumber px = new MutableNumber(0, 0);
		final MutableNumber pw = new MutableNumber(0, 0);
		final RendererState p = rendererData.newPoint();
		int offset = 0;
		int c = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				px.set(rendererData.point());
				pw.set(rendererData.positionX(x), rendererData.positionY(y));
				if (redraw) {
					c = rendererStrategy.renderPoint(p, px, pw);
				} else {
					rendererData.getPoint(offset, p);
					c = rendererStrategy.renderColor(p);
				}
				rendererData.setPoint(offset, p);
				rendererData.setPixel(offset, c);
				offset += 1;
			}
			if (y % 20 == 0) {
				progress = (float)y / (float)height;
				if (rendererDelegate != null) {
					rendererDelegate.didChanged(progress, rendererData.getPixels());
				}
			}
			if (isInterrupted()) {
				aborted = true;
				break;
			}
			Thread.yield();
		}
		if (aborted) {
			progress = 1;
		}
		if (rendererDelegate != null) {
			rendererDelegate.didChanged(progress, rendererData.getPixels());
		}
		Thread.yield();
	}
}
