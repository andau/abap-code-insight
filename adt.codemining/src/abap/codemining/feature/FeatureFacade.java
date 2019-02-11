package abap.codemining.feature;

public class FeatureFacade {
	private final FeatureCreator featureCreator;

	private ClassMiningFeature classHeaderMiningFeature;
	private ClassMiningFeature classBodyMiningFeature;
	private MethodBodyMiningFeature methodBodyMiningFeature;

	private ReferenceMiningFeature cdsMiningFeature;

	private ReferenceMiningFeature fmMiningFeature;

	private ReferenceMiningFeature reportMiningFeature;

	private ReferenceMiningFeature interfaceMiningFeature;

	private TestReferencesFeature testReferenceFeature;

	private UpdateFeature updateFeature;

	private DebugCodeInsightFeature debugCodeInsightFeature;

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

	public ReferenceMiningFeature getCdsMiningFeature() {
		if (cdsMiningFeature == null) {
			cdsMiningFeature = featureCreator.createCdsMiningFeature();
		}
		return cdsMiningFeature;
	}

	public ReferenceMiningFeature getFunctionModuleMiningFeature() {
		if (fmMiningFeature == null) {
			fmMiningFeature = featureCreator.createFmMiningFeature();
		}
		return fmMiningFeature;
	}

	public ReferenceMiningFeature getReportMiningFeature() {
		if (reportMiningFeature == null) {
			reportMiningFeature = featureCreator.createReportMiningFeature();
		}
		return reportMiningFeature;
	}

	public ReferenceMiningFeature getInterfaceMiningFeature() {
		if (interfaceMiningFeature == null) {
			interfaceMiningFeature = featureCreator.createInterfaceMiningFeature();
		}
		return interfaceMiningFeature;
	}

	public TestReferencesFeature getTestReferenceFeature() {
		if (testReferenceFeature == null) {
			testReferenceFeature = featureCreator.createTestReferencesFeature();
		}
		return testReferenceFeature;
	}

	public DebugCodeInsightFeature getDebugCodeInsightFeature() {
		if (debugCodeInsightFeature == null) {
			debugCodeInsightFeature = featureCreator.createDebugCodeInsightFeature();
		}
		return debugCodeInsightFeature;
	}

	public UpdateFeature getPerformanceFeature() {
		if (updateFeature == null) {
			updateFeature = featureCreator.createPerformanceFeature();
		}
		return updateFeature;
	}

}
