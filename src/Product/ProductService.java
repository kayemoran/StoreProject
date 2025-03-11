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

    public void updateProduct (Product product) throws SQLException {
        String sql = "UPDATE products SET price = ? WHERE product_id = ?";

        try (Connection conn = ProductRepository.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, product.getPrice());
            pstmt.setInt(2, product.getProductId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Ingen produkt uppdaterades. Kontrollera att produkt ID är rätt.");
            } else {
                System.out.println("Produktens pris har uppdaterats i databasen");
            }
        }catch (SQLException e){
            System.out.println("Fel vid uppdatering av produkt: "+ e.getMessage());
        }
    }

    public  void updateStockQuantity(int productId, int quantityOrdered) throws SQLException {
        try {
            productRepository.updateStockQuantity(productId, quantityOrdered);
            System.out.println("Lagerkvantitet uppdaterad för produkt ID: " + productId);
        } catch (SQLException e) {
            System.out.println("Fel vid uppdatering av lagerkvantitet för produkt ID " + productId + ": " + e.getMessage());
        }
    }

    public ArrayList<Product> searchProductsByName(String searchTerm) throws SQLException {
        ArrayList<Product> allProducts = productRepository.getAll();  // Hämta alla produkter
        ArrayList<Product> matchingProducts = new ArrayList<>();

        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(searchTerm.toLowerCase())) { // Matcha produkternas namn
                matchingProducts.add(product);  // Lägg till produkter som matchar sökterm
            }
        }

        return matchingProducts;
    }

}
