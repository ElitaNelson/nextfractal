package com.nextbreakpoint.nextfractal.flux;


public interface FractalSession {
	/**
	 * @param listener
	 */
	public void addSessionListener(FractalSessionListener listener);

	/**
	 * @param listener
	 */
	public void removeSessionListener(FractalSessionListener listener);

	/**
	 * @param source
	 */
	public void setSource(String source);

	/**
	 * @return
	 */
	public String getSource();

	/**
	 * @param packageName
	 */
	public void setPackageName(String packageName);

	/**
	 * @return
	 */
	public String getPackageName();

	/**
	 * @param className
	 */
	public void setClassName(String className);

	/**
	 * @return
	 */
	public String getClassName();

	/**
	 * 
	 */
	public void terminate();
}