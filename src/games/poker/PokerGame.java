package games.poker;

import games.Card;

import java.util.*;

public class PokerGame {

    private final List<Card> deck = Card.getStandardDeck(); // The dark of cards for the game
    private int playerCount;  // the number of players in the game
    private int cardsInHand; // the number of cards each player gets in their hand
    private List<PokerHand> pokerHands; // the list of poker hands, one for each player
    private List<Card> remainingCards;  // any cards left in the deck after dealing


    /*
        Constructs a PokerGame with the specified number of players and cards per hand

        @param playerCount the number of players participating
        @param cardsInHand the number of cards each player will be dealt
     */
    public PokerGame(int playerCount, int cardsInHand) {
        this.playerCount = playerCount;
        this.cardsInHand = cardsInHand;
        pokerHands = new ArrayList<>(cardsInHand);  // initialize the list of poker hands
    }


    /*
        Start the game by shuffling, rotating, and dealing the deck,
        then prints the deck and each player's hand.
     */
    public void startPlay() {
        Collections.shuffle(deck);
        Card.printDeck(deck);
        int randomMiddle = new Random().nextInt(15, 35);
        Collections.rotate(deck, randomMiddle);
        Card.printDeck(deck);

        deal();
        System.out.println("--------------------------------");
        pokerHands.forEach(System.out::println);
    }

    /*
        Deals cards to players, distributing the specified number of cards per hand
     */

    private void deal() {
        // create a 2D array to store cards and each player's hand
        Card[][] hands = new Card[playerCount][cardsInHand];

        // Distribute cards from the deck to the players' hands
        for (int deckIndex = 0, i = 0; i < cardsInHand; i++) {
            for (int j = 0; j < playerCount; j++) {
                hands[j][i] = deck.get(deckIndex++); // Assign the next card from the deck
            }
        }

        // Convert each player's array of cards into a PokerHand object
        int playerNo = 1;
        for (Card[] hand : hands) {
            pokerHands.add(new PokerHand(playerNo++, Arrays.asList(hand)));
        }
    }
}
