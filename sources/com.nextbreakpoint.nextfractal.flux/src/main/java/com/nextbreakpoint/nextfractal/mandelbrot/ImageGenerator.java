package com.nextbreakpoint.nextfractal.mandelbrot;

import java.nio.IntBuffer;
import java.util.concurrent.ThreadFactory;

import com.nextbreakpoint.nextfractal.core.DoubleVector4D;
import com.nextbreakpoint.nextfractal.core.IntegerVector4D;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.Compiler;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompilerBuilder;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompilerReport;
import com.nextbreakpoint.nextfractal.mandelbrot.core.Color;
import com.nextbreakpoint.nextfractal.mandelbrot.core.Number;
import com.nextbreakpoint.nextfractal.mandelbrot.core.Orbit;
import com.nextbreakpoint.nextfractal.mandelbrot.renderer.Renderer;
import com.nextbreakpoint.nextfractal.mandelbrot.renderer.RendererTile;
import com.nextbreakpoint.nextfractal.mandelbrot.renderer.RendererView;
import com.nextbreakpoint.nextfractal.render.RenderFactory;

public class ImageGenerator {
	private Renderer renderer;

	public ImageGenerator(ThreadFactory threadFactory, RenderFactory renderFactory, RendererTile tile) {
		renderer = new Renderer(threadFactory, renderFactory, tile);
	}

	public IntBuffer renderImage(MandelbrotSession session, MandelbrotData data) {
		IntBuffer pixels = IntBuffer.allocate(renderer.getWidth() * renderer.getHeight());
		try {
			Compiler compiler = new Compiler(session.getOutDir(), session.getPackageName(), session.getClassName() + "Generator");
			CompilerReport report = compiler.generateJavaSource(data.getSource());
			//TODO report errors
			CompilerBuilder<Orbit> orbitBuilder = compiler.compileOrbit(report);
			CompilerBuilder<Color> colorBuilder = compiler.compileColor(report);
			renderer.abortTasks();
			renderer.waitForTasks();
			double[] traslation = data.getTraslation();
			double[] rotation = data.getRotation();
			double[] scale = data.getScale();
			double[] constant = data.getConstant();
			boolean julia = data.isJulia();
			renderer.setOrbit(orbitBuilder.build());
			renderer.setColor(colorBuilder.build());
			renderer.init();
			RendererView view = new RendererView();
			view .setTraslation(new DoubleVector4D(traslation));
			view.setRotation(new DoubleVector4D(rotation));
			view.setScale(new DoubleVector4D(scale));
			view.setState(new IntegerVector4D(0, 0, 0, 0));
			view.setJulia(julia);
			view.setConstant(new Number(constant));
			renderer.setView(view);
			renderer.runTask();
			renderer.waitForTasks();
			renderer.getPixels(pixels);
		} catch (Exception e) {
			e.printStackTrace();//TODO display errors
		}
		return pixels;
	}

	public int getHeight() {
		return renderer.getWidth();
	}

	public int getWidth() {
		return renderer.getHeight();
	}
}