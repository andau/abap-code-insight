package abap.codemining.feature;

public class FeatureFacade {
	private final FeatureCreator featureCreator;

	private ClassMiningFeature classHeaderMiningFeature;
	private ClassMiningFeature classBodyMiningFeature;
	private MethodBodyMiningFeature methodBodyMiningFeature;

	private ReconcilerFeature reconcilerFeature;

	private CdsMiningFeature cdsMiningFeature;

	public FeatureFacade() {
		featureCreator = new FeatureCreator();
	}

	public ClassMiningFeature getClassHeaderMiningFeature() {
		if (classHeaderMiningFeature == null) {
			classHeaderMiningFeature = featureCreator.createClassHeaderMiningFeature();
		}
		return classHeaderMiningFeature;
	}

	public MethodBodyMiningFeature getMethodBodyMiningFeature() {

		if (methodBodyMiningFeature == null) {
			methodBodyMiningFeature = featureCreator.createMethodBodyMiningFeature();
		}

		return methodBodyMiningFeature;

	}

	public ClassMiningFeature getClassBodyMiningFeature() {

		if (classBodyMiningFeature == null) {
			classBodyMiningFeature = featureCreator.createClassBodyMiningFeature();
		}

		return classBodyMiningFeature;

	}

	public ReconcilerFeature getReconcilerFeature() {
		if (reconcilerFeature == null) {
			reconcilerFeature = featureCreator.createReconcilerFeature();
		}

		return reconcilerFeature;
	}

	public CdsMiningFeature getCdsMiningFeature() {
		if (cdsMiningFeature == null)  {
			cdsMiningFeature = featureCreator.createCdsMiningFeature(); 
		}
		return cdsMiningFeature; 
	}

}
