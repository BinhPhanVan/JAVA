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
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class TinhToan_Client extends JFrame {

	private JPanel contentPane;
	private JTextField tfText;
	private JTextField tfPort;
	private JButton btConnect;
	private JButton btnClose;
	private JButton btnSend;
	private JTextArea textArea;
	private Socket sk=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TinhToan_Client frame = new TinhToan_Client();
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
	public TinhToan_Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhap so hieu cong server: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 96, 190, 20);
		contentPane.add(lblNewLabel);
		
		tfPort = new JTextField();
		tfPort.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfPort.setBounds(210, 96, 96, 22);
		contentPane.add(tfPort);
		tfPort.setColumns(10);
		
		btConnect = new JButton("Connect");
		btConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int port=Integer.parseInt(tfPort.getText());
					sk = new Socket("localhost",port); 
					textArea.setText("Ket noi thanh cong den Server");
					btnSend.setEnabled(true);
					btnClose.setEnabled(true);
					setTitle(String.valueOf(sk.getLocalPort()));
				}
				catch(Exception ex){
					JOptionPane.showInternalMessageDialog(contentPane, "Thong tin cong khong hop le!"
							, "Thong bao"
							,JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btConnect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btConnect.setBounds(314, 92, 107, 29);
		contentPane.add(btConnect);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sk.close();
					btnSend.setEnabled(false);
					btnClose.setEnabled(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnClose.setEnabled(false);
		btnClose.setBackground(Color.WHITE);
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(424, 92, 107, 29);
		contentPane.add(btnClose);
		
		JLabel lblNhapBieuThuc = new JLabel("Nhap bieu thuc tinh: ");
		lblNhapBieuThuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhapBieuThuc.setBounds(10, 151, 190, 20);
		contentPane.add(lblNhapBieuThuc);
		
		tfText = new JTextField();
		tfText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfText.setColumns(10);
		tfText.setBounds(10, 181, 411, 29);
		contentPane.add(tfText);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String text = tfText.getText();
					DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
					DataInputStream din = new DataInputStream(sk.getInputStream());
					dos.writeUTF(text);
					String result=din.readUTF();
					textArea.setText(textArea.getText().concat(result));
					tfText.setText("");
				}
				catch(Exception ex){
					JOptionPane.showInternalMessageDialog(contentPane, "ex.getMessage()"
							, "Thong bao"
							,JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSend.setEnabled(false);
		btnSend.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSend.setBounds(424, 181, 107, 29);
		contentPane.add(btnSend);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 15));
		textArea.setEditable(false);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(10, 220, 521, 308);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Calculator");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 38));
		lblNewLabel_1.setBounds(162, 10, 203, 60);
		contentPane.add(lblNewLabel_1);
	}
}
