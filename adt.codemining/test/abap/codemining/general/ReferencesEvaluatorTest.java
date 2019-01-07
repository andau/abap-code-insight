package abap.codemining.general;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import com.sap.adt.ris.model.usagereferences.IUsageReferenceResult;
import com.sap.adt.ris.model.usagereferences.IUsageReferencedObject;
import com.sap.adt.ris.model.usagereferences.IUsageReferencedObjects;
import com.sap.adt.ris.search.usagereferences.IAdtRisUsageReferencesSearchService;
import com.sap.adt.tools.core.model.util.ServiceNotAvailableException;

public class ReferencesEvaluatorTest {
	IAdtRisUsageReferencesSearchService usageReferencesSearchService = Mockito
			.mock(IAdtRisUsageReferencesSearchService.class);

	private static final String TEST_URI = "test/uri";
	private final IProject project = Mockito.mock(IProject.class);
	private final URI uri = URI.create(TEST_URI);

	private final IUsageReferenceResult usageReferenceResult = Mockito.mock(IUsageReferenceResult.class);
	private final IUsageReferencedObjects referenceObjects = Mockito.mock(IUsageReferencedObjects.class);

	private final EList<IUsageReferencedObject> referencedObject = new BasicEList<>();

	@Test
	public void testStandard() throws ServiceNotAvailableException, IOException {
		Mockito.when(usageReferencesSearchService.search(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(usageReferenceResult);
		Mockito.when(usageReferenceResult.getReferencedObjects()).thenReturn(referenceObjects);
		Mockito.when(referenceObjects.getReferencedObject()).thenReturn(referencedObject);
		ReferencesEvaluator cut = new ReferencesEvaluator();
		Whitebox.setInternalState(cut, "usageReferencesSearchService", usageReferencesSearchService);
		String referenceResult = cut.getReferencesResult(uri);
		assertEquals("0 references", referenceResult);
	}

}
