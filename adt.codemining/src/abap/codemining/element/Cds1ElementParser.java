package abap.codemining.element;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import com.sap.adt.tools.abapsource.internal.sources.outline.AdtClientStructuralInfoService;
import com.sap.adt.tools.abapsource.internal.sources.outline.AdtClientStructuralInfoService.ClientStructuralInfoException;
import com.sap.adt.tools.abapsource.internal.sources.outline.IAdtClientStructuralInfoService;
import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureElement;
import com.sap.adt.tools.abapsource.ui.AbapSourceUi;
import com.sap.adt.tools.abapsource.ui.internal.sources.outline.AdtStructuralInfoService;
import com.sap.adt.tools.abapsource.ui.sources.outline.AdtOutlineTreeContentProvider;
import com.sap.adt.tools.abapsource.ui.sources.outline.IAdtStructuralInfoService;
import com.sap.adt.tools.abapsource.ui.sources.outline.IAdtStructuralInfoService.EditorSource;
import com.sap.adt.tools.core.model.adtcore.IAdtObject;

import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.FeatureFacade;

public class Cds1ElementParser implements IAbapElementParser {

	FeatureFacade featureFacade;
	IFile file;
	IAdtObject adtObject;
	IDocument document;

	AbapClassElementParserFactory abapClassElementParserFactory;

	public Cds1ElementParser(FeatureFacade featureFacade, IAdtObject adtObject, IFile file) {
		this.featureFacade = featureFacade;
		this.adtObject = adtObject;
		this.file = file;
		this.document = null;
		this.abapClassElementParserFactory = new AbapClassElementParserFactory();
	}

	@Override
	public AbapElementInformation getElementInformation(IDocument doc) {
		final Set<IAbapElement> abapElements = new HashSet<>();

		try {

			/**
			 * final IAdtStructuralInfoService adtStructuralInfoService = new
			 * AdtStructuralInfoService();
			 *
			 * final IObjectStructureElement objectStructureElement =
			 * adtStructuralInfoService .getOrLoadObjectStructure(file, adtObject, null,
			 * true, new NullProgressMonitor());
			 **/

			final IAdtClientStructuralInfoService adtClientStructuralInfoService = new AdtClientStructuralInfoService();
			IObjectStructureElement objectStructureElement = adtClientStructuralInfoService
					.calculateStructureElement(file, doc.get(), null, adtObject);

			final IAdtStructuralInfoService adtStructuralInfoService = AbapSourceUi.getInstance()
					.getOrCreateStructuralInfoService();
			final AdtOutlineTreeContentProvider outlineTreeContentProvider = new AdtOutlineTreeContentProvider(
					adtStructuralInfoService);
			AdtStructuralInfoService.INJECTED_USE_CLIENT_SIDE_OUTLINE = true;
			final List<EditorSource> editorSources = Arrays.asList(new EditorSource(file, doc.get(), 0, null));
			objectStructureElement = adtStructuralInfoService.getOrLoadObjectStructure(file, adtObject, editorSources,
					false, new NullProgressMonitor());
			adtStructuralInfoService.mergeClientResultWithExistingOutline(adtObject);

			abapElements.addAll(
					abapClassElementParserFactory.createAbapElements(document, objectStructureElement, featureFacade));
			for (final IObjectStructureElement childObjectStructureElement : objectStructureElement.getChildren()) {
				abapElements.addAll(abapClassElementParserFactory.createAbapElements(document,
						childObjectStructureElement, featureFacade));
			}

		} catch (final BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final ClientStructuralInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new AbapElementInformation(abapElements);
	}

}
