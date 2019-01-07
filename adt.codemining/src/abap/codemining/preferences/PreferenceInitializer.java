package abap.codemining.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import abap.codemining.plugin.AbapCodeMiningPluginHelper;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

	private final AbapCodeMiningPluginHelper abapCodeMiningPluginHelper;

	public PreferenceInitializer() {
		abapCodeMiningPluginHelper = new AbapCodeMiningPluginHelper();
	}

	@Override
	public void initializeDefaultPreferences() {

		final IPreferenceStore store = abapCodeMiningPluginHelper.getPreferenceStore();
		store.setDefault(PreferenceConstants.SHOW_CLASS_HEADER_REFERENCE_COUNT, true);
		store.setDefault(PreferenceConstants.SHOW_CLASS_BODY_REFERENCE_COUNT, true);

		store.setDefault(PreferenceConstants.SHOW_METHOD_BODY_SIGNATURE, true);
		store.setDefault(PreferenceConstants.SHOW_METHOD_BODY_REFERENCE_COUNT, true);
	}

}
