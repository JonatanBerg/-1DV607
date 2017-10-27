package blackjack.model.rules;

import blackjack.model.Dealer;
import blackjack.model.Player;

public class DealerWinStrategy implements IWinStrategy {

	public boolean isDealerWinner(Dealer a_dealer, Player a_player) {
		int maxScore = a_dealer.getMaxScore();
		
		if(a_player.CalcScore() > maxScore) {
			return true;
		} else if(a_dealer.CalcScore() > maxScore) {
			return false;
		} else if(a_dealer.CalcScore() == a_player.CalcScore()) {
			return true;
		} else if(a_dealer.CalcScore() > a_player.CalcScore()) {
			return true;
		} else {
			return false; // If player has higher score than dealer
		}
	}
}
