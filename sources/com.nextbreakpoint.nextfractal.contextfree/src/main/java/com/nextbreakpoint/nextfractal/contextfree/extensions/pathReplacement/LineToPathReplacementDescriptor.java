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
package com.nextbreakpoint.nextfractal.contextfree.extensions.pathReplacement;

import com.nextbreakpoint.nextfractal.contextfree.extensionPoints.pathReplacement.PathReplacementExtensionDescriptor;

/**
 * @author Andrea Medeghini
 */
public class LineToPathReplacementDescriptor extends PathReplacementExtensionDescriptor {
	/**
	 * Returns the extensionId.
	 * 
	 * @return the extensionId.
	 */
	@Override
	public String getExtensionId() {
		return "contextfree.path.replacement.operation.lineto";
	}

	/**
	 * Returns the extensionName.
	 * 
	 * @return the extensionName.
	 */
	@Override
	public String getExtensionName() {
		return "LineTo Replacement";
	}

	/**
	 * Returns the extensionRuntimeClass.
	 * 
	 * @return the extensionRuntimeClass.
	 */
	@Override
	public LineToPathReplacementRuntime getExtensionRuntime() {
		return new LineToPathReplacementRuntime();
	}

	/**
	 * Returns the extensionConfigClass.
	 * 
	 * @return the extensionConfigClass.
	 */
	@Override
	public LineToPathReplacementConfig getExtensionConfig() {
		return new LineToPathReplacementConfig();
	}
}
