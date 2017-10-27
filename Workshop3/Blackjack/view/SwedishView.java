package blackjack.view;

public class SwedishView implements IView 
    {
		private int input;
		
        public void DisplayWelcomeMessage()
        {
         
            for(int i = 0; i < 50; i++) {System.out.print("\n");};

            System.out.println("Hej Black Jack Världen");
            System.out.println("----------------------");
            System.out.println("Skriv 'p' för att Spela, 'h' för nytt kort, 's' för att stanna 'q' för att avsluta\n");
        }
        
        public void GetInput()
        {
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
            if (a_card.GetColor() == blackjack.model.Card.Color.Hidden)
            {
                System.out.println("Dolt Kort");
            }
            else
            {
                String colors[] = 
                    { "Hjärter", "Spader", "Ruter", "Klöver" };
                String values[] =  
                    { "två", "tre", "fyra", "fem", "sex", "sju", "åtta", "nio", "tio", "knekt", "dam", "kung", "ess" };
                System.out.println("" + colors[a_card.GetColor().ordinal()] + " " + values[a_card.GetValue().ordinal()]);
            }
        }
        public void DisplayPlayerHand(Iterable<blackjack.model.Card> a_hand, int a_score)
        {
            DisplayHand("Spelare", a_hand, a_score);
        }
        public void DisplayDealerHand(Iterable<blackjack.model.Card> a_hand, int a_score)
        {
            DisplayHand("Croupier", a_hand, a_score);
        }
        
        public void DisplayGameOver(boolean a_dealerIsWinner)
        {
            System.out.println("Slut: ");
            if (a_dealerIsWinner)
            {
                System.out.println("Croupiern Vann!");
            }
            else
            {
                System.out.println("Du vann!");
            }
        }

        private void DisplayHand(String a_name, Iterable<blackjack.model.Card> a_hand, int a_score)
        {
            System.out.println(a_name + " Har: " + a_score);
            for(blackjack.model.Card c : a_hand)
            {
                DisplayCard(c);
            }
            System.out.println("Poäng: " + a_score);
            System.out.println("");
        }
        
        public void DisplaySeperator() {
        	System.out.println("\n===============================================================");
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
