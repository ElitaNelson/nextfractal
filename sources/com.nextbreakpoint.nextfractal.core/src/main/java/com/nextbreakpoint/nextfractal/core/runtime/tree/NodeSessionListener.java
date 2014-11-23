/**
 * 
 */
package com.nextbreakpoint.nextfractal.core.runtime.tree;

/**
 * @author Andrea Medeghini
 */
public interface NodeSessionListener {
	public void fireSessionChanged();

	public void fireSessionAccepted();

	public void fireSessionCancelled();
}