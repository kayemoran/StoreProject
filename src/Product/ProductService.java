package Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public ArrayList<Product> showAllProducts() throws SQLException {
        ArrayList<Product> products = productRepository.getAll();

        System.out.println();

        if (products.isEmpty()) {
            System.out.println("Inga produkter hittades.");
        }

        // Skriv ut alla kunder med tydlig formatering
        System.out.println("\n=== Products ===");
        return products;

    }
    public void addProduct(Product product) throws SQLException {
        productRepository.addProduct(product);
    }
    public Product getProductById (int productId) throws SQLException{
        return productRepository.getProductById(productId);
    }

    public void updateProduct (Product product) throws SQLException {
        productRepository.addProduct(product);
    }

    public  void updateStockQuantity(int productId, int quantityOrdered) throws SQLException {
        productRepository.updateStockQuantity(productId, quantityOrdered);
    }


}
