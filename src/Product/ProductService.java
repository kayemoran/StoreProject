package Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

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
    public void addProduct(Product product) throws SQLException {
        productRepository.addProduct(product);
    }
    public Product getProductById (int productId) throws SQLException{
        return productRepository.getProductById(productId);
    }

    public void updateProduct (Product product) {
        try {
            productRepository.updateProduct(product);
            System.out.println("Product has been successfully updated.");
        } catch (SQLException e) {
            System.out.println("Failed to update: " + e.getMessage());
        }

    }

    public Product showProductName(String productName) throws SQLException {
        Product product = productRepository.getProductByName(productName);

        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("No product found by that name.");
        }
        return product;
    }

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

    public  void updateStockQuantity(int productId, int quantityOrdered) {
        try {
            productRepository.updateStockQuantity(productId, quantityOrdered);

        } catch (SQLException e) {
            System.out.println("Failed to update the stock quantity:" + e.getMessage());
        }

    }


}
