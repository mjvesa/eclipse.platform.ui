package org.eclipse.ui.internal;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.*;
import org.eclipse.ui.actions.OpenPerspectiveMenu;

public class WindowPerspectiveMenu extends OpenPerspectiveMenu {

	/**
	 * Constructor for WindowPerspectiveMenu.
	 * @param window
	 * @param input
	 */
	public WindowPerspectiveMenu(IWorkbenchWindow window, IAdaptable input) {
		super(window, input);
	}

	/**
	 * Runs an action for a particular perspective. Opens the persepctive supplied
	 * in a new window or a new page depending on the workbench preference.
	 *
	 * @param desc the selected perspective
	 */
	protected void run(IPerspectiveDescriptor desc) {
		IPreferenceStore store = WorkbenchPlugin.getDefault().getPreferenceStore();
		boolean smart = 
			store.getBoolean(IPreferenceConstants.REUSE_PERSPECTIVES);
		if (smart) {
			runReplaceCurrent(desc);
		} else {
			super.run(desc);
		}
	}
	
	/**
	 * Runs an action for a particular perspective. Check for shift or control events
	 * to decide which event to run.
	 *
	 * @param desc the selected perspective
	 * @param event SelectionEvent - the event send along with the selection callback
	 */
	protected void run(IPerspectiveDescriptor desc, SelectionEvent event) {
		IPreferenceStore store = WorkbenchPlugin.getDefault().getPreferenceStore();
		boolean smart = 
			store.getBoolean(IPreferenceConstants.REUSE_PERSPECTIVES);
		if (smart) {
			runReplaceCurrent(desc);
		} else {
			super.run(desc, event);
		}
	}
}

