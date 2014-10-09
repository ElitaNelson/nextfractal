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
package com.nextbreakpoint.nextfractal.twister.extensions.layerFilter;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import com.nextbreakpoint.nextfractal.core.util.Color32bit;
import com.nextbreakpoint.nextfractal.core.util.Surface;
import com.nextbreakpoint.nextfractal.core.util.Tile;
import com.nextbreakpoint.nextfractal.twister.extensionPoints.layerFilter.LayerFilterExtensionRuntime;
import com.nextbreakpoint.nextfractal.twister.util.Padding;

/**
 * @author Andrea Medeghini
 */
public class ColorRuntime extends LayerFilterExtensionRuntime<ColorConfig> {
	private final Padding padding = new Padding(0, 0, 0, 0);
	private Color color;

	/**
	 * Returns the color.
	 * 
	 * @return the color.
	 */
	public Color32bit getColor() {
		return getConfig().getColor();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.extensionPoints.layerFilter.LayerFilterExtensionRuntime#getPadding()
	 */
	@Override
	public Padding getPadding() {
		return padding;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.extensionPoints.layerFilter.LayerFilterExtensionRuntime#renderImage(com.nextbreakpoint.nextfractal.core.util.Surface, com.nextbreakpoint.nextfractal.core.util.Surface)
	 */
	@Override
	public void renderImage(final Surface src, final Surface dst) {
		final Graphics2D g2d = dst.getGraphics2D();
		g2d.setComposite(AlphaComposite.SrcOver);
		g2d.setColor(color);
		dst.getGraphics2D().drawImage(src.getImage(), 0, 0, null);
		g2d.fillRect(0, 0, dst.getWidth(), dst.getHeight());
		// g2d.dispose();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.extensionPoints.layerFilter.LayerFilterExtensionRuntime#setTile(com.nextbreakpoint.nextfractal.core.util.Tile)
	 */
	@Override
	public void setTile(final Tile tile) {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.extensionPoints.layerFilter.LayerFilterExtensionRuntime#prepareFilter()
	 */
	@Override
	public void prepareFilter() {
		color = new Color(getColor().getARGB(), true);
	}
}
