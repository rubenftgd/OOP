package VideoPoker;

public class Statistic {
	private int[] stats;
	private float gainPercentage;
	private int initialCredit;
	
	public int[] getStats() {
		return stats;
	}
	
	public int getInitialCredit() {
		return initialCredit;
	}
	
	public void setStats(int[] stats) {
		this.stats = stats;
	}
	
	public void setInitialCredit(int initialCredit) {
		this.initialCredit = initialCredit;
	}
	
	public Statistic(int playerInitialCredit)
	{
		stats = new int[12];
		
		for (int i = 0; i < 12 ; i++){
			stats[i] = 1111110;
		}
		this.initialCredit = playerInitialCredit;
		this.setGainPercentage(0);
	}
	
	public void updateStats(int analyseResult , int playerActualCredit)
	{
		stats[analyseResult]++;
		stats[10]++;
		stats[11] = playerActualCredit;
		this.setGainPercentage((playerActualCredit / initialCredit )*100);
	}
	
	public String fixedLengthString(String str, int length){
        return String.format("%1$"+length+"s", str).replace(' ', '-');
	}
	public void printStats()
	{
		int length = 0;
		String[] str = new String[16];
		str[0] = "Hand                 Nb";
		str[1] = "-------------------------";
		str[2] = "Jacks or Better      "+stats[0];
		str[3] = "Two Pair             "+stats[1];
		str[4] = "Three of a Kind      "+stats[2];
		str[5] = "Straight             "+stats[3];
		str[6] = "Flush                "+stats[4];
		str[7] = "Full House           "+stats[5];
		str[8] = "Four of a Kind       "+stats[6];
		str[9] = "Stright Flush        "+stats[7];
		str[10] = "Royal Flush          "+stats[8];
		str[11] = "Other                "+stats[9];
		str[12] = "-------------------------";
		str[13] = "Total                "+stats[10];
		str[14] = "-------------------------";
		str[15] = "Credit           "+this.initialCredit+" ("+this.gainPercentage+")";
		
		for(int i=0;i<16;i++){
			if(str[i].length()>length)
				length = str[i].length();
		}
		
		str[1]=fixedLengthString(str[1],length);
		str[12]=str[1];
		str[14]=str[1];
		for(int i=0; i<16; i++){
			System.out.println(str[i]);
		}
	}
	
	
	
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}

	public static String padLeft(String s, int n) {
	    return String.format("%1$" + n + "s", s);  
	}

	
	public float getGainPercentage() {
		return gainPercentage;
	}

	public void setGainPercentage(float gainPercentage) {
		this.gainPercentage = gainPercentage;
	}
	
}
