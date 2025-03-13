package Maman11Q1;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Game {

	private static final int NUMBER_OF_CARDS = 52;
	private static final int CARDS_FOR_EACH_PLAYER = 26;

	public static void main(String[] args) {

		// Notify that the game has started using Comic Sans MS font.
		showStyleJOptionPaneMessage("Game Start", "--- The war game is start !!!! ---");

		// Create, shuffle, and split the deck between two players.
		DeckOfCards deckOfCards = new DeckOfCards();
		deckOfCards.shuffle();
		ArrayList<Card> deckOne = new ArrayList<>(CARDS_FOR_EACH_PLAYER);
		ArrayList<Card> deckTwo = new ArrayList<>(CARDS_FOR_EACH_PLAYER);
		splitCards(deckOne, deckTwo, deckOfCards);

		// Play rounds until one player's deck is empty.
		while (!deckOne.isEmpty() && !deckTwo.isEmpty()) {

			// Remove the top card from each deck.
			Card cardOne = deckOne.remove(0);
			Card cardTwo = deckTwo.remove(0);

			String message = "Player One's Card: " + cardOne.toString() + "\n" + "Player Two's Card: "
					+ cardTwo.toString() + "\n";

			// Compare cards to decide the round winner.
			// Player One wins the round: add both cards to the bottom of deckOne.
			if (cardOne.getCardNumber() > cardTwo.getCardNumber()) {
				deckOne.add(cardOne);
				deckOne.add(cardTwo);

				message += "**Player One's win the round**";
				// Display the consolidated round result.
				showStyleJOptionPaneMessage("Round Result", message);
			}
			// Player Two wins the round.
			else if (cardOne.getCardNumber() < cardTwo.getCardNumber()) {
				deckTwo.add(cardTwo);
				deckTwo.add(cardOne);

				message += "**Player Two's win the round**";
				// Display the consolidated round result.
				showStyleJOptionPaneMessage("Round Result", message);

			}

			else {

				// In case of a tie, start a war.
				message += "----------It's a tie! Prepare for WAR!------------";
				showStyleJOptionPaneMessage("Round Result", message);
				ArrayList<Card> warPile = new ArrayList<>();
				warPile.add(cardOne);
				warPile.add(cardTwo);
				isWar(deckOne, deckTwo, warPile);
			}

		}

		// Determine the winner.
		if (deckOne.isEmpty()) {
			showStyleJOptionPaneMessage("Game Winner!", "The winner is player Two!!");
			return;
		} else {
			showStyleJOptionPaneMessage("Game Winner!", "The winner is player Two!!");
			return;
		}

	}

	// Splits the full deck between two players by alternating cards.
	private static void splitCards(ArrayList<Card> deckOne, ArrayList<Card> deckTwo, DeckOfCards deckOfCards) {
		for (int i = 0; i < NUMBER_OF_CARDS; i++) {
			if (i % 2 == 0) {
				deckOne.add(deckOfCards.dealCard());

			} else {
				deckTwo.add(deckOfCards.dealCard());
			}
		}
	}

	// function helper to do the messages and make them look nice ;)
	// i know its not neccessery
	private static void showStyleJOptionPaneMessage(String title, String message) {
		JTextArea textArea = new JTextArea(message);

		// Set the font
		textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

		// Make it non-editable, no border, and transparent to look like a label
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setBorder(null);

		// Enable line wrapping
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setColumns(30);

		// Display the message dialog
		JOptionPane.showMessageDialog(null, textArea, title, JOptionPane.INFORMATION_MESSAGE);
	}

	// Handle a war round.
	private static void isWar(ArrayList<Card> deckOne, ArrayList<Card> deckTwo, ArrayList<Card> warPile) {
		boolean isWarState = true;

		// Continue war until a player wins or one player lacks enough cards.
		while (isWarState == true && !deckOne.isEmpty() && !deckTwo.isEmpty()) {

			// If a player does not have at least 3 cards, they lose.
			if (deckOne.size() < 3) {
				deckOne.clear(); // Force game over.
				return;
			} else if (deckTwo.size() < 3) {
				deckTwo.clear(); // Force game over.
				return;
			}

			// Each player places two cards face down into the war pile.
			warPile.add(deckOne.remove(0));
			warPile.add(deckOne.remove(0));
			warPile.add(deckTwo.remove(0));
			warPile.add(deckTwo.remove(0));

			// Each player draws one card to compare.
			Card cardOne = deckOne.remove(0);
			Card cardTwo = deckTwo.remove(0);

			String message = "Player One's Card: " + cardOne.toString() + "\n" + "Player Two's Card: "
					+ cardTwo.toString() + "\n";

			// Add the drawn cards to the war pile.
			warPile.add(cardOne);
			warPile.add(cardTwo);

			// Determine the war round winner.
			if (cardOne.getCardNumber() > cardTwo.getCardNumber()) {
				// Player One wins all cards in the war pile
				deckOne.addAll(warPile);
				warPile.clear();
				message += "**Winner: Player One wins this WAR!**";
				// Display the consolidated round result.
				showStyleJOptionPaneMessage("Round Result", message);
				isWarState = false;
			} else if (cardOne.getCardNumber() < cardTwo.getCardNumber()) {
				// Player Two wins all cards in the war pile
				deckTwo.addAll(warPile);
				warPile.clear();
				message += "**Winner: Player Two wins this WAR!**";
				// Display the consolidated round result.
				showStyleJOptionPaneMessage("Round Result", message);
				isWarState = false;
			} else {
				// If tied again, the war continues with accumulated warPile.
				message += "---------It's a tie! Prepare for another WAR!-------";
				// Display the consolidated round result.
				showStyleJOptionPaneMessage("Round Result", message);
			}

		}

	}

}
