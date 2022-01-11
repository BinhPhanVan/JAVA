import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class client extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client frame = new client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void access() throws IOException, IOException
	{
		Socket socket = new Socket("localhost",8888);
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
	}
	public void run() throws IOException, IOException
	{
		Socket socket = new Socket("localhost",8888);
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		Scanner input = new Scanner(System.in);
		while(true)
		{
			System.out.print("Client: ");
			String mesage = input.nextLine();
			dos.writeUTF("Client: "+mesage);
			dos.flush();
			String reply = dis.readUTF();
			System.out.println(reply);
			input = input.reset();
		}

	}
	public client() {
		setTitle("CLIENT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton send = new JButton("Send");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socket socket;
				try {
					socket = new Socket("localhost",8888);
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					Scanner input = new Scanner(System.in);
					while(true)
					{
						System.out.print("Client: ");
						String mesage = input.nextLine();
						dos.writeUTF("Client: "+mesage);
						dos.flush();
						String reply = dis.readUTF();
						System.out.println(reply);
						input = input.reset();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		send.setBounds(309, 240, 68, 34);
		contentPane.add(send);
		
		textField = new JTextField();
		textField.setBounds(79, 240, 229, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 11, 367, 226);
		contentPane.add(textArea);
		
		JButton access = new JButton("Access");
		access.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socket socket;
				try {
					socket = new Socket("localhost",8888);
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					textArea.setText(socket.getInetAddress()+"\n" + socket.getPort()+"\n"+ socket.getLocalPort());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		access.setBounds(10, 240, 68, 34);
		contentPane.add(access);
		
		
	}
}
