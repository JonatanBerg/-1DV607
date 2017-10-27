package blackjack.model.rules;

import blackjack.model.Player;

class BasicHitStrategy implements IHitStrategy {
    private final int g_hitLimit = 17;

    public boolean DoHit(Player a_dealer, Player a_player) {
    	if(a_dealer.CalcScore() > a_player.CalcScore()) {
    		return false;
    	}
    	
    	return a_dealer.CalcScore() < g_hitLimit;  
    }
}