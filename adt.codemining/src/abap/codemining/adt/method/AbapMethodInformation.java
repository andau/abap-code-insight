package abap.codemining.adt.method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import abap.codemining.adt.IAbapCodeElementInformation;
import abap.codemining.method.MethodParam;
import abap.codemining.method.MethodParamType;
import abap.codemining.utils.StringUtils;

public class AbapMethodInformation implements IAbapCodeElementInformation {
	private final String visibility;
	private final String level;
	private final String name;
	private final MethodParam returnParameter;
	private final Collection<MethodParam> impParameters;
	private final Collection<MethodParam> expParameters;

	public AbapMethodInformation(String visibility, String level, String name, MethodParam returnParameter,
			Collection<MethodParam> impParameters, Collection<MethodParam> expParameters) {
		this.visibility = visibility;
		this.level = level;
		this.name = name;
		this.returnParameter = returnParameter;
		this.impParameters = impParameters;
		this.expParameters = expParameters;

	}

	@Override
	public String getSignatureLabel() {
		String returnLabel = getReturnValue() == null ? "void" : getReturnValue().getLabel();
		String impParameterLabel = buildParameterLabel(getImpParameters());
		String expParameterLabel = buildParameterLabel(getExpParameters());
		String expParameterSpace = expParameterLabel.equals(StringUtils.EMPTY) ? StringUtils.EMPTY : StringUtils.SPACE;

		String levelLabel = getLevel().equals("instance") ? StringUtils.EMPTY : getLevel() + StringUtils.SPACE;

		return getVisibility() + StringUtils.SPACE + levelLabel + returnLabel + StringUtils.SPACE + getName()
				+ "(" + impParameterLabel + expParameterSpace + expParameterLabel + ")";
	}

	private String getVisibility() {
		return visibility;
	}

	private String getLevel() {
		return level;
	}

	private String getName() {
		return name;
	}

	private MethodParam getReturnValue() {
		return returnParameter;
	}

	private Collection<MethodParam> getImpParameters() {
		return impParameters;
	}

	private Collection<MethodParam> getExpParameters() {
		return expParameters;
	}

	private String buildParameterLabel(Collection<MethodParam> parameters) {
		List<String> parameterLabels = new ArrayList<>();

		for (MethodParam parameter : parameters) {
			parameterLabels.add(getLabelForParameterType(parameter.getMethodParamType()) + parameter.getLabel());
		}

		return String.join(", ", parameterLabels);
	}

	private String getLabelForParameterType(MethodParamType methodParamType) {
		switch (methodParamType) {
		case IMPORTING:
			return "";
		case EXPORTING:
			return "EXP:";
		case RETURNING:
			return "ret";
		default:
			return "undef";
		}
	}

}
