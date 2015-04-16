package logic;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AdminDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5606199772730455036L;

	public AdminDialog(Frame owner, String title, boolean model) {
		super(owner, title, model);
		this.parentFrame = (Terminal) owner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean ret = false;
		if (e.getSource() == addFlavorbtn) {
			Item item = getFlavor();
			if (item != null) {
				ret = parentFrame.addFlavor(item);
				if (!ret) {
					JOptionPane.showMessageDialog(this,
							"This flavor is already in the list!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, this.getLastErrorMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == addDecoratorbtn) {
			Item item = this.getDecorator();
			if (item != null) {
				ret = parentFrame.addDecorator(item);
				if (!ret) {
					JOptionPane.showMessageDialog(this,
							"This decorator is already in the list!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, this.getLastErrorMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void initAll() {
		this.addDecoratorbtn = new JButton("Add");
		this.addFlavorbtn = new JButton("Add");
		this.addDecoratorbtn.addActionListener(this);
		this.addFlavorbtn.addActionListener(this);
		this.setSize(320, 120);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(3, 4, 1, 1));

		flavorName = new JTextField();
		flavorPrice = new JTextField();
		decoratorName = new JTextField();
		decoratorPrice = new JTextField();

		this.add(new JLabel("Item"));
		this.add(new JLabel("Name"));
		this.add(new JLabel("Price/$"));
		this.add(new JLabel());

		this.add(new JLabel("Flavor"));
		this.add(flavorName);
		this.add(flavorPrice);
		this.add(addFlavorbtn);

		this.add(new JLabel("Decorator"));
		this.add(decoratorName);
		this.add(decoratorPrice);
		this.add(addDecoratorbtn);
	}

	protected Item getItem(String name, String price_text) {
		double price;
		if (name.isEmpty()) {// should plus an isBlank filter
			lastErrorMessage = "NAME cannot be empty!";
			return null;
		}
		try {
			price = Double.parseDouble(price_text);
		} catch (NumberFormatException e1) {
			lastErrorMessage = "illegal Price format";
			return null;
		}
		Item item = new Item(name, price);
		return item;
	}

	public Item getDecorator() {
		return getItem(this.decoratorName.getText(),
				this.decoratorPrice.getText());
	}

	public Item getFlavor() {
		return getItem(this.flavorName.getText(), this.flavorPrice.getText());
	}

	public JButton addDecoratorbtn, addFlavorbtn;// for listener to detect
	protected JTextField flavorName, flavorPrice, decoratorName,
			decoratorPrice;
	protected Terminal parentFrame;
	protected String lastErrorMessage;

	public String getLastErrorMessage() {
		return lastErrorMessage;
	}

}
