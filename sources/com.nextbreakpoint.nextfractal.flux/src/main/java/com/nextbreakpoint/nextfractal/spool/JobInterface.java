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
package com.nextbreakpoint.nextfractal.spool;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author Andrea Medeghini
 */
public interface JobInterface {
	/**
	 * 
	 */
	public static final int MAX_FRAMES = 50;

	/**
	 * @return
	 */
	public String getJobId();

	/**
	 * @return the frame number
	 */
	public int getFrameNumber();

	/**
	 * @return the lastUpdate
	 */
	public long getLastUpdate();

	/**
	 * 
	 */
	public void reset();

	/**
	 * 
	 */
	public void start();

	/**
	 * 
	 */
	public void stop();

	/**
	 * 
	 */
	public void abort();

	/**
	 * 
	 */
	public void dispose();

	/**
	 * @return
	 */
	public boolean isStarted();

	/**
	 * @return
	 */
	public boolean isAborted();

	/**
	 * @return
	 */
	public boolean isTerminated();

	/**
	 * @param profile
	 */
	public void setJobProfile(JobProfile profile);

	/**
	 * @return
	 */
	public JobProfile getJobProfile();

	/**
	 * @return the first frame number
	 */
	public int getFirstFrameNumber();

	/**
	 * @param frameNumber
	 */
	public void setFirstFrameNumber(int frameNumber);

	/**
	 * @return
	 */
	public int getTotalFrames();

	/**
	 * @param jobData
	 */
	public void setJobData(byte[] jobData);

	/**
	 * @return
	 */
	public byte[] getJobData();

	/**
	 * @return
	 * @throws IOException
	 */
	public RandomAccessFile getRAF() throws IOException;
}
