package logic;

import java.util.Vector;

import javax.swing.AbstractListModel;

public class ItemListModel extends AbstractListModel<Item> {

	private static final long serialVersionUID = 8426355927837502191L;

	@Override
	public int getSize() {
		return items.size();
	}

	@Override
	public Item getElementAt(int index) {
		return items.get(index);
	}

	/**
	 * @param item
	 * @return
	 */
	public boolean addElement(Item item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).equals(item)) {
				return false;
			}
		}
		items.add(item);
		fireContentsChanged(this, 0, items.size());
		return true;
	}

	protected Vector<Item> items = new Vector<Item>();
}
