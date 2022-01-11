import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class TinhToan_Server extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnClose;
	private JButton btRun;
	private JTextArea textArea;
	private ServerSocket server = null;
	public static double TinhToan(String str) {
	    return new Object() {
	        int pos = -1, ch;

	        void nextChar() {
	            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	        }

	        boolean eat(int charToEat) {
	            while (ch == ' ') nextChar();
	            if (ch == charToEat) {
	                nextChar();
	                return true;
	            }
	            return false;
	        }

	        double parse() {
	            nextChar();
	            double x = parseExpression();
	            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
	            return x;
	        }

	        double parseExpression() {
	            double x = parseTerm();
	            for (;;) {
	                if      (eat('+')) x += parseTerm(); // addition
	                else if (eat('-')) x -= parseTerm(); // subtraction
	                else return x;
	            }
	        }

	        double parseTerm() {
	            double x = parseFactor();
	            for (;;) {
	                if      (eat('*')) x *= parseFactor(); // multiplication
	                else if (eat('/')) x /= parseFactor(); // division
	                else return x;
	            }
	        }

	        double parseFactor() {
	            if (eat('+')) return parseFactor(); // unary plus
	            if (eat('-')) return -parseFactor(); // unary minus

	            double x;
	            int startPos = this.pos;
	            if (eat('(')) { // parentheses
	                x = parseExpression();
	                eat(')');
	            } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
	                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                x = Double.parseDouble(str.substring(startPos, this.pos));
	            } else if (ch >= 'a' && ch <= 'z') { // functions
	                while (ch >= 'a' && ch <= 'z') nextChar();
	                String func = str.substring(startPos, this.pos);
	                x = parseFactor();
	                if (func.equals("sqrt")) x = Math.sqrt(x);
	                else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
	                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
	                else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
	                else throw new RuntimeException("Unknown function: " + func);
	            } else {
	                throw new RuntimeException("Unexpected: " + (char)ch);
	            }

	            if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

	            return x;
	        }
	    }.parse();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TinhToan_Server frame = new TinhToan_Server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TinhToan_Server() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 9));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhap so hieu cong:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 86, 137, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Server ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 38));
		lblNewLabel_1.setBounds(199, 10, 146, 65);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(173, 86, 113, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btRun = new JButton("Run Server");
		btRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int port = Integer.parseInt(textField.getText());
					server = new ServerSocket(port);
					Thread client = new Thread(new TinhToan_Server.Receive_Helpper());
					client.start();
					btRun.setEnabled(false);
					textArea.setText("SERVER IS LISTENING...");
				}
				catch(Exception ex){
					JOptionPane.showInternalMessageDialog(contentPane, "Thong tin cong khong hop le!"
							, "Thong bao"
							,JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btRun.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btRun.setBounds(315, 86, 116, 29);
		contentPane.add(btRun);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server.close();
					btRun.setEnabled(true);
					textField.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(441, 86, 116, 29);
		contentPane.add(btnClose);
		
		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setFont(new Font("Courier New", Font.PLAIN, 16));
		textArea.setBounds(10, 125, 547, 379);
		contentPane.add(textArea);
	}
	class  Execute extends Thread{
		Socket sk = null;
		Execute(Socket sk){
			this.sk= sk;
		}
		public void run() {
			try {
				while(!server.isClosed()) {
					String output = "\nServer tra ket qua: ";
					DataInputStream dis = new DataInputStream(sk.getInputStream());
					DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
					String input= dis.readUTF();
					String textOld= textArea.getText();
					String port = String.valueOf(sk.getPort());
					textArea.setText(textOld.concat("\nPort "+port+" send data: "+ input));
					try {
						String kq = "= "+ String.valueOf(TinhToan(input));
						 output += input.concat(kq);
						 dos.writeUTF(output);
					}
					catch(Exception ex1) {
						output += " Bieu thuc khong hop le!"; 
						 dos.writeUTF(output);
					}
				}
			}
			catch(Exception ex) {
				JOptionPane.showInternalMessageDialog(contentPane,  "Loi ket noi!"
						,"Thong bao"
						,JOptionPane.INFORMATION_MESSAGE);
			}
		}
}
	
	class Receive_Helpper implements Runnable{		
		public void run() {
				while(!server.isClosed()) {
					try {
						Socket sk = server.accept();
						Execute execute = new Execute(sk);
						String textOld= textArea.getText();
						textArea.setText(textOld.concat("\nClient "+sk+" is connected"));
						execute.start();
					} catch(Exception ex) {
						JOptionPane.showInternalMessageDialog(contentPane, "Thong bao"
								, "Loi ket noi!"
								,JOptionPane.INFORMATION_MESSAGE);
					}	
			}		
		}
	}
}


