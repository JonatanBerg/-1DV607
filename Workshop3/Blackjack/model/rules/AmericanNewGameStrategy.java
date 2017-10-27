package blackjack.model.rules;

import blackjack.model.Deck;
import blackjack.model.Dealer;
import blackjack.model.Player;

class AmericanNewGameStrategy implements INewGameStrategy {

  public boolean NewGame(Deck a_deck, Dealer a_dealer, Player a_player) {
	// Dealer deals out start cards to the dealer and the player
    a_dealer.DealCard(a_player);
    
    a_dealer.DealCard(a_dealer);
    
    a_dealer.DealCard(a_player);
    
    a_dealer.DealCard(a_dealer, false);
    
    return true;
  }
}