import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Analyser {
	private List<Card> draw = new ArrayList<>();
	
	public Analyser (List<Card> draw){
		this.draw = draw;
	}
	
	public int getResult(){
		int cardValue1, cardValue2, cardValue3, cardValue4, cardValue5;
		int result = 0;
	
		draw.sort(new Comparator<Card>(){

			@Override
			public int compare(Card a, Card b) {
				
				return a.getValue()>b.getValue()? 1:-1;
			}
		});
		
		cardValue1 = draw.get(0).getValue();
		cardValue2 = draw.get(1).getValue();
		cardValue3 = draw.get(2).getValue();
		cardValue4 = draw.get(3).getValue();
		cardValue5 = draw.get(4).getValue();
	
		if(cardValue1==cardValue2 || cardValue2==cardValue3 || cardValue3==cardValue4 || cardValue4==cardValue5){
			
			result++;
			if((cardValue1==cardValue2 && cardValue2==cardValue3) || (cardValue2==cardValue3 && cardValue3==cardValue4) || (cardValue3==cardValue4 && cardValue4==cardValue5)){
				result+=2;
				if(((cardValue1==cardValue2) && (cardValue2==cardValue3) && (cardValue3==cardValue4)) || ((cardValue1==cardValue2) && (cardValue2==cardValue3) && (cardValue3==cardValue4)))
					result++;
			} 
			if((cardValue1==cardValue2 && cardValue3==cardValue4) || (cardValue2==cardValue3 && cardValue4==cardValue5) || (cardValue1==cardValue2) && (cardValue4==cardValue5))
				result++;
			if(((cardValue1==cardValue2 && cardValue1>10) || (cardValue2==cardValue3 && cardValue2>10) || (cardValue3==cardValue4 && cardValue3>10) || (cardValue4==cardValue5 && cardValue4>10)==false) && result==1)
				result--;
			System.out.println(result);
			return result;			
		}
		
		if(draw.get(0).getSuit().equals(draw.get(1).getSuit()) && draw.get(1).getSuit().equals(draw.get(2).getSuit()) 
				&& draw.get(2).getSuit().equals(draw.get(3).getSuit()) && draw.get(3).getSuit().equals(draw.get(4).getSuit()))
			result+=7;
		if((cardValue1==cardValue2-1) && (cardValue2==cardValue3-1) && (cardValue3==cardValue4-1) && ((cardValue4==cardValue5-1) || (cardValue5==14) && cardValue4==5))
			result+=6;
		if(result==13){
			result=8;
			if(cardValue4==13)
				result++;
		}
		System.out.println(result);
		return result;							
		}
}
