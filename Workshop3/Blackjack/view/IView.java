package blackjack.view;

public interface IView
{
  void DisplayWelcomeMessage();
  void GetInput();
  void DisplayCard(blackjack.model.Card a_card);
  void DisplayPlayerHand(Iterable<blackjack.model.Card> a_hand, int a_score);
  void DisplayDealerHand(Iterable<blackjack.model.Card> a_hand, int a_score);
  void DisplayGameOver(boolean a_dealerIsWinner);
  void DisplaySeperator();
  boolean newGameInput();
  boolean hitInput();
  boolean standInput();
  boolean quitInput();
}
