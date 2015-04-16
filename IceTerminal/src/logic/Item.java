package logic;

public class Item {
	public Item(String name, double price) {
		this.setName(name);
		this.setPrice(price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String toString() {
		return "[" + this.name + ", " + this.price + "$]";
	}

	@Override
	/**
	 * @param object, an Item
	 * @return returns true only if the two items have the same name!
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Item)) {
			return object.equals(this);
		}
		Item item = (Item) object;
		if (item.name.compareTo(this.name) == 0)
			return true;
		return false;
	}

	public int compareTo(Item item) {
		double diff = this.price - item.price;
		if (diff > 0)
			return 1;
		else if (diff < 0)
			return -1;
		else
			return 0;
	}

	protected String name;
	protected double price;
}
