package com.nextbreakpoint.nextfractal.renderer.java2D;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.nextbreakpoint.nextfractal.renderer.RendererAffine;
import com.nextbreakpoint.nextfractal.renderer.RendererBuffer;
import com.nextbreakpoint.nextfractal.renderer.RendererColor;
import com.nextbreakpoint.nextfractal.renderer.RendererFactory;
import com.nextbreakpoint.nextfractal.renderer.RendererGraphicsContext;

public class Java2DRendererFactory implements RendererFactory {
	/**
	 * @see com.nextbreakpoint.nextfractal.RendererFactory.twister.renderer.RenderFactory#createBuffer(int, int)
	 */
	@Override
	public RendererBuffer createBuffer(int width, int height) {
		return new Java2DRendererBuffer(width, height);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.RendererFactory.twister.renderer.RenderFactory#createGraphicsContext(java.lang.Object)
	 */
	@Override
	public RendererGraphicsContext createGraphicsContext(Object context) {
		return new Java2DRendererGraphicsContext((Graphics2D)context);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.RendererFactory.twister.renderer.RenderFactory#createTranslateAffine(double, double)
	 */
	@Override
	public RendererAffine createTranslateAffine(double x, double y) {
		return new Java2DRendererAffine(AffineTransform.getTranslateInstance(x, y));
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.RendererFactory.twister.renderer.RenderFactory#createRotateAffine(double, double, double)
	 */
	@Override
	public RendererAffine createRotateAffine(double a, double centerX, double centerY) {
		return new Java2DRendererAffine(AffineTransform.getRotateInstance(a, centerX, centerY));
	}
	
	/**
	 * @see com.nextbreakpoint.nextfractal.RendererFactory.twister.renderer.RenderFactory#createAffine()
	 */
	@Override
	public RendererAffine createAffine() {
		return new Java2DRendererAffine(new AffineTransform());
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.RendererFactory.twister.renderer.RenderFactory#createColor(int, int, int, int)
	 */
	@Override
	public RendererColor createColor(double red, double green, double blue, double opacity) {
		return new Java2DRendererColor(red, green, blue, opacity);
	}
}
