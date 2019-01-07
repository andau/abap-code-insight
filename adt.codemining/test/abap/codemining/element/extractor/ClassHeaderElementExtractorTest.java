package abap.codemining.element.extractor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import abap.codemining.element.domain.IAbapElement;
import abap.codemining.element.extractor.ClassHeaderElementExtractor;
import abap.codemining.feature.ClassMiningFeature;

public class ClassHeaderElementExtractorTest {

	private static final String TESTCLASS_NAME = "testclass_name";
	ClassHeaderElementExtractor cut;
	private final ClassMiningFeature classHeaderMiningFeature = Mockito.mock(ClassMiningFeature.class);

	@Before
	public void before() {
		cut = new ClassHeaderElementExtractor(classHeaderMiningFeature);
	}

	@Test
	public void testNull() {
		IAbapElement abapElement = cut.extractFromLine("any", 1);
		assertNull(abapElement);
	}

	@Test
	public void testStandard() {
		IAbapElement abapElement = cut.extractFromLine("class " + TESTCLASS_NAME + " definition", 1);
		assertNotNull(abapElement);
		assertEquals(TESTCLASS_NAME, abapElement.getElementname());
	}

}
