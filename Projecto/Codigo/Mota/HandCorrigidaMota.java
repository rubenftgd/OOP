package VideoPoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
	private List<Card> hand = new ArrayList<>();
	private boolean[] hold;
	
	public Hand(Deck deck){
		this.hold = new boolean[5];
		for(int i=0;i<10;i++){
		  	this.hand.add(deck.getOneCard(i));
			if(i<5){
				this.hold[i] = false;
			}
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
		System.out.println("player's hand size: "+ this.hand.size());
		for(int i=0;i<hold.length;i++){
			hand.get(i).printCard();
		}
		System.out.println("");
	}
	
	public void updateHand(){		
		int swapPoint = hold.length;
		for(int i=0;i<hold.length;i++){
			if(this.hold[i]==false){
				Collections.swap(hand, i, swapPoint);
				hand.remove(hold.length);
			}
		}
	}
	
	public void updateHold(int [] setTrue){
		if(setTrue.length == 0) return;
		for(int i=0;i<setTrue.length;i++){
			hold[setTrue[i]]=true;
		}
	}
	
	public void renewHand(Deck deck){
		for(int i=0;i<hold.length;i++){
			//Existem duas maneiras de biscar!!if(mode==1)
			 hand.set(i, deck.getOneCard(hand.size()+i));
			//else hand.set(i, deck.getOneCard(c));
			if(i<hand.size()/2) hold[i]=false;
		}
	}
	
	public void printHold(){
		for(int i=0;i<hold.length;i++) System.out.println(hold[i]);
	}
}
