package abap.codemining.element.extractor;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import abap.codemining.element.domain.IAbapElement;

public class MethodBodyElementExtractorTest {

	MethodBodyElementExtractor cut;

	@Before
	public void before() {
		cut = new MethodBodyElementExtractor(null);
	}

	@Test
	public void testExtractFromLine() {
		final IAbapElement abapElement = cut.extractFromLine("method /IW/IF_SRV_RUNTIME~CREATE_ENTITY.", 1);
		assertNotNull(abapElement);
	}

}
