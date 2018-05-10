import card.imitator.CardImitator;
import card.imitator.JokerImitator;
import cards.ordered.Deck;
import cards.sorted.Hand;
import observer.PrintObserver;

class Main {
    public static void main(String[] args) {
        Deck deck = new Deck(2);
        deck.shuffle();

        Hand hand = new Hand(
            "Hand", new PrintObserver(), deck, 10
        );
        hand.printCards();

        CardImitator imitator = new JokerImitator();
        if(deck.include(imitator)) {
            hand.pickFrom(deck, imitator);
        }
        else {
            System.out.println("Already you have two jokers.");
        }

        hand.printCards();
    }
}
