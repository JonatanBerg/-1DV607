package blackjack.model.rules;

import blackjack.model.Player;

public interface IHitStrategy {
    boolean DoHit(Player a_dealer, Player a_player);
}