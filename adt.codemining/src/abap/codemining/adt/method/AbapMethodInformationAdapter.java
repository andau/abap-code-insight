package abap.codemining.adt.method;

import java.util.List;

import com.sap.adt.tools.abapsource.internal.sources.codeelementinformation.ICodeElement;

import abap.codemining.adt.CodeElementWrapper;
import abap.codemining.adt.IAbapCodeElementInformation;
import abap.codemining.method.MethodParam;

public class AbapMethodInformationAdapter {

	public IAbapCodeElementInformation adapt(ICodeElement codeElement) {
		CodeElementWrapper codeElementWrapper = new CodeElementWrapper(codeElement);
		String visibility = codeElementWrapper.getVisibility();
		String level = codeElementWrapper.getLevel();
		String name = codeElement.getName().toLowerCase();

		MethodParam returningParameter = codeElementWrapper.getReturningParameter();
		List<MethodParam> impParameters = codeElementWrapper.getImportingParameters();
		List<MethodParam> expParameters = codeElementWrapper.getExportingParameters();

		return new AbapMethodInformation(visibility, level, name, returningParameter, impParameters,
				expParameters);
	}

}
