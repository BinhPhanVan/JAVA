import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class TCP_chatClient extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JButton btn_access;
	private JButton btn_send;
	private JButton btn_bye;
	private DataInputStream din;
	private DataOutputStream dos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TCP_chatClient frame = new TCP_chatClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Socket socket;

	public TCP_chatClient() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn_access = new JButton("ACCESS");
		btn_access.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					socket = new Socket("localhost",8888);
					btn_send.setEnabled(true);
					textArea.setEnabled(true);
					btn_bye.setEnabled(true);
					textField.setEnabled(true);
					btn_access.setEnabled(false);
					setTitle(String.valueOf(socket.getLocalPort()));
					Receive_Data receive = new Receive_Data();
					receive.start();
				}
				catch(Exception ex) {			
				}
				
			}
		});
		btn_access.setBounds(10, 405, 73, 37);
		contentPane.add(btn_access);
		
		textField = new JTextField();
		textField.setBounds(157, 405, 223, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btn_send = new JButton("SEND");
		btn_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String msg =textField.getText();
					if(msg != "") {
						dos = new DataOutputStream(socket.getOutputStream());
						dos.writeUTF(msg);
						dos.flush();
						textField.setText("");
					}
				}
				catch(Exception ex) {		
					JOptionPane.showMessageDialog(contentPane,
			                "Thong bao",
			                "Loi trong viec ket noi den Server!",
			                JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_send.setBounds(380, 405, 73, 37);
		contentPane.add(btn_send);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 38, 441, 356);
		contentPane.add(textArea);
		
		JLabel lblNewLabel = new JLabel("Simple Chat Client");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(157, 11, 159, 26);
		contentPane.add(lblNewLabel);
		
		btn_bye = new JButton("BYE");
		btn_bye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					socket.close();
					btn_access.setEnabled(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_bye.setBounds(81, 405, 73, 37);
		contentPane.add(btn_bye);
	}
	class Receive_Data extends Thread{
		public void run(){
				while(!socket.isClosed()) {
						try {
							din = new DataInputStream(socket.getInputStream());
							String messenger = din.readUTF();
//							String messenger_old= textArea.getText();
//							textArea.setText(messenger_old.concat(messenger));
							textArea.append(messenger);
						}
						catch (Exception ex) {
//							String messenger = din.readUTF();
//							String messenger_old= textArea.getText();
//							textArea.setText(messenger_old.concat("\n"+ ex.getMessage()));
//							textArea.append(messenger);s
						}
				}
		}
	}
}
