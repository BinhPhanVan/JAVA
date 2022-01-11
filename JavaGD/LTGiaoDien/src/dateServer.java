import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.awt.event.ActionEvent;

public class dateServer extends JFrame {

	private JPanel contentPane;
	private JTextField txt1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dateServer frame = new dateServer();
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
	public dateServer() {
		setTitle("SERVER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 256, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton start = new JButton("start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ServerSocket server = new ServerSocket(7090);
					txt1.setText("Server is started!!!");
					while(true)
					{
						Socket socket = server.accept();
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						String time = new Date(0).toString();
						dos.writeUTF("Server Response: "+ time);
						socket.close();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		start.setBounds(78, 92, 89, 41);
		contentPane.add(start);
		
		txt1 = new JTextField();
		txt1.setBounds(32, 183, 186, 20);
		contentPane.add(txt1);
		txt1.setColumns(10);
	}
}
