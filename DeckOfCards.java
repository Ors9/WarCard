package Maman11Q1;

import java.security.SecureRandom;
import java.util.ArrayList;

public class DeckOfCards {

	private static final SecureRandom randomNumbers = new SecureRandom();
	private static final int NUMBER_OF_CARDS = 52;
	private ArrayList<Card> deck = new ArrayList<>(NUMBER_OF_CARDS);
	private int currentCard = 0;

	// Constructor: Initializes the deck with 52 cards (faces and suits).
	public DeckOfCards() {
		String[] faces = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };

		// Create 52 cards by pairing each face with each suit.
		for (int count = 0; count < NUMBER_OF_CARDS; count++) {
			deck.add(new Card(faces[count % 13], suits[count / 13]));
		}
	}

	// Shuffle the deck by swapping each card with a random card.
	public void shuffle() {
		currentCard = 0; // Reset to start dealing from the top again.

		// Swap each card with another randomly chosen card.
		for (int first = 0; first < NUMBER_OF_CARDS; first++) {
			int second = randomNumbers.nextInt(NUMBER_OF_CARDS);
			Card temp = deck.get(first);
			deck.set(first, deck.get(second));
			deck.set(second, temp);
		}
	}

	// Deal one card or return null if none left.
	public Card dealCard() {
		if (currentCard < NUMBER_OF_CARDS) {
			return deck.get(currentCard++);
		} else {
			return null;
		}
	}

}
