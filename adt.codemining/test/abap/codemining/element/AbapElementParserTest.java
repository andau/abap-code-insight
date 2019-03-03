package abap.codemining.element;

import org.eclipse.jface.text.IDocument;
import org.junit.Before;
import org.mockito.Mockito;

import abap.codemining.feature.FeatureFacade;
import abap.codemining.sample.SampleDocumentContent;

public class AbapElementParserTest {

	AbapClassElementParser cut;
	IDocument document = Mockito.mock(IDocument.class);
	private SampleDocumentContent sampleDocumentContents;

	FeatureFacade featureFacade = Mockito.mock(FeatureFacade.class);

	@Before
	public void before() {
		cut = new AbapClassElementParser(featureFacade, null, null);
		sampleDocumentContents = new SampleDocumentContent();

	}

}
