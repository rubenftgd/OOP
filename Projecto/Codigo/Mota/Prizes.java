package videoPoker;

import java.util.List;

public class Prizes {
	private int[] rewards;
	private int doubleBonus; /* Wont be much of a help */
	
	public int[] getRewards() {
		return rewards;
	}

	public int getDoubleBonus() {
		return doubleBonus;
	}

	public void setRewards(int[] rewards) {
		this.rewards = rewards;
	}

	public void setDoubleBonus(int doubleBonus) {
		this.doubleBonus = doubleBonus;
	}

	public Prizes()
	{
		/* In this constructor we create a vector called rewards
		 * in this vector the index of the integer array means the result of the hand
		 * and the number on that index is the value to multiply for the bet soo the
		 * player can get payed*/
		
		/** TODO PROFESSORA */
		setDoubleBonus(1); /** If the professor changes this parameter you can change
		the game mode */
		
		rewards = new int[12];
		
		if (doubleBonus == 1)
		{
			rewards[0] = 1; /* Pair*/
			rewards[1] = 1; /* 2 Pairs*/
			rewards[2] = 3; /* Three of a Kind*/
			rewards[3] = 5; /* Straight */
			rewards[4] = 7; /* Flush*/
			rewards[5] = 10; /* Full House */
			rewards[6] = 50; /* Poker 5 to K*/
			rewards[7] = 80; /* Poker 2-4*/
			rewards[8] = 160; /* Poker Aces */
			rewards[9] = 50; /* Straight Flush */
			rewards[10] = 250; /* Royal FLush */
			rewards[11] = 800; /* Special case for Royal flush with bet 5 */
		}
		else
		{
			/* Implements other game mode instead of the 10 7 */
		}
	}
	
	public int getRewardValue (int bet, int handResult , List<Card> sortedHand)
	{
		int rewardValue;
		switch (handResult) {
        
		case 0: /* High Pair */
			rewardValue = bet * rewards[handResult] + bet;
        	break;
        
        case 1: /* 2 Pairs */
        	rewardValue = bet * rewards[handResult] + bet;
        	break;
        
        case 2: /* Three of a Kind */
    		rewardValue = bet * rewards[handResult] + bet;
        	break;
        
        case 3: /* Straight */
    		rewardValue = bet * rewards[handResult] + bet;
            break;
        
        case 4: /* Flush */
    		rewardValue = bet * rewards[handResult] + bet;
            break;
        
        case 5: /* Full House */
    		rewardValue = bet * rewards[handResult] + bet;
        	break;
        
        case 6: /* Poker */
        	if (sortedHand.get(3).getValue() >= 5 && sortedHand.get(3).getValue() <= 13){ /* 5-K Poker */
        		rewardValue = bet * rewards[handResult] + bet;
        	}
        	else if (sortedHand.get(3).getValue() == 14){ /* Ace Poker */
        		rewardValue = bet * rewards[handResult+2] + bet;
        	}
        	else /* 2-4 Poker */
        	{
        		rewardValue = bet * rewards[handResult+1] + bet;
        	}
            break;
        
        case 7: /* Straight Flush */
        	rewardValue = bet * rewards[handResult+2] + bet;
            break;
        
        case 8: /* Royal Flush */
                if(bet == 5)
                {
                	rewardValue = bet * rewards[handResult+3] + bet;
                }
                else{
                	rewardValue = bet * rewards[handResult+2] + bet;
                }
        		break;
        
        case 9: /* Nothing in your hand... Feel Sorry */
        		rewardValue = 0;
                break;
        default:
        	System.out.println("Invalid Hand Result");
        	/** Throw Exception here */
        	/* Remove this line with rewardValue */
        	rewardValue = 0;
        	break;
    }
		return rewardValue;
	}
	
}
