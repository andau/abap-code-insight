package abap.codemining.element;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import com.sap.adt.tools.abapsource.internal.sources.outline.AdtClientStructuralInfoService;
import com.sap.adt.tools.abapsource.internal.sources.outline.AdtClientStructuralInfoService.ClientStructuralInfoException;
import com.sap.adt.tools.abapsource.internal.sources.outline.IAdtClientStructuralInfoService;
import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureElement;
import com.sap.adt.tools.core.IAdtObjectReference;

import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.FeatureFacade;

public class Cds1ElementParser implements IAbapElementParser {

	FeatureFacade featureFacade;
	IFile file;
	IAdtObjectReference adtObjectReference;
	IDocument document;

	AbapClassElementParserFactory abapClassElementParserFactory;

	public Cds1ElementParser(FeatureFacade featureFacade, IAdtObjectReference adtObjectReference, IFile file) {
		this.featureFacade = featureFacade;
		this.adtObjectReference = adtObjectReference;
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
			final IObjectStructureElement objectStructureElement = adtClientStructuralInfoService
					.calculateStructureElement(file, doc.get(), null);

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
