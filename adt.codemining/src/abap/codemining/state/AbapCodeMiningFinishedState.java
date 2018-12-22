package abap.codemining.state;

public class AbapCodeMiningFinishedState implements IAbapCodeParsingState {

	@Override
	public IAbapCodeParsingState getNextState() {
		return this;
	}

	@Override
	public AbapCodeParsingState getState() {
		return AbapCodeParsingState.FINISHED;
	}

}
