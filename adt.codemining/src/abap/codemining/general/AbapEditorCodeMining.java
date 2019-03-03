package abap.codemining.general;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.jface.text.codemining.ICodeMiningProvider;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.ui.texteditor.ITextEditor;

import com.sap.adt.communication.resources.ResourceException;
import com.sap.adt.tools.core.IAdtObjectReference;
import com.sap.adt.tools.core.model.util.ServiceNotAvailableException;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.editor.EditorFacade;
import abap.codemining.element.AbapElementInformation;
import abap.codemining.element.IAbapElementParser;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.label.MiningLabelBuildingException;
import abap.codemining.utils.AdtObjectUriCreator;

public class AbapEditorCodeMining {

	private final EditorFacade textEditorFacade;
	private final AbapCodeMiningCreator abapCodeMiningCreator;
	private final IAbapElementParser abapElementParser;
	private final ITextViewer viewer;

	public AbapEditorCodeMining(ITextEditor textEditor, ITextViewer viewer, IAbapElementParser abapElementParser) {
		this.viewer = viewer;
		textEditorFacade = new EditorFacade(textEditor);

		this.abapElementParser = abapElementParser;

		abapCodeMiningCreator = new AbapCodeMiningCreator();
	}

	public void evaluateCodeMinings(List<ICodeMining> minings, ICodeMiningProvider provider) {

		final IDocument doc = viewer.getDocument();
		final AbapElementInformation methodInformation = abapElementParser.getElementInformation(doc);

		final IAbapProject abapProject = textEditorFacade.getAbapProject();
		final IAdtObjectReference adtObject = textEditorFacade.getAdtObject();

		for (final IAbapElement abapElement : methodInformation.getAbapElements()) {
			try {

				final URI uri = createUriForMethodBody(adtObject, abapElement);

				addReferenceMiningIfActivated(minings, provider, doc, abapProject, abapElement, uri);
				addSignatureMiningIfActivated(minings, provider, doc, abapProject, abapElement, uri);

			} catch (BadLocationException | URISyntaxException | ServiceNotAvailableException | IOException
					| MiningLabelBuildingException | ResourceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void addSignatureMiningIfActivated(List<ICodeMining> minings, ICodeMiningProvider provider, IDocument doc,
			IAbapProject abapProject, IAbapElement abapElement, URI uri)
			throws MiningLabelBuildingException, BadLocationException {

		if (abapElement.getMiningLabelBuilder().showSignature()) {
			final String signatureLabel = abapElement.getMiningLabelBuilder().buildSignatureLabel(abapProject, uri,
					doc.get());
			minings.add(abapCodeMiningCreator.create(abapElement.getLinenumber() - 1, doc, provider, signatureLabel));
		}

	}

	private void addReferenceMiningIfActivated(List<ICodeMining> minings, ICodeMiningProvider provider, IDocument doc,
			IAbapProject abapProject, IAbapElement abapElement, URI uri)
			throws ServiceNotAvailableException, IOException, BadLocationException {

		if (abapElement.getMiningLabelBuilder().showRef()) {

			final String referencesLabel = abapElement.getMiningLabelBuilder().buildReferencesLabel(abapProject, uri,
					doc.get());
			final ISearchQuery usageReferencesQuery = abapCodeMiningCreator
					.createUsageReferencQuery(abapProject.getProject(), uri);

			minings.add(abapCodeMiningCreator.createRef(abapElement.getLinenumber() - 1, doc, provider, referencesLabel,
					usageReferencesQuery));
		}

	}

	private URI createUriForMethodBody(IAdtObjectReference adtObject, IAbapElement abapElement)
			throws URISyntaxException {
		final AdtObjectUriCreator adtObjectUriCreator = new AdtObjectUriCreator(adtObject);
		return adtObjectUriCreator.createUriForLine(abapElement.getLinenumber(), abapElement.getStartindex());

	}

}