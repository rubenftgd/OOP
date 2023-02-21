package videoPoker;

public class Statistic {
	private int[] stats;
	private float gainPercentage;
	private int credit;
	private int initialCredit;
	
	public int[] getStats() {
		return stats;
	}
	
//	public int getCredit() {
//		return credit;
//	}
	
	public void setStats(int[] stats) {
		this.stats = stats;
	}
	
//	public void setCredit(int Credit) {
//		this.credit = Credit;
//	}
	
	public Statistic(int playerInitialCredit)
	{
		stats = new int[12];
		for (int i = 0; i < 12 ; i++){
			stats[i] = 0;
		}
		
		this.credit = playerInitialCredit;
		this.setGainPercentage(0);
	}
	
	public void updateStats(int analyseResult , float gainPercentage, int playerActualCredit)
	{
		stats[analyseResult]++;
		stats[10]++;
		stats[11] = playerActualCredit;
		this.setGainPercentage(gainPercentage*100);
	}
	
	public String fixedLength(String str, int length){
        return String.format("%1$"+length+"s", str).replace(' ', '-');
	}
	
	public String fixedLengthString(String str, int length){
        return String.format("%1$"+length+"s", str);
	}
	
	public void printStats(int credits)
	{
		int maxLength = String.valueOf(this.initialCredit).length()+String.valueOf(this.gainPercentage).length()+4;
	
		if(String.valueOf(stats[10]).length()>maxLength)
			maxLength=10;
		
		int[] length = new int[11];
		
		for(int i=0; i<11; i++)
			length[i] = String.valueOf(stats[i]).length();
			
		String[] str = new String[16];
		str[0] = "Hand              |"+fixedLengthString("",(maxLength/2)-1)+"Nb";
		str[1] = "------------------+";
		str[2] = "Jacks or Better   |"+fixedLengthString("",maxLength/2-length[0]/2)+stats[0];
		str[3] = "Two Pair          |"+fixedLengthString("",maxLength/2-length[1]/2)+stats[1];
		str[4] = "Three of a Kind   |"+fixedLengthString("",maxLength/2-length[2]/2)+stats[2];
		str[5] = "Straight          |"+fixedLengthString("",maxLength/2-length[3]/2)+stats[3];
		str[6] = "Flush             |"+fixedLengthString("",maxLength/2-length[4]/2)+stats[4];
		str[7] = "Full House        |"+fixedLengthString("",maxLength/2-length[5]/2)+stats[5];
		str[8] = "Four of a Kind    |"+fixedLengthString("",maxLength/2-length[6]/2)+stats[6];
		str[9] = "Stright Flush     |"+fixedLengthString("",maxLength/2-length[7]/2)+stats[7];
		str[10] = "Royal Flush       |"+fixedLengthString("",maxLength/2-length[8]/2)+stats[8];
		str[11] = "Other             |"+fixedLengthString("",maxLength/2-length[9]/2)+stats[9];
		str[13] = "Total             |"+fixedLengthString("",maxLength/2-length[10]/2)+stats[10];
		str[15] = "Credit            | "+credits+" ("+this.gainPercentage+"%)";
		
		/* This print in maxLength +1*/
		str[1]=str[1]+fixedLength("",maxLength+1);
		str[12]=str[1];
		str[14]=str[1];
		for(int i=0; i<16; i++){
			System.out.println(str[i]);
		}
	}
	
	public float getGainPercentage() {
		return gainPercentage;
	}

	public void setGainPercentage(float gainPercentage) {
		this.gainPercentage = gainPercentage;
	}
	
}
