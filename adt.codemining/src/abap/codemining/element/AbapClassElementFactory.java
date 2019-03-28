package abap.codemining.element;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureElement;

import abap.codemining.element.domain.AbapClassBody;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.FeatureFacade;
import abap.codemining.label.IMiningLabelBuilder;
import abap.codemining.label.MethodBodyMiningLabelBuilder;
import abap.codemining.label.StandardReferenceLabelBuilder;

public class AbapClassElementFactory {

	private static final String OBJECT_CLASS_TYPE = "CLAS/OC";
	private static final String INTERFACE_CLASS_TYPE = "INTF/OI";
	private static final String INTERFACE_METHOD_TYPE = "INTF/IO";
	private static final String OBJECT_CLASS_TYPE_LOCAL = "CLAS/OCL";
	private static final String OBJECT_CLASS_ATTRIBUTE_TYPE = "CLAS/OA";
	private static final String OBJECT_CLASS_METHOD_TYPE = "CLAS/OM";
	private static final String OBJECT_CLASS_METHOD_TYPE_2 = "CLAS/OR";
	private static final String OBJECT_CLASS_TYPE_TYPE = "CLAS/OT";
	private static final String IMPLEMENTATION_IDENTIFIER = "implementationIdentifier";
	private static final String DEFINITION_IDENTIFIER = "definitionIdentifier";
	private final FeatureFacade featureFacade;

	public AbapClassElementFactory(FeatureFacade featureFacade) {
		this.featureFacade = featureFacade;
	}

	public IAbapElement create(IDocument doc, IObjectStructureElement childObjectStructureElement,
			ElementLinkInfo elementLinkInfo) throws InvalidElementLinkException, BadLocationException {
		IMiningLabelBuilder miningLabelBuilder;

		switch (elementLinkInfo.getType()) {
		case OBJECT_CLASS_METHOD_TYPE:
		case OBJECT_CLASS_METHOD_TYPE_2:
		case INTERFACE_METHOD_TYPE:
			switch (elementLinkInfo.getRelation()) {
			case IMPLEMENTATION_IDENTIFIER:
				miningLabelBuilder = new MethodBodyMiningLabelBuilder(
						featureFacade.getClassMiningFeature().isMethodImplementationReferenceEnabled(),
						featureFacade.getClassMiningFeature().isMethodImplementationSignatureEnabled());
				break;
			case DEFINITION_IDENTIFIER:
				miningLabelBuilder = new StandardReferenceLabelBuilder(
						featureFacade.getClassMiningFeature().isMethodDefinitionReferenceEnabled());
				break;
			default:
				miningLabelBuilder = new StandardReferenceLabelBuilder(
						featureFacade.getClassMiningFeature().isClassUnknownElementReferenceEnabled());
				break;
			}
			break;
		case OBJECT_CLASS_TYPE:
		case OBJECT_CLASS_TYPE_LOCAL:
		case INTERFACE_CLASS_TYPE:
			miningLabelBuilder = getDefaultMiningLabelBuilder(elementLinkInfo,
					featureFacade.getClassMiningFeature().isClassDefinitionReferenceEnabled(),
					featureFacade.getClassMiningFeature().isClassImplemenationReferenceEnabled());
			break;
		case OBJECT_CLASS_ATTRIBUTE_TYPE:
			miningLabelBuilder = getDefaultMiningLabelBuilder(elementLinkInfo,
					featureFacade.getClassMiningFeature().isClassAttributeReferenceEnabled(),
					featureFacade.getClassMiningFeature().isClassAttributeReferenceEnabled());
			break;
		case OBJECT_CLASS_TYPE_TYPE:
			miningLabelBuilder = getDefaultMiningLabelBuilder(elementLinkInfo,
					featureFacade.getClassMiningFeature().isClassTypeReferenceEnabled(),
					featureFacade.getClassMiningFeature().isClassTypeReferenceEnabled());
			break;
		default:
			miningLabelBuilder = null;
			break;
		}

		if (miningLabelBuilder != null) {
			return createAbapElement(doc, childObjectStructureElement, elementLinkInfo, miningLabelBuilder);
		} else {
			return null;
		}

	}

	private IMiningLabelBuilder getDefaultMiningLabelBuilder(ElementLinkInfo elementLinkInfo,
			boolean implementationReferenceActive, boolean definitionReferenceActive) {
		IMiningLabelBuilder miningLabelBuilder;
		switch (elementLinkInfo.getRelation()) {
		case IMPLEMENTATION_IDENTIFIER:
			miningLabelBuilder = new StandardReferenceLabelBuilder(implementationReferenceActive);
			break;
		case DEFINITION_IDENTIFIER:
			miningLabelBuilder = new StandardReferenceLabelBuilder(definitionReferenceActive);
			break;
		default:
			miningLabelBuilder = new StandardReferenceLabelBuilder(
					featureFacade.getClassMiningFeature().isClassUnknownElementReferenceEnabled());
			break;
		}
		return miningLabelBuilder;
	}

	private IAbapElement createAbapElement(IDocument doc, IObjectStructureElement childObjectStructureElement,
			ElementLinkInfo elementLinkInfo, IMiningLabelBuilder miningLabelBuilder)
			throws BadLocationException, InvalidElementLinkException {

		final int miningStartindex = doc.getLineLength(elementLinkInfo.getLinenumber());

		return new AbapClassBody(childObjectStructureElement.getName(), elementLinkInfo.getLinenumber(),
				doc.get(elementLinkInfo.getElementStartposition(), doc.getLineLength(elementLinkInfo.getLinenumber())),
				elementLinkInfo.getElementStartposition(), miningStartindex, miningLabelBuilder);
	}

}
