package Maman11Q1;

public class Card {
	private final String face; // The face value of the card (e.g., "2", "3", "10", "Jack", "Queen", "King",
								// "Ace").
	private final String suit; // The suit of the card (e.g., "Hearts", "Diamonds", "Clubs", "Spades").

	// Constructs a new Card with the specified face value and suit.
	public Card(String cardFace, String cardSuit) {
		this.face = cardFace;
		this.suit = cardSuit;

	}

	// Returns a string representation of the card.
	public String toString() {
		return face + " of " + suit;
	}

	// Convert the card face to its numeric value for winning force.
	public int getCardNumber() {

		switch (face) {
		case "Ace":
			return 14; // Ace is high
		case "King":
			return 13;
		case "Queen":
			return 12;
		case "Jack":
			return 11;
		case "10":
			return 10;
		case "9":
			return 9;
		case "8":
			return 8;
		case "7":
			return 7;
		case "6":
			return 6;
		case "5":
			return 5;
		case "4":
			return 4;
		case "3":
			return 3;
		case "2":
			return 2;
		default:
			return 0; // actually unreachable
		}

	}
}