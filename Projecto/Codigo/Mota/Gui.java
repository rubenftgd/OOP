package videoPoker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Label;
import java.awt.Color;
import javax.swing.JEditorPane;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Gui {
//
//	private JFrame Frame;
//	private final ButtonGroup buttonGroup = new ButtonGroup();
//	private JTextField txtPlayerCredits;
//
//	/**
//	 * Create the application.
//	 */
//	public Gui() {
//		initialize();
//		
//	}
//	
//	public int askCredits(){
//		JFrame askCredits = new JFrame();
//		String a = (String)JOptionPane.showInputDialog(
//                askCredits,
//                "Please Insert Credits",
//                "Customized Dialog",
//                JOptionPane.PLAIN_MESSAGE,
//                null,null, "100");	
//		if(a.length()==0) System.exit(-1); //FALTA STRING PROTECTION
//		if(Integer.parseInt(a)>0) return Integer.parseInt(a); //Case it's a valid
//		
//		boolean validCredits = false;
//		String s = null;
//		while(!validCredits){
//			s = (String)JOptionPane.showInputDialog(
//	                askCredits,
//	                "Please Insert a numeric value of Credits or bigger than 0",
//	                "Customized Dialog",
//	                JOptionPane.PLAIN_MESSAGE,
//	                null,null, "100");		
//			if(s.length()==0) System.exit(-1); //FALTA STRING PROTECTION
//			if(Integer.parseInt(s)>0) validCredits = true;
//			
//	}
//		return Integer.parseInt(s);
//	}
//		
//	/**
//	 * Launch the application.
//	 */	
//	public static void main(String[] args) {
//		
//		/*JFrame frame = new JFrame("VideoPoker ");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(300, 300);*/
//		
//		/**Asks how much are the initial credits and make sure it's acceptable*/
//		
//		
////		
////		while(!validCredits){
////			String s = (String)JOptionPane.showInputDialog(
////	                frame,
////	                "Please Insert a numeric value of Credits or bigger than 0",
////	                "Customized Dialog",
////	                JOptionPane.PLAIN_MESSAGE,
////	                null,null, "100");		
////			if(s.length()==0) System.exit(-1); //FALTA STRING PROTECTION
////			if(Integer.parseInt(s)>0) validCredits = true;
////			
////		}
//				
//		EventQueue.invokeLater(new Runnable() {
//			
//			public int askCredits(){
//				JFrame askCredits = new JFrame();
//				String a = (String)JOptionPane.showInputDialog(
//		                askCredits,
//		                "Please Insert Credits",
//		                "Customized Dialog",
//		                JOptionPane.PLAIN_MESSAGE,
//		                null,null, "100");	
//				if(a.length()==0) System.exit(-1); //FALTA STRING PROTECTION
//				if(Integer.parseInt(a)>0) return Integer.parseInt(a); //Case it's a valid
//				
//				boolean validCredits = false;
//				String s = null;
//				while(!validCredits){
//					s = (String)JOptionPane.showInputDialog(
//			                askCredits,
//			                "Please Insert a numeric value of Credits or bigger than 0",
//			                "Customized Dialog",
//			                JOptionPane.PLAIN_MESSAGE,
//			                null,null, "100");		
//					if(s.length()==0) System.exit(-1); //FALTA STRING PROTECTION
//					if(Integer.parseInt(s)>0) validCredits = true;
//					
//			}
//				return Integer.parseInt(s);
//			}
//			public void run() {
//				try {
//					Gui window = new Gui();
//					window.Frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			
//			}
//		});
//	
//	}
//	
//	
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		Frame = new JFrame("Video Poker");
//		Frame.setResizable(false);
//		Frame.getContentPane().setLocation(new Point(7, 0));
//		Frame.getContentPane().setSize(new Dimension(500, 500));
//		Frame.getContentPane().setPreferredSize(new Dimension(4, 4));
//		Frame.getContentPane().setName("Possible Hands");
//		Frame.getContentPane().setBackground(new Color(60, 179, 113));
//		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Frame.getContentPane().setForeground(new Color(0, 255, 0));
//		Frame.setBackground(Color.GREEN);
//		Frame.setBounds(100, 100, 530, 583);
//		Frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		Frame.getContentPane().setLayout(null);
//		Frame.setVisible(true);
//		
//		JEditorPane dtrpnPossibleHandsRoyal = new JEditorPane();
//		dtrpnPossibleHandsRoyal.setPreferredSize(new Dimension(50, 18));
//		dtrpnPossibleHandsRoyal.setEditable(false);
//		dtrpnPossibleHandsRoyal.setLocation(new Point(4, 0));
//		dtrpnPossibleHandsRoyal.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//		dtrpnPossibleHandsRoyal.setCaretColor(Color.RED);
//		dtrpnPossibleHandsRoyal.setBounds(new Rectangle(4, 4, 2, 2));
//		dtrpnPossibleHandsRoyal.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
//		dtrpnPossibleHandsRoyal.setAlignmentY(Component.BOTTOM_ALIGNMENT);
//		dtrpnPossibleHandsRoyal.setAlignmentX(Component.LEFT_ALIGNMENT);
//		dtrpnPossibleHandsRoyal.setForeground(new Color(0, 0, 205));
//		dtrpnPossibleHandsRoyal.setBackground(new Color(255, 255, 255));
//		dtrpnPossibleHandsRoyal.setText("    Possible Hands:\r\n\r\n    Royal Flush\t\r\n    Straight Flush \r\n    Four Aces\r\n    Four 2-4\r\n    Four 5-K\r\n    Full House\r\n    Flush\r\n    Straight\r\n    Three of a Kind\r\n    Two Pair\r\n    Jacks or Better");
//		dtrpnPossibleHandsRoyal.setBounds(10, 11, 111, 214);
//		Frame.getContentPane().add(dtrpnPossibleHandsRoyal);
//		
//		JRadioButton R1 = new JRadioButton("1");
//		buttonGroup.add(R1);
//		R1.setBounds(80, 495, 41, 23);
//		Frame.getContentPane().add(R1);
//		
//		JRadioButton R2 = new JRadioButton("2");
//		buttonGroup.add(R2);
//		R2.setBounds(123, 495, 41, 23);
//		Frame.getContentPane().add(R2);
//		
//		JRadioButton R3 = new JRadioButton("3");
//		buttonGroup.add(R3);
//		R3.setBounds(166, 495, 41, 23);
//		Frame.getContentPane().add(R3);
//		
//		JRadioButton R4 = new JRadioButton("4");
//		buttonGroup.add(R4);
//		R4.setBounds(209, 495, 41, 23);
//		Frame.getContentPane().add(R4);
//		
//		JRadioButton R5 = new JRadioButton("5");
//		buttonGroup.add(R5);
//		R5.setBounds(252, 495, 41, 23);
//		Frame.getContentPane().add(R5);
//		
//		JButton btnDraw = new JButton("Draw");
//		btnDraw.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) { //isPressed
//				
//			}
//		});
//		btnDraw.setBounds(390, 495, 65, 23);
//		Frame.getContentPane().add(btnDraw);
//		
//		JEditorPane dtrpnBet = new JEditorPane();
//		dtrpnBet.setText("    bet 1\r\n\r\n    250\r\n    50\r\n    160\r\n    80 \r\n    50\r\n    10\r\n    7\r\n    5\r\n    3\r\n    1\r\n    1\r\n");
//		dtrpnBet.setPreferredSize(new Dimension(50, 18));
//		dtrpnBet.setLocation(new Point(4, 0));
//		dtrpnBet.setForeground(new Color(0, 0, 205));
//		dtrpnBet.setEditable(false);
//		dtrpnBet.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//		dtrpnBet.setCaretColor(Color.RED);
//		dtrpnBet.setBounds(new Rectangle(4, 4, 2, 2));
//		dtrpnBet.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
//		dtrpnBet.setBackground(Color.WHITE);
//		dtrpnBet.setAlignmentY(1.0f);
//		dtrpnBet.setAlignmentX(0.0f);
//		dtrpnBet.setBounds(121, 11, 50, 214);
//		Frame.getContentPane().add(dtrpnBet);
//		
//		JEditorPane dtrpnBet_1 = new JEditorPane();
//		dtrpnBet_1.setText("    bet 2\r\n\r\n    500\r\n    100\r\n    320\r\n    160\r\n    100\r\n    20\r\n    14\r\n    10\r\n    6\r\n    2\r\n    2\r\n");
//		dtrpnBet_1.setPreferredSize(new Dimension(50, 18));
//		dtrpnBet_1.setLocation(new Point(4, 0));
//		dtrpnBet_1.setForeground(new Color(0, 0, 205));
//		dtrpnBet_1.setEditable(false);
//		dtrpnBet_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//		dtrpnBet_1.setCaretColor(Color.RED);
//		dtrpnBet_1.setBounds(new Rectangle(4, 4, 2, 2));
//		dtrpnBet_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
//		dtrpnBet_1.setBackground(Color.WHITE);
//		dtrpnBet_1.setAlignmentY(1.0f);
//		dtrpnBet_1.setAlignmentX(0.0f);
//		dtrpnBet_1.setBounds(166, 11, 50, 214);
//		Frame.getContentPane().add(dtrpnBet_1);
//		
//		JEditorPane dtrpnBet_2 = new JEditorPane();
//		dtrpnBet_2.setText("    bet 3\r\n\r\n    750\r\n    150\r\n    480\r\n    240\r\n    150\r\n    30\r\n    21\r\n    15\r\n    9\r\n    3\r\n    3\r\n");
//		dtrpnBet_2.setPreferredSize(new Dimension(50, 18));
//		dtrpnBet_2.setLocation(new Point(4, 0));
//		dtrpnBet_2.setForeground(new Color(0, 0, 205));
//		dtrpnBet_2.setEditable(false);
//		dtrpnBet_2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//		dtrpnBet_2.setCaretColor(Color.RED);
//		dtrpnBet_2.setBounds(new Rectangle(4, 4, 2, 2));
//		dtrpnBet_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
//		dtrpnBet_2.setBackground(Color.WHITE);
//		dtrpnBet_2.setAlignmentY(1.0f);
//		dtrpnBet_2.setAlignmentX(0.0f);
//		dtrpnBet_2.setBounds(209, 11, 50, 214);
//		Frame.getContentPane().add(dtrpnBet_2);
//		
//		JEditorPane dtrpnBet_3 = new JEditorPane();
//		dtrpnBet_3.setText("    bet 4\r\n\r\n    1000\r\n    200\r\n    640\r\n    320\r\n    200\r\n    40\r\n    28\r\n    20\r\n    12\r\n    4\r\n    4\r\n");
//		dtrpnBet_3.setPreferredSize(new Dimension(50, 18));
//		dtrpnBet_3.setLocation(new Point(4, 0));
//		dtrpnBet_3.setForeground(new Color(0, 0, 205));
//		dtrpnBet_3.setEditable(false);
//		dtrpnBet_3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//		dtrpnBet_3.setCaretColor(Color.RED);
//		dtrpnBet_3.setBounds(new Rectangle(4, 4, 2, 2));
//		dtrpnBet_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
//		dtrpnBet_3.setBackground(Color.WHITE);
//		dtrpnBet_3.setAlignmentY(1.0f);
//		dtrpnBet_3.setAlignmentX(0.0f);
//		dtrpnBet_3.setBounds(252, 11, 50, 214);
//		Frame.getContentPane().add(dtrpnBet_3);
//		
//		JEditorPane dtrpnBet_4 = new JEditorPane();
//		dtrpnBet_4.setText("    bet 5\r\n\r\n    4000\r\n    250\r\n    800\r\n    400\r\n    250\r\n    50\r\n    35\r\n    25\r\n    15\r\n    5\r\n    5\r\n");
//		dtrpnBet_4.setPreferredSize(new Dimension(50, 18));
//		dtrpnBet_4.setLocation(new Point(4, 0));
//		dtrpnBet_4.setForeground(new Color(0, 0, 205));
//		dtrpnBet_4.setEditable(false);
//		dtrpnBet_4.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//		dtrpnBet_4.setCaretColor(Color.RED);
//		dtrpnBet_4.setBounds(new Rectangle(4, 4, 2, 2));
//		dtrpnBet_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
//		dtrpnBet_4.setBackground(Color.WHITE);
//		dtrpnBet_4.setAlignmentY(1.0f);
//		dtrpnBet_4.setAlignmentX(0.0f);
//		dtrpnBet_4.setBounds(295, 11, 50, 214);
//		Frame.getContentPane().add(dtrpnBet_4);
//		
//		
//		Icon[] icons = {UIManager.getIcon("OptionPane.informationIcon"),
//                UIManager.getIcon("OptionPane.errorIcon"),
//                UIManager.getIcon("OptionPane.warningIcon")};
//		
//		ImageIcon pictures0, pictures1, pictures2, pictures3, pictures4;
//		
//		JLabel card0 = new JLabel();
//		pictures0 = new ImageIcon("");
//		card0.setBounds(40, 309, 81, 123);
//		Frame.getContentPane().add(card0);
//		
//		JLabel card1 = new JLabel();
//		pictures1 = new ImageIcon("");		
//		card1.setBounds(131, 309, 81, 123);
//		Frame.getContentPane().add(card1);
//		
//		JLabel card2 = new JLabel();
//		pictures2 = new ImageIcon("");				
//		card2.setBounds(221, 309, 81, 123);
//		Frame.getContentPane().add(card2);
//		
//		JLabel card3 = new JLabel();
//		pictures3 = new ImageIcon("");				
//		card3.setBounds(312, 309, 81, 123);
//		Frame.getContentPane().add(card3);
//		
//		JLabel card4 = new JLabel();
//		pictures4 = new ImageIcon("");				
//		card4.setBounds(403, 309, 81, 123);
//		Frame.getContentPane().add(card4);
//		
//		JButton BetButton = new JButton("Bet");
//		BetButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Draw Button pressed!!!!!!");
//			}
//		});
//		BetButton.setBounds(10, 495, 65, 23);
//		Frame.getContentPane().add(BetButton);
//		
//		txtPlayerCredits = new JTextField();
//		txtPlayerCredits.setText("Player Credits: ");
//		txtPlayerCredits.setBounds(355, 11, 159, 20);
//		Frame.getContentPane().add(txtPlayerCredits);
//		txtPlayerCredits.setColumns(10);
//		updateCredits(20);
//		
//		
//		/**
//		pictures1 = new ImageIcon(drawImage("KH"));
//        card3.setIcon(icons[3]);
//        System.out.println("picture 1 should be displayed here");
//	*/
//	}	
//	public JFrame getFrame() {
//		return Frame;
//	}
//
//	public void setFrame(JFrame frame) {
//		Frame = frame;
//	}
//
//	public JTextField getTxtPlayerCredits() {
//		return txtPlayerCredits;
//	}
//
//	public void setTxtPlayerCredits(JTextField txtPlayerCredits) {
//		this.txtPlayerCredits = txtPlayerCredits;
//	}
//
//	public ButtonGroup getButtonGroup() {
//		return buttonGroup;
//	}
//
//	public String drawImage(String name){
//		String src = "/src/videoPoker/Deck";
//		return src+name;
//	}
//	
//	public void updateCredits(int playerCredits){
//		txtPlayerCredits.setText("Player Credits: "+playerCredits);
//	}
}
