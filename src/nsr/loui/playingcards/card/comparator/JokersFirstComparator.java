package nsr.loui.playingcards.card.comparator;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.Joker;
import nsr.loui.playingcards.card.RankedCard;

/**
 * This class means comparator what always put jokers at first of cards.
 * Cards aligned such as [jokers, ranked, ..., ranked, ranked].
 *
 * I referenced java.util.Comparator.nullsFirst to name this class.
 */
public abstract class JokersFirstComparator extends CardComparator {
    /**
     * @param rankedCard1 ranked card to be compared.
     * @param rankedCard2 ranked card to be compared.
     * @return a negative integer, zero, or a positive integer
     *              as the RANKED-CARD1 is former than, equal to, or latter then RANKED-CARD2.
     */
    protected abstract int compare(RankedCard rankedCard1, RankedCard rankedCard2);

    /**
     * @param card1 card to be compared.
     * @param card2 card to be compared.
     * @return a negative integer, zero, or a positive integer
     *              as the CARD1 is former than, equal to, or latter than CARD2.
     */
    @Override
    public int compare(Card card1, Card card2) {
        boolean card1IsJoker = card1 instanceof Joker;
        boolean card2IsJoker = card2 instanceof Joker;

        if(card1IsJoker && card2IsJoker) {
            return compare(
                (Joker)card1, (Joker)card2
            );
        }

        if(card1IsJoker) return -1;
        if(card2IsJoker) return +1;

        return compare(
            (RankedCard)card1, (RankedCard)card2
        );
    }
}
