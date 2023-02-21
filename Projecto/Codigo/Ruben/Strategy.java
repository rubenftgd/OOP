package videoPoker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Strategy {
	private List<Card> handUnsorted = new ArrayList<>();
	private List<Card> handSorted = new ArrayList<>();
	private List<Integer> indicesToHold = new ArrayList<Integer>();
	private int analyserResult;
	
	/**
	 * Constructor of Strategy Class
	 * @param  List<Card> hand
	 * @param  int handResult
	 */
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

	/**
	 * Updates theeStrategy class, gets a new hand and updates the
	 * Strategy attributes 
	 * @param  List<Card> hand
	 * @param  int handResult
	 * @return
	 */
	public void updateStrategy(List<Card> hand, int handResult){
		
		handSorted.clear();
		handUnsorted.clear();
		
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
	/** This method checks if we have a low pair */
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
	
	private int outsideStraight(){
		
		int countOutsideStraight = 0;
		int validator = 0;
		
		for (int j = 0 ; j<4; j++){
			if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue()-1){
				countOutsideStraight++;
			}
			if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue()){
				validator++;
			}
		}
		if(validator >= 2){
			return validator;
		}
		
		return countOutsideStraight;
	}
	
	private String checkFlushSuit(int stopCondition)
	{		
		int diamondsCount = 0;
		int clubsCount = 0;
		int spadesCount = 0;
		int heartsCount = 0;
		
		for (Card e : this.handSorted) {
			if (e.getSuit().equals("H")){
				heartsCount++;
			}
			else if (e.getSuit().equals("C")){
				clubsCount++;
			}
			else if (e.getSuit().equals("S")){
				spadesCount++;
			}
			else if (e.getSuit().equals("D")){
				diamondsCount++;
			}
		}
		
		if (heartsCount == stopCondition)
		{
			return "H";
		}
		else if (spadesCount == stopCondition)
		{
			return "S";
		}
		else if (diamondsCount == stopCondition)
		{
			return "D";
		}
		else if (clubsCount == stopCondition)
		{
			return "C";
		}
		
		return null;
	}
	
	/* This method will gather the indices
	 * and store them in a integer vector
	 * we can later update the hold vector in the Hand Class */
	private void gatherIndexToHold(){
		
		int countHigh = 0;
		String tempSuit = null;
		String validatorSuit = null;
		int auxiliarIterator = 0;
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
		if (checkFlushSuit(4) != null){ /* We have at least 4 cards of same suit */
			tempSuit = checkFlushSuit(4);
			int royalCount = 0;
			for(int f = 0; f<5 ;f++){
				if (handSorted.get(f).getValue() == 14 && handSorted.get(f).getSuit().equals(tempSuit)){
					this.indicesToHold.add(f);
					royalCount++;
					continue;
				}
				else if (handSorted.get(f).getValue() == 13 && handSorted.get(f).getSuit().equals(tempSuit)){
					this.indicesToHold.add(f);
					royalCount++;
					continue;
				}
				else if (handSorted.get(f).getValue() == 12 && handSorted.get(f).getSuit().equals(tempSuit)){
					this.indicesToHold.add(f);
					royalCount++;
					continue;
				}
				else if (handSorted.get(f).getValue() == 11 && handSorted.get(f).getSuit().equals(tempSuit)){
					this.indicesToHold.add(f);
					royalCount++;
					continue;
				}
				else if (handSorted.get(f).getValue() == 10 && handSorted.get(f).getSuit().equals(tempSuit)){
					this.indicesToHold.add(f);
					royalCount++;
					continue;
				}
			}
			if (royalCount < 4){
				this.indicesToHold.clear();
			}
			else{
				return;
			}
		}
		
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
		
		if (checkFlushSuit(4) != null){ /* We have at least 4 cards of same suit */
			tempSuit = checkFlushSuit(4);
			int[] straightCount = new int[5];
			for(int i = 0; i<5 ;i++){
				for(int j = 0; j<5 ;j++){
					if (i==j){
						continue;
					}
					if (handSorted.get(i).getSuit().equals(tempSuit) && 
						handSorted.get(j).getSuit().equals(tempSuit) &&
						(handSorted.get(i).getValue() == handSorted.get(j).getValue() - Math.abs(i-j)-1 ||
						 handSorted.get(i).getValue() == handSorted.get(j).getValue() + Math.abs(i-j)+1 ||
					     handSorted.get(i).getValue() == handSorted.get(j).getValue() - Math.abs(i-j) ||
						 handSorted.get(i).getValue() == handSorted.get(j).getValue() + Math.abs(i-j) ||
						 handSorted.get(0).getValue() == handSorted.get(4).getValue() - 12 ||
						 handSorted.get(0).getValue() == handSorted.get(4).getValue() - 11
								)
					){
						straightCount[i]++;
					}
				}
			}
			
			for (int z = 4; z>=0 ; z--){
				if (straightCount[z]>= 3){
					this.indicesToHold.add(z);
				}
			}
			if(this.indicesToHold.size() != 4)
			{
				this.indicesToHold.clear();
			}
			else{
				return;
			}
		}
		
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
		
		if (checkHighPair()){
			for (int j = 0 ; j<4; j++){
				if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue()){
					this.indicesToHold.add(j);
					this.indicesToHold.add(j+1);
					return;
				}
			}
		}
		/** Case 9 - 4 to a Flush */
		
		if (checkFlushSuit(4) != null){
			tempSuit = checkFlushSuit(4);
			for (int j = 0 ; j<5; j++){
				if(handSorted.get(j).getSuit().equals(tempSuit)){
					this.indicesToHold.add(j);
				}
			}
			return;
		}
		
		/** Case 10 - 3 to a royal Flush */
		
		if (checkFlushSuit(3) != null){ /* We have at least 3 cards of same suit */
			int royalCount = 0;
			for(int f = 0; f<5 ;f++){
				if (handSorted.get(f).getValue() == 14){
					this.indicesToHold.add(f);
					royalCount++;
					continue;
				}
				else if (handSorted.get(f).getValue() == 13){
					this.indicesToHold.add(f);
					royalCount++;
					continue;
				}
				else if (handSorted.get(f).getValue() == 12){
					this.indicesToHold.add(f);
					royalCount++;
					continue;
				}
				else if (handSorted.get(f).getValue() == 11){
					this.indicesToHold.add(f);
					royalCount++;
					continue;
				}
				else if (handSorted.get(f).getValue() == 10){
					this.indicesToHold.add(f);
					royalCount++;
					continue;
				}
			}
			if (royalCount < 3){
				this.indicesToHold.clear();
			}else{
				return;
			}
		}
		
		/** Case 11 - 4 to an outside straight */
		
		if (outsideStraight() == 3){
			for (int j = 0 ; j<4; j++){
				if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue()-1){
					this.indicesToHold.add(j);
				}
			}
			
			if(handSorted.get(3).getValue() == handSorted.get(4).getValue()-1){
				this.indicesToHold.add(4);
			}
			return;
		}
			
		
		/** Case 12 - Low pair */
		
		if (checkPair()){
			for (int j = 0 ; j<4; j++){
				if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue()){
					this.indicesToHold.add(j);
					this.indicesToHold.add(j+1);
					return;
				}
			}
		}
		
		/** Case 13 - AKQJ unsuited */
		
		for (int j = 0 ; j<5; j++){
			if(handSorted.get(j).getValue() == 11){
				handSorted.get(j).getSuit(); 
				this.indicesToHold.add(j);
			}
			else if(handSorted.get(j).getValue() == 12){
				this.indicesToHold.add(j);
			}
			else if(handSorted.get(j).getValue() == 13){
				this.indicesToHold.add(j);
			}
			else if(handSorted.get(j).getValue() == 14){
				this.indicesToHold.add(j);
			}
		}
		if(indicesToHold.size() != 4){
			indicesToHold.clear();
		}else{
			return;
		}
		
		/** Case 14 - 3 to a straight Flush (type 1) */
		
		if (checkFlushSuit(3) != null){ /* We have at least 4 cards of same suit */
			tempSuit = checkFlushSuit(3);
			int[] straightCount = new int[5];
			for(int i = 0; i<5 ;i++){
				for(int j = 0; j<5 ;j++){
					if (i==j){
						continue;
					}
					if (handSorted.get(i).getSuit().equals(tempSuit) && 
						handSorted.get(j).getSuit().equals(tempSuit) &&
						(handSorted.get(i).getValue() == handSorted.get(j).getValue() - Math.abs(i-j)-1 ||
						 handSorted.get(i).getValue() == handSorted.get(j).getValue() + Math.abs(i-j)+1 ||
					     handSorted.get(i).getValue() == handSorted.get(j).getValue() - Math.abs(i-j) ||
						 handSorted.get(i).getValue() == handSorted.get(j).getValue() + Math.abs(i-j) ||
						 handSorted.get(0).getValue() == handSorted.get(4).getValue() - 12 ||
						 handSorted.get(0).getValue() == handSorted.get(4).getValue() - 11
								)
					){
						straightCount[i]++;
					}
				}
			}
			
			for (int z = 4; z>=0 ; z--){
				if (straightCount[z]>= 2){
					this.indicesToHold.add(z);
				}
			}
			if(this.indicesToHold.size() != 3)
			{
				this.indicesToHold.clear();
			}
			else{
				return;
			}
		}
		
		/** Case 15 - 4 to an inside straight with 3 high cards */
		
		countHigh = 0;
		for (int j = 0 ; j<4; j++){
			if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue() - 1){
				this.indicesToHold.add(j);
				auxiliarIterator++;
				if (handSorted.get(j).getValue() > 10){
					countHigh++;
				}
			}
			if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue() - 2){
				this.indicesToHold.add(j+1);
				auxiliarIterator++;
				if (handSorted.get(j+1).getValue() > 10){
					countHigh++;
				}
			}
		}
		if(auxiliarIterator != 4 || countHigh != 3){
			this.indicesToHold.clear();
		}else{
			return;
		}
		
		/** Case 16 - QJ suited */
		
		for (int j = 0 ; j<5; j++){
			if(handSorted.get(j).getValue() == 11){
				validatorSuit = handSorted.get(j).getSuit(); 
				this.indicesToHold.add(j);
			}
			else if(handSorted.get(j).getValue() == 12  && validatorSuit.equals(handSorted.get(j).getSuit())){
				this.indicesToHold.add(j);
			}
		}
		if(indicesToHold.size() != 2){
			indicesToHold.clear();
		}else{
			return;
		}
		
		/** Case 17 - 3 to a flush with 2 high cards */
		
		if(checkFlushSuit(3) != null){
			validatorSuit = checkFlushSuit(3);
			countHigh = 0;
			for (int i = 0; i<5 ; i++){
				if (validatorSuit.equals(handSorted.get(i).getSuit()) && handSorted.get(i).getValue() > 10){
					countHigh++;
					this.indicesToHold.add(i);
				}
			}
			if (countHigh != 2){
				this.indicesToHold.clear();
			}else
			{
				return;
			}
		}
		
		/** Case 18 - 2 suited high cards */ 
		validatorSuit.equals(null);
		int countHighCards = 0;
		for (int i = 0; i<5 ; i++){
			if (handSorted.get(i).getValue() > 10){
				validatorSuit.equals(handSorted.get(i).getSuit()); 
				this.indicesToHold.add(i);
				countHighCards++;
			}
		}
		if(countHighCards != 2)
		{
			this.indicesToHold.clear();
		}else{
			return;
		}
		
		/** Case 19 - 4 to an inside straight with 2 high cards */
		
		countHigh = 0;
		for (int j = 0 ; j<4; j++){
			if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue() - 1){
				this.indicesToHold.add(j);
				auxiliarIterator++;
				if (handSorted.get(j).getValue() > 10){
					countHigh++;
				}
			}
			if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue() - 2){
				this.indicesToHold.add(j+1);
				auxiliarIterator++;
				if (handSorted.get(j+1).getValue() > 10){
					countHigh++;
				}
			}
		}
		if(auxiliarIterator != 4 || countHigh != 2){
			this.indicesToHold.clear();
		}else{
			return;
		}
		
		/** Case 20 - 3 to a straight flush (type 2) */
		
		if (checkFlushSuit(3) != null){ /* We have at least 4 cards of same suit */
			tempSuit = checkFlushSuit(3);
			int[] straightCount = new int[5];
			for(int i = 0; i<5 ;i++){
				for(int j = 0; j<5 ;j++){
					if (i==j){
						continue;
					}
					if (handSorted.get(i).getSuit().equals(tempSuit) && 
						handSorted.get(j).getSuit().equals(tempSuit) &&
						(handSorted.get(i).getValue() == handSorted.get(j).getValue() - Math.abs(i-j)-1 ||
						 handSorted.get(i).getValue() == handSorted.get(j).getValue() + Math.abs(i-j)+1 ||
					     handSorted.get(i).getValue() == handSorted.get(j).getValue() - Math.abs(i-j) ||
						 handSorted.get(i).getValue() == handSorted.get(j).getValue() + Math.abs(i-j) ||
						 handSorted.get(0).getValue() == handSorted.get(4).getValue() - 12 ||
						 handSorted.get(0).getValue() == handSorted.get(4).getValue() - 11
								)
					){
						straightCount[i]++;
					}
				}
			}
			
			for (int z = 4; z>=0 ; z--){
				if (straightCount[z]>= 2){
					this.indicesToHold.add(z);
				}
				if(this.indicesToHold.size() == 3)
				{
					return;
				}
			}
		}
		
		/** Case 21 - 4 to an inside straight with 1 high card */
		
		countHigh = 0;
		for (int j = 0 ; j<4; j++){
			if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue() - 1){
				this.indicesToHold.add(j);
				auxiliarIterator++;
				if (handSorted.get(j).getValue() > 10){
					countHigh++;
				}
			}
			if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue() - 2){
				this.indicesToHold.add(j+1);
				auxiliarIterator++;
				if (handSorted.get(j+1).getValue() > 10){
					countHigh++;
				}
			}
		}
		if(auxiliarIterator != 4 || countHigh != 1){
			this.indicesToHold.clear();
		}else{
			return;
		}
		
		/** Case 22 - KQJ unsuited */
		
		for (int j = 0 ; j<5; j++){
			if(handSorted.get(j).getValue() == 11){
				handSorted.get(j).getSuit(); 
				this.indicesToHold.add(j);
			}
			else if(handSorted.get(j).getValue() == 12){
				this.indicesToHold.add(j);
			}
			else if(handSorted.get(j).getValue() == 13){
				this.indicesToHold.add(j);
			}
		}
		if(indicesToHold.size() != 3){
			indicesToHold.clear();
		}else{
			return;
		}
		
		/** Case 23 - JT suited */
		
		for (int j = 0 ; j<5; j++){
			if(handSorted.get(j).getValue() == 10){
				tempSuit = handSorted.get(j).getSuit(); 
				this.indicesToHold.add(j);
			}
			else if(handSorted.get(j).getValue() == 11 && tempSuit.equals(handSorted.get(j).getSuit())){
				this.indicesToHold.add(j);
			}
		}
		if(indicesToHold.size() != 2){
			indicesToHold.clear();
		}else{
			return;
		}
		
		/** Case 24 - QJ unsuited */
		
		for (int j = 0 ; j<5; j++){
			if(handSorted.get(j).getValue() == 11){
				this.indicesToHold.add(j);
			}
			else if(handSorted.get(j).getValue() == 12){
				this.indicesToHold.add(j);
			}
		}
		if(indicesToHold.size() != 2){
			indicesToHold.clear();
		}else{
			return;
		}
		
		/** Case 25 - 3 to a flush with 1 high card */
		
		if (checkFlushSuit(3) != null){ /* We have at least 3 cards of same suit */
			tempSuit = checkFlushSuit(3);
			countHigh = 0;
			for (int i = 0; i<5 ; i++){
				if (validatorSuit.equals(handSorted.get(i).getSuit()) && handSorted.get(i).getValue() > 10){
					countHigh++;
					this.indicesToHold.add(i);
				}
			}
			if (countHigh != 1){
				this.indicesToHold.clear();
			}else
			{
				return;
			}
		}
		
		/** Case 26 - QT suited */
		
		for (int j = 0 ; j<5; j++){
			if(handSorted.get(j).getValue() == 10){
				tempSuit = handSorted.get(j).getSuit(); 
				this.indicesToHold.add(j);
			}
			else if(handSorted.get(j).getValue() == 12 && tempSuit.equals(handSorted.get(j).getSuit())){
				this.indicesToHold.add(j);
			}
		}
		if(indicesToHold.size() != 2){
			indicesToHold.clear();
		}else{
			return;
		}
		
		/** Case 27 - 3 to a straight Flush (type 3) */
		
		if (checkFlushSuit(3) != null){ /* We have at least 4 cards of same suit */
			tempSuit = checkFlushSuit(3);
			int[] straightCount = new int[5];
			for(int i = 0; i<5 ;i++){
				for(int j = 0; j<5 ;j++){
					if (i==j){
						continue;
					}
					if (handSorted.get(i).getSuit().equals(tempSuit) && 
						handSorted.get(j).getSuit().equals(tempSuit) &&
						(handSorted.get(i).getValue() == handSorted.get(j).getValue() - Math.abs(i-j)-1 ||
						 handSorted.get(i).getValue() == handSorted.get(j).getValue() + Math.abs(i-j)+1 ||
					     handSorted.get(i).getValue() == handSorted.get(j).getValue() - Math.abs(i-j) ||
						 handSorted.get(i).getValue() == handSorted.get(j).getValue() + Math.abs(i-j) ||
						 handSorted.get(0).getValue() == handSorted.get(4).getValue() - 12 ||
						 handSorted.get(0).getValue() == handSorted.get(4).getValue() - 11
								)
					){
						straightCount[i]++;
					}
				}
			}
			
			for (int z = 4; z>=0 ; z--){
				if (straightCount[z]>= 2){
					this.indicesToHold.add(z);
				}
			}
			if(this.indicesToHold.size() != 3)
			{
				this.indicesToHold.clear();
			}else{
				return;
			}
		}
		
		/** Case 28 - KQ, KJ unsuited */
		
		for (int j = 0 ; j<5; j++){
			if(handSorted.get(j).getValue() == 12){ 
				this.indicesToHold.add(j);
			}
			else if(handSorted.get(j).getValue() == 13){
				this.indicesToHold.add(j);
			}
		}
		if(indicesToHold.size() != 2){
			indicesToHold.clear();
		}else{
			return;
		}
		
		for (int j = 0 ; j<5; j++){
			if(handSorted.get(j).getValue() == 11){ 
				this.indicesToHold.add(j);
			}
			else if(handSorted.get(j).getValue() == 13){
				this.indicesToHold.add(j);
			}
		}
		if(indicesToHold.size() != 2){
			indicesToHold.clear();
		}else{
			return;
		}
		
		/** Case 29 - Ace */
		for (int j = 0 ; j<5; j++){
			if(handSorted.get(j).getValue() == 14){
				this.indicesToHold.add(j);
				return;
			}
		}
		/** Case 30 - KT suited */
		
		for (int j = 0 ; j<5; j++){
			if(handSorted.get(j).getValue() == 10){
				tempSuit = handSorted.get(j).getSuit(); 
				this.indicesToHold.add(j);
			}
			else if(handSorted.get(j).getValue() == 13 && tempSuit.equals(handSorted.get(j).getSuit())){
				this.indicesToHold.add(j);
			}
		}
		if(indicesToHold.size() != 2){
			indicesToHold.clear();
		}else{
			return;
		}
		
		/** Case 31 - Jack, Queen or King */
		int jack = -1;
		int queen = -1;
		
		for (int j = 0 ; j<5; j++){
			if(handSorted.get(j).getValue() == 13){
				this.indicesToHold.add(j);
				return;
			}
			else if(handSorted.get(j).getValue() == 12){
				queen = j;
			}
			else if(handSorted.get(j).getValue() == 11){
				jack = j;
			}
		}
		if(queen != -1){
			this.indicesToHold.add(queen);
			return;
		}
		if (jack != -1){
			this.indicesToHold.add(jack);
			return;
		}
		
		/** Case 32 - 4 to an inside straight with no high cards */
		for (int j = 0 ; j<4; j++){
			if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue() - 1){
				this.indicesToHold.add(j);
				auxiliarIterator++;
			}
			if(handSorted.get(j).getValue() == handSorted.get(j+1).getValue() - 2){
				this.indicesToHold.add(j+1);
				auxiliarIterator++;
			}
		}
		if(auxiliarIterator != 4){
			this.indicesToHold.clear();
		}else{
			return;
		}
		
		/** Case 33 - 3 to a Flush with no high cards */
		
		if (checkFlushSuit(3) != null){
			tempSuit = checkFlushSuit(3);
			for (int j = 0 ; j<5; j++){
				if(handSorted.get(j).getSuit().equals(tempSuit)){
					this.indicesToHold.add(j);
				}
			}
			return;
		}
		
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
