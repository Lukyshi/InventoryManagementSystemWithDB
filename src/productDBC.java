// add products
// view all products, list all products from database
// search product by id
// search product by name
// update product
// delete product

// select = executeQuery
// insert, update, delete = executeUpdate

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class productDBC {
    public void addProducts(product product) { //create
        // make a sql statement, that will connec to prepared statement
        String sql = "INSERT INTO products(name, price, quantity) values(?, ?, ?)";

        try(Connection connection = DATABASE_Inventory.getConnection(); // connect it to databaseInventory
        PreparedStatement stmt = connection.prepareStatement(sql)){ // read the sql we write
            stmt.setString(1, product.getName()); // the number is a value of question mark
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding" + e.getMessage());
        }
    }

    public List<product> getAllProducts(){ // read
        List<product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try(Connection connection = DATABASE_Inventory.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                product p = mapRowForProducts(rs);
                products.add(p);

            }

        }catch (SQLException e){
            System.out.println("Error " + e.getMessage());
        }
        return products;
    }

    public product mapRowForProducts(ResultSet rs) throws SQLException {
        return new product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("quantity")
        );
    }


    public product getSearchProductByID (int id){
        String sql = "SELECT * FROM products WHERE id = ?";

        try(Connection connection = DATABASE_Inventory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, id); // 1 is the value of question mark
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return new product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
            }
        }catch (SQLException e) {
            System.out.println("Error search " + e.getMessage());
        }
        return null;
    }

    public product getSearchByName(String name){
        String sql = "SELECT * FROM products WHERE name LIKE ?";

        try(Connection connection = DATABASE_Inventory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs =stmt.executeQuery();

            if(rs.next()) {
                return new product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
            }
        }catch (SQLException e){
            System.out.println("Error searching "+ e.getMessage());
        }
        return null;
    }

    public void updateProduct(int id, double newPrice, int newQuantity) {
        String sql = "UPDATE products SET price = ?, quantity = ? WHERE id = ?";

        try(Connection connection = DATABASE_Inventory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDouble(1, newPrice);
            stmt.setInt(2, newQuantity);
            stmt.setInt(3, id);

            // add row
            int rows = stmt.executeUpdate();
            if(rows > 0){
                System.out.println("Product update successfully");
            }else {
                System.out.println("Product update failed");
            }

        }catch(SQLException e){
            System.out.println("Error updating " + e.getMessage());
        }
    }

    public void deleteProduct(int id){
        String sql = "DELETE FROM products WHERE id = ?";

        try(Connection connection = DATABASE_Inventory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if(rows > 0) {
                System.out.println("Product deleted successfully");
            }else {
                System.out.println("Product deleted failed");
            }

        }catch (SQLException e){
            System.out.println("Error deleting " + e.getMessage());
        }
    }

}



