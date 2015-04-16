package logic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Terminal extends JFrame implements ActionListener,
		ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3011273548209994374L;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.adminbtn)) {
			this.adminDialog.setVisible(true);
		} else if (e.getSource().equals(this.newbtn)) {
			this.resetTransation();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == this.flavorList
				|| e.getSource() == this.decoratorList) {
			this.caculatePrice();
		}
	}

	public void resetTransation() {
		totalLabel.setText(Default_Total_String);

	}

	public boolean addFlavor(String name, double price) {
		return addFlavor(new Item(name, price));
	}

	public boolean addDecorator(String name, double price) {
		return addDecorator(new Item(name, price));
	}

	public boolean addFlavor(Item item) {
		if (item != null)
			return flavors.addElement(item);
		return false;
	}

	public boolean addDecorator(Item item) {
		if (item != null)
			return decorators.addElement(item);
		return false;
	}

	public void caculatePrice() {
		double total = 0.0;
		// flavor
		Item flavor = flavorList.getSelectedValue();
		if (flavor != null) {
			total += flavor.getPrice();
		}
		for (Item decorator : decoratorList.getSelectedValuesList()) {
			total += decorator.getPrice();
		}
		this.totalLabel.setText("Total: " + total + "$");
	}

	public void initAll() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(480, 320);
		this.setLocationRelativeTo(null);

		flavors = new ItemListModel();
		decorators = new ItemListModel();
		flavorList = new JItemList();
		decoratorList = new JItemList();
		flavorList.setModel(flavors);
		decoratorList.setModel(decorators);
		flavorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		decoratorList
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		adminDialog = new AdminDialog(this, "Administer", true);
		adminDialog.initAll();
		newbtn = new JButton("New IceCream");
		adminbtn = new JButton("System Administrator");
		totalLabel = new JLabel(Default_Total_String, JLabel.LEFT);
		totalLabel.setVerticalAlignment(JLabel.BOTTOM);
		// Listener
		newbtn.addActionListener(this);
		adminbtn.addActionListener(this);
		flavorList.addListSelectionListener(this);
		decoratorList.addListSelectionListener(this);
		// layout
		JPanel jp_north = new JPanel();
		jp_north.setLayout(new GridLayout(1, 3, 5, 5));
		jp_north.add(new JLabel("ICE-CREAM Flavor", JLabel.CENTER));
		jp_north.add(new JLabel("Decorator", JLabel.CENTER));
		jp_north.add(newbtn);
		this.add(jp_north, BorderLayout.NORTH);

		JPanel jp_center = new JPanel();
		jp_center.setLayout(new GridLayout(1, 3, 5, 5));
		jp_center.add(flavorList);
		jp_center.add(decoratorList);
		jp_center.add(this.totalLabel);
		this.add(jp_center, BorderLayout.CENTER);

		JPanel jp_south = new JPanel();
		jp_south.setLayout(new GridLayout(1, 3, 5, 5));
		jp_south.add(adminbtn);
		this.add(jp_south, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	protected ItemListModel flavors;
	protected ItemListModel decorators;
	protected JItemList flavorList;
	protected JItemList decoratorList;
	protected JButton newbtn, adminbtn;
	protected JLabel totalLabel;
	// Dialog
	protected AdminDialog adminDialog;
	//
	public static final String Default_Total_String = "Total:0.0$";

	public static void main(String[] args) {
		Terminal terminal = new Terminal();
		terminal.initAll();
		terminal.addFlavor(new Item("Chocolate", 20.0));
		terminal.addFlavor(new Item("Vanilla", 20.0));
		terminal.addDecorator(new Item("M&M", 5.0));
		terminal.addDecorator(new Item("Strawberry", 4.0));
	}

}
