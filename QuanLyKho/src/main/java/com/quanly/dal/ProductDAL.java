package com.quanly.dal;

public class ProductDAL {
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT p.*, c.category_name FROM Products p JOIN Categories c ON p.category_id = c.category_id";
        try (Connection conn = MyConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ProductDTO p = new ProductDTO();
                p.setId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setCategoryName(rs.getString("category_name"));
                list.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean addProduct(ProductDTO p) {
        String sql = "INSERT INTO Products (product_name, price, quantity, category_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, p.getName());
            pst.setDouble(2, p.getPrice());
            pst.setInt(3, p.getQuantity());
            pst.setInt(4, p.getCategoryId());
            return pst.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }

    // Tương tự viết cho Update và Delete...
}

