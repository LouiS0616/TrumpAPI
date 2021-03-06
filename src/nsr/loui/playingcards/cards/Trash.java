package nsr.loui.playingcards.cards;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.imitator.IndividualCardImitator;
import nsr.loui.playingcards.exceptions.ProhibitedOperationException;
import nsr.loui.playingcards.observer.Observer;

import java.util.stream.Stream;

/**
 * Trash instance can be owner, and ANY card can be included in this instance as trash.
 * Note that card once throw into trash cannot be no longer collected.
 *
 * This class is singleton so call makeTrash method to get instance.
 */
public final class Trash extends Cards implements CardOwner {
    //
    // Generate methods

    /**
     * Call it to get singleton instance.
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
        setCertificate(this.certificate_);
    }

    //
    // Class-specific methods
    @SuppressWarnings("FieldCanBeLocal")
    private final CardOwnerCertificate certificate_ = new CardOwnerCertificate(this) {
        @Override
        public boolean permits(CardOwnerCertificate other) {
            return true;
        }
    };


    //
    //

    /**
     * Just trash the CARD.
     * @param card card you want to throw away.
     */
    @Override
    void add(Card card) {
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
     * Do NOT call this method.
     * @deprecated This method is prohibited.
     * @return no chance to return value.
     * @exception ProhibitedOperationException anytime.
     */
    @Override
    Stream<Card> stream() {
        throw new ProhibitedOperationException();
    }

    /**
     * Do NOT call this method.
     * @deprecated This method is prohibited.
     * @return no chance to return value.
     * @exception ProhibitedOperationException anytime.
     */
    @Override
    public IndividualCardImitator peek() {
        throw new ProhibitedOperationException();
    }

    /**
     * Do NOT call this method.
     * @deprecated This method is prohibited.
     * @return no chance to return value.
     * @exception ProhibitedOperationException anytime.
     */
    @Override
    protected Card draw(IndividualCardImitator purpose) {
        throw new ProhibitedOperationException();
    }
}
