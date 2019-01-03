package abap.codemining.method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.eclipse.jdt.experimental.ui.javaeditor.codemining.SampleDocumentContent;
import org.eclipse.jface.text.IDocument;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import abap.codemining.element.IAbapElement;

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
		Set<IAbapElement> abapElements = abapMethodDefinitionExtractor
				.getMethodBodies(sampleDocumentContents.getOneSimpleMethodContent().toLowerCase());
		assertEquals(2, abapElements.size());
		assertTrue(abapElements.stream().anyMatch(item -> item.getElementname().equals("samplemethod2")));
	}

}
