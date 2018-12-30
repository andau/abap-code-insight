package abap.codemining.plugin;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.codemining.CodeMiningReconciler;

public class AbapCodeMiningPluginHelper {

	public IPreferenceStore getPreferenceStore() {
		return AbapCodeMiningPlugin.getDefault().getPreferenceStore();
	}

	public ImageDescriptor getImageDescriptor(String imageString) {
		return AbapCodeMiningPlugin.getImageDescriptor(imageString);
	}

	public CodeMiningReconciler getCodeMiningReconciler() {
		return AbapCodeMiningPlugin.getCodeMiningReconciler();
	}

}
