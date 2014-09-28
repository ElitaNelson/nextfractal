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
package com.nextbreakpoint.nextfractal.twister.layer;

import com.nextbreakpoint.nextfractal.core.config.ValueChangeEvent;
import com.nextbreakpoint.nextfractal.core.config.ValueChangeListener;
import com.nextbreakpoint.nextfractal.core.config.ValueConfigElement;
import com.nextbreakpoint.nextfractal.twister.image.ImageConfigElement;
import com.nextbreakpoint.nextfractal.twister.image.ImageRuntimeElement;

/**
 * @author Andrea Medeghini
 */
public class ImageLayerRuntimeElement extends AbstractLayerRuntimeElement {
	private ImageRuntimeElement imageElement;
	private ImageElementListener imageListener;

	/**
	 * Constructs a new layer.
	 * 
	 * @param layerElement
	 */
	public ImageLayerRuntimeElement(final ImageLayerConfigElement layerElement) {
		super(layerElement);
		createImage(layerElement);
		imageListener = new ImageElementListener();
		layerElement.getImageSingleElement().addChangeListener(imageListener);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.config.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((getLayerElement() != null) && (imageListener != null)) {
			((ImageLayerConfigElement) getLayerElement()).getImageSingleElement().removeChangeListener(imageListener);
		}
		imageListener = null;
		if (imageElement != null) {
			imageElement.dispose();
			imageElement = null;
		}
		super.dispose();
	}

	private void createImage(final ImageLayerConfigElement layerElement) {
		final ImageConfigElement imageElement = layerElement.getImageConfigElement();
		if (imageElement != null) {
			final ImageRuntimeElement image = new ImageRuntimeElement(imageElement);
			setImage(image);
		}
	}

	/**
	 * Returns the image.
	 * 
	 * @return the image.
	 */
	public ImageRuntimeElement getImage() {
		return imageElement;
	}

	private void setImage(final ImageRuntimeElement imageElement) {
		if (this.imageElement != null) {
			this.imageElement.dispose();
		}
		this.imageElement = imageElement;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.config.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		final boolean imageChanged = imageElement.isChanged();
		return super.isChanged() || imageChanged;
	}

	private class ImageElementListener implements ValueChangeListener {
		/**
		 * @see com.nextbreakpoint.nextfractal.core.config.ValueChangeListener#valueChanged(com.nextbreakpoint.nextfractal.core.config.ValueChangeEvent)
		 */
		public void valueChanged(final ValueChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setImage(new ImageRuntimeElement((ImageConfigElement) e.getParams()[0]));
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
}
