package videoPoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
	private List<Card> hand = new ArrayList<>();
	private boolean[] hold;
	
	public Hand(List<Card> deck)
	{ 
		if (deck.size()<10){
			System.out.println("Not enough cards to play");
			System.exit(-5);
			} 
		this.hold = new boolean[5];
		for(int i=0;i<10;i++)
		{
		  	this.hand.add(deck.get(i));
		}
		
	}
	
	public List<Card> getHand() {
		return hand;
	}

	public boolean[] getHold() {
		return hold;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public void setHold(boolean[] hold) {
		this.hold = hold;
	}

	public void printHand(){
		System.out.print("player's hand : ");
		for(int i=0;i<hold.length;i++)
		{
			hand.get(i).printCard();
		}
		System.out.println("");
	}
	
	public void updateHand(){		
		int swapPoint = hold.length;
		for(int i=0;i<hold.length;i++)
		{
			if(this.hold[i]==false){
				Collections.swap(hand, i, i+5);
			}
		}
	}
	
	public void updateHold(int setTrue){
			hold[setTrue]=true;
	}
	
	public void renewHand(List<Card> deck){
		
		for(int i=0;i<hand.size();i++)
		{	
			hand.set(i, deck.get(i));
			//else hand.set(i, deck.getOneCard(c));
		
			if(i<hand.size()/2)
			{
				hold[i]=false;
			}
		}
	}
	
	public void printHold()
	{
		for(int i=0;i<hold.length;i++)
		{
			System.out.println(hold[i]);
		}
	}
}
