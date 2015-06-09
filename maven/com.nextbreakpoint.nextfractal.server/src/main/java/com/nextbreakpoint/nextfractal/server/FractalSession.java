package com.nextbreakpoint.nextfractal.server;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.nextbreakpoint.nextfractal.core.renderer.RendererSize;

@XmlRootElement
public class FractalSession {
	private static final int BORDER_SIZE = 0;
	private final RendererSize size = new RendererSize(512, 512);
	private final int tileSize = 64;
	private final String pluginId = "Mandelbrot";
	private final float quality = 1;
	private final float frameRate = 0;
	private List<RemoteJob> jobs;

	public FractalSession() {
		createJobs(0);
	}

	public List<RemoteJob> getJobs() {
		return jobs;
	}

	private void createJobs(int frameNumber) {
		jobs = new ArrayList<RemoteJob>();
		final int frameWidth = size.getWidth();
		final int frameHeight = size.getHeight();
		final int nx = frameWidth / tileSize;
		final int ny = frameHeight / tileSize;
		final int rx = frameWidth - tileSize * nx;
		final int ry = frameHeight - tileSize * ny;
		if ((nx > 0) && (ny > 0)) {
			for (int tx = 0; tx < nx; tx++) {
				for (int ty = 0; ty < ny; ty++) {
					int tileOffsetX = tileSize * tx;
					int tileOffsetY = tileSize * ty;
					jobs.add(createJob(createProfile(frameNumber, frameWidth, frameHeight, tileOffsetX, tileOffsetY)));
				}
			}
		}
		if (rx > 0) {
			for (int ty = 0; ty < ny; ty++) {
				int tileOffsetX = tileSize * nx;
				int tileOffsetY = tileSize * ty;
				jobs.add(createJob(createProfile(frameNumber, frameWidth, frameHeight, tileOffsetX, tileOffsetY)));
			}
		}
		if (ry > 0) {
			for (int tx = 0; tx < nx; tx++) {
				int tileOffsetX = tileSize * tx;
				int tileOffsetY = tileSize * ny;
				jobs.add(createJob(createProfile(frameNumber, frameWidth, frameHeight, tileOffsetX, tileOffsetY)));
			}
		}
		if (rx > 0 && ry > 0) {
			int tileOffsetX = tileSize * nx;
			int tileOffsetY = tileSize * ny;
			jobs.add(createJob(createProfile(frameNumber, frameWidth, frameHeight, tileOffsetX, tileOffsetY)));
		}
	}

	private RemoteProfile createProfile(int frameNumber, final int frameWidth, final int frameHeight, int tileOffsetX, int tileOffsetY) {
		final RemoteProfile profile = new RemoteProfile();
		profile.setPluginId(pluginId);
		profile.setQuality(quality);
		profile.setFrameNumber(frameNumber);
		profile.setFrameRate(frameRate);
		profile.setFrameWidth(frameWidth);
		profile.setFrameHeight(frameHeight);
		profile.setTileWidth(tileSize);
		profile.setTileHeight(tileSize);
		profile.setTileOffsetX(tileOffsetX);
		profile.setTileOffsetY(tileOffsetY);
		profile.setBorderWidth(BORDER_SIZE);
		profile.setBorderHeight(BORDER_SIZE);
		return profile;
	}

	private RemoteJob createJob(RemoteProfile profile) {
		return new RemoteJob(profile);
	}

	public int getTileSize() {
		return tileSize;
	}

	public int getJobsCount() {
		return jobs.size();
	}
}
