package abap.codemining.adt.method;

import java.util.List;

import com.sap.adt.tools.abapsource.internal.sources.codeelementinformation.ICodeElement;

import abap.codemining.adt.CodeElementWrapper;
import abap.codemining.adt.IAbapCodeElementInformation;
import abap.codemining.method.MethodParam;

public class AbapMethodInformationAdapter {

	public IAbapCodeElementInformation adapt(ICodeElement codeElement) {
		final CodeElementWrapper codeElementWrapper = new CodeElementWrapper(codeElement);
		final String visibility = codeElementWrapper.getVisibility();
		final String level = codeElementWrapper.getLevel();
		final String name = codeElement.getName().toLowerCase();

		final MethodParam returningParameter = codeElementWrapper.getReturningParameter();
		final List<MethodParam> impParameters = codeElementWrapper.getImportingParameters();
		final List<MethodParam> expParameters = codeElementWrapper.getExportingParameters();
		final List<MethodParam> changeParameters = codeElementWrapper.getChangingParameters();

		return new AbapMethodInformation(visibility, level, name, returningParameter, impParameters, expParameters,
				changeParameters);
	}

}
