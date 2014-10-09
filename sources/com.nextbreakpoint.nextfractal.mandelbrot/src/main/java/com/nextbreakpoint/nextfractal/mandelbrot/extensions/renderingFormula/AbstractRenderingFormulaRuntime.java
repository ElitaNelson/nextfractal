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
package com.nextbreakpoint.nextfractal.mandelbrot.extensions.renderingFormula;

import com.nextbreakpoint.nextfractal.core.math.Complex;
import com.nextbreakpoint.nextfractal.core.util.DoubleVector2D;
import com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.processingFormula.ProcessingFormulaExtensionRuntime;
import com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionConfig;
import com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime;
import com.nextbreakpoint.nextfractal.mandelbrot.orbitTrap.extension.OrbitTrapExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractRenderingFormulaRuntime<T extends RenderingFormulaExtensionConfig> extends RenderingFormulaExtensionRuntime<T> {
	protected ProcessingFormulaExtensionRuntime formulaRuntime;
	protected OrbitTrapExtensionRuntime<?> orbitTrapRuntime;
	protected double threshold;
	protected int iterations;

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime#prepareForRendering(com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.processingFormula.ProcessingFormulaExtensionRuntime, com.nextbreakpoint.nextfractal.mandelbrot.orbitTrap.extension.OrbitTrapExtensionRuntime)
	 */
	@Override
	public void prepareForRendering(final ProcessingFormulaExtensionRuntime formulaRuntime, final OrbitTrapExtensionRuntime<?> orbitTrapRuntime) {
		this.formulaRuntime = formulaRuntime;
		this.orbitTrapRuntime = orbitTrapRuntime;
		this.threshold = this.getThreshold();
		this.iterations = this.getIterations();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime#isMandelbrotModeAllowed()
	 */
	@Override
	public boolean isMandelbrotModeAllowed() {
		return true;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime#getThreshold()
	 */
	@Override
	public double getThreshold() {
		return getConfig().getThreshold();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime#getIterations()
	 */
	@Override
	public int getIterations() {
		return getConfig().getIterations();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime#getDefaultCenter()
	 */
	@Override
	public DoubleVector2D getDefaultCenter() {
		return getConfig().getDefaultCenter();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime#getDefaultScale()
	 */
	@Override
	public DoubleVector2D getDefaultScale() {
		return getConfig().getDefaultScale();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime#getCenter()
	 */
	@Override
	public DoubleVector2D getCenter() {
		return getConfig().getCenter();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime#getScale()
	 */
	@Override
	public DoubleVector2D getScale() {
		return getConfig().getScale();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime#getInitialPoint()
	 */
	@Override
	public Complex getInitialPoint() {
		return new Complex(0.0, 0.0);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime#isHorizontalSymetryAllowed()
	 */
	@Override
	public boolean isHorizontalSymetryAllowed() {
		return false;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime#isVerticalSymetryAllowed()
	 */
	@Override
	public boolean isVerticalSymetryAllowed() {
		return false;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime#getHorizontalSymetryPoint()
	 */
	@Override
	public double getHorizontalSymetryPoint() {
		return 0.0;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime#getVerticalSymetryPoint()
	 */
	@Override
	public double getVerticalSymetryPoint() {
		return 0.0;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.extension.ExtensionRuntime#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
	}
}
