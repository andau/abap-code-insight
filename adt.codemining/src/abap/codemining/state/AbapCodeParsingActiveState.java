package abap.codemining.state;

public class AbapCodeParsingActiveState implements IAbapCodeParsingState {

	@Override
	public IAbapCodeParsingState getNextState() {
		return new AbapCodeMiningFinishedState();
	}

	@Override
	public AbapCodeParsingState getState() {
		return AbapCodeParsingState.ACTIVE;
	}

}
