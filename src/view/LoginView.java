package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Employee;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Numero de empleado");
		lblNewLabel.setBounds(48, 53, 128, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(48, 123, 45, 13);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(58, 76, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(58, 146, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		btnNewButton = new JButton("Acceder");
		btnNewButton.setBounds(341, 232, 85, 21);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			Employee employee = new Employee();
			if (this.isNumeric(textField.getText())) {

				if (employee.login(Integer.valueOf(textField.getText()), textField_1.getText())) {
					ShopView shopView = new ShopView();
					shopView.setVisible(true);
					this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(LoginView.this,"Usuario o password incorrectos","Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		}

	}

	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}


}
