package videoPoker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Analyser {

	/* Draw differs from the hand because
	 * draw has only 5 cards instead of 10 */
	private List<Card> draw = new ArrayList<>();

	/* Get an ArrayList of at max size 10
	 * but converts it to size 5 */
	public Analyser (List<Card> hand){
		for (int i=0; i < 5; i++){
			this.draw.add(hand.get(i));
		}
		sorting();
	}
	
	public void updateDraw (List<Card> hand){
		draw.clear();
		for (int i=0; i < 5; i++){
			this.draw.add(hand.get(i));
		}
		sorting();
	}
	
	public List<Card> getDraw() {
		return draw;
	}

	public void setDraw(List<Card> draw) {
		this.draw = draw;
	}

	public void sorting (){
		draw.sort(new Comparator<Card>(){

			@Override
			public int compare(Card a, Card b) {
				
				/** Return 1 if true - Meaning A bigger than B 
				 *  Return -1 if true - Meaning A smaller than B */
				return a.getValue()>b.getValue()? 1:-1;
			}
		});	
	}
	
	private boolean checkFlush()
	{
		/* Need a new name for this variable */
		/** This gets the first card's suit that is in the draw*/
		String iterationSuit = draw.get(0).getSuit();
		
		for (int i = 1; i< this.draw.size(); i++)
		{
			if(!iterationSuit.equals(draw.get(i).getSuit())){
				return false;
			}
		}
		return true;
	}
	
	private boolean checkStraight()
	{
		/* Need a new name for this variable */
		/** This gets the first card's suit that is in the draw*/
		int iterationValue = draw.get(0).getValue();
		
		for (int i = 1; i < this.draw.size(); i++ , iterationValue++)
		{
			if(iterationValue != draw.get(i).getValue()-1 && 
					draw.get(3).getValue() != draw.get(4).getValue() - 9){
				/** This OR in the if , is to see if the A is in the end
				 * with a 5 preceding him*/
				return false;
			}
		}
		return true;
	}
	
	private boolean checkPair()
	/** This method checks if there is at least a pair of Jacks or Higher */
	{
		/* Need a new name for this variable */
		int iterationValue = draw.get(0).getValue();
		
		for (int i = 1; i < this.draw.size(); i++)
		{
			if(iterationValue == draw.get(i).getValue()){
				return true;
			}
			iterationValue = draw.get(i).getValue();
		}
		return false;
	}
	
	private boolean checkHighPair()
	/** This method checks if there is at least a pair of Jacks or Higher */
	{
		/* Need a new name for this variable */
		int iterationValue = draw.get(0).getValue();
		
		for (int i = 1; i < this.draw.size(); i++)
		{
			if(iterationValue == draw.get(i).getValue() && iterationValue >= 10){
				return true;
			}
			iterationValue = draw.get(i).getValue();
		}
		return false;
	}
	
	private boolean check2Pairs()
	/** This method checks if there is at least a pair */
	{
		int numberOfPairs = 0;
		/* Need a new name for this variable */
		int iterationValue = draw.get(0).getValue();
		int cardValue = 0;
		
		for (int i = 1; i < this.draw.size(); i++)
		{
			if(iterationValue == draw.get(i).getValue() && iterationValue != cardValue){
				numberOfPairs++;
				cardValue = draw.get(i).getValue();
			}
			
			if(numberOfPairs == 2)
			{
				if (iterationValue != draw.get(i-2).getValue()){
					return true;	
				}
			}
			iterationValue = draw.get(i).getValue();
		}
		return false;
	}
	
	private boolean checkThree()
	/** This method checks if there is at least a three of a kind */
	{
		int checkPair = 1;
		/* Need a new name for this variable */
		int iterationValue = draw.get(0).getValue();
		
		for (int i = 1; i < this.draw.size(); i++)
		{
			if(iterationValue == draw.get(i).getValue()){
				checkPair++;
			}
			else{
				checkPair = 1;
			}
			if (checkPair == 3)
			{
				return true;
			}
			iterationValue = draw.get(i).getValue();
		}
		return false;
	}
	
	private boolean checkPoker()
	/** This method checks if we have a poker. 4 of a kind */
	{
		int checkTriplePair = 1;
		/* Need a new name for this variable */
		int iterationValue = draw.get(0).getValue();
		
		for (int i = 1; i < this.draw.size(); i++)
		{
			if(iterationValue == draw.get(i).getValue()){
				checkTriplePair++;
			}
			else{
				checkTriplePair = 1;
			}
			if (checkTriplePair == 4)
			{
				return true;
			}
			iterationValue = draw.get(i).getValue();
		}
		return false;
	}
	
	public int getCheckerResult(){
		
		/** This is the integer that we will
		 *  return from this function. Depending
		 *  on its value, we result we have on our hand */
		
		int analyseResult = 9; 
		/** This number means that the player has nothing on the hand */
		
		/* Check Straight */
		if (checkStraight())
		{
			if (checkFlush()) /* We have Straight flush */
			{
				analyseResult = 7;
				
				if (draw.get(2).getValue() == 10) /* We have Royal Flush */
				{
					analyseResult = 8;
				}
			}
			else{ /* We only have Straight */
				analyseResult = 3;
			}
		}
		
		/* Check Flush */
		else if (checkFlush())
		{
			analyseResult = 4;
		}
		
		/* Check at least one Pair */
		else if (checkPair()) /* Low Pair */
		{
			if (checkHighPair()) /* High valid Pair */
			{
				analyseResult = 0;
			}
			
			if (check2Pairs()) /* Two Pairs */
			{
				analyseResult = 1;
				
				if (checkThree()) /* Full House */
				{
					analyseResult = 5;
				}
			}
			
			else if (checkThree()) /* Three of a kind */
			{
				analyseResult = 2;
			}
			
			else if (checkPoker()) /* Poker */
			{
				analyseResult = 6;
			}
		}
		
		return analyseResult;
		
	}
	
	public String printHandResult(){
		switch (getCheckerResult()) {
        
		case 0: /* High Pair */
			return "Jacks or Better";
        
        case 1: /* 2 Pairs */
        	return "Two Pair";
        
        case 2: /* Three of a Kind */
        	return "Three of a Kind";
        
        case 3: /* Straight */
        	return "Straight";
        
        case 4: /* Flush */
        	return "Flush";
        
        case 5: /* Full House */
        	return "Full House";
        
        case 6: /* Poker */
        	return "Four of a Kind";
        	
        case 7: /* Straight Flush */
        	return "Straight Flush";
        
        case 8: /* Royal Flush */
        	return "Royal Flush";

        case 9: /* Other */
        	return "Other";
        	
        default:
        	return "Invalid Hand Result";
		}
	}
	
	public void printDraw()
	{
		for(int i=0;i<draw.size();i++)
		{
			draw.get(i).printCard();
		}
		System.out.println("");
	}
	
}
