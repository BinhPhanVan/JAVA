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
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.awt.event.ActionEvent;

public class UDP_serverExpression extends JFrame {

	private JPanel contentPane;
	private JTextField txt_port;
	private JButton btn_RUN;
	private JButton btn_Close;

	public boolean DoiXung(String st)
	{
		 String reverseString = "";
		 
		 for ( int i = st.length() - 1 ; i >= 0 ; i-- )
	            reverseString = reverseString + st.charAt(i);
		 if(st.equals(reverseString))
		 {
			 return true;
		 }
		 return false;
	}
	private JTextArea textArea;
	private DatagramSocket serverSocket;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDP_serverExpression frame = new UDP_serverExpression();
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
	public UDP_serverExpression() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SERVER_UDP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(153, 11, 116, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PORT");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 64, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txt_port = new JTextField();
		txt_port.setBounds(76, 63, 140, 20);
		contentPane.add(txt_port);
		txt_port.setColumns(10);
		
		btn_RUN = new JButton("RUN");
		btn_RUN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int portServer = Integer.parseInt(txt_port.getText());
					serverSocket = new DatagramSocket(portServer);
					textArea.setText("Server UDP is started!!");
					Thread client = new Thread(new XuLyAccess());
					client.start();
					btn_RUN.setEnabled(false);
					btn_Close.setEnabled(true);
				} catch (SocketException e2) {
					// TODO Auto-generated catch blocks
					e2.printStackTrace();
				}
			}
		});
		btn_RUN.setBounds(226, 62, 89, 23);
		contentPane.add(btn_RUN);
		
		btn_Close = new JButton("CLOSE");
		btn_Close.setEnabled(false);
		btn_Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverSocket.close();
				btn_RUN.setEnabled(true);
				btn_Close.setEnabled(false);
			}
		});
		btn_Close.setBounds(325, 62, 89, 23);
		contentPane.add(btn_Close);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 103, 404, 254);
		contentPane.add(textArea);
	}
	class XuLyAccess implements Runnable
	{
		public void run()
		{
			while(!serverSocket.isClosed())
			{
					try 
					{
						byte[] receiveData = new byte[1024];
						byte[] sendData = new byte[1024];
						DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
						serverSocket.receive(receivePacket);
						InetAddress IPAddress = receivePacket.getAddress();
						int port = receivePacket.getPort();
						textArea.append("\nServer receive data from "+ IPAddress +" "+ port +" Value: ");
						String request = new String(receivePacket.getData(), "UTF-8");
						request = request.trim();
						textArea.append(request);
						String mesage = "";
						try 
						{
							if(DoiXung(request))
							{
								mesage = "Server UDP: La chuoi doi xung!!";
							}
							else
							{
								mesage = "Server UDP: Khong phai chuoi doi xung";
							}
							
						}
						catch (Exception ex) 
						{
							mesage = "Server UDP: dữ liệu không hợp lệ";
						}
						sendData = mesage.getBytes();
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port );
						serverSocket.send(sendPacket);
					}
					catch (IOException ex) 
					{
						JOptionPane.showMessageDialog(contentPane, "Server dừng hoạt động", "Thong bao",
								JOptionPane.INFORMATION_MESSAGE);
					}
			}
		}
	}
}

