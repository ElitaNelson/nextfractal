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
package com.nextbreakpoint.nextfractal.encoder;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.freeimage4java.FIBITMAP;
import net.sf.freeimage4java.FREE_IMAGE_FORMAT;
import net.sf.freeimage4java.FreeImage4Java;
import net.sf.freeimage4java.FreeImage4JavaConstants;
import net.sf.freeimage4java.RGBQUAD;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractImageEncoder {
	private static final Logger logger = Logger.getLogger(AbstractImageEncoder.class.getName());
	private EncoderDelegate delegate;
	static {
		FreeImage4Java.FreeImage_Initialise(FreeImage4JavaConstants.TRUE);
	}

	public void setDelegate(final EncoderDelegate delegate) {
		this.delegate = delegate;
	}

	public void encode(final EncoderContext context, final File path) throws EncoderException {
		fireStateChanged(0);
		RGBQUAD value = null;
		FIBITMAP dib = null;
		try {
			if (AbstractImageEncoder.logger.isLoggable(Level.FINE)) {
				AbstractImageEncoder.logger.fine("Starting encoding...");
			}
			long time = System.currentTimeMillis();
			int channels = isAlphaSupported() ? 4 : 3;
			value = new RGBQUAD();
			dib = FreeImage4Java.FreeImage_Allocate(context.getImageWidth(), context.getImageHeight(), channels * 8, 0x00FF0000, 0x0000FF00, 0x000000FF);
			final byte[] data = context.getPixelsAsByteArray(0, 0, 0, context.getImageWidth(), context.getImageHeight(), channels);
			for (int y = 0; y < context.getImageHeight(); y++) {
				int j = (context.getImageHeight() - y - 1) * context.getImageWidth();
				for (int x = 0; x < context.getImageWidth(); x++) {
					int i = (j + x) * channels;
					value.setRgbRed(data[i + 0]);
					value.setRgbGreen(data[i + 1]);
					value.setRgbBlue(data[i + 2]);
					if (isAlphaSupported()) {
						value.setRgbReserved(data[i + 3]);
					}
					else {
						value.setRgbReserved((short) 255);
					}
					FreeImage4Java.FreeImage_SetPixelColor(dib, x, y, value);
					if (delegate.isInterrupted()) {
						break;
					}
				}
			}
			if (!delegate.isInterrupted()) {
				FreeImage4Java.FreeImage_Save(getFormat(getFormatName()), dib, path.getAbsolutePath(), 0);
				time = System.currentTimeMillis() - time;
				if (AbstractImageEncoder.logger.isLoggable(Level.INFO)) {
					AbstractImageEncoder.logger.info("Profile exported: elapsed time " + String.format("%3.2f", time / 1000.0d) + "s");
				}
				fireStateChanged(100);
			}
		}
		catch (final Exception e) {
			throw new EncoderException(e);
		}
		finally {
			if (dib != null) {
				FreeImage4Java.FreeImage_Unload(dib);
				dib.delete();
			}
			if (value != null) {
				value.delete();
			}
		}
	}

	protected boolean isAlphaSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void fireStateChanged(int state) {
		// TODO Auto-generated method stub
		
	}

	protected FREE_IMAGE_FORMAT getFormat(String formatName) {
		switch (formatName) {
		case "PNG":
			return FREE_IMAGE_FORMAT.FIF_PNG;

		case "JPEG":
			return FREE_IMAGE_FORMAT.FIF_JPEG;
			
		default:
			return FREE_IMAGE_FORMAT.FIF_PNG;
		}
	}

	protected abstract String getFormatName();
}