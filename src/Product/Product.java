package Product;

import Super.Model; //ärver från Super.Model

/**
 * Class representing a product in the system.
 * It extends from the Model class.
 */
public class
Product extends Model {

    private int productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    /**
     * Constructor that initializes an object of Product.
     * @param productId the ID of the product
     * @param name the name of the product
     * @param description the description of the product
     * @param price the price of the product
     * @param stockQuantity the stock quantity of the product
     */
    public Product(int productId, String name, String description, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }


    /**
     * Gets the product ID.
     * @return the product ID
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the product ID.
     * @param productId the new product ID
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }


    /**
     * Gets the product name.
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     * @param name the new product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the product description.
     * @return the product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the product description.
     * @param description the new product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the product price.
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     * @param price the new product price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the stock quantity of the product.
     * @return the stock quantity of the product
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Sets the stock quantity of the product.
     * @param stockQuantity the new stock quantity of the product
     */
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    //getters setters

    /**
     * String representation of the product.
     * @return formatted string of product
     */
    @Override
    public String toString() {
        return String.format("Product ID: %-5d | Name: %-20s | Description: %-20s | Price %-10.2f kr | Stock Quantity: %-5d",
                this.productId, this.name, this.description, this.price, this.stockQuantity);
    }//en snygg strängrepresentation av objekt
}
