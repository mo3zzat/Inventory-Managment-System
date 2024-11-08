package pk_inventorymanagment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pk_dababase.ConnectionProvider;

public class Customer {

    private int customerPk;
    private String name;
    private String mobileNumber;
    private String email;

    Connection con = null;
    Statement st = null;
    PreparedStatement ps = null;

    public Customer() {
        con = ConnectionProvider.getCon();
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getCustomerPk() {
        return customerPk;
    }

    public void setCustomerPk(int customerPk) {
        this.customerPk = customerPk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailExist() {
        try {
            ps = con.prepareStatement("select * from customer where email = ? and customer_pk != ?");
            ps.setString(1, email);
            ps.setInt(2, customerPk);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Email Address already exist.");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isMobileExist() {
        try {
            ps = con.prepareStatement("select * from customer where mobileNumber = ? and customer_pk != ?");
            ps.setString(1, mobileNumber);
            ps.setInt(2, customerPk);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Mobile Number already exist.");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void getCustomerData(JTable table) {
        try {
            String sql = "select * from Customer ;";
            ResultSet rs = st.executeQuery(sql);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (rs.next()) {
                Object[] u = new Object[4];
                u[0] = rs.getInt(1);
                u[1] = rs.getString(2);
                u[2] = rs.getString(3);
                u[3] = rs.getString(4);
                model.addRow(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert() {
        String sql = "insert into customer(name,mobileNumber,email) "
                + "values(?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, mobileNumber);
            ps.setString(3, email);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Customer added successfully.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Update() {
        String sql = "update customer set name = ?, mobileNumber = ?, email = ?  where customer_pk = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, mobileNumber);
            ps.setString(3, email);
            ps.setInt(4, customerPk);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Customer Updated seccessfully.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
