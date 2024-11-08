package pk_inventorymanagment;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pk_dababase.ConnectionProvider;

public class Product {

    private int product_pk;
    private String name;
    private int Quantity;
    private double price;
    private String description;
    private Category category;

    Connection con = null;
    Statement st = null;
    PreparedStatement ps = null;

    public Product() {
        con = ConnectionProvider.getCon();
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getProduct_pk() {
        return product_pk;
    }

    public void setProduct_pk(int product_pk) {
        this.product_pk = product_pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(int id, String name) {
        this.category = new Category(id, name);
    }

    public boolean isProductExist() {
        try {
            ps = con.prepareStatement("select * from product where name = ? and product_pk != ?");
            ps.setString(1, name);
            ps.setInt(2, product_pk);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Product Name already exist.");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void getProductData(JTable table) {
        try {
            String sql = "select product_pk,p.name, p.quantity,p.Price,p.description,c.category_pk,c.name from "
                    + "inventory.product p inner join inventory.category c on(p.category_fk = c.category_pk) ;";
            ResultSet rs = st.executeQuery(sql);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (rs.next()) {
                Object[] u = new Object[7];
                u[0] = rs.getInt(1);
                u[1] = rs.getString(2);
                u[2] = rs.getInt(3);
                u[3] = rs.getDouble(4);
                u[4] = rs.getString(5);
                u[5] = rs.getInt(6);
                u[6] = rs.getString(7);
                model.addRow(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert() {
        String sql = "insert into Product(name,Quantity,price,description,category_fk) "
                + "values(?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, Quantity);
            ps.setDouble(3, price);
            ps.setString(4, description);
            ps.setInt(5, category.getCategory_id());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product added successfully.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
        String sql = "update product set name = ? , price = ?, description = ? , quantity = ? where product_pk = ?";
        try {
            if (isProductExist()) {
                ps = con.prepareStatement(sql);
                System.out.println(name);
                ps.setString(1, name);
                ps.setDouble(2, price);
                ps.setString(3, description);
                ps.setInt(4, Quantity);
                ps.setInt(5, product_pk);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Product Updated seccessfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cannot Update.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateQuantity() {
        String sql = "update product set quantity = ? where product_pk = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, Quantity);
            ps.setInt(2, product_pk);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product Updated seccessfully.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getTotalQuantity() {
        try {
            ps = con.prepareStatement("select quantity from product where product_pk = ?");
            ps.setInt(1, product_pk);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            } else {
                JOptionPane.showMessageDialog(null, "Product is already exist.");
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
