package blackjack.model.rules;

import blackjack.model.Deck;
import blackjack.model.Dealer;
import blackjack.model.Player;

class InternationalNewGameStrategy implements INewGameStrategy {

  public boolean NewGame(Deck a_deck, Dealer a_dealer, Player a_player) {
		// Deal start cards to the dealer and the player
	    a_dealer.DealCard(a_player);
	    
	    a_dealer.DealCard(a_dealer);
	    
	    a_dealer.DealCard(a_player);
	    
	    return true;
  }
}