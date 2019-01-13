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

	public ReconcilerFeature createReconcilerFeature() {
		initPrefs();
		return new ReconcilerFeature(prefs.getBoolean(PreferenceConstants.UPDATE_CHANGED_EDITORS));
	}

	public CdsMiningFeature createCdsMiningFeature() {
	initPrefs(); 
	//TODO preferences 
	return new CdsMiningFeature(true); 
	}

}
