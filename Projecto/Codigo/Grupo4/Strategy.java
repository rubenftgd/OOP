package videoPoker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Strategy {
	private List<Card> handUnsorted = new ArrayList<>();
	private List<Card> handSorted = new ArrayList<>();
	private List<Integer> indicesToHold = new ArrayList<Integer>();
	private int analyserResult;
	
	public Strategy(List<Card> hand, int handResult){
		
		/** This integer comes from the function
		 *  getCheckerResult in the Analyser class */
		this.analyserResult = handResult;
		
		for (int i=0; i < 5; i++){
			this.handUnsorted.add(hand.get(i));
			this.handSorted.add(hand.get(i));
		}
		/** Sorts the hand */
		sorting();
	}

	private void sorting (){
		handSorted.sort(new Comparator<Card>(){

			@Override
			public int compare(Card a, Card b) {
				
				/** Return 1 if true - Meaning A bigger than B 
				 *  Return -1 if true - Meaning A smaller than B */
				return a.getValue()>b.getValue()? 1:-1;
			}
		});	
	}
	
	public void holdPositionCorrector()
	{
		gatherIndexToHold();
		
		for(int i = 0; i < this.indicesToHold.size(); i++){
			
			/* What this line does is gets the element of the Sorted and that is in the indicesToHold
			 * Then gets the index of the same element in the handUnsorted 
			 */
			this.indicesToHold.set(i, handUnsorted.indexOf(handSorted.get(indicesToHold.get(i))));
		}
	}
	
	public void setIndicesToHold(List<Integer> indicesToHold) {
		this.indicesToHold = indicesToHold;
	}

	private boolean checkThreeAces()
	/** This method checks if there is at least a three of a kind */
	{
		int checkPair = 1;
		/* Need a new name for this variable */
		int iterationValue = handSorted.get(0).getValue();
		
		for (int i = 1; i < this.handSorted.size(); i++)
		{
			if(iterationValue == handSorted.get(i).getValue() && iterationValue == 14){
				checkPair++;
			}
			else{
				checkPair = 1;
			}
			if (checkPair == 3)
			{
				return true;
			}
			iterationValue = handSorted.get(i).getValue();
		}
		return false;
	}
	
	private boolean checkThree()
	/** This method checks if there is at least a three of a kind */
	{
		int checkPair = 1;
		/* Need a new name for this variable */
		int iterationValue = handSorted.get(0).getValue();
		
		for (int i = 1; i < this.handSorted.size(); i++)
		{
			if(iterationValue == handSorted.get(i).getValue() && iterationValue != 14){
				checkPair++;
			}
			else{
				checkPair = 1;
			}
			if (checkPair == 3)
			{
				return true;
			}
			iterationValue = handSorted.get(i).getValue();
		}
		return false;
	}
	
	private boolean checkPair()
	/** This method checks if there is at least a pair of Jacks or Higher */
	{
		/* Need a new name for this variable */
		int iterationValue = handSorted.get(0).getValue();
		
		for (int i = 1; i < this.handSorted.size(); i++)
		{
			if(iterationValue == handSorted.get(i).getValue()){
				return true;
			}
			iterationValue = handSorted.get(i).getValue();
		}
		return false;
	}
	
	private boolean checkHighPair()
	/** This method checks if there is at least a pair of Jacks or Higher */
	{
		/* Need a new name for this variable */
		int iterationValue = handSorted.get(0).getValue();
		
		for (int i = 1; i < this.handSorted.size(); i++)
		{
			if(iterationValue == handSorted.get(i).getValue() && iterationValue >= 10){
				return true;
			}
			iterationValue = handSorted.get(i).getValue();
		}
		return false;
	}
	
	/* This method will gather the indices
	 * and store them in a integer vector
	 * we can later update the hold vector in the Hand Class */
	private void gatherIndexToHold(){
		
		this.indicesToHold.clear();
		
		/** Case 1 - Straight Flush, four of a kind, royal Flush */
		if (analyserResult == 8){ /* Royal flush */
			holdAll();
			return;
		}
		else if (analyserResult == 7){ /* Straight Flush */
			holdAll();
			return;
		}
		else if (analyserResult == 6){ /* Poker */
			if(handSorted.get(0).getValue() == handSorted.get(1).getValue()){
				for(int i = 0; i < 4; i++){
					this.indicesToHold.add(i);
				}
			}
			else{
				for(int i = 1; i < 5; i++){
					this.indicesToHold.add(i);
				}
			}
			return;
		}
		
		/** Case 2 - 4 to a royal Flush */
		
		/** Case 3 - Three aces */
		
		if(checkThreeAces()){
			for(Card d : handSorted){
				if (d.getValue() == 14){
					this.indicesToHold.add(handSorted.indexOf(d));
					this.indicesToHold.add(handSorted.indexOf(d)+1);
					this.indicesToHold.add(handSorted.indexOf(d)+2);
					return;
				}
			}
		}
		
		/** Case 4 - Straight, flush, full house */
		
		if (analyserResult == 5){ /* Full House */
			holdAll();
			return;
		}
		else if (analyserResult == 4){ /* Flush */
			holdAll();
			return;
		}
		else if (analyserResult == 3){ /* Straight */
			holdAll();
			return;
		}	
		
		/** Case 5 - Three of a kind (except aces) */
		
		if(checkThree()){
			for(Card d : handSorted){
				if (d.getValue() != 14){
					this.indicesToHold.add(handSorted.indexOf(d));
					this.indicesToHold.add(handSorted.indexOf(d)+1);
					this.indicesToHold.add(handSorted.indexOf(d)+2);
					return;
				}
			}
		}
		
		/** Case 6 - 4 to a straight flush */
		
		/** Case 7 - Two pair */
		
		if (analyserResult == 1){ /* Two Pair */
			for (int j = 0 ; j<4; j++){
				if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue()){
					this.indicesToHold.add(j);
					this.indicesToHold.add(j+1);
				}
			}
			return;
		}
		
		/** Case 8 - High pair */
		
		/** Case 9 - 4 to a Flush */
		
		/** Case 10 - 3 to a royal Flush */
		
		/** Case 11 - 4 to an outside straight */
		
		/** Case 12 - Low pair */
		
		/** Case 13 - AKQJ unsuited */
		
		/** Case 14 - 3 to a straight Flush (type 1) */
		
		/** Case 15 - 4 to an inside straight with 3 high cards */
		
		/** Case 16 - QJ suited */
		
		/** Case 17 - 3 to a flush with 2 high cards */
		
		/** Case 18 - 2 suited high cards */ 
		
		/** Case 19 - 4 to an inside straight with 2 high cards */
		
		/** Case 20 - 3 to a straight flush (type 2) */
		
		/** Case 21 - 4 to an inside straight with 1 high card */
		
		/** Case 22 - KQJ unsuited */
		
		/** Case 23 - JT suited */
		
		/** Case 24 - QJ unsuited */
		
		/** Case 25 - 3 to a flush with 1 high card */
		
		/** Case 26 - QT suited */
		
		/** Case 27 - 3 to a straight Flush (type 3) */
		
		/** Case 28 - KQ, KJ unsuited */
		
		/** Case 29 - Ace */
		
		/** Case 30 - KT suited */
		
		/** Case 31 - Jack, Queen or King */
		
		/** Case 32 - 4 to an inside straight with no high cards */
		
		/** Case 33 - 3 to a Flush with no high cards */
		
		/** Case 34 - Discard everything */
		return;
	}
	
	private void holdAll(){
		for(int i = 0; i < 5; i++){
			this.indicesToHold.add(i);
		}
	}
	
	public List<Card> getHandUnsorted() {
		return handUnsorted;
	}

	public List<Integer> getIndicesToHold() {
		return indicesToHold;
	}

	public int getAnalyserResult() {
		return analyserResult;
	}

	public List<Card> getHandSorted() {
		return handSorted;
	}
}
