package blackjack.view;

public class SimpleView implements IView 
{
	private int input;

  public void DisplayWelcomeMessage()
        {
          for(int i = 0; i < 50; i++) {System.out.print("\n");}; 
          System.out.println("Hello Black Jack World");
          System.out.println("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
        }

        public void GetInput()
        {
        	System.out.print("\nYour choice: ");
          try {
            int c = System.in.read();
            while (c == '\r' || c =='\n') {
              c = System.in.read();
            }
            input = c;
          } catch (java.io.IOException e) {
            System.out.println("" + e);
          }
        }

        public void DisplayCard(blackjack.model.Card a_card)
        {
            System.out.println("" + a_card.GetValue() + " of " + a_card.GetColor());
        }

        public void DisplayPlayerHand(Iterable<blackjack.model.Card> a_hand, int a_score)
        {
            DisplayHand("Player", a_hand, a_score);
        }

        public void DisplayDealerHand(Iterable<blackjack.model.Card> a_hand, int a_score)
        {
            DisplayHand("Dealer", a_hand, a_score);
        }

        private void DisplayHand(String a_name, Iterable<blackjack.model.Card> a_hand, int a_score)
        {
            System.out.println(a_name + " Has: ");
            for(blackjack.model.Card c : a_hand)
            {
                DisplayCard(c);
            }
            System.out.println("Score: " + a_score);
            System.out.println("");
        }

        public void DisplayGameOver(boolean a_dealerIsWinner)
        {
            System.out.println("GameOver: ");
            if (a_dealerIsWinner)
            {
                System.out.println("Dealer Won!");
            }
            else
            {
                System.out.println("You Won!");
            }
            
        }
        
        public void DisplaySeperator() {
        	System.out.println("\n===============================================================\n");
        }

		public boolean newGameInput() {
			return input == 'p';
		}

		public boolean hitInput() {
			return input == 'h';
		}

		public boolean standInput() {
			return input == 's';
		}
		
		public boolean quitInput() {
			return input == 'q';
		}


    }
