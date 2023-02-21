package videoPoker;

public class Player {
	private int credits;
	
	public Player(int S){
		this.credits = S;
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
}