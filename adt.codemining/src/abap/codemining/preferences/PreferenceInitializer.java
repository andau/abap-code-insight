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
		store.setDefault(PreferenceConstants.SHOW_CLASS_DEFINITION_REFERENCE_ENABLED, true);
		store.setDefault(PreferenceConstants.SHOW_CLASS_IMPLEMENTATION_REFERENCE_ENABLED, true);
		store.setDefault(PreferenceConstants.SHOW_CLASS_METHOD_DEFINITION_REFERENCE_ENABLED, true);
		store.setDefault(PreferenceConstants.SHOW_CLASS_METHOD_IMPLEMENTATION_REFERENCE_ENABLED, true);
		store.setDefault(PreferenceConstants.SHOW_CLASS_PROPERTIES_REFERENCE_ENABLED, true);
		store.setDefault(PreferenceConstants.SHOW_CLASS_TYPE_REFERENCE_ENABLED, true);
		store.setDefault(PreferenceConstants.SHOW_CLASS_UNKNOWN_REFERENCE_ENABLED, true);

		store.setDefault(PreferenceConstants.SHOW_CLASS_METHOD_IMPLEMENTATION_SIGNATURE, true);

		store.setDefault(PreferenceConstants.SHOW_INTERFACE_HEADER_REFERENCE_COUNT, true);

		store.setDefault(PreferenceConstants.SHOW_CDS_HEADER_REFERENCE_COUNT, true);
		store.setDefault(PreferenceConstants.SHOW_FM_HEADER_REFERENCE_COUNT, true);
		store.setDefault(PreferenceConstants.SHOW_REPORT_HEADER_REFERENCE_COUNT, true);
		store.setDefault(PreferenceConstants.SHOW_TEST_REFERENCE_COUNT_SEPARATELY, true);
		store.setDefault(PreferenceConstants.UPDATE_CHANGED_EDITORS, true);
		store.setDefault(PreferenceConstants.OMIT_CODE_INSIGHT_FOR_MORE_LINES_THAN, 5000);
		store.setDefault(PreferenceConstants.SHOW_DEBUG_VARIABLE_VALUES, false);

	}

}
