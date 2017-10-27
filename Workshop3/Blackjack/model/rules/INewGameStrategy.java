package blackjack.model.rules;

import blackjack.model.Deck;
import blackjack.model.Dealer;
import blackjack.model.Player;

public interface INewGameStrategy {
    boolean NewGame(Deck a_deck, Dealer a_dealer, Player a_player);
}