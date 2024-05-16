

package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import main.Shop;
import model.Product;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InventoryView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Shop shop;
	private JTable table;



	/**
	 * Create the dialog.
	 */
	
	public InventoryView(Shop shop) {
		setBounds(100, 100, 705, 265);
		getContentPane().setLayout(new BorderLayout());
		setBounds(100, 100, 374, 194);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			ArrayList<Product> inventory = shop.getInventory();
			Object[][] data = new Object [inventory.size() + 1][4];
			data[0][0] = "Name";
			data[0][1] = "Public Price";
			data[0][2] = "Wholesaler Price";
			data[0][3] = "Stock";
			for(int i = 0;i<inventory.size();i++) {
				if (inventory.get(i) != null) {
					
				data[i+1][0] = inventory.get(i).getName();
				data[i+1][1] = inventory.get(i).getPublicPrice().getValue();
				data[i+1][2] = inventory.get(i).getWholesalerPrice().getValue();
				data[i+1][3] = inventory.get(i).getStock();
				
				}
			}

			String[] columnName = {"Name","Public Price","Wholesealer Price","Stock"};
			table = new JTable(data, columnName);
			table.setModel(new DefaultTableModel(
				data,
				columnName
			));
			table.getColumnModel().getColumn(2).setPreferredWidth(96);
			contentPanel.add(table);
			table.setBounds(10, 10, 340, 300);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.openInventory();
	}
	
	public void openInventory() {

	
	}
}
