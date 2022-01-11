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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TCP_expressionClient extends JFrame {

	private JPanel contentPane;
	private JTextField textField_input;
	private JTextField textField_result;
	private JLabel lblNewLabel_2;
	private JTextField txt_port;
	private JButton btn_connect;
	private JButton btn_close;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TCP_expressionClient frame = new TCP_expressionClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Socket socket;
	private Socket Run(int port) throws IOException
	{
		this.socket = new Socket("localhost",port);
		return this.socket;
	}
	
	public TCP_expressionClient() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("T\u00EDnh to\u00E1n bi\u1EC3u th\u1EE9c");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(195, 11, 169, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nh\u1EADp bi\u1EC3u th\u1EE9c");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(217, 94, 117, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_input = new JTextField();
		textField_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				textField_result.setText("");
			}
		});
		textField_input.setBounds(10, 119, 495, 41);
		contentPane.add(textField_input);
		textField_input.setColumns(10);
		
		JButton btn_KetQua = new JButton("K\u1EBET QU\u1EA2");
		btn_KetQua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_input.getText()!="" && txt_port.getText()!="")
				{
					try {
						socket = Run(Integer.parseInt(txt_port.getText()));
						DataInputStream dis = new DataInputStream(socket.getInputStream());
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						String mesage = textField_input.getText();
						dos.writeUTF(mesage);
						String reply = dis.readUTF();
						textField_result.setText(reply);
//						socket.close();
					} catch (IOException e1)
					{
						JOptionPane.showInternalMessageDialog(contentPane, "e1.getMessage()"
								, "Thong bao"
								,JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
				{
					textField_result.setText("Ban hay nhap du lieu !");
				}
			}
		});
		btn_KetQua.setBounds(225, 171, 89, 37);
		contentPane.add(btn_KetQua);
		
		textField_result = new JTextField();
		textField_result.setEditable(false);
		textField_result.setBounds(10, 219, 495, 114);
		contentPane.add(textField_result);
		textField_result.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Nhập số hiệu cổng");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 54, 131, 14);
		contentPane.add(lblNewLabel_2);
		
		txt_port = new JTextField();
		txt_port.setBounds(136, 52, 108, 20);
		contentPane.add(txt_port);
		txt_port.setColumns(10);
		
		btn_connect = new JButton("CONNECT");
		btn_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_connect.setBounds(275, 51, 89, 23);
		contentPane.add(btn_connect);
		
		btn_close = new JButton("Close");
		btn_close.setBounds(391, 51, 89, 23);
		contentPane.add(btn_close);
	}

}
