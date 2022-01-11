import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UDP_chatClient extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btn_send, btn_access, btn_bye;
	private JTextArea textArea;
	public final static int PORT = 7799;
	private String hostname;
	private InetAddress IP = null;
	private DatagramSocket socketClient;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDP_chatClient frame = new UDP_chatClient();
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
	public UDP_chatClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 textArea = new JTextArea();
		textArea.setBounds(49, 34, 360, 352);
		contentPane.add(textArea);
		
		 btn_access = new JButton("Access");
		 btn_access.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
//		 		String s =textField.getText();
//				if("".equals(s) == false) {
//					try {
//						IP = InetAddress.getByName("localhost");
//						socketClient = new DatagramSocket();
//						MessageReceiver receive = new MessageReceiver();
//						receive.start();
//						ReceiveData rc = new ReceiveData();
//						rc.start();
//						btn_send.setVisible(true);
//						textArea.setText("");
//						hostname = s;
//						setTitle(s);
//						textField.setText(null);
//						btn_access.setEnabled(false);
//					} catch (Exception e2) {
//						 JOptionPane.showMessageDialog(contentPane, e2.getMessage(),"Thong bao",JOptionPane.OK_OPTION);
//					}
//				}
//				else JOptionPane.showMessageDialog(contentPane, "Moi ban nhap ten de su dung","Thong bao",JOptionPane.OK_OPTION);
		 	}
		 });
		btn_access.setBounds(10, 406, 67, 29);
		contentPane.add(btn_access);
		
		 btn_bye = new JButton("Bye");
		btn_bye.setBounds(77, 406, 62, 29);
		contentPane.add(btn_bye);
		
		textField = new JTextField();
		textField.setBounds(139, 406, 231, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		 btn_send = new JButton("SEND");
		 btn_send.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
//		 		if(!"".equals(textField.getText().trim())) {
//					try {
//						String str = hostname+" :"+textField.getText().trim();
//						DatagramSocket clientSocket = new DatagramSocket(); 	
//						byte[] sendData = str.getBytes();
//						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IP,PORT);
//						clientSocket.send(sendPacket);
//						clientSocket.setSoTimeout(1000);
//						textField.setText("");
//					}
//					catch(Exception ex) {
//						JOptionPane.showMessageDialog(contentPane, ex.getMessage(),"Thong bao" 
//						, JOptionPane.INFORMATION_MESSAGE);
//					}
//				}
		 	}
		 });
		btn_send.setBounds(372, 406, 73, 29);
		contentPane.add(btn_send);
		
		JLabel lblNewLabel = new JLabel("CLIENT UDP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(180, 11, 100, 23);
		contentPane.add(lblNewLabel);
	}
	class MessageReceiver extends Thread {
		 
		public void run() {
			while (!socketClient.isClosed()) {
				try {
					byte[] receiveData= new byte[1024];			
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					socketClient.receive(receivePacket);
					String dataReceive= new String(receivePacket.getData());
					String textOld = textArea.getText();
					textArea.setText(textOld.concat("\n"+dataReceive.trim()));
					textField.setText(null);
					socketClient.setSoTimeout(1000);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, e.getMessage(),"Thong bao loi ket noi",JOptionPane.OK_OPTION);
				}
			}
		}
	}
	class ReceiveData extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				String str = textField.getText().trim();
				if(str != "") {
					try {
						byte[] receiveData= new byte[1024];		
						DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
						socketClient.receive(receivePacket);
						String dataReceive= new String(receivePacket.getData());
						String textOld = textArea.getText();
						textArea.setText(textOld.concat("\n"+dataReceive.trim()));
						textField.setText(null);
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(contentPane, ex.getMessage(),"Thong bao" 
						, JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}
}
