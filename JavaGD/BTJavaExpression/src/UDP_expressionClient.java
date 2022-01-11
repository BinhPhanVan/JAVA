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
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UDP_expressionClient extends JFrame {

	private JPanel contentPane;
	private JTextField textField_input;
	private JTextField textField_result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDP_expressionClient frame = new UDP_expressionClient();
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
	public UDP_expressionClient() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("T\u00EDnh to\u00E1n bi\u1EC3u th\u1EE9c");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(99, 11, 169, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nh\u1EADp bi\u1EC3u th\u1EE9c");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 48, 117, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_input = new JTextField();
		textField_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				textField_result.setText("");
			}
		});
		textField_input.setBounds(10, 73, 336, 41);
		contentPane.add(textField_input);
		textField_input.setColumns(10);
		
		JButton btnNewButton = new JButton("K\u1EBET QU\u1EA2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_input.getText()!="")
				{
					try {
						DatagramSocket clientSocket = new DatagramSocket();
						InetAddress IPAddress = InetAddress.getByName("localhost");
						byte[] sendData = new byte[1024];
						byte[] receiveData = new byte[1024];
						
						String mesage = textField_input.getText();
						sendData = mesage.getBytes();
						
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 7090);
						clientSocket.send(sendPacket);
						
						DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
						clientSocket.receive(receivePacket);

						
						String str = new String(receivePacket.getData());
						textField_result.setText(str);
						clientSocket.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnNewButton.setBounds(132, 125, 89, 37);
		contentPane.add(btnNewButton);
		
		textField_result = new JTextField();
		textField_result.setEditable(false);
		textField_result.setBounds(10, 173, 336, 41);
		contentPane.add(textField_result);
		textField_result.setColumns(10);
	}

}
