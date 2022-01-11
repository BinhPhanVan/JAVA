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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class TCP_clientExpression extends JFrame {

	private JPanel contentPane;
	private JTextField port;
	private JTextField textField_1;
	private JTextArea textArea;
	private JButton btn_Tinh;
	private JButton btn_close;
	private JButton btn_connect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TCP_clientExpression frame = new TCP_clientExpression();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Socket sk;
	public TCP_clientExpression() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CLIENT");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(194, 11, 67, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nh\u1EADp c\u1ED5ng server");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 55, 121, 22);
		contentPane.add(lblNewLabel_1);
		
		port = new JTextField();
		port.setBounds(141, 58, 86, 20);
		contentPane.add(port);
		port.setColumns(10);
		
		btn_connect = new JButton("CONNECT");
		btn_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int port1 = Integer.parseInt(port.getText());
					 sk = new Socket("localhost",port1); 
					textArea.setText("Ket noi thanh cong den Server");
					btn_connect.setEnabled(false);
					btn_Tinh.setEnabled(true);
					btn_close.setEnabled(true);
					setTitle("Client : " + String.valueOf(sk.getLocalPort()));
				}
				catch(Exception ex){
					JOptionPane.showInternalMessageDialog(contentPane, "Please enter port, you want access!!"
							, "Thong bao"
							,JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_connect.setBounds(239, 57, 89, 23);
		contentPane.add(btn_connect);
		
		btn_close = new JButton("CLOSE");
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sk.close();
					btn_connect.setEnabled(true);
					btn_Tinh.setEnabled(false);
					btn_close.setEnabled(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_close.setBounds(335, 57, 89, 23);
		contentPane.add(btn_close);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 235, 414, 132);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel("Nhap so nguyen: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 99, 86, 22);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 134, 414, 35);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btn_Tinh = new JButton("Thuc hien");
		btn_Tinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String text = textField_1.getText();
					DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
					DataInputStream din = new DataInputStream(sk.getInputStream());
					dos.writeUTF(text);
					String result=din.readUTF();
					textArea.append(result);
					textField_1.setText("");
					if(result.contains("true"))
					{
						textField_1.setEnabled(false);
						sk.close();
					}
				}
				catch(Exception ex){
					JOptionPane.showInternalMessageDialog(contentPane, "Server is closed !"
							, "Thong bao"
							,JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_Tinh.setBounds(182, 190, 89, 23);
		contentPane.add(btn_Tinh);
	}

}
