package blackjack.model.rules;

import blackjack.model.Dealer;
import blackjack.model.Player;

public interface IWinStrategy {
	boolean isDealerWinner(Dealer a_dealer, Player a_player);
}
