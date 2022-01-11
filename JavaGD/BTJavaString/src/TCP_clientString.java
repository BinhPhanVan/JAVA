import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class TCP_clientString extends JFrame {

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
					TCP_clientString frame = new TCP_clientString();
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
	public TCP_clientString() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 326, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lab_content = new JLabel("Content");
		lab_content.setBounds(10, 11, 46, 14);
		contentPane.add(lab_content);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 290, 48);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 130, 290, 120);
		contentPane.add(textArea);
		
		JButton btn_send = new JButton("SEND");
		btn_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText()!="")
				{
					try {
						Socket socket = new Socket("localhost",8008);
						DataInputStream dis = new DataInputStream(socket.getInputStream());
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						Scanner input = new Scanner(System.in);
						String mesage = textField.getText();
						dos.writeUTF(mesage);
						String reply = dis.readUTF();
						textArea.setText(reply);
						socket.close();
					} catch (IOException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_send.setBounds(113, 95, 89, 23);
		contentPane.add(btn_send);
		
		
	}
}
