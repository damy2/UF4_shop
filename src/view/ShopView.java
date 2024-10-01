package view;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.border.EmptyBorder;

import main.Shop;

import javax.swing.*;

public class ShopView extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton Opcion0;
	private JButton Opcion1;
	private JButton Opcion2;
	private JButton Opcion3;
	private JButton Opcion4;
	private JButton Opcion5;
	private JButton Opcion6;
	private JButton Opcion7;
	private JButton Opcion8;
	private JButton Opcion9;
	private JButton Opcion10;
	private Shop shop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopView frame = new ShopView();
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
	public ShopView() {

		shop = new Shop();
		shop.loadInventory();
		this.addKeyListener(this);
		setFocusable(true);

		setTitle("MiTienda.com - Menu principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Seleccione o pulse una opcion:");
		lblNewLabel.setBounds(147, 0, 148, 28);
		contentPane.add(lblNewLabel);

		Opcion1 = new JButton("1. Contar caja");
		Opcion1.setBounds(29, 35, 179, 28);
		contentPane.add(Opcion1);
		Opcion1.addActionListener(this);

		Opcion2 = new JButton("2. Añadir Producto");
		Opcion2.setBounds(29, 73, 179, 28);
		contentPane.add(Opcion2);
		Opcion2.addActionListener(this);

		Opcion3 = new JButton("3. Añadir stock");
		Opcion3.setBounds(29, 111, 179, 28);
		contentPane.add(Opcion3);
		Opcion3.addActionListener(this);

		Opcion4 = new JButton("4. Establecer expiracion");
		Opcion4.setBounds(29, 149, 179, 28);
		contentPane.add(Opcion4);
		Opcion4.addActionListener(this);

		Opcion5 = new JButton("5. Enseñar inventario");
		Opcion5.setBounds(29, 187, 179, 28);
		contentPane.add(Opcion5);
		Opcion5.addActionListener(this);

		Opcion6 = new JButton("6. Vender");
		Opcion6.setBounds(218, 35, 179, 28);
		contentPane.add(Opcion6);
		Opcion6.addActionListener(this);

		Opcion7 = new JButton("7. Enseñar ventas");
		Opcion7.setBounds(216, 73, 179, 28);
		contentPane.add(Opcion7);
		Opcion7.addActionListener(this);

		Opcion8 = new JButton("8. Enseñar ventas totales");
		Opcion8.setBounds(218, 111, 179, 28);
		contentPane.add(Opcion8);
		Opcion8.addActionListener(this);

		Opcion9 = new JButton("9. Eliminar producto");
		Opcion9.setBounds(218, 149, 179, 28);
		contentPane.add(Opcion9);
		Opcion9.addActionListener(this);

		Opcion10 = new JButton("10. Salir");
		Opcion10.setBounds(218, 187, 179, 28);
		contentPane.add(Opcion10);
		Opcion10.addActionListener(this);
		
		Opcion0 = new JButton("0. Exportar inventario");
		Opcion0.setBounds(123, 225, 179, 28);
		contentPane.add(Opcion0);
		Opcion0.addActionListener(this);

	}

	@Override
	public void keyTyped(KeyEvent e) {
	

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {

		case KeyEvent.VK_1: {
			openCashView();
			break;
		}

		case KeyEvent.VK_2: {
			openProductView(2, shop);
			break;
		}

		case KeyEvent.VK_3: {
			openProductView(3, shop);
			break;
		}
		case KeyEvent.VK_5: {
			openInventoryView(shop);
			break;
		}
		case KeyEvent.VK_6: {
			openSaleView(shop);
			break;
		}

		case KeyEvent.VK_9: {
			openProductView(9, shop);
			break;

		}
		case KeyEvent.VK_0: {
			exportInventory(shop);
			break;
			
		}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	

	}

	@Override                           
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Opcion0) {
			exportInventory(shop);
		}else if (e.getSource() == Opcion1) {
			openCashView();
		} else if (e.getSource() == Opcion2) {
			openProductView(2, shop);
		} else if (e.getSource() == Opcion3) {
			openProductView(3, shop);
		} else if (e.getSource() == Opcion4) {
			openProductView(4, shop);
		} else if (e.getSource() == Opcion5) {
			openInventoryView(shop);
		} else if (e.getSource() == Opcion6){
			openSaleView(shop);
		} else if (e.getSource() == Opcion7) {
			openProductView(9, shop);
		} else if (e.getSource() == Opcion8) {
			openProductView(9, shop);
		} else if (e.getSource() == Opcion9) {
			openProductView(9, shop);
		} else if (e.getSource() == Opcion10) {
			openProductView(9, shop);
		}

	} 

	public void openCashView() {
		CashView CashView = new CashView();
		CashView.setVisible(true);
	}

	public void openProductView(int opcion, Shop shop) {
		ProductView productView = new ProductView(opcion, shop);
		productView.setVisible(true);

	}
	
	public void openInventoryView(Shop shop) {
		InventoryView inventoryView = new InventoryView(shop);
		inventoryView.setVisible(true);
	}
	
	public void openSaleView(Shop shop) {
		SaleView saleView = new SaleView(shop);
		saleView.setVisible(true);
	}
	
	public void exportInventory(Shop shop) {
		if(shop.writeInventory()) {
			JOptionPane.showMessageDialog(ShopView.this, "Inventario exportado correctamente", "Informacion",
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(ShopView.this, "Error exportando inventario", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
