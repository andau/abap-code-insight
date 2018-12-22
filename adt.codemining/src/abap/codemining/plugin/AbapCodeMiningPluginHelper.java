package abap.codemining.plugin;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;

public class AbapCodeMiningPluginHelper {

	public IPreferenceStore getPreferenceStore() {
		return AbapCodeMiningPlugin.getDefault().getPreferenceStore();
	}

	public ImageDescriptor getImageDescriptor(String imageString) {
		return AbapCodeMiningPlugin.getImageDescriptor(imageString);
	}

}
