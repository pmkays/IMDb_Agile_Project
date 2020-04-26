package app.model.Enumerations;

public enum ShowStatus {
	SUBMITTED (0),
	UNDER_INVESTIGATION (1),
	PENDING (2),
	SUSPENDED (3),
	VISIBLE (4),
	REJECTED (5);
	
	private int numDisplay;
	ShowStatus(int num){
		this.numDisplay = num;
	}
	
	public int getNumDisplay() {
		return this.numDisplay;
	}
}
