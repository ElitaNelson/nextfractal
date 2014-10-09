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
package com.nextbreakpoint.nextfractal.mandelbrot.extensions.processingFormula;

import com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.processingFormula.ProcessingFormulaExtensionRuntime;
import com.nextbreakpoint.nextfractal.mandelbrot.renderer.RenderedPoint;

/**
 * @author Andrea Medeghini
 */
public class MaximumFormulaRuntime extends ProcessingFormulaExtensionRuntime {
	private double value;
	private double zr;
	private double zi;
	private double m;

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.processingFormula.ProcessingFormulaExtensionRuntime#prepareForProcessing()
	 */
	@Override
	public void prepareForProcessing() {
		value = Double.MIN_VALUE;
		zr = 0;
		zi = 0;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.processingFormula.ProcessingFormulaExtensionRuntime#processPoint(com.nextbreakpoint.nextfractal.mandelbrot.renderer.RenderedPoint)
	 */
	@Override
	public void processPoint(final RenderedPoint cp) {
		m = cp.zr * cp.zr + cp.zi * cp.zi;
		if (m > value) {
			value = m;
			zr = cp.zr;
			zi = cp.zi;
		}
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.processingFormula.ProcessingFormulaExtensionRuntime#renderPoint(com.nextbreakpoint.nextfractal.mandelbrot.renderer.RenderedPoint)
	 */
	@Override
	public void renderPoint(final RenderedPoint cp) {
		cp.tr = zr;
		cp.ti = zi;
	}
}
