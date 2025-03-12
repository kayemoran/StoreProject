package Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Service class to manage the product related logic.
 */
public class ProductService {
    private ProductRepository productRepository;

    /**
     * Constructor that initializes repository object.
     * Product repo being initialized to interact with the database.
     */
    public ProductService() {
        productRepository = new ProductRepository();
    }

    /**
     * Shows all products.
     * @return a list of products
     * @throws SQLException if there's a database error
     */
    public ArrayList<Product> showAllProducts() throws SQLException {
        ArrayList<Product> products = productRepository.getAll();


        if (products.isEmpty()) {
            System.out.println("Inga produkter hittades.");
        }else {
            System.out.println("\n=== Produkter i Butiken ===");
            for (Product product : products) {
                System.out.println("ID: "+ product.getProductId() +
                        ", Namn: "+ product.getName() +
                        ", Pris: "+ product.getPrice()+ " kr");
            }
        }
        return products;

    }

    /**
     * Gets a producgt by ID.
     * @param productId the ID of the product to get
     * @return the product, null if not found
     * @throws SQLException if there's a database error
     */
    public Product getProductById (int productId) throws SQLException{
        return productRepository.getProductById(productId);
    }

    /**
     * Updates the product details in the database.
     * @param product the product with updated details
     */
    public void updateProduct (Product product) {
        try {
            productRepository.updateProduct(product);
            System.out.println("Product has been successfully updated.");
        } catch (SQLException e) {
            System.out.println("Failed to update: " + e.getMessage());
        }

    }

    /**
     * Gets a product by its name.
     * @param productName the name of the product to get
     * @return the product, null if not found
     * @throws SQLException if there's a database error
     */
    public Product showProductName(String productName) throws SQLException {
        Product product = productRepository.getProductByName(productName);

        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("No product found by that name.");
        }
        return product;
    }

    /**
     * Shows all categories.
     * @throws SQLException if there's a database error
     */
    public void showCategories() throws SQLException {
        ArrayList<String> categories = productRepository.getAllCategories();

        if (categories.isEmpty()) {
            System.out.println("No categories found");
        } else {
            System.out.println("Categories: ");
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i));
            }
        }
    }

    /**
     * Shows all products in a given category.
     * @param categoryName the category name to filter products by
     */
    public void showProductsByCategory(String categoryName) {
        try {
            ArrayList<Product> products = productRepository.getProductByCategory(categoryName);

            if (products.isEmpty()) {
                System.out.println("No products found");
            } else {
                System.out.println("Products in category " + categoryName + ";");
                for (Product product : products) {
                    System.out.println("ID: "+ product.getProductId() +
                            ", Namn: "+ product.getName() +
                            ", Pris: "+ product.getPrice()+ " kr" +
                            ", Stock: " + product.getStockQuantity());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding products in this category: " + e.getMessage());
        }

    }

    /**
     * Updates the stock quantity of a product.
     * @param productId the ID of the product to update
     * @param quantityOrdered the quantity to subtract from the stock
     */
    public  void updateStockQuantity(int productId, int quantityOrdered) {
        try {
            productRepository.updateStockQuantity(productId, quantityOrdered);

        } catch (SQLException e) {
            System.out.println("Failed to update the stock quantity:" + e.getMessage());
        }

    }


}
