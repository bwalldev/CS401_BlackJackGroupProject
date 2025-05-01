// Enumerator used in the Card class

public enum Symbol {
	ACE(1, 10),  //Ace has value of either 1 or 10
	TWO(2), 
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10), //10 & all facecards have value of 10
	JACK(10),
	QUEEN(10),
	KING(10);

	//counter for value of ace in array 1 or 10
	private final int[] aceValue;

	//Constructor for the ace value in game
	Symbol(int[] aceValue) {
		this.aceValue = aceValue;
	}

	
	
}
