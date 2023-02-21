package videoPoker;

public class Player {
	private int credits;
	private int initialCredits;
//	private int previousBet;
	
	public Player(int S){
		this.credits = S;
		this.initialCredits = S;
//		this.previousBet = -1;
	}
//	public void setBet(int bet){
//		previousBet = bet;
//	}
	
//	public int getBet(){
//		return previousBet;
//	}
	public int getInitialCredits(){
		return initialCredits;
	}
	
	public void rewards (int prize){
		this.credits+=prize;
	}
	
	public void bet(int bet){
		if(this.credits>= bet) 
			this.credits-=bet;
	}
	
	public int getCredits(){
		return credits;
	}
	
	public void printCredits(){
		System.out.println("Player's credits: "+this.credits);
	}
	
	public float getWinningPercentage(){
		return (float)(100*(double)credits/(double)initialCredits);
	}
}