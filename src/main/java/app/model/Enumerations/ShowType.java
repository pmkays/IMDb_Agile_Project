package app.model.Enumerations;

public enum ShowType {
	MOVIE (0),
	TV_SERIES (1);
	
	private int numDisplay;
	ShowType(int num){
		this.numDisplay = num;
	}
	
	public int getNumDisplay() {
		return this.numDisplay;
	}
}
