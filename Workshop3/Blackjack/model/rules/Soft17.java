package blackjack.model.rules;

import blackjack.model.Card;
import blackjack.model.Player;

public class Soft17 implements IHitStrategy {
	private final int g_hitLimit = 17;
	
	public boolean DoHit(Player a_dealer, Player a_player) {
		int amountAces = 0;
		
		// Check if dealer has an ace in hand
		for(Card c : a_dealer.GetHand()) {
			if(c.GetValue() == Card.Value.Ace) {
				amountAces++;
			}
		}
		
		if(a_player.CalcScore() > a_player.getMaxScore()) 
		{
			return false;
		}
		else if(amountAces > 0) 
		{ // If dealer has aces, deal a new card if dealer has soft 17 or below
			return a_dealer.CalcScore() - (11 * amountAces) <= 6; 
		} 
		else if(a_player.CalcScore() > a_dealer.CalcScore() && a_dealer.CalcScore() < a_dealer.getMaxScore()) 
		{ // If the player is winning and dealer isn't at max score, deal a new card
			return true;
		} 
		else if(a_dealer.CalcScore() == a_player.CalcScore() && a_dealer.CalcScore() > 11) 
		{ // If the score is the same (dealer winning), no need to draw a new card
			return false;
		} 
		else 
		{
			return a_dealer.CalcScore() < g_hitLimit;
		}
		
	}
}
