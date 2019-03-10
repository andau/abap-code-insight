package abap.codemining.element;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureElement;
import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureService;
import com.sap.adt.tools.core.model.adtcore.AdtVersionEnum;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.adt.AbapCodeServiceFactory;
import abap.codemining.element.domain.AbapClassBody;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.element.extractor.ElementLineInfoExtractor;
import abap.codemining.feature.FeatureFacade;
import abap.codemining.label.ClassMiningLabelBuilder;
import abap.codemining.label.MethodBodyMiningLabelBuilder;

public class AbapClassElementParserFactory {

	public IObjectStructureService createObjectStructureService(IAbapProject abapProject)
			throws OutOfSessionsException {
		final AbapCodeServiceFactory abapCodeServiceFactory = new AbapCodeServiceFactory();
		final IObjectStructureService objectStructureService = abapCodeServiceFactory
				.createObjectStructureService(abapProject.getDestinationId());
		return objectStructureService;
	}

	public IObjectStructureElement createObjectStructureElementClass(
			final IObjectStructureService objectStructureService, URI uri) {
		final URI baseUri = URI.create(uri.toString().replace("source/main", "objectstructure"));
		final IObjectStructureElement objectStructureElement = objectStructureService.getObjectStructureTree(baseUri,
				AdtVersionEnum.ACTIVE, true, true, new NullProgressMonitor());
		return objectStructureElement;
	}

	public IObjectStructureElement createObjectStructureElementCds(final IObjectStructureService objectStructureService,
			URI uri) {
		final URI baseUri = URI.create(uri.toString().replace("source/main", "objectstructure"));
		final IObjectStructureElement objectStructureElement = objectStructureService.getObjectStructureTree(baseUri,
				AdtVersionEnum.ACTIVE, true, true, new NullProgressMonitor());
		return objectStructureElement;
	}

	public IObjectStructureElement createObjectStructureElementFm(final IObjectStructureService objectStructureService,
			URI uri) {
		final URI baseUri = URI.create(uri.toString().replace("source/main", "objectstructure"));
		final IObjectStructureElement objectStructureElement = objectStructureService.getObjectStructureTree(baseUri,
				AdtVersionEnum.ACTIVE, true, true, new NullProgressMonitor());
		return objectStructureElement;
	}

	public Set<IAbapElement> createAbapElements(IDocument doc,
			final IObjectStructureElement childObjectStructureElement, FeatureFacade featureFacade)
			throws BadLocationException {
		final Set<IAbapElement> abapElements = new HashSet<>();

		final Set<ElementLinkInfo> elementLinkInfos = getElementLinkInfos(childObjectStructureElement);

		for (final ElementLinkInfo elementLinkInfo : elementLinkInfos) {
			int miningStartindex;
			try {
				miningStartindex = doc.getLineLength(elementLinkInfo.getLinenumber());
				final int elementStartindex = elementLinkInfo.getElementStartposition();
				if (elementLinkInfo.getType().equals("CLAS/OM")
						&& elementLinkInfo.getRelation().contentEquals("implementationIdentifier")) {
					abapElements.add(new AbapClassBody(childObjectStructureElement.getName(),
							elementLinkInfo.getLinenumber(), elementStartindex, miningStartindex,
							new MethodBodyMiningLabelBuilder(featureFacade.getMethodBodyMiningFeature())));
				} else {
					abapElements.add(new AbapClassBody(childObjectStructureElement.getName(),
							elementLinkInfo.getLinenumber(), elementStartindex, miningStartindex,
							new ClassMiningLabelBuilder(featureFacade.getClassHeaderMiningFeature())));
				}
			} catch (final InvalidElementLinkException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return abapElements;
	}

	private Set<ElementLinkInfo> getElementLinkInfos(IObjectStructureElement childObjectStructureElement) {

		final ElementLineInfoExtractor elementLineInfoExtractor = new ElementLineInfoExtractor();
		return elementLineInfoExtractor.extractElementLinkInfos(childObjectStructureElement);
	}

}
