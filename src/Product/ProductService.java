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


}
