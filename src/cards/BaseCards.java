package cards;

import card.Card;
import card.imitator.CardImitator;
import card.imitator.individual.IndividualCardImitator;
import card.imitator.wild.WildCardImitator;
import cards.exceptions.CardNotFoundException;
import cards.exceptions.CardOwnerImproperException;
import cards.own.CardAffiliation;
import cards.own.CardOwner;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BaseCards implements Iterable<Card> {
    //
    // Generate methods
    protected BaseCards(String name) {
        this.name_ = name;
    }
    public void setOwner(CardOwner owner) {
        this.affiliation_ = owner.getAffiliation();
    }

    //
    // Check methods
    public boolean include(CardImitator imitator) {
        return stream()
            .anyMatch(imitator::isEquivalent)
        ;
    }
    public int countCard(WildCardImitator imitator) {
        return (int)stream()
            .filter(imitator::isEquivalent)
            .count()
        ;
    }
    public abstract int countCard();

    //
    // Methods related drawing
    protected abstract void add$owner_is_already_checked(Card card);
    public final void add(Card card) throws CardOwnerImproperException {
        if(affiliation_ == null) {
            throw new CardOwnerImproperException("You MUST register cards to valid card-owner.");
        }

        if(affiliation_.own(card)) {
            add$owner_is_already_checked(card);
        }
    }

    protected abstract Card draw();
    protected abstract Card draw(IndividualCardImitator purpose) throws CardNotFoundException;

    //
    // Iterate methods
    public abstract Iterator<Card> iterator();
    public abstract Stream<Card> stream();

    //
    // Display methods
    @Override
    public String toString() {
        return this.name_;
    }
    public void printCards() {
        System.out.println(
            this.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "))
        );
    }

    //
    // Fields
    private final String name_;
    private CardAffiliation affiliation_;
}
