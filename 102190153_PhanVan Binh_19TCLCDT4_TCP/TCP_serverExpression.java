import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TCP_serverExpression extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static double eval(final String str) {
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
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TCP_serverExpression frame = new TCP_serverExpression();
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
	private JButton btn_Close;
	private JButton btn_RUN;
	public TCP_serverExpression() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SERVER");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(187, 11, 65, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PORT");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 49, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(78, 48, 135, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btn_RUN = new JButton("RUN");
		btn_RUN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int port = Integer.parseInt(textField.getText());
					server = new ServerSocket(port);
					Thread client = new Thread(new TCP_serverExpression.Access());
					client.start();
					btn_RUN.setEnabled(false);
					textArea.setText("SERVER IS LISTENING...");
					setTitle("SERVER :"+ port);
				}
				catch(Exception ex){
					JOptionPane.showInternalMessageDialog(contentPane, "Thong tin cong khong hop le!"
							, "Thong bao"
							,JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_RUN.setBounds(223, 47, 89, 23);
		contentPane.add(btn_RUN);
		
		btn_Close = new JButton("CLOSE");
		btn_Close.setEnabled(true);
		btn_Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server.close();
					btn_RUN.setEnabled(true);
					textField.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Close.setBounds(322, 47, 89, 23);
		contentPane.add(btn_Close);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 77, 414, 354);
		contentPane.add(textArea);
	}
	private ServerSocket server;
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
						String kq = "= "+ String.valueOf(eval(input));
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
				JOptionPane.showInternalMessageDialog(contentPane,  "Client mất kết nối!"
						,"Thong bao"
						,JOptionPane.INFORMATION_MESSAGE);
			}
		}
}
	
	class Access implements Runnable{		
		public void run() {
				while(!server.isClosed()) {
					try {
						Socket sk = server.accept();
						Execute execute = new Execute(sk);
						String textOld= textArea.getText();
						textArea.append("\nClient "+sk+" is connected");
						execute.start();
					} catch(Exception ex) {
						JOptionPane.showInternalMessageDialog(contentPane, "Thong bao"
								, "Đóng kết nối server !"
								,JOptionPane.INFORMATION_MESSAGE);
						btn_Close.setEnabled(false);
						btn_RUN.setEnabled(true);
					}	
			}		
		}
	}
}
