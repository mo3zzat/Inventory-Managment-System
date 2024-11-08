package pk_inventorymanagment;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pk_dababase.ConnectionProvider;

public class User {

    private int appuser_pk;
    private String userRole;
    private String name;
    private String mobileNumber;
    private String email;
    private String password;
    private String address;
    private String Status;
    Connection con = null;
    Statement st = null;
    PreparedStatement ps = null;

    public User() {
        con = ConnectionProvider.getCon();
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getAppuser_pk() {
        return appuser_pk;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return Status;
    }

    // check email address exist or not
    public boolean isEmailExist() {
        try {
            ps = con.prepareStatement("select * from appuser where email = ? and appuser_pk != ?");
            ps.setString(1, email);
            ps.setInt(2, appuser_pk);
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

    // check Phone Number exist or not
    public boolean isPhoneExist() {
        try {
            ps = con.prepareStatement("select * from appuser where mobileNumber = ? and appuser_pk != ?");
            ps.setString(1, mobileNumber);
            ps.setInt(2, appuser_pk);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Phone Number already exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public void getUserData(JTable table) {
        try {
            String sql = "select * from appuser where userRole = 'Admin'";
            ResultSet rs = st.executeQuery(sql);
            User user = null;
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (rs.next()) {
                Object[] u = new Object[8];
                u[0] = rs.getInt(1);
                u[1] = rs.getString(2);
                u[2] = rs.getString(3);
                u[3] = rs.getString(4);
                u[4] = rs.getString(5);
                u[5] = rs.getString(6);
                u[6] = rs.getString(7);
                u[7] = rs.getString(8);
                model.addRow(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insert() {
        String sql = "insert into appuser(userRole,name,mobileNumber,email,password,address,status) "
                + "values(?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, userRole);
            ps.setString(2, name);
            ps.setString(3, mobileNumber);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setString(6, address);
            ps.setString(7, Status);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New User added successfully.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update() {
        String sql = "update appuser set name = ? , mobileNumber = ?, email = ?, address = ?, status = ?"
                + " where appuser_pk = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, mobileNumber);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setString(5, Status);
            ps.setInt(6, appuser_pk);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "User Updated seccessfully.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setAppuser_pk(int appuser_pk) {
        this.appuser_pk = appuser_pk;
    }

}
