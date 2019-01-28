package abap.codemining.plugin;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.ITextViewer;

public class AbapCodeInsightPluginHelper {

	public IPreferenceStore getPreferenceStore() {
		return AbapCodeInsightPlugin.getDefault().getPreferenceStore();
	}

	public ImageDescriptor getImageDescriptor(String imageString) {
		return AbapCodeInsightPlugin.getImageDescriptor(imageString);
	}

	public void reinitViewInReconcilers(ITextViewer viewer) {
		AbapCodeInsightPlugin.reinitViewInReconcilers(viewer);
	}

}
