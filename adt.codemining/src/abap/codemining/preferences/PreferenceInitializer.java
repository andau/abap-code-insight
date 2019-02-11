package abap.codemining.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import abap.codemining.plugin.AbapCodeInsightPluginHelper;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

	private final AbapCodeInsightPluginHelper abapCodeMiningPluginHelper;

	public PreferenceInitializer() {
		abapCodeMiningPluginHelper = new AbapCodeInsightPluginHelper();
	}

	@Override
	public void initializeDefaultPreferences() {

		final IPreferenceStore store = abapCodeMiningPluginHelper.getPreferenceStore();
		store.setDefault(PreferenceConstants.SHOW_CLASS_HEADER_REFERENCE_COUNT, true);
		store.setDefault(PreferenceConstants.SHOW_CLASS_BODY_REFERENCE_COUNT, true);
		store.setDefault(PreferenceConstants.SHOW_INTERFACE_HEADER_REFERENCE_COUNT, true);

		store.setDefault(PreferenceConstants.SHOW_METHOD_BODY_SIGNATURE, true);
		store.setDefault(PreferenceConstants.SHOW_METHOD_BODY_REFERENCE_COUNT, true);

		store.setDefault(PreferenceConstants.SHOW_CDS_HEADER_REFERENCE_COUNT, true);
		store.setDefault(PreferenceConstants.SHOW_FM_HEADER_REFERENCE_COUNT, true);
		store.setDefault(PreferenceConstants.SHOW_REPORT_HEADER_REFERENCE_COUNT, true);
		store.setDefault(PreferenceConstants.SHOW_TEST_REFERENCE_COUNT_SEPARATELY, true);
		store.setDefault(PreferenceConstants.UPDATE_CHANGED_EDITORS, true);
		store.setDefault(PreferenceConstants.SHOW_DEBUG_VARIABLE_VALUES, true);

	}

}
