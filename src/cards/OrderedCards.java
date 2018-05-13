package cards;

import card.Card;
import card.imitator.IndividualCardImitator;
import exceptions.CardNotEnoughException;
import exceptions.CardNotFoundException;
import exceptions.ProhibitedOperationException;
import observer.Observer;
import util.CollectionUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * You can draw top card of cards, and cannot draw random card.
 */
public abstract class OrderedCards extends Cards {
    //
    // Generate methods
    protected OrderedCards(String name, Observer observer) {
        super(name, observer);
    }

    /**
     * Set cards and these owner.
     * @exception ProhibitedOperationException When originally cards is not empty.
     */
    protected void setCards(LinkedList<Card> cards, CardOwner owner) {
        if(this.cards_.isEmpty()) {
            this.cards_ = cards;
            setOwner(owner);
        }
        else {
            throw new ProhibitedOperationException();
        }
    }

    //
    // Class-specific methods
    public final void shuffle() {
        Collections.shuffle(this.cards_);
    }

    //
    // Check methods
    @Override
    public final int countCard() {
        return cards_.size();
    }

    //
    // Iterate methods
    @Override
    public final Iterator<Card> iterator() {
        return cards_.iterator();
    }
    @Override
    public final Stream<Card> stream() {
        return cards_.stream();
    }

    //
    // Methods related drawing
    @Override
    protected final IndividualCardImitator peek() {
        if(countCard() == 0) {
            throw new CardNotEnoughException();
        }

        return cards_.get(0).getIndividualImitator();
    }
    @Override
    protected final Card draw(IndividualCardImitator purpose) {
        return CollectionUtil.popElem(
            cards_,
            stream()
                .filter(purpose::isEquivalent)
                .findFirst()
                .orElseThrow(CardNotFoundException::new)
        );
    }
    @Override
    protected final void add(Card card) {
        cards_.add(card);
    }

    //
    // Fields

    // This field should be LinkedList object instead of List.
    // Because OrderedCards does'nt allow random access.
    private LinkedList<Card> cards_ = new LinkedList<>();
}