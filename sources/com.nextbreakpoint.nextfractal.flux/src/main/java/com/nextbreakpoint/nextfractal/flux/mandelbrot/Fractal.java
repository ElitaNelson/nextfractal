package com.nextbreakpoint.nextfractal.flux.mandelbrot;

import java.util.HashMap;
import java.util.Map;

public abstract class Fractal {
	private Map<String, Variable> vars = new HashMap<>();
	protected Number x = new Number(0,0);
	protected Number w = new Number(0,0);
	protected Number z = new Number(0,0);
	protected Number n = new Number(0);
	protected Number c = new Number(0);
	protected float[] color = new float[] { 1f, 0f, 0f, 0f };

	public Fractal() {
	}

	public void setX(Number x) {
		this.x = x;
	}

	public void setW(Number w) {
		this.w = w;
	}

	public Number getX() {
		return x;
	}

	public Number getW() {
		return w;
	}

	public Number getZ() {
		return z;
	}

	public Number getN() {
		return n;
	}

	public Number getC() {
		return c;
	}

	protected Number number(int n) {
		return new Number(n, 0);
	}
	
	protected Number number(double r) {
		return new Number(r, 0);
	}
	
	protected Number number(double r, double i) {
		return new Number(r, i);
	}
	
	protected Number opAdd(Number a, Number b) {
		return new Number(a.r() + b.r(), a.i() + b.i());
	}
	
	protected Number opSub(Number a, Number b) {
		return new Number(a.r() - b.r(), a.i() - b.i());
	}
	
	protected Number opMul(Number a, Number b) {
		return new Number(a.r() * b.r() - a.i() * b.i(), a.r() * b.i() + a.r() * b.i());
	}
	
	protected Number opAdd(Number a, double b) {
		return new Number(a.r() + b, a.i());
	}
	
	protected Number opSub(Number a, double b) {
		return new Number(a.r() - b, a.i());
	}
	
	protected Number opMul(Number a, double b) {
		return new Number(a.r() * b, a.i() * b);
	}

	protected Number opDiv(Number a, double b) {
		return new Number(a.r() / b, a.i() / b);
	}
	
	protected Number opAdd(double a, Number b) {
		return new Number(a + b.r(), +b.i());
	}
	
	protected Number opSub(double a, Number b) {
		return new Number(a - b.r(), -b.i());
	}
	
	protected Number opMul(double a, Number b) {
		return new Number(a * b.r(), a * b.i());
	}

	protected Number opAdd(double a, double b) {
		return new Number(a + b, 0);
	}
	
	protected Number opSub(double a, double b) {
		return new Number(a - b, 0);
	}
	
	protected Number opMul(double a, double b) {
		return new Number(a * b, 0);
	}

	protected Number opPow(double a, double b) {
		return new Number(Math.pow(a, b), 0);
	}

	protected Number opNeg(Number a) {
		return new Number(-a.r(), -a.i());
	}
	
	protected Number opPos(Number a) {
		return new Number(+a.r(), +a.i());
	}

	protected double funcMod(double x) {
		return Math.abs(x);
	}

	protected double funcPha(double x) {
		return 0;
	}

	protected double funcAcos(double x) {
		return Math.acos(x);
	}

	protected double funcAsin(double x) {
		return Math.asin(x);
	}

	protected double funcAtan(double x) {
		return Math.atan(x);
	}

	protected double funcCos(double x) {
		return Math.cos(x);
	}
	
	protected double funcSin(double x) {
		return Math.sin(x);
	}

	protected double funTan(double x) {
		return Math.tan(x);
	}

	protected double funExp(double x) {
		return Math.exp(x);
	}

	protected double funLog(double x) {
		return Math.log(x);
	}

	protected double funcSqrt(double x) {
		return Math.sqrt(x);
	}

	protected double funcPow(double x, double y) {
		return Math.pow(x, y);
	}

	protected double funHypot(double x, double y) {
		return Math.hypot(x, y);
	}

	protected double funAtan2(double x, double y) {
		return Math.atan2(x, y);
	}

	protected double funcMod(Number x) {
		return Math.hypot(x.r(), x.i());
	}
	
	protected double funcPha(Number x) {
		return Math.atan2(x.i(), x.r());
	}
	
	protected Number funcSin(Number x) {
		return new Number(Math.sin(x.r())*Math.cosh(x.i()), +Math.cos(x.r())*Math.sinh(x.i()));
	}
	
	protected Number funcCos(Number x) {
		return new Number(Math.cos(x.r())*Math.cosh(x.i()), -Math.sin(x.r())*Math.sinh(x.i()));
	}
	
	protected Number funcTan(Number x) {
		double d = Math.pow(Math.cos(x.r()), 2) + Math.pow(Math.sinh(x.i()), 2);
		return new Number((Math.sin(x.r())*Math.cos(x.r())) / d, (Math.sinh(x.i())*Math.cosh(x.i())) / d);
	}

	protected Number funcExp(Number x) {
		double d = Math.exp(x.r());
		return new Number(d*Math.cos(x.i()), d*Math.sin(x.i()));
	}

	protected Number funcPow(Number x, double y) {
		double d = Math.pow(Math.hypot(x.r(), x.i()), y);
		return new Number(d*Math.cos(x.r()*y), d*Math.sin(x.i()*y));
	}

	protected Number funcSqrt(Number x) {
		double d = Math.sqrt(Math.hypot(x.r(), x.i()));
		return new Number(d*Math.cos(x.r()*0.5), d*Math.sin(x.i()*0.5));
	}

	protected double funcReal(Number n) {
		return n.r();
	}
	
	protected void registerVar(String name, Variable var) {
		vars.put(name, var);
	}

	public Variable getVar(String name) {
		return vars.get(name);
	}

	protected Trap trap(Number center) {
		return new Trap(center);
	}

	protected Palette palette(int length) {
		return new Palette(length);
	}

	protected PaletteElement element(int beginIndex, int endIndex, float[] beginColor, float[] endColor, PaletteExpression expression) {
		return new PaletteElement(beginIndex, endIndex, beginColor, endColor, expression);
	}
	
	protected float[] color(double x) {
		return new float[] { 1f, (float)x, (float)x, (float)x };
	}
	
	protected float[] color(double r, double g, double b) {
		return new float[] { 1f, (float)r, (float)g, (float)b };
	}
	
	protected float[] color(double a, double r, double g, double b) {
		return new float[] { (float)a, (float)r, (float)g, (float)b };
	}
	
	protected float[] addColor(double opacity, float[] color) {
		for (int i = 0; i < 4; i++) {
			this.color[i] = (float)Math.min(1, this.color[i] + Math.max(0, color[i]) * opacity);
		}
		return color;
	}
	
	public float[] color() {
		return color;
	}
	
	public abstract void compute();
}