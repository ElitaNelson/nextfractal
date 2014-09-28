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
package com.nextbreakpoint.nextfractal.mandelbrot.extensions.paletteRenderer;

import com.nextbreakpoint.nextfractal.core.util.Palette;
import com.nextbreakpoint.nextfractal.mandelbrot.util.RenderedPalette;

/**
 * @author Andrea Medeghini
 */
public class DefaultRendererRuntime extends AbstractPaletteRendererRuntime<DefaultRendererConfig> {
	private RenderedPalette palette;

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.paletteRenderer.extension.PaletteRendererExtensionRuntime#prepareForRendering()
	 */
	@Override
	public void prepareForRendering() {
		final DefaultRendererConfig config = getConfig();
		palette = config.getRenderedPalette();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.paletteRenderer.extension.PaletteRendererExtensionRuntime#renderPalette()
	 */
	@Override
	public Palette renderPalette() {
		return palette;
	}
}
