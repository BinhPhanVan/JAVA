import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class UDP_clientExpression extends JFrame {

	private JPanel contentPane;
	private JTextField textField_port;
	private JTextField textField_bt;
	private JTextArea textArea;
	private JButton btn_close;
	private JButton btn_Tinh;
	private JButton btn_access;
	private DatagramSocket clientSocket;
	private InetAddress IPAddress;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDP_clientExpression frame = new UDP_clientExpression();
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
	public UDP_clientExpression() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CLIENT_UDP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(168, 11, 117, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nh\u1EADp c\u1ED5ng server");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 50, 117, 26);
		contentPane.add(lblNewLabel_1);
		
		textField_port = new JTextField();
		textField_port.setBounds(140, 55, 100, 20);
		contentPane.add(textField_port);
		textField_port.setColumns(10);
		
		btn_access = new JButton("ACCESS");
		btn_access.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						byte[] sendData = new byte[1024];
						byte[] receiveData = new byte[1024];
						int port1 = Integer.parseInt(textField_port.getText());
						clientSocket = new DatagramSocket();
						IPAddress = InetAddress.getByName("localhost");
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port1);
						btn_access.setEnabled(false);
						btn_close.setEnabled(true);
						btn_Tinh.setEnabled(true);
					} catch (SocketException e1) {
						textArea.setText("CLient lỗi !!!");
					}
					
				} catch (UnknownHostException e1) {
					textArea.setText("Server không tồn tại");
				}
				
			}
		});
		btn_access.setBounds(254, 54, 89, 23);
		btn_access.setEnabled(true);
		contentPane.add(btn_access);
		
		 btn_close = new JButton("CLOSE");
		 btn_close.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		clientSocket.close();
		 		btn_access.setEnabled(true);
		 		btn_close.setEnabled(false);
		 		btn_Tinh.setEnabled(false);
		 	}
		 });
		btn_close.setBounds(347, 54, 89, 23);
		btn_close.setEnabled(false);
		contentPane.add(btn_close);
		
		textField_bt = new JTextField();
		textField_bt.setBounds(10, 124, 426, 41);
		contentPane.add(textField_bt);
		textField_bt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nhap chuoi");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 87, 117, 26);
		contentPane.add(lblNewLabel_2);
		
		btn_Tinh = new JButton("Thuc hien");
		btn_Tinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						byte[] sendData = new byte[1024];
						byte[] receiveData = new byte[1024];
						
						int port1 = Integer.parseInt(textField_port.getText());
						String mesage = textField_bt.getText();
						sendData = mesage.getBytes();
						
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port1);
						clientSocket.setSoTimeout(1000);
						clientSocket.send(sendPacket);
						textArea.setText("Kết nối thành công đến server\n");
						DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
						clientSocket.receive(receivePacket);
						
						String str = new String(receivePacket.getData());
						textArea.append(str);
						if(str.contains("La"))
						{
							textField_bt.setEnabled(false);
							clientSocket.close();
						}
						
					} catch (NumberFormatException e1) {
						textArea.setText("Dữ liệu đầu vào không hợp lệ !!");
					}
					catch (IOException e1) {
						textArea.setText("Server không tồn tại");
					}
					catch (Exception e1) {
						textArea.setText("Server không tồn tại");
					}
			}
		});
		btn_Tinh.setBounds(182, 189, 89, 23);
		btn_Tinh.setEnabled(false);
		contentPane.add(btn_Tinh);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 223, 426, 164);
		contentPane.add(textArea);
	}
}
