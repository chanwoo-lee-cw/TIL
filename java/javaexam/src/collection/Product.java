package collection;

public class Product {

	private String productID;
	private String productName;
	private String productPrice;

	Product(String productID, String productName, String productPrice) {
		setProductID(productID);
		setProductName(productName);
		setProductPrice(productPrice);
	}

	public String proInfo() {
		return String.format("%-10s%-10s%-10s", productID, productName, productPrice);
	}


	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

}
