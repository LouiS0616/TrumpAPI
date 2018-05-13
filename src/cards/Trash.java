package cards;

import card.Card;
import card.imitator.IndividualCardImitator;
import exceptions.ProhibitedOperationException;
import observer.Observer;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 * This class is singleton, and ANY card can be included in this instance to trash.
 */
public final class Trash extends Cards implements CardOwner {
    //
    // Generate methods
    public static Trash makeTrash() {
        if(instance_ != null) {
            return instance_;
        }

        return instance_ = new Trash();
    }
    private static Trash instance_ = null;

    private Trash() {
        super("Trash", Observer.STUB);
        setOwner(this);
    }

    //
    // Class-specific methods
    private CardAffiliation affiliation_ = new CardAffiliation("Trash") {
        @Override
        public boolean isEquivalent(CardAffiliation other) {
            return true;
        }
    };
    @Override
    public CardAffiliation getAffiliation() {
        return affiliation_;
    }

    //
    //
    @Override
    protected void add(Card card) {
        // Just trash the card.
        ++numOfTrashedCard_;
    }
    @Override
    public int countCard() {
        return numOfTrashedCard_;
    }

    //
    // Fields
    private int numOfTrashedCard_ = 0;


    /**
     * @deprecated This method is prohibited.
     * @exception ProhibitedOperationException Anytime.
     */
    @Override
    public Iterator<Card> iterator() {
        throw new ProhibitedOperationException();
    }

    /**
     * @deprecated This method is prohibited.
     * @exception ProhibitedOperationException Anytime.
     */
    @Override
    public Stream<Card> stream() {
        throw new ProhibitedOperationException();
    }

    /**
     * @deprecated This method is prohibited.
     * @exception ProhibitedOperationException Anytime.
     */
    @Override
    protected IndividualCardImitator peek() {
        throw new ProhibitedOperationException();
    }

    /**
     * @deprecated This method is prohibited.
     * @exception ProhibitedOperationException Anytime.
     */
    @Override
    protected Card draw(IndividualCardImitator purpose) {
        throw new ProhibitedOperationException();
    }
}
