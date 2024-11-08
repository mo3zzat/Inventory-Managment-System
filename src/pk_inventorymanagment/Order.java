package pk_inventorymanagment;

import com.itextpdf.text.Paragraph;
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

public class Order {

    private Customer customer;
    private Product product;
    private String order_id;
    private String orderDate;
    private double totalPrice;

    public Paragraph products = new Paragraph();

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String ordr_pk) {
        this.order_id = ordr_pk;
    }
    Connection con = null;
    Statement st = null;
    PreparedStatement ps = null;

    public Order() {
        customer = new Customer();
        product = new Product();

        con = ConnectionProvider.getCon();
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isOrderExist() {
        try {
            ps = con.prepareStatement("select * from order_details where order_pk = ?");
            ps.setString(1, order_id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Order is already exist.");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void getOrderData(JTable customerTable, JTable productTable) {
        customer.getCustomerData(customerTable);
        product.getProductData(productTable);
    }

    public void insert(JTable table) {
        String sql = "insert into order_details(order_id,order_date,totalPrice,customer_fk) "
                + "values(?,?,?,?)";
        StringBuilder orderDetails = new StringBuilder("\tProduct Name \t\tProduct Quantity\tSub Total\n");
        if (table.getRowCount() != 0) {
            try {
                if (table.getRowCount() != 0) {
                    ps = con.prepareStatement(sql);
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    for (int i = 0; i < model.getRowCount(); i++) {
                        product = new Product();
                        product.setProduct_pk(Integer.parseInt(model.getValueAt(i, 0).toString()));

                        int quantity = product.getTotalQuantity() - Integer.parseInt(model.getValueAt(i, 2).toString());
                        System.out.println(product.getTotalQuantity() + "  " + model.getValueAt(i, 2).toString());
                        product.setQuantity(quantity);

                        String productDetails = "\t" + model.getValueAt(i, 1).toString() + "\t\t" + model.getValueAt(i, 2).toString()
                                + "\t" + model.getValueAt(i, 5).toString() + "\n";
                        orderDetails.append(productDetails);
                        product.updateQuantity();

                    }
                    products.add(orderDetails.toString());

                    ps.setString(1, order_id);
                    ps.setString(2, orderDate);
                    ps.setDouble(3, totalPrice);
                    ps.setInt(4, customer.getCustomerPk());
                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "New order added successfully.");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No Row in table.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getOrderData(JTable table) {
        String sql = "select order_id,order_date,totalPrice from inventory.order_details where customer_fk = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, customer.getCustomerPk());
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (rs.next()) {
                Object[] u = new Object[3];
                u[0] = rs.getString(1);
                u[1] = rs.getString(2);
                u[2] = rs.getDouble(3);

                model.addRow(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
