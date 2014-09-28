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
package com.nextbreakpoint.nextfractal.test.twister.common;

import org.junit.Test;

import com.nextbreakpoint.nextfractal.core.common.IntegerElement;
import com.nextbreakpoint.nextfractal.core.common.IntegerElementNode;
import com.nextbreakpoint.nextfractal.core.common.IntegerElementXMLExporter;
import com.nextbreakpoint.nextfractal.core.common.IntegerElementXMLImporter;
import com.nextbreakpoint.nextfractal.core.test.AbsractValueElementTest;
import com.nextbreakpoint.nextfractal.core.tree.Node;

/**
 * @author Andrea Medeghini
 */
public class IntegerElementTest extends AbsractValueElementTest<Integer, IntegerElement> {
	@Override
	protected Integer getFirstValue() {
		return new Integer(0);
	}

	@Override
	protected Integer getSecondValue() {
		return new Integer(1);
	}

	@Override
	protected IntegerElement createConfigElement(final Integer defaultValue) {
		final IntegerElement configElement = new IntegerElement(defaultValue);
		return configElement;
	}

	@Override
	protected Node createElementNode() {
		return new IntegerElementNode("value", getConfigElement()) {
		};
	}

	@Override
	protected IntegerElementXMLImporter createXMLImporter() {
		return new IntegerElementXMLImporter();
	}

	@Override
	protected IntegerElementXMLExporter createXMLExporter() {
		return new IntegerElementXMLExporter();
	}

	@Override
	@Test
	public void testSetValue() {
		super.testSetValue();
	}

	@Override
	@Test
	public void testNode() {
		super.testNode();
	}

	@Override
	@Test
	public void testClone() {
		super.testClone();
	}

	@Override
	@Test
	public void testSerialization() {
		super.testSerialization();
	}

	@Override
	@Test
	public void testXML() {
		super.testXML();
	}

	@Override
	@Test
	public void testActions() {
		super.testActions();
	}
}
