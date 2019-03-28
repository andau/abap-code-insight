package abap.codemining.feature;

public class FeatureFacade {
	private final FeatureCreator featureCreator;

	private ClassMiningFeature classMiningFeature;

	private ReferenceMiningFeature cdsMiningFeature;

	private ReferenceMiningFeature fmMiningFeature;

	private ReferenceMiningFeature reportMiningFeature;

	private ReferenceMiningFeature interfaceMiningFeature;

	private ReferenceMiningFeature structureMiningFeature;

	private TestReferencesFeature testReferenceFeature;

	private PerformanceFeature performanceFeature;

	private DebugCodeInsightFeature debugCodeInsightFeature;

	public FeatureFacade() {
		featureCreator = new FeatureCreator();
	}

	public ClassMiningFeature getClassMiningFeature() {
		if (classMiningFeature == null) {
			classMiningFeature = featureCreator.createClassMiningFeature();
		}
		return classMiningFeature;
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

	public ReferenceMiningFeature getStructureFeature() {
		if (structureMiningFeature == null) {
			structureMiningFeature = featureCreator.createStructureMiningFeature();
		}
		return structureMiningFeature;
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

	public PerformanceFeature getPerformanceFeature() {
		if (performanceFeature == null) {
			performanceFeature = featureCreator.createPerformanceFeature();
		}
		return performanceFeature;
	}

}
