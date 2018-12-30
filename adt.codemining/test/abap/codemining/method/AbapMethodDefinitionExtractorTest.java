package abap.codemining.method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.eclipse.jdt.experimental.ui.javaeditor.codemining.SampleDocumentContent;
import org.eclipse.jface.text.IDocument;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AbapMethodDefinitionExtractorTest {

	AbapMethodDefinitionExtractor abapMethodDefinitionExtractor;
	IDocument document = Mockito.mock(IDocument.class);
	private SampleDocumentContent sampleDocumentContents;

	@Before
	public void before() {
		abapMethodDefinitionExtractor = new AbapMethodDefinitionExtractor();
		sampleDocumentContents = new SampleDocumentContent();
	}

	@Test
	public void testGetMethodBodies() {
		Set<AbapMethodBody> methodBodies = abapMethodDefinitionExtractor
				.getMethodBodies(sampleDocumentContents.getOneSimpleMethodContent().toLowerCase());
		assertEquals(2, methodBodies.size());
		assertTrue(methodBodies.stream().anyMatch(item -> item.getMethodname().equals("samplemethod2")));
	}

}
