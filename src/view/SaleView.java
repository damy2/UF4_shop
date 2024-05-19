package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Shop;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SaleView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Shop shop;
	private JTextField clientNameField;
	/**
	 * Create the dialog.
	 */
	public SaleView(Shop shop) {
		setTitle("Sale ");
		this.shop = shop;
		setBounds(100, 100, 324, 245);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JCheckBox premiumCheck = new JCheckBox("Premium");
		premiumCheck.setBounds(10, 27, 93, 21);
		contentPanel.add(premiumCheck);
		
		JLabel lblNewLabel = new JLabel("Nombre cliente:");
		lblNewLabel.setBounds(10, 78, 73, 13);
		contentPanel.add(lblNewLabel);
		
		clientNameField = new JTextField();
		clientNameField.setBounds(122, 75, 96, 19);
		contentPanel.add(clientNameField);
		clientNameField.setColumns(10);
		
		JButton añadirProductoSaleBtn = new JButton("Añadir producto a la venta");
		añadirProductoSaleBtn.setBounds(20, 121, 187, 21);
		contentPanel.add(añadirProductoSaleBtn);
	}
}
