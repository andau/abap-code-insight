package abap.codemining.general;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.jface.text.codemining.ICodeMiningProvider;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.ui.texteditor.ITextEditor;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.core.IAdtObjectReference;
import com.sap.adt.tools.core.model.util.ServiceNotAvailableException;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.editor.EditorFacade;
import abap.codemining.element.IAbapElement;
import abap.codemining.method.AbapMethodDefinitionExtractor;
import abap.codemining.method.AbapMethodInformation;
import abap.codemining.utils.AdtObjectUriCreator;

public class AbapEditorCodeMining {

	private final EditorFacade textEditorFacade;
	private final AbapCodeMiningCreator abapCodeMiningCreator;
	private final MiningLabelBuilder miningLabelBuilder;

	public AbapEditorCodeMining(ITextEditor textEditor) {
		textEditorFacade = new EditorFacade(textEditor);
		this.abapCodeMiningCreator = new AbapCodeMiningCreator();
		miningLabelBuilder = new MiningLabelBuilder();
	}

	public void evaluateCodeMinings(List<ICodeMining> minings, ICodeMiningProvider provider) {

		IDocument doc = textEditorFacade.getDocument();
		AbapMethodDefinitionExtractor abapMethodDefinitionExtractor = new AbapMethodDefinitionExtractor();
		AbapMethodInformation methodInformation = abapMethodDefinitionExtractor.getMethodInformation(doc);

		IAbapProject abapProject = textEditorFacade.getAbapProject();
		IAdtObjectReference adtObject = textEditorFacade.getAdtObject();

		for (IAbapElement abapElement : methodInformation.getAbapElements()) {
			try {

				URI uri = createUriForMethodBody(adtObject, abapElement);
				String miningLabel = buildMiningLabel(abapProject, uri, doc.get());
				String referencesLabel = buildMiningReferencesLabel(abapProject, uri, doc.get());
				
				ISearchQuery usageReferencesQuery = abapCodeMiningCreator
						.createUsageReferencQuery(abapProject.getProject(), uri);

				minings.add(abapCodeMiningCreator.create(abapElement.getLinenumber() - 1, doc, provider, referencesLabel,
						usageReferencesQuery));
				minings.add(abapCodeMiningCreator.create(abapElement.getLinenumber() - 1, doc, provider, miningLabel,
						usageReferencesQuery));

			} catch (BadLocationException | URISyntaxException | OutOfSessionsException | ServiceNotAvailableException
					| IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private URI createUriForMethodBody(IAdtObjectReference adtObject, IAbapElement abapElement)
			throws URISyntaxException {
		AdtObjectUriCreator adtObjectUriCreator = new AdtObjectUriCreator(adtObject);
		return adtObjectUriCreator.createUriForLine(abapElement.getLinenumber(), abapElement.getStartindex());

	}

	private String buildMiningReferencesLabel(IAbapProject abapProject, URI uri, String doc)
			throws OutOfSessionsException, ServiceNotAvailableException, IOException {

		return miningLabelBuilder.buildReferencesLabel(abapProject, uri, doc);

	}
	
	private String buildMiningLabel(IAbapProject abapProject, URI uri, String doc)
			throws OutOfSessionsException, ServiceNotAvailableException, IOException {

		return miningLabelBuilder.build(abapProject, uri, doc);

	}

}