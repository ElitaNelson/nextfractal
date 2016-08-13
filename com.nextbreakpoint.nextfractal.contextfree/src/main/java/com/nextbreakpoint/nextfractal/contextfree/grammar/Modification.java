/*
 * NextFractal 1.2.1
 * https://github.com/nextbreakpoint/nextfractal
 *
 * Copyright 2015-2016 Andrea Medeghini
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

import java.awt.geom.AffineTransform;

class Modification {
	private Rand64 rand64Seed = new Rand64();
	private AffineTransform transform = new AffineTransform();
	private AffineTransform1D transformZ = new AffineTransform1D();
	private AffineTransformTime transformTime = new AffineTransformTime();
	private HSBColor color;
	private HSBColor colorTarget;
	private int colorAssignment;

	public Rand64 getRand64Seed() {
		return rand64Seed;
	}

	public void setRand64Seed(Rand64 rand64Seed) {
		this.rand64Seed = rand64Seed;
	}

	public AffineTransform getTransform() {
		return transform;
	}

	public void setTransform(AffineTransform transform) {
		this.transform = transform;
	}

	public AffineTransform1D getTransformZ() {
		return transformZ;
	}

	public void setTransformZ(AffineTransform1D transformZ) {
		this.transformZ = transformZ;
	}

	public AffineTransformTime getTransformTime() {
		return transformTime;
	}

	public void setTransformTime(AffineTransformTime transformTime) {
		this.transformTime = transformTime;
	}

	public HSBColor color() {
		return color;
	}

	public void setColor(HSBColor color) {
		this.color = color;
	}

	public HSBColor colorTarget() {
		return colorTarget;
	}

	public void setColorTarget(HSBColor colorTarget) {
		this.colorTarget = colorTarget;
	}

	public int colorAssignment() {
		return colorAssignment;
	}

	public void setColorAssignment(int colorAssignment) {
		this.colorAssignment = colorAssignment;
	}

	public double getZ() {
		return transformZ.tz();
	}

	public double area() {
		return transform.getDeterminant();
	}

	public boolean isFinite() {
		return Double.isFinite(transform.getScaleX()) && Double.isFinite(transform.getScaleY()) && Double.isFinite(transform.getShearX()) && Double.isFinite(transform.getShearY()) && Double.isFinite(transform.getTranslateX()) && Double.isFinite(transform.getTranslateY());
	}

	public void setSeed(int seed) {
		rand64Seed.setSeed(seed);
	}

	public Modification concat(Modification modification) {
		transform.preConcatenate(modification.getTransform());
		transformZ.preConcatenate(modification.getTransformZ());
		transformTime.preConcatenate(modification.getTransformTime());
		HSBColor.adjust(color, colorTarget, modification.color(), modification.colorTarget(), modification.colorAssignment());
		rand64Seed.add(modification.getRand64Seed());
		return this;
	}

	public boolean merge(Modification modification) {
		transform.preConcatenate(modification.getTransform());
		transformZ.preConcatenate(modification.getTransformZ());
		transformTime.preConcatenate(modification.getTransformTime());

		HSBColor.adjust(color, colorTarget, modification.color(), modification.colorTarget(), modification.colorAssignment());

		rand64Seed.add(modification.getRand64Seed());

		// TODO rivedere

		boolean conflict = (colorAssignment & modification.colorAssignment) != 0 ||
				((modification.colorAssignment & EAssignmentType.HueMask.getType()) != 0 && color.hue() != 0.0) ||
				((colorAssignment & EAssignmentType.HueMask.getType()) != 0 && modification.color.hue() != 0.0) ||
				(color.bright() != 0.0 && modification.color.bright() != 0.0) ||
				(color.sat() != 0.0 && modification.color.sat() != 0.0) ||
				(color.alpha() != 0.0 && modification.color.alpha() != 0.0);

		colorTarget.addHue(modification.colorTarget.hue());
		colorTarget.addBright(modification.colorTarget.bright());
		colorTarget.addSat(modification.colorTarget.sat());
		colorTarget.addAlpha(modification.colorTarget.alpha());

		color.addHue(modification.color.hue());
		color.addBright(modification.color.bright());
		color.addSat(modification.color.sat());
		color.addAlpha(modification.color.alpha());

		colorAssignment |= modification.colorAssignment;

		return conflict;
	}
}
