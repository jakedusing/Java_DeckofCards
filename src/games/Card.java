package games;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public record Card(Suit suit, String face, int rank) {

    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE;

        public char getImage() {
            return (new char[]{9827, 9830, 9829, 9824})[this.ordinal()];
        }
    }

    public static Comparator<Card> sortRankReversedSuit() {
        return Comparator.comparing(Card::rank).reversed().thenComparing(Card::suit);
    }

    @Override
    public String toString() {

        int index = face.equals("10") ? 2 : 1;
        String faceString = face.substring(0, index);
        return "%s%c(%d)".formatted(faceString, suit.getImage(), rank);
    }

    /*
        Creates a numeric card (2-10) given its suit and number
        @param suit the suit of the card
        @param cardNumber the numeric value of the card (2-10)
        @return a new Card object or null if the number is invalid
     */

    public static Card getNumericCard(Suit suit, int cardNumber) {

        if (cardNumber > 1 && cardNumber < 11) {
            return new Card(suit, String.valueOf(cardNumber), cardNumber - 2);
        }
        System.out.println("Invalid Numeric card selected");
        return null;
    }

    /*
        Creates a face card (J, Q, K, A) given its suit and abbreviation
        @param suti the suit of the card
        @param abbrev the abbreviation of the face card ('J', 'Q', 'K', 'A')
        @return a new Card object or null if the abbreviation is invalid
     */

    public static Card getFaceCard(Suit suit, char abbrev) {
        int charIndex = "JQKA".indexOf(abbrev);
        if (charIndex > -1) {
            return new Card(suit, "" + abbrev, charIndex + 9);
        }
        System.out.println("Invalid Face card selected");
        return null;
    }

    /*
        Generates a standard deck of 52 playing cards.
        @return a list of Card objects representing a full deck
     */

    public static List<Card> getStandardDeck() {

        List<Card> deck = new ArrayList<>(52);
        for (Suit suit : Suit.values()) {
            // Add numeric cards (2-10) for each suit
            for (int i = 2; i <= 10; i++) {
                deck.add(getNumericCard(suit, i));
            }
            // Add face card (J, Q, K, A) for each suit
            for (char c : new char[]{'J', 'Q', 'K', 'A'}) {
                deck.add(getFaceCard(suit, c));
            }
        }
        return deck;
    }

    /*
        Prints the entire deck with a default title and 4 rows
        @param deck the list of Card objects to print
     */

    public static void printDeck(List<Card> deck) {
        printDeck(deck, "Current Deck", 4);
    }

    /*
        Prints the deck in a formatted layout with the given description and row count
        @param deck the list of Card objects to print
        @param description a title or description for the printed deck
        @param rows the number of rows to display the cards in
     */

    public static void printDeck(List<Card> deck, String description, int rows) {

        System.out.println("---------------------------------");
        if (description != null) {
            System.out.println(description);
        }
        int cardsInRow = deck.size() / rows;
        for (int i = 0; i < rows; i++) {
            int startIndex = i * cardsInRow;
            int endIndex = startIndex + cardsInRow;
            deck.subList(startIndex, endIndex).forEach(c -> System.out.print(c + " "));
            System.out.println();
        }
    }


}
