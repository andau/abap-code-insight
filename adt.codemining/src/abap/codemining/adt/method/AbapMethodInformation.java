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
	private final Collection<MethodParam> changeParameters;

	public AbapMethodInformation(String visibility, String level, String name, MethodParam returnParameter,
			Collection<MethodParam> impParameters, Collection<MethodParam> expParameters,
			Collection<MethodParam> changeParameters) {
		this.visibility = visibility;
		this.level = level;
		this.name = name;
		this.returnParameter = returnParameter;
		this.impParameters = impParameters;
		this.expParameters = expParameters;
		this.changeParameters = changeParameters;

	}

	@Override
	public String getSignatureLabel() {
		final String returnLabel = getReturnValue() == null ? "void" : getReturnValue().getLabel();
		final String impParameterLabel = buildParameterLabel(getImpParameters());
		final String changeParameterLabel = buildParameterLabel(getChangeParameters());
		final String changeParameterSpace = changeParameterLabel.equals(StringUtils.EMPTY) ? StringUtils.EMPTY
				: StringUtils.SPACE;
		final String expParameterLabel = buildParameterLabel(getExpParameters());
		final String expParameterSpace = expParameterLabel.equals(StringUtils.EMPTY) ? StringUtils.EMPTY
				: StringUtils.SPACE;

		final String levelLabel = getLevel().equals("instance") ? StringUtils.EMPTY : getLevel() + StringUtils.SPACE;

		final String parameterLabel = "(" + impParameterLabel + changeParameterSpace + changeParameterLabel
				+ expParameterSpace + expParameterLabel + ")";
		if (getImpParameters().size() + getExpParameters().size() + getChangeParameters().size() > -1) {
			return getVisibility() + StringUtils.SPACE + levelLabel + "["
					+ (getReturnValue() == null ? StringUtils.EMPTY : getReturnValue()) + "]" + StringUtils.SPACE
					+ parameterLabel;
		} else {
			return getVisibility() + StringUtils.SPACE + levelLabel + returnLabel + StringUtils.SPACE + getName()
					+ parameterLabel;
		}
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

	private Collection<MethodParam> getChangeParameters() {
		return changeParameters;
	}

	private String buildParameterLabel(Collection<MethodParam> parameters) {
		final List<String> parameterLabels = new ArrayList<>();

		for (final MethodParam parameter : parameters) {
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
		case CHANGING:
			return "CHA:";
		case RETURNING:
			return "ret";
		default:
			return "undef";
		}
	}

}
