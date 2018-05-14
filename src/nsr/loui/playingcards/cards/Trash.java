package nsr.loui.playingcards.cards;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.imitator.IndividualCardImitator;
import nsr.loui.playingcards.exceptions.ProhibitedOperationException;
import nsr.loui.playingcards.observer.Observer;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Trash instance can be owner, and ANY card can be included in this instance to trash.
 * Note that card once throw into trash cannot be no longer collected.
 *
 * This class is singleton. Call makeTrash method to get instance.
 */
public final class Trash extends Cards implements CardOwner {
    //
    // Generate methods

    /**
     * @return singleton instance.
     */
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
    private final CardOwnerCertificate affiliation_ = new CardOwnerCertificate("Trash") {
        @Override
        public boolean isEquivalent(CardOwnerCertificate other) {
            return true;
        }
    };
    @Override
    public CardOwnerCertificate getCertificate() {
        return affiliation_;
    }

    //
    //

    /**
     * Just trash the CARD.
     * @param card card you want to throw away.
     */
    @Override
    protected void add(Card card) {
        ++numOfTrashedCard_;
    }

    /**
     * @return num of cards had been thrown away.
     */
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
