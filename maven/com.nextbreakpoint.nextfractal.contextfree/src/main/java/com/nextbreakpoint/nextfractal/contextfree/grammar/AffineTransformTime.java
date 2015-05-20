/*
 * NextFractal 1.0.5
 * https://github.com/nextbreakpoint/nextfractal
 *
 * Copyright 2015 Andrea Medeghini
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
package com.nextbreakpoint.nextfractal.contextfree.grammar;

public class AffineTransformTime {
	private double step;
	private double begin;
	private double end;
	
	public AffineTransformTime() {
		this(0, 0, 1);
	}
	
	private AffineTransformTime(double step, double begin, double end) {
		this.step = step;
		this.begin = begin;
		this.end = end;
	}

	public static AffineTransformTime getTranslateInstance(double begin ,double end) {
		return new AffineTransformTime(1.0, begin, end);
	}
	
	public static AffineTransformTime getScaleInstance(double step) {
		return new AffineTransformTime(step, 0.0, 0.0);
	}

	public void preConcatenate(AffineTransformTime t) {
		this.step = this.step * t.step;
		this.begin = this.begin * t.step + t.begin;
		this.end = this.end * t.step + t.end;
	}
}
