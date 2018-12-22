package abap.codemining.state;

public interface IAbapCodeParsingState {

	IAbapCodeParsingState getNextState();

	AbapCodeParsingState getState();

}
