package videoPoker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import videoPoker.Analyser;

public class Main {
	public static void main(String[] args){
		int argc = args.length;
		int bet = 5;
		int playerCredits = 0;
		int numberDeals = 0;
		
		if(argc==0){
			System.out.println("No arguments");
			System.exit(-1);
		}
		switch(args[0])
		{
		
		case "-i": /** Interactive Mode */
			if(argc==2){			
				try{
					playerCredits = Integer.parseInt(args[1]);
			
				//Catches all NumberFormatExceptions but not other errors
				} catch(NumberFormatException e) {
				//Handle error here
					System.out.println("Credits need to be a number");
					System.exit(-2);
			}//ELSE EXCEPTION
				Player player = new Player(Integer.parseInt(args[1]));
				Deck deck = new Deck();
				deck.shuffle();
				Hand hand = new Hand(deck.getDeck());
				Analyser analyser = new Analyser(hand.getHand());
				Prizes prizes = new Prizes();
				Statistic stats = new Statistic(player.getCredits());
				Strategy strategy = new Strategy(hand.getHand());
				int reward;
				String handResult;
				String input;
				String command;
				int stateMachine = 0;
				System.out.println("Type what you want to do: ");
				while(true){
					try{
						BufferedReader bufferRead = new BufferedReader (new InputStreamReader(System.in));
						input = bufferRead.readLine();
						command = input.substring(0,1);
					}
					catch(IOException e) {
						e.printStackTrace();
						System.out.println("Problem with the input");
						continue;
					}
					
					if (command.equals("s"))
					{ /** We ask if the input is 1 to ensure that
					      the user don't put more arguments than what
					      is supposed */
						String[] inputArgs = input.split(" ");
						if (inputArgs[0].length() != 1){
							System.out.println("$ : illegal expression");
							continue;
						}
						else{
							stats.printStats();
						}
					}
					else if (command.equals("$"))
					{
						String[] inputArgs = input.split(" ");
						if (inputArgs[0].length() != 1){
							System.out.println("$ : illegal expression");
							continue;
						}
						else{
							player.printCredits();
						}
					}
					else if (command.equals("b"))
					{
						String[] inputArgs = input.split(" ");
						if (inputArgs[0].length() != 1){
							System.out.println("b : illegal expression");
							continue;
						}
						try{
								if (inputArgs.length>1 && inputArgs[0].length() == 1){
									bet = Integer.parseInt(inputArgs[1]);
									
								}
							//Catches all NumberFormatExceptions but not other errors
							} catch(NumberFormatException e) {
							//Handle error here
								System.out.println("Invalid bet value!");
							}
						
						if (stateMachine >= 1){
							System.out.println("You already made a bet!");
							continue;
						}
						if (bet <= 5 && bet >= 1 && stateMachine == 0)
						{
							player.bet(bet);
							System.out.println("player is betting "+ bet);
							stateMachine++;
						}
						else{
							System.out.println("b : illegal amount");
						}
					}
					else if (command.equals("a"))
					{
						if(stateMachine == 2)
						{
							String[] inputArgs = input.split(" ");
							if (inputArgs[0].length() != 1){
								System.out.println("s : illegal expression");
								continue;
							}
							else{
								//strategy.printAdvice();
							}
						}
						else{
							System.out.println("Cannot tell you advices if you don't make any deal!");
						}
						
					}
					else if (command.equals("d"))
					{
						if(stateMachine == 1)
						{
							String[] inputArgs = input.split(" ");
							if (inputArgs[0].length() != 1){
								System.out.println("d : illegal expression");
								continue;
							}
							else{
								hand.printHand();
								stateMachine++;
								continue;
							}
						}
						if (stateMachine == 2){
							System.out.println("You already made a deal!");
						}
						else{
							System.out.println("Cannot make a deal without making a bet!");
						}
						
					}
					else if (command.equals("h"))
					{
						if(stateMachine == 2)
						{
							String[] inputArgs = input.split(" ");
							if(inputArgs.length > 1){
								for (int holdIterator = 1; holdIterator < inputArgs.length;holdIterator++)
								{
									int holdingPosition = Integer.parseInt(inputArgs[holdIterator]);
									if(holdingPosition >= 1 && holdingPosition <= 5){
										hand.updateHold(holdingPosition-1);
									}else{
										System.out.println("Please enter a correct value between 1 to 5 to hold");
									}
								}	
							}
							hand.updateHand();
							analyser.updateDraw(hand.getHand());
							
							/** Gives the reward to the player*/
							reward = prizes.getRewardValue(bet, analyser.getCheckerResult(), analyser.getDraw());
							player.rewards(reward);
							hand.printHand();
						//	analyser.printDraw();
							if (reward == 0)
							{
								System.out.println("player loses and his credit is "+ player.getCredits());
							}
							else {
								handResult = analyser.printHandResult();
								System.out.println("player wins with "+ handResult + " and his credit is "+ player.getCredits());
							}
							
							deck.shuffle();
							hand.renewHand(deck.getDeck());
							stateMachine = 0;
						}
						else{
							System.out.println("Cannot make hold anything without making a deal!");
						}
					} 
					else if (command.equals("q") && input.length() == 1)
					{
						System.out.println("Final Stats"); System.out.println("");
						stats.printStats();
						
						System.out.println("Thank you come again!");
						System.exit(1);
					}
					else
					{
						//throw exception
						System.out.printf(("Wrong command...\n\nYou can only use this commands:" +
						"\n$ = For Player's Credits" + "\nb = To make a bet\nd = To make a deal" +
						"\nh = To make a hold\na = To make a advice\ns = To see the statistics\n"));
					}
				}
			}
		break;
			/**	* * * * * * * * * * * * * * * ** * * * * * * * * *	*/
		case "-d": /** Debug mode */
			if(argc==4){
				try{
					playerCredits = Integer.parseInt(args[1]);
				//Catches all NumberFormatExceptions but not other errors
				} catch(NumberFormatException e) {
				//Handle error here
					System.out.println("Credits need to be a number");
					System.exit(-2);
				}//ELSE EXCEPTION
			String cmd_file = args[2];
			
			FileHandler file = new FileHandler(args[3]); /** Deck file*/
			Deck deck = new Deck(file.getCardsVector());
			ArrayList<String> cmdVector = file.getCmdVector();
			int cmd = 0;
			//While there's cards and commands to execute
			while(deck.getSize()>=10 && cmdVector.get(cmd)!=null){
				//Bet
				//Update Hand
				//Hold
				//UpdateHand
				//Stats
				
				cmd++;
			}
			
			
			
			}
		break;
		
		case "-s":
			if(argc==4){
				try{
					playerCredits = Integer.parseInt(args[1]);
					bet = Integer.parseInt(args[2]);
					numberDeals = Integer.parseInt(args[3]);
					
				}catch(NumberFormatException e){
					System.out.print("Credits, bet and number of deals have to be numbers");
					System.exit(-2);
				}
			
			Player player = new Player(Integer.parseInt(args[1]));
			Deck deck = new Deck();
			deck.shuffle();
			Hand hand = new Hand(deck.getDeck());
			Analyser analyser = new Analyser(hand.getHand());
			
			Prizes prizes = new Prizes();
			Statistic stats = new Statistic(player.getCredits()); //ADVISER
			Strategy strategy = new Strategy(hand.getHand());
			int reward;
			
			for(int i=0; i<numberDeals;i++){
				player.bet(bet);
				strategy.updateHand(hand.getHand());
				//Update Hold according to advice:
				hand.setHold(strategy.holdPositionCorrector());
				hand.updateHand();
				analyser.updateDraw(hand.getHand());
				analyser.getCheckerResult();
				reward = prizes.getRewardValue(bet, analyser.getCheckerResult(), analyser.getDraw());
				player.rewards(reward);
				//Next move preparation
				deck.shuffle();
				hand.renewHand(deck.getDeck());
			}
			
		}else{ System.out.println("Missing arguments: \n"+"First argument is the initial Credits value \n"+
					"The second one is the only value of the bet\n"+
				"number of deals to play");
		}
			
		break;
		
		}
		
	}
			
}