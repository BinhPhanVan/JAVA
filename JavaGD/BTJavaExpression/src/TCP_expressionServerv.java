import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class TCP_expressionServerv extends JFrame {

	private JPanel contentPane;
	private JTextField port;
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
					TCP_expressionServerv frame = new TCP_expressionServerv();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private String content="";
	private ServerSocket server; 
	private ServerSocket Run(int port) throws IOException
	{
		this.server = new ServerSocket((port));
		return this.server;
	}
	private void Exit() throws IOException
	{
		server.close();
	}
	/**
	 * Create the frame.
	 */
	public TCP_expressionServerv() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SERVER");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(226, 11, 82, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nh\u1EADp s\u1ED1 hi\u1EC7u c\u1ED5ng: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 59, 121, 21);
		contentPane.add(lblNewLabel_1);
		
		port = new JTextField();
		port.setBounds(141, 60, 121, 20);
		contentPane.add(port);
		port.setColumns(10);
		
		JButton btn_RUN = new JButton("RUN");
		btn_RUN.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
//				&& Integer.parseInt(port.getText())!=0
				if(port.getText()!="")
				{
					try {
				        server =Run(Integer.parseInt(port.getText()));
						port.enable(false);
						Thread client = new Thread(new TCP_expressionServerv.Receive_Helpper());
						client.start();
						btn_RUN.setEnabled(false);
						textArea.setText("SERVER IS LISTENING...");
					}
					catch(Exception ex){
						JOptionPane.showInternalMessageDialog(contentPane, "Thong tin cong khong hop le!"
								, "Thong bao"
								,JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
				{
					textArea.setText("Hãy nhập dữ liệu");	
				}
				textArea.setText(content);
			}
		});
		btn_RUN.setBounds(279, 59, 89, 23);
		contentPane.add(btn_RUN);
		
		JButton btn_Close = new JButton("Close");
		btn_Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server.close();
					btn_RUN.setEnabled(true);
					port.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Close.setBounds(389, 59, 89, 23);
		contentPane.add(btn_Close);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 91, 468, 190);
		contentPane.add(textArea);
	}
	class  Execute extends Thread{
		Socket sk = null;
		Execute(Socket sk){
			this.sk= sk;
		}
		public void run() {
			try {
//				trong khi server chưa đóng kết nối
				while(!server.isClosed()) {
					String output = "\nServer tra ket qua: ";
//					Tạo ra hai luồng vào ra
					DataInputStream dis = new DataInputStream(sk.getInputStream());
					DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
//					đọc yêu cầu từ server
					String input= dis.readUTF();
//					Lấy kết quả cũ
					String textOld= textArea.getText();
					
					String port = String.valueOf(sk.getPort());
//					trả về kết quả mới
					textArea.setText(textOld.concat("\nPort " + port + " send data: " + input));
					try 
					{
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
//				JOptionPane.showInternalMessageDialog(contentPane,  "Loi ket noi!"
//						,"Thong bao"
//						,JOptionPane.INFORMATION_MESSAGE);
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
//						JOptionPane.showInternalMessageDialog(contentPane, "Thong bao"
//								, "Loi ket noi!"
//								,JOptionPane.INFORMATION_MESSAGE);
					}	
			}		
		}
	}
}
