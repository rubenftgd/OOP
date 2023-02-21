package VideoPoker;

public class Table {
	private Card[] draw;
	private boolean[] hold;
	
	public void updateDraw(Deck deck){
		for(int i=0;i<5;i++){
			if(hold[i]==false){
			//	draw[i]=deck.getNewCard();
			}
		}
	}
	
	public void printDraw(){
		for(int i=0;i<5;i++){
			System.out.println(this.draw[i].toString());
		}
	}
	
	public void setFalseHold(int pos[]){
		for(int i=0;i<5;i++){
			if(pos[i]==1) hold[i]=false;
		}
	}
	
}
