package abap.codemining.feature;

import org.eclipse.jface.preference.IPreferenceStore;

import abap.codemining.plugin.AbapCodeMiningPluginHelper;
import abap.codemining.preferences.PreferenceConstants;

public class FeatureCreator {

	private IPreferenceStore prefs;
	AbapCodeMiningPluginHelper abapCodeMiningPluginHelper;

	public FeatureCreator() {
		abapCodeMiningPluginHelper = new AbapCodeMiningPluginHelper();
	}

	private void initPrefs() {
		prefs = (prefs == null) ? abapCodeMiningPluginHelper.getPreferenceStore() : prefs;
	}

	public ClassMiningFeature createClassHeaderMiningFeature() {
		initPrefs();
		return new ClassMiningFeature(prefs.getBoolean(PreferenceConstants.SHOW_CLASS_HEADER_REFERENCE_COUNT));
	}

	public MethodBodyMiningFeature createMethodBodyMiningFeature() {
		initPrefs();
		return new MethodBodyMiningFeature(prefs.getBoolean(PreferenceConstants.SHOW_METHOD_BODY_REFERENCE_COUNT),
				prefs.getBoolean(PreferenceConstants.SHOW_METHOD_BODY_SIGNATURE));
	}

	public ClassMiningFeature createClassBodyMiningFeature() {
		initPrefs();
		return new ClassMiningFeature(prefs.getBoolean(PreferenceConstants.SHOW_CLASS_BODY_REFERENCE_COUNT));
	}

	public ReferenceMiningFeature createCdsMiningFeature() {
		initPrefs();
		return new ReferenceMiningFeature(prefs.getBoolean(PreferenceConstants.SHOW_CDS_HEADER_REFERENCE_COUNT));
	}

	public ReferenceMiningFeature createFmMiningFeature() {
		initPrefs();
		return new ReferenceMiningFeature(prefs.getBoolean(PreferenceConstants.SHOW_FM_HEADER_REFERENCE_COUNT));
	}

	public ReferenceMiningFeature createReportMiningFeature() {
		initPrefs();
		return new ReferenceMiningFeature(prefs.getBoolean(PreferenceConstants.SHOW_REPORT_HEADER_REFERENCE_COUNT));
	}

	public ReferenceMiningFeature createInterfaceMiningFeature() {
		initPrefs();
		return new ReferenceMiningFeature(prefs.getBoolean(PreferenceConstants.SHOW_INTERFACE_HEADER_REFERENCE_COUNT));
	}

	public TestReferencesFeature createTestReferencesFeature() {
		initPrefs();
		return new TestReferencesFeature(prefs.getBoolean(PreferenceConstants.SHOW_TEST_REFERENCE_COUNT_SEPARATELY));
	}

	public UpdateFeature createUpdateFeature() {
		initPrefs();
		final boolean activated = prefs.getBoolean(PreferenceConstants.UPDATE_CHANGED_EDITORS);
		return new UpdateFeature(activated);
	}

}
