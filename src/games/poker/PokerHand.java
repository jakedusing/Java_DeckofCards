package games.poker;

import games.Card;

import java.util.ArrayList;
import java.util.List;

public class PokerHand {

    private List<Card> hand; // The cards in the player's hand
    private List<Card> keepers; // The cards the player decides to keep
    private List<Card> discards; // the cards the player decides to discard
    private Ranking score = Ranking.NONE; // The ranking of the hand, initially set to NONE
    private int playerNo; // The player's number or ID

    /*
        Constructs a PokerHand with the given player's number and initial set of cards.
        The cards are sorted in descending order by rank and suit

        @param playerNo the player's number or ID
        @param hand the initial set of cards in the player's hand
     */

    public PokerHand(int playerNo, List<Card> hand) {
        hand.sort(Card.sortRankReversedSuit());  // Sort the cards by rank (highest first) and suit
        this.hand = hand;
        this.playerNo = playerNo;
        keepers = new ArrayList<>(hand.size()); // initialize the list of cards to keep
        discards = new ArrayList<>(hand.size()); // initialize the list of cards to discard
    }

    /*
        Provides a string of the PokerHand, including player details,
        hand ranking, the cards in hand, and any discarded cards.
     */
    @Override
    public String toString() {
        return "%d. %-16s Rank:%d %-40s %s".formatted(
                playerNo, score, score.ordinal(), hand,
                (discards.size() > 0) ? "Discards:" + discards : "");
    }
}
