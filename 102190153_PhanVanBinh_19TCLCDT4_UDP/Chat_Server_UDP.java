import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Frame;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Chat_Server_UDP extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btSend;
	private JTextField textField;
	public static ArrayList<DatagramPacket> ListClient;
	private static DatagramSocket server = null;
	private JButton btnConnect;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat_Server_UDP frame = new Chat_Server_UDP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Chat_Server_UDP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
		textArea.setBounds(10, 46, 467, 408);
		contentPane.add(textArea);

		JLabel lblNewLabel = new JLabel("SERVER CHAT UDP");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(144, 10, 206, 26);
		contentPane.add(lblNewLabel);

		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server = new DatagramSocket(7799);
					ListClient = new ArrayList<DatagramPacket>();
					ReceiveData receive = new ReceiveData();
					receive.start();
					btnConnect.setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, e2.getMessage(), "Thong bao", JOptionPane.OK_OPTION);
				}
			}
		});
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConnect.setBounds(10, 455, 467, 35);
		contentPane.add(btnConnect);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(10, 455, 384, 35);
		contentPane.add(textField);
		textField.setColumns(10);

		btSend = new JButton("Send");
		btSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!"".equals(textField.getText().trim())) {
					String data = "Server: " + textField.getText();
					String textOld = textArea.getText();
					textArea.setText(textOld.concat("\n" + data));
					for(int i=0;i<ListClient.size();i++) {
						try {
							suportSend(data, ListClient.get(i));
							textField.setText("");
						} catch (Exception ex) {
							ListClient.remove(ListClient.get(i));
						}
					}
				}
			}
		});
		btSend.setFont(new Font("Tahoma", Font.BOLD, 16));
		btSend.setBounds(392, 455, 85, 35);
		contentPane.add(btSend);
	}

	private void addClient(DatagramPacket packet) {
		for (DatagramPacket client : ListClient) {
			if (client.getAddress().equals(packet.getAddress()) && client.getPort() == packet.getPort()) {
				return;
			}
		}
		ListClient.add(packet);
	}

	private void suportSend(String value, DatagramPacket packet) throws IOException {
		byte[] buffer = new byte[1024];
		buffer = value.getBytes();
		DatagramPacket send_Packet = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
		server.send(send_Packet);
	}

	class ReceiveData extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					byte[] receiveData = new byte[1024];
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					server.receive(receivePacket);
					addClient(receivePacket);
					String request = new String(receivePacket.getData(), "UTF-8");
					request = request.trim();
					String textOld = textArea.getText();
					textArea.setText(textOld.concat("\n" + request));
					for(int i=0;i<ListClient.size();i++) {
						try {
							suportSend(request, ListClient.get(i));
						} catch (Exception e) {
							ListClient.remove(ListClient.get(i));
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Thong bao", JOptionPane.OK_OPTION);
				}
			}
		}
	}
}
