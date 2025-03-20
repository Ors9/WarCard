# War Card Game

## Important note
You can download the ZIP file and extract the files , after that go to the folder you extract the files and  click twice on the run.bat to run it on your computer

## Description
This project implements the classic card game **"War"**. The game is structured into different components, including:
- **Deck Management:** Handles the deck of cards.
- **Discard Pile:** Manages discarded cards.
- **Main Game Logic:** Oversees the gameplay and determines the winner.

## Rules of the Game
1. The deck is shuffled and divided equally between two players.
2. Each turn, both players reveal the top card of their deck.
3. The player with the higher card wins the round and places both cards in their discard pile.
4. If both cards are equal, a "War" occurs:
   - Each player places two additional face-down cards and one face-up card.
   - The player with the higher face-up card wins all the cards in the round.
   - If another tie occurs, the process repeats until a winner is determined.
5. The first player to collect all cards wins the game.

## Usage
- The game logic is implemented in **Java**, using a `Card` and `DeckOfCards` class.
- Players' hands are managed using **ArrayList**, while gameplay interactions handle drawing, discarding, and resolving battles.
- The game tracks each player's deck size and handles reshuffling when needed.

## Features
- Automatic deck shuffling and distribution.
- Handling of "War" scenarios according to the official game rules.
- Dynamic game loop that continues until one player wins all the cards.
- Structured implementation to allow easy modifications and extensions.

This project is designed to follow clear object-oriented principles, ensuring maintainability and expandability. Future enhancements may include additional game modes or AI opponents.

