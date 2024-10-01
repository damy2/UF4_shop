package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Shop;
import model.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ProductView extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private Shop shop;
	private int opcion;
	private JTextField newProductField;
	private JTextField stockField;
	private JTextField priceField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	public ProductView(int opcion, Shop shop) {
		this.shop = shop;
		this.opcion = opcion;
		setFocusable(true);
		setTitle("Añadir Producto");
		setBounds(100, 100, 427, 241);
		getContentPane().setLayout(null);
		this.addKeyListener(this);

		JPanel contentPanel_1 = new JPanel();
		contentPanel_1.setBounds(23, 24, 339, 103);
		getContentPane().add(contentPanel_1);
		contentPanel_1.setLayout(null);
		contentPanel_1.setBorder(new EmptyBorder(5, 5, 5, 5));

		lblNewLabel = new JLabel("Nombre producto:");
		lblNewLabel.setBounds(40, 10, 105, 13);
		contentPanel_1.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Stock producto:");
		lblNewLabel_1.setBounds(40, 42, 105, 13);
		contentPanel_1.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Precio producto:");
		lblNewLabel_2.setBounds(40, 79, 105, 13);
		contentPanel_1.add(lblNewLabel_2);

		newProductField = new JTextField();
		newProductField.setColumns(10);
		newProductField.setBounds(168, 7, 96, 19);
		contentPanel_1.add(newProductField);

		stockField = new JTextField();
		stockField.setColumns(10);
		stockField.setBounds(168, 39, 96, 19);
		contentPanel_1.add(stockField);

		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(168, 76, 96, 19);
		contentPanel_1.add(priceField);

		newProductField.setVisible(true);
		stockField.setVisible(true);
		priceField.setVisible(true);
		lblNewLabel.setVisible(true);
		lblNewLabel_1.setVisible(true);
		lblNewLabel_2.setVisible(true);

		if (opcion == 2) {

		} else if (opcion == 3) {
			setTitle("Añadir Stock");
			priceField.setVisible(false);
			lblNewLabel_2.setVisible(false);

		} else if (opcion == 4) {
			setTitle("Eliminar Producto");
			stockField.setVisible(false);
			priceField.setVisible(false);
			lblNewLabel_1.setVisible(false);
			lblNewLabel_2.setVisible(false);

		} else if (opcion == 9) {
			setTitle("Eliminar Producto");
			stockField.setVisible(false);
			priceField.setVisible(false);
			lblNewLabel_1.setVisible(false);
			lblNewLabel_2.setVisible(false);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.setVisible(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	

	}
}
