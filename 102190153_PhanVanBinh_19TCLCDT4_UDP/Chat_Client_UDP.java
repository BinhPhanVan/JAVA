import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Chat_Client extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JTextField textField;
	public final static int PORT = 7799;
	private String hostname;
	private InetAddress IP = null;
	private JButton btSend;
	private static  DatagramSocket socketClient=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat_Client frame = new Chat_Client();
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
	public Chat_Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setText("Nhap ten vao ben duoi de ket noi");
		textArea.setEditable(false);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(10, 42, 464, 414);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s =textField.getText();
				if(!"".equals(s) ) {
					try {
						socketClient = new DatagramSocket(); 
						IP = InetAddress.getByName("localhost");				
						hostname = s;
						byte[] sendData = (hostname+" da ket noi den ChatRom").getBytes();
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IP,PORT);
						socketClient.send(sendPacket);
						btSend.setVisible(true);
						textArea.setText("");
						MessageReceiver receive = new MessageReceiver();
						receive.start();
						setTitle(s);
						textField.setText(null);
						btnNewButton.setEnabled(false);
					} catch (Exception e2) {
						 JOptionPane.showMessageDialog(contentPane, e2.getMessage(),"Thong bao",JOptionPane.OK_OPTION);
					}
				}
				else JOptionPane.showMessageDialog(contentPane, "Moi ban nhap ten de su dung","Thong bao",JOptionPane.OK_OPTION);
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 453, 96, 44);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(107, 453, 295, 44);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btSend = new JButton("Send");
		btSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(!"".equals(textField.getText().trim())) {
					try {
						String str = hostname+" :"+textField.getText().trim();	
						byte[] sendData = str.getBytes();
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IP,PORT);
						socketClient.send(sendPacket);
						textField.setText("");
						textField.setText(null);
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(contentPane, ex.getMessage(),"Thong bao" 
						, JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btSend.setVisible(false);
		btSend.setFont(new Font("Dialog", Font.PLAIN, 14));
		btSend.setBounds(402, 453, 72, 44);
		contentPane.add(btSend);
		
		JLabel lblNewLabel = new JLabel("UDP CHAT ROOM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(178, 10, 152, 22);
		contentPane.add(lblNewLabel);
	}
	
	class MessageReceiver extends Thread {
		public void run() {
			while (true) {
				try {
					byte[] receiveData= new byte[1024];			
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					socketClient.receive(receivePacket);
					String dataReceive= new String(receivePacket.getData());
					String textOld = textArea.getText();
					textArea.setText(textOld.concat("\n"+dataReceive.trim()));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, e.getMessage(),"Thong bao loi ket noi",JOptionPane.OK_OPTION);
				}
			}
		}
	}
}
