package blackjack.controller;

import blackjack.view.IView;

import java.util.concurrent.TimeUnit;

import blackjack.model.Game;
import blackjack.model.ICardReceivedObserver;

public class PlayGame implements ICardReceivedObserver {
	private boolean initialized = false;
	private Game m_game;
	private IView m_view;
	
	public boolean Play(Game a_game, IView a_view) {
		if(!initialized) {
			m_game = a_game;
			m_view = a_view;
			m_game.addCardReceivedObserver(this);
			initialized = true;
		}
				
		m_view.DisplayWelcomeMessage();
		m_view.DisplayDealerHand(m_game.GetDealerHand(), m_game.GetDealerScore());
		m_view.DisplayPlayerHand(m_game.GetPlayerHand(), m_game.GetPlayerScore());

		if (m_game.IsGameOver())
		{
			m_view.DisplayGameOver(a_game.IsDealerWinner());
		}

		m_view.GetInput();

		if (m_view.newGameInput())
		{
			m_game.NewGame();
		}
		else if (m_view.hitInput())
		{
			m_game.Hit();
		}
		else if (m_view.standInput())
		{
			m_game.Stand();
		}

		return !m_view.quitInput();
	}

	public void cardReceived() {
		try {
			m_view.DisplaySeperator();
			m_view.DisplayDealerHand(m_game.GetDealerHand(), m_game.GetDealerScore());
			m_view.DisplayPlayerHand(m_game.GetPlayerHand(), m_game.GetPlayerScore());
			TimeUnit.SECONDS.sleep(4);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}