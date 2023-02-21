package videoPoker;

import java.util.ArrayList;

public class MainTestes {
	public static void main(String[] args){
		
		ArrayList<String> cardsVector = new ArrayList<String>();;
		FileHandler fileHandler = new FileHandler("./src/videoPoker/teste.txt");
		
		cardsVector = fileHandler.getCardsVector();
		Deck deck = new Deck(cardsVector);
		
		Hand hand = new Hand(deck.getDeck());
		
		Analyser analyser = new Analyser(hand.getHand());
		
		Strategy strategy = new Strategy(hand.getHand(),analyser.getCheckerResult());
		for (int i = 0; i<4; i++){
			
			strategy.holdPositionCorrector();
			
			for (Card b : strategy.getHandSorted()){
				System.out.println(b.getRank()+b.getSuit());
			}
			for (int a : strategy.getIndicesToHold()){
				System.out.println("Guarda a carta numero = " + a);
			}
			System.out.println("" );
			
			deck.updateRemoveDeck();
			hand.renewHand(deck.getDeck());
			analyser.updateDraw(hand.getHand());
			strategy.updateStrategy(hand.getHand(), analyser.getCheckerResult());
			
			for (int b : strategy.getIndicesToHold())
			{
				hand.updateHold(b);
			}
			hand.updateHand();
		}
	}		
}