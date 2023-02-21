package videoPoker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {

	private int cardsNumber;
	private ArrayList<String> cardsVector;
	private ArrayList<String> cmdVector; 

	public FileHandler(String inputFile){
		File f = new File(inputFile);
		if(!(f.exists()  && !f.isDirectory())) {
		    System.out.println("Card File doesn't exist or wrong directory");
		    System.exit(-8);
		}
		
		String cardsLine = "";
		cardsVector = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			   String line = null;
			   while ((line = br.readLine()) != null) {
				   /* We do the split with the space to remove enters*/
				   cardsLine = cardsLine.concat(line + " ");
			   }
			   
			   String[] cardsWithSpaces = cardsLine.split("\\W+");
			   
			   for (String content : cardsWithSpaces){
				   this.cardsVector.add(content);
			   }
			   
			   this.cardsNumber = cardsVector.size();
			   
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
			System.exit(-8);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getCmdVector() {
		return cmdVector;
	}

	public void setCmdVector(String cmd) {
		File f = new File(SetPathName(cmd));
		if(!(f.exists()) && !f.isDirectory()) { 
		    System.out.println("Command File doesn't exist or wrong directory");
		    System.exit(-8);
		}
		String cmdLine = "";
		cmdVector = new ArrayList<String>();
	
		try (BufferedReader br = new BufferedReader(new FileReader(SetPathName(cmd)))) {
			   
			   String line = null;
			   while ((line = br.readLine()) != null) {
				   /* We do the split with the space to remove enters*/
				   cmdLine = cmdLine.concat(line + " ");
			   }
			   
//			   String[] cmdWithSpaces = cmdLine.split("\\W++");
			   String[] cmdWithSpaces = cmdLine.split("\\s+");
			   
			   for (String content : cmdWithSpaces){
				   this.cmdVector.add(content);
			   }		   
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public String SetPathName(String c){
		String reg;
		
		if ((System.getProperty("os.name")).toUpperCase().contains("WIN")) {
			reg = ".\\TESTS\\"+c;
		} else {
			reg = "./TESTS/"+c;
		}
		return reg;		
	}
	
	public int getCardsNumber() {
		return cardsNumber;
	}

	public ArrayList<String> getCardsVector() {
		return cardsVector;
	}

	public void setCardsVector(ArrayList<String> cardsVector) {
		this.cardsVector = cardsVector;
	}

	/* Just for testing */
	public void printCards()
	{
		for (String line : cardsVector){
			System.out.println(line);
	    }
	}
	
}
