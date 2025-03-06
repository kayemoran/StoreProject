package Product;

import Super.Model; //ärver från Super.Model

public class Product extends Model {

    private int productId;
    private int manufacturerId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    public Product(int productId, String name, String description, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    /*
    public Product(int manufacturerId, String name, String description, double price, int stockQuantity) {
        this.manufacturerId = manufacturerId;
        this.description = description;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

     */


    public int getManufacturerId() {
        return manufacturerId;
    }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    /*public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    //getters setters
    @Override
    public String toString() {
        return String.format("Product ID: %-5d | Name: %-20s | Description: %-20s | Price %-10.2f kr | Stock Quantity: %-5d",
                this.productId, this.name, this.description, this.price, this.stockQuantity);
    }//en snygg strängrepresentation av objekt
}
