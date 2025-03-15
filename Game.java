package Maman11Q1;

import java.util.ArrayList;

public class Game {

	private static final int CARDS_FOR_EACH_PLAYER = 26;
	private static final int MIN_CARDS_FOR_WAR = 3;

	private ArrayList<Card> deckOne; // Player One's deck
	private ArrayList<Card> deckTwo; // Player Two's deck

	public static void main(String[] args) {
		new Game().startGame();
	}

	// Manages the War card game between two players.
	private void startGame() {

		// Notify that the game has started using Comic Sans MS font.
		MessageUtil.showStyleJOptionPaneMessage("Game Start", "--- The war game has started! ---");

		initializeGame();// Create, shuffle, and split the deck between two players.

		// Play rounds until one player's deck is empty.
		while (!deckOne.isEmpty() && !deckTwo.isEmpty()) {
			playRound();
		}

		// Determine the winner.
		determineWinner();

	}

	// Create, shuffle, and split the deck between two players.
	private void initializeGame() {
		// Create, shuffle, and split the deck between two players.
		DeckOfCards deckOfCards = new DeckOfCards();
		deckOfCards.shuffle();
		deckOne = new ArrayList<>(CARDS_FOR_EACH_PLAYER);
		deckTwo = new ArrayList<>(CARDS_FOR_EACH_PLAYER);
		DeckOfCards.splitCards(deckOne, deckTwo, deckOfCards);
	}

	// Determines and announces the winner of the game.
	private void determineWinner() {
		// Determine the winner.
		if (this.deckOne.isEmpty()) {
			MessageUtil.showStyleJOptionPaneMessage("Game Winner!", "The winner is player Two!!");
		} else {
			MessageUtil.showStyleJOptionPaneMessage("Game Winner!", "The winner is player One!!");
		}
	}

	// Play one round of the game
	private void playRound() {

		// Remove the top card from each deck.
		Card cardOne = deckOne.remove(0);
		Card cardTwo = deckTwo.remove(0);

		// Create a message displaying the played cards.
		String message = "Player One's Card: " + cardOne.toString() + "\n" + "Player Two's Card: " + cardTwo.toString()
				+ "\n";

		// Compare cards to decide the round winner.
		// Player One wins the round: add both cards to the bottom of deckOne.
		if (cardOne.getCardNumber() > cardTwo.getCardNumber()) {
			deckOne.add(cardOne);
			deckOne.add(cardTwo);

			message += "Player One wins the round";
			// Display the consolidated round result.
			MessageUtil.showStyleJOptionPaneMessage("Round Result", message);
		}
		// Player Two wins the round.
		else if (cardOne.getCardNumber() < cardTwo.getCardNumber()) {
			deckTwo.add(cardTwo);
			deckTwo.add(cardOne);

			message += "Player Two wins the round";
			// Display the consolidated round result.
			MessageUtil.showStyleJOptionPaneMessage("Round Result", message);

		}

		else {

			// In case of a tie, start a war.
			message += "----------It's a tie! Prepare for WAR!------------";
			MessageUtil.showStyleJOptionPaneMessage("Round Result", message);
			ArrayList<Card> warPile = new ArrayList<>();
			warPile.add(cardOne);
			warPile.add(cardTwo);
			handleWar(warPile);
		}

	}

	// Handle a war round.
	private void handleWar(ArrayList<Card> warPile) {
		// Check if players have enough cards for war.
		if (!haveEnoughCardsForWar())
			return;

		// Play one round of war (if need will call again from inside)
		playRoundOfWar(warPile);

	}

	// if one of ther players have not enough card for war they lose
	private boolean haveEnoughCardsForWar() {
		if (this.deckOne.size() < MIN_CARDS_FOR_WAR) {
			this.deckOne.clear(); // Force game over.
			return false;
		}
		if (this.deckTwo.size() < MIN_CARDS_FOR_WAR) {
			this.deckTwo.clear(); // Force game over.
			return false;
		}
		return true;
	}

	// Place 2 cards in war pile
	private void placeWarCardsInPile(ArrayList<Card> warPile) {
		for (int i = 0; i < 2; i++) { // Each player places 2 cards face down
			warPile.add(this.deckOne.remove(0));
			warPile.add(this.deckTwo.remove(0));
		}
	}

	// Play one round of war
	private void playRoundOfWar(ArrayList<Card> warPile) {
		// Check if players have enough cards for war.
		if (!haveEnoughCardsForWar())
			return;

		// Each player places two cards face down into the war pile.
		placeWarCardsInPile(warPile);

		// Each player draws one card to compare.
		Card cardOne = this.deckOne.remove(0);
		Card cardTwo = this.deckTwo.remove(0);

		String message = "Player One's Card: " + cardOne.toString() + "\n" + "Player Two's Card: " + cardTwo.toString()
				+ "\n";

		// Add the drawn cards to the war pile.
		warPile.add(cardOne);
		warPile.add(cardTwo);

		// Determine the war round winner.
		if (cardOne.getCardNumber() > cardTwo.getCardNumber()) {
			// Player One wins all cards in the war pile
			this.deckOne.addAll(warPile);
			warPile.clear();
			message += "Winner: Player One wins this WAR!";
			// Display the consolidated round result.
			MessageUtil.showStyleJOptionPaneMessage("Round Result", message);
		} else if (cardOne.getCardNumber() < cardTwo.getCardNumber()) {
			// Player Two wins all cards in the war pile
			this.deckTwo.addAll(warPile);
			warPile.clear();
			message += "Winner: Player Two wins this WAR!";
			// Display the consolidated round result.
			MessageUtil.showStyleJOptionPaneMessage("Round Result", message);
		} else {
			// If tied again, the war continues with accumulated warPile.
			message += "---------It's a tie! Prepare for another WAR!-------";
			// Display the consolidated round result.
			MessageUtil.showStyleJOptionPaneMessage("Round Result", message);
			handleWar(warPile);
		}
	}

}