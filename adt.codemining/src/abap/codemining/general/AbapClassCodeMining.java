package abap.codemining.general;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.jface.text.codemining.ICodeMiningProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.abapsource.internal.sources.codeelementinformation.ICodeElement;
import com.sap.adt.tools.abapsource.sources.AdtSourceServicesFactory;
import com.sap.adt.tools.abapsource.sources.IAdtSourceServicesFactory;
import com.sap.adt.tools.abapsource.sources.codeelementinformation.ICodeElementInformationBackendService;
import com.sap.adt.tools.core.IAdtObjectReference;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.method.AbapMethodBody;
import abap.codemining.method.AbapMethodDefinitionExtractor;
import abap.codemining.method.AbapMethodInformation;

public class AbapClassCodeMining {

	public void evaluateCodeMinings(List<ICodeMining> minings, ITextEditor textEditor, ITextViewer viewer, ICodeMiningProvider provider,
			IDocument doc) throws URISyntaxException {

		AbapMethodDefinitionExtractor abapMethodDefinitionExtractor = new AbapMethodDefinitionExtractor();

		AbapMethodInformation methodInformation = abapMethodDefinitionExtractor.getMethodInformation(doc);
		
		IProject project = getProject(textEditor);
		IAbapProject abapProject = project.getAdapter(IAbapProject.class); 
		String destination = abapProject.getDestinationData().getId(); 
		
		IEditorInput editorInput = textEditor.getEditorInput(); 
		IFile file = ((IFileEditorInput) editorInput).getFile(); 
		
		
		IAdtObjectReference adtObject = (IAdtObjectReference) Adapters.adapt((Object) file, IAdtObjectReference.class);

		
		for (AbapMethodBody methodBody : methodInformation.getAbapMethodBodies()) {
			try {
				URI uri = adtObject.getUri(); 
				URI completeUri = new URI(uri.toString() + "#start=" + (methodBody.getLinenumber()+1) + ",9");

		        String methodLabel = buildMethodLabel(destination, completeUri, viewer.getDocument().get());
				minings.add(new AbapMethodHeaderCodeMining(methodBody.getLinenumber(), viewer.getDocument(), provider,
						methodLabel));
			} catch (BadLocationException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String buildMethodLabel(String destination, URI uri, String doc) {
		return getCodeCompletion(destination, uri, doc); 
	}
	
	
	public String getCodeCompletionTestCall() 
	{
		URI uri = null; 
		String arg1 = null; 

		return getCodeCompletion("", uri, arg1); 
	}
	
	private String getCodeCompletion(String destination, URI uri, String arg1) 
	{
	IAdtSourceServicesFactory adtSourceServicesFactory = AdtSourceServicesFactory.createInstance(); 
	try {
		ICodeElementInformationBackendService codeCompletionService = adtSourceServicesFactory.createCodeElementInformationService(destination );
				
		IProgressMonitor monitor = new NullProgressMonitor();
		ICodeElement codeElement = (ICodeElement)codeCompletionService.getCodeElementInformation(uri, arg1, monitor); 
        return codeElement.getProperty("visibility").getValue();  
	} catch (OutOfSessionsException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	return "-1"; 
}

	public static IProject getProject(IEditorPart editorPart) {
		IProject currentProject = null;

		if (editorPart != null) {
			IEditorInput input = editorPart.getEditorInput();
			currentProject = input.getAdapter(IProject.class);
			if (currentProject == null) {
				IResource resource = input.getAdapter(IResource.class);
				if (resource != null) {
					currentProject = resource.getProject();
				}
			}
		}

		return currentProject;

	}

}