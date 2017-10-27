package blackjack.model;

import java.util.ArrayList;

import blackjack.model.rules.*;

public class Dealer extends Player {
	private Deck m_deck;
	private INewGameStrategy m_newGameRule;
	private IHitStrategy m_hitRule;
	private IWinStrategy m_winRule;
	private ArrayList<ICardReceivedObserver> m_observers;

	public Dealer(RulesFactory a_rulesFactory) {
		m_newGameRule = a_rulesFactory.GetNewGameRule();
		m_hitRule = a_rulesFactory.GetHitRule();
		m_winRule = a_rulesFactory.GetWinRule();
		m_observers = new ArrayList<ICardReceivedObserver>();
	}


	public boolean NewGame(Player a_player) {
		if (m_deck == null || IsGameOver(a_player)) {
			m_deck = new Deck();
			ClearHand();
			a_player.ClearHand();
			return m_newGameRule.NewGame(m_deck, this, a_player);   
		}
		return false;
	}

	public boolean Hit(Player a_player) {
		if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver(a_player)) {
			DealCard(a_player);

			return true;
		}
		return false;
	}

	public boolean Stand(Player a_player) {
		if (m_deck != null) {
			ShowHand();

			// While dealer is supposed to hit (according to m_hitRule), assign a new card to the dealer
			while (m_hitRule.DoHit(this, a_player)) {
				DealCard(this);
			}

			return true;
		}
		return false;
	}

	public boolean IsDealerWinner(Player a_player) {
		return m_winRule.isDealerWinner(this, a_player);
	}

	public boolean IsGameOver(Player a_player) {
		if (m_deck != null && m_hitRule.DoHit(this, a_player) != true) {
			return true;
		}
		return false;
	}

	public void DealCard(Player a_player) {
		Card c = m_deck.GetCard();
		c.Show(true);
		a_player.ReceiveCard(c);

		// Inform m_observers that a card has been received
		for(int i = 0; i < m_observers.size(); i++) {
			m_observers.get(i).cardReceived();
		}
	}

	public void DealCard(Player a_player, boolean showCard) {
		Card c = m_deck.GetCard();
		c.Show(showCard);
		a_player.ReceiveCard(c);

		// Inform m_observers that a card has been received
		for(int i = 0; i < m_observers.size(); i++) {
			m_observers.get(i).cardReceived();
		}
	}

	public void addObserver(ICardReceivedObserver observer) {
		m_observers.add(observer);
	}

}