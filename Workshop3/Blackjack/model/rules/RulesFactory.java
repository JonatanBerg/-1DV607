package blackjack.model.rules;

public class RulesFactory {

  public IHitStrategy GetHitRule() {
    return new Soft17();
  }

  public INewGameStrategy GetNewGameRule() {
    return new AmericanNewGameStrategy();
  }
  
  public IWinStrategy GetWinRule() {
	  return new DealerWinStrategy();
  }
}