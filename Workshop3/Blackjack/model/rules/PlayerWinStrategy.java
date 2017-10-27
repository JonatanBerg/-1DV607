package blackjack.model.rules;

import blackjack.model.Dealer;
import blackjack.model.Player;

public class PlayerWinStrategy implements IWinStrategy {
	@Override
	public boolean isDealerWinner(Dealer a_dealer, Player a_player) {
		int maxScore = a_dealer.getMaxScore();
		
		if(a_dealer.CalcScore() > maxScore) {
			return false;
		} else if(a_player.CalcScore() > maxScore) {
			return true;
		} else if(a_dealer.CalcScore() == a_player.CalcScore()) {
			return false;
		} else if(a_player.CalcScore() > a_dealer.CalcScore()) {
			return false;
		} else {
			return true; // If dealer has higher score than dealer
		}
	}
}
