package abap.codemining.general;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.jface.text.codemining.ICodeMiningProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.ris.model.usagereferences.IUsageReferenceRequest;
import com.sap.adt.ris.model.usagereferences.IUsageReferenceResult;
import com.sap.adt.ris.search.usagereferences.AdtRisUsageReferencesSearchServiceFactory;
import com.sap.adt.ris.search.usagereferences.IAdtRisUsageReferencesSearchService;
import com.sap.adt.tools.core.IAdtObjectReference;
import com.sap.adt.tools.core.model.util.ServiceNotAvailableException;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.adt.AbapCodeServiceFactory;
import abap.codemining.adt.ICodeElementInformation;
import abap.codemining.method.AbapMethodBody;
import abap.codemining.method.AbapMethodDefinitionExtractor;
import abap.codemining.method.AbapMethodInformation;
import abap.codemining.utils.AdtObjectUriCreator;
import abap.codemining.utils.EditorPartProjectAdapter;
import abap.codemining.utils.TextEditorUtil;

public class AbapClassCodeMining {

	private final AbapCodeServiceFactory abapCodeServiceFactory;

	public AbapClassCodeMining() {
		this.abapCodeServiceFactory = new AbapCodeServiceFactory();
	}

	public void evaluateCodeMinings(List<ICodeMining> minings, ITextEditor textEditor, ITextViewer viewer,
			ICodeMiningProvider provider, IDocument doc) {

		AbapMethodDefinitionExtractor abapMethodDefinitionExtractor = new AbapMethodDefinitionExtractor();
		AbapMethodInformation methodInformation = abapMethodDefinitionExtractor.getMethodInformation(doc);

		IAbapProject abapProject = getAbapProjectFromTextEditor(textEditor);
		IFile file = getFileFromEditorInput(textEditor);

		IAdtObjectReference adtObject = Adapters.adapt((Object) file, IAdtObjectReference.class);

		for (AbapMethodBody methodBody : methodInformation.getAbapMethodBodies()) {
			try {
				URI uri = createUriForMethodBody(adtObject, methodBody);
				String methodLabel = buildMethodLabel(abapProject, uri, viewer.getDocument().get());

				minings.add(new AbapMethodHeaderCodeMining(methodBody.getLinenumber(), viewer.getDocument(), provider,
						methodLabel));

			} catch (BadLocationException | URISyntaxException | OutOfSessionsException | ServiceNotAvailableException
					| IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private IFile getFileFromEditorInput(ITextEditor textEditor) {
		TextEditorUtil textEditorUtil = new TextEditorUtil(textEditor);
		return textEditorUtil.getFile();
	}

	private IAbapProject getAbapProjectFromTextEditor(ITextEditor textEditor) {
		EditorPartProjectAdapter adapter = new EditorPartProjectAdapter(textEditor);
		IProject project = adapter.getProject();
		IAbapProject abapProject = project.getAdapter(IAbapProject.class);
		return abapProject;
	}

	private URI createUriForMethodBody(IAdtObjectReference adtObject, AbapMethodBody methodBody)
			throws URISyntaxException {
		AdtObjectUriCreator adtObjectUriCreator = new AdtObjectUriCreator(adtObject);
		return adtObjectUriCreator.createUriForLine(methodBody.getLinenumber());

	}

	private String buildMethodLabel(IAbapProject abapProject, URI uri, String doc)
			throws OutOfSessionsException, ServiceNotAvailableException, IOException {
		ICodeElementInformation abapCodeElementInformation = abapCodeServiceFactory
				.createAbapCodeElementInformation(abapProject.getDestinationId());
		String visibility = abapCodeElementInformation.getVisibility(uri, doc);

		String references = computeReferences(abapProject.getProject(), uri);
		return references + visibility;
	}

	private String computeReferences(IProject project, URI uri) throws ServiceNotAvailableException, IOException {

		IAdtRisUsageReferencesSearchService usageReferencesSearchService = AdtRisUsageReferencesSearchServiceFactory
				.createUsageReferencesSearchService(project, new NullProgressMonitor());
		IUsageReferenceRequest var2 = null;
		IUsageReferenceResult usageReferenceResult = usageReferencesSearchService.search(uri, var2,
				new NullProgressMonitor());
		return Integer.toString(usageReferenceResult.getReferencedObjects().getReferencedObject().size());

	}

}