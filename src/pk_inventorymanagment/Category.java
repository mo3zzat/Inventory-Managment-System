package pk_inventorymanagment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pk_dababase.ConnectionProvider;

public class Category {

    private int category_pk;
    private String name;
    Connection con = null;
    Statement st = null;
    PreparedStatement ps = null;

    public Category() {
        con = ConnectionProvider.getCon();
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getCategory_id() {
        return category_pk;
    }

    public void setCategory_id(int category_id) {
        this.category_pk = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCategoryExist() {
        try {
            ps = con.prepareStatement("select * from category where name = ? and category_pk != ?");
            ps.setString(1, name);
            ps.setInt(2, category_pk);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Category already exist.");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void getCategoryData(JTable table) {
        try {
            String sql = "select * from category";
            ResultSet rs = st.executeQuery(sql);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (rs.next()) {
                Object[] u = new Object[2];
                u[0] = rs.getInt(1);
                u[1] = rs.getString(2);
                model.addRow(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert() {
        String sql = "insert into category(name) "
                + "values(?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New Category added successfully.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Update() {
        String sql = "update category set name = ? where category_pk = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, category_pk);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Category Updated seccessfully.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Category> getAllCategory() {
        try {
            ArrayList<Category> categorys = new ArrayList<>();
            ResultSet rs = st.executeQuery("select * from category");
            while (rs.next()) {
                Category c = new Category(rs.getInt(1), rs.getString(2));
                categorys.add(c);
            }
            return categorys;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return new ArrayList<>();
    }

    public Category(int category_pk, String name) {
        this.category_pk = category_pk;
        this.name = name;
    }
}
