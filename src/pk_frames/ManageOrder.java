/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pk_frames;

import Common.OpenPdf;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pk_dababase.InventoryUtils;
import pk_inventorymanagment.Customer;
import pk_inventorymanagment.Order;
import pk_inventorymanagment.Product;

/**
 *
 * @author G.A
 */
public class ManageOrder extends javax.swing.JFrame {

    private int customerPk = 0;
    private int productPk = 0;
    private double finalTotalPrice = 0.0;
    private int productQuantity = 0;
    private String orderId;

    private Order order = new Order();

    public ManageOrder() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void clearProductFeilds() {
        txtProductName.setText(null);
        txtProductPrice.setText(null);
        txtProductDescription.setText(null);
        txtOrderQuantity.setText(null);
    }

    private String getUniqueId(String prefix) {
        return prefix + System.nanoTime();
    }

    private boolean validateFields() {
        if (!txtOrderQuantity.getText().isEmpty() && !txtCustomerName.getText().isEmpty() && !txtProductName.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private void CloseEditable() {
        txtCustomerName.setEditable(false);
        txtCustomerName.setBackground(Color.GRAY);
        txtCustomerMobileNumber.setEditable(false);
        txtCustomerMobileNumber.setBackground(Color.GRAY);
        txtCustomerEmail.setEditable(false);
        txtCustomerEmail.setBackground(Color.GRAY);
        txtProductName.setEditable(false);
        txtProductName.setBackground(Color.GRAY);
        txtProductPrice.setEditable(false);
        txtProductPrice.setBackground(Color.GRAY);
        txtProductDescription.setEditable(false);
        txtProductDescription.setBackground(Color.GRAY);
    }

    private void Clear() {
        setVisible(false);
        new ManageOrder().setVisible(true);
    }

    private void addToCart() {
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        for (int i = 0; i < cartTable.getRowCount(); i++) {
            if (Integer.parseInt(model.getValueAt(i, 0).toString()) == productPk) {
                JOptionPane.showMessageDialog(null, "Product already exist in cart.");
                return;
            }
        }
        int subQuantity = Integer.parseInt(txtOrderQuantity.getText());
        if (productQuantity >= subQuantity) {
            Object[] object = new Object[6];
            object[0] = productPk;
            object[1] = txtProductName.getText();
            object[2] = subQuantity;
            object[3] = txtProductPrice.getText();
            object[4] = txtProductDescription.getText();

            double subTotal = subQuantity * Double.parseDouble(txtProductPrice.getText());
            finalTotalPrice += subTotal;
            object[5] = subTotal;
            model.addRow(object);
            JOptionPane.showMessageDialog(this, "Added Successfully to cart.");
            clearProductFeilds();
        } else {
            JOptionPane.showMessageDialog(this, "product is out of stock. only " + productQuantity + " left.");
        }
    }

    public void createDocument() {
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(InventoryUtils.billPath + "" + orderId + ".pdf"));
            doc.open();
            Paragraph projectName = new Paragraph("\t\t\tInventory Management System\n");
            doc.add(projectName);
            Paragraph starLine = new Paragraph("****************************************************************************************************************\n");
            doc.add(starLine);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Calendar cal = Calendar.getInstance();
            Paragraph details = new Paragraph("\n\tOrder Id: " + orderId + "\n\tDate: " + sdf.format(cal.getTime()) + "\n\t Total Price\t" + finalTotalPrice + "\n\n");
            doc.add(details);
            doc.add(starLine);
            PdfPTable tbl = new PdfPTable(5);

            PdfPCell name = new PdfPCell(new Phrase("Name"));
            PdfPCell description = new PdfPCell(new Phrase("Description"));
            PdfPCell price = new PdfPCell(new Phrase("Price"));
            PdfPCell quantity = new PdfPCell(new Phrase("Quantity"));
            PdfPCell SubTotal = new PdfPCell(new Phrase("Sub Total Price "));

            BaseColor background = new BaseColor(255, 204, 51);
            name.setBackgroundColor(background);
            description.setBackgroundColor(background);
            price.setBackgroundColor(background);
            quantity.setBackgroundColor(background);
            SubTotal.setBackgroundColor(background);

            tbl.addCell(name);
            tbl.addCell(description);
            tbl.addCell(price);
            tbl.addCell(quantity);
            tbl.addCell(SubTotal);

            for (int i = 0; i < cartTable.getRowCount(); i++) {
                tbl.addCell(cartTable.getValueAt(i, 1).toString());
                tbl.addCell(cartTable.getValueAt(i, 4).toString());
                tbl.addCell(cartTable.getValueAt(i, 3).toString());
                tbl.addCell(cartTable.getValueAt(i, 2).toString());
                tbl.addCell(cartTable.getValueAt(i, 5).toString());
            }
            doc.add(tbl);
            doc.add(starLine);

            Paragraph thank = new Paragraph("\n\nThank you please Visit agian");
            doc.add(thank);

            OpenPdf.openById(orderId);
            doc.close();

        } catch (Exception ex) {
            Logger.getLogger(ManageOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCustomerMobileNumber = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCustomerEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtProductPrice = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtProductDescription = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtOrderQuantity = new javax.swing.JTextField();
        btnAddToCart = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        lblFinalPrice = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Impact", 3, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Manage Order");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(556, 0, 249, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Customer List");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 57, 372, -1));

        customerTable.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        customerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Mobile Number", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(customerTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 86, 372, 251));

        productTable.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", " Quantity", "Price", "Description", "Category ID", "Category Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(productTable);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 86, 464, 251));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Product List");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 57, 464, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Cart");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(878, 57, 473, -1));

        cartTable.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Name", "Quantity", "Price", "Description", "Sub Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cartTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(cartTable);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(878, 86, 473, 251));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Selected Customer");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 355, 372, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel6.setText("Name");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 394, 117, -1));

        txtCustomerName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        getContentPane().add(txtCustomerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 432, 325, 28));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel7.setText("Mobile Number");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 482, 144, -1));

        txtCustomerMobileNumber.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        getContentPane().add(txtCustomerMobileNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 523, 325, 28));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel8.setText("Email");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 573, 110, -1));

        txtCustomerEmail.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        getContentPane().add(txtCustomerEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 614, 325, 28));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Selected Product");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 355, 464, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel10.setText("Product Name ");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 394, 148, -1));

        txtProductName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        getContentPane().add(txtProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 432, 325, 28));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel11.setText("Product Price");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 482, 124, -1));

        txtProductPrice.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        getContentPane().add(txtProductPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 523, 325, 28));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel12.setText("Product Description");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 573, 161, -1));

        txtProductDescription.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        getContentPane().add(txtProductDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 614, 325, 28));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel13.setText("Order Quantity");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 660, 141, -1));

        txtOrderQuantity.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        getContentPane().add(txtOrderQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 690, 325, 28));

        btnAddToCart.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnAddToCart.setText("Add to Cart");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 730, 325, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setText("Total Amount RS: ");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(994, 393, 165, -1));

        lblFinalPrice.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblFinalPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFinalPrice.setText("00000");
        getContentPane().add(lblFinalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(1165, 393, 62, -1));

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnSave.setText("Save Order Details");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(878, 478, 473, -1));

        btnReset.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(878, 569, 473, -1));

        btnClose.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        getContentPane().add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(878, 660, 473, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pk_image/Orders_background.png"))); // NOI18N
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        order.getOrderData(customerTable, productTable);
        CloseEditable();
    }//GEN-LAST:event_formComponentShown

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (finalTotalPrice != 0 && !txtCustomerName.getText().isEmpty()) {

            orderId = getUniqueId("Bill-");
            order.setOrder_id(orderId);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Calendar cal = Calendar.getInstance();
            order.setOrderDate(sdf.format(cal.getTime()));
            order.setTotalPrice(finalTotalPrice);
            if (order.isOrderExist()) {
                order.insert(cartTable);
                createDocument();
                Clear();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please added some product to cart or select Customer.");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void customerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTableMouseClicked
        // TODO add your handling code here:
        int index = 0;
        order = new Order();
        Customer customer = new Customer();
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        index = customerTable.getSelectedRow();
        customerPk = Integer.parseInt(model.getValueAt(index, 0).toString());
        customer.setCustomerPk(customerPk);
        txtCustomerName.setText(model.getValueAt(index, 1).toString());
        customer.setName(txtCustomerName.getText());
        txtCustomerMobileNumber.setText(model.getValueAt(index, 2).toString());
        customer.setMobileNumber(txtCustomerMobileNumber.getText());
        txtCustomerEmail.setText(model.getValueAt(index, 3).toString());
        customer.setEmail(txtCustomerEmail.getText());
        order.setCustomer(customer);
    }//GEN-LAST:event_customerTableMouseClicked

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        Clear();
    }//GEN-LAST:event_btnResetActionPerformed

    private void productTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productTableMouseClicked
        // TODO add your handling code here:
        Product product = new Product();
        int index = 0;
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        index = productTable.getSelectedRow();

        productPk = Integer.parseInt(model.getValueAt(index, 0).toString());
        product.setProduct_pk(productPk);

        txtProductName.setText(model.getValueAt(index, 1).toString());
        product.setName(txtProductName.getText());

        productQuantity = Integer.parseInt(model.getValueAt(index, 2).toString());
        product.setQuantity(productQuantity);

        txtProductPrice.setText(model.getValueAt(index, 3).toString());
        product.setPrice(Double.parseDouble(txtProductPrice.getText()));

        txtProductDescription.setText(model.getValueAt(index, 4).toString());
        product.setDescription(txtProductDescription.getText());

        int id = Integer.parseInt(model.getValueAt(index, 5).toString());
        product.setCategory(id, model.getValueAt(index, 6).toString());

        order.setProduct(product);
    }//GEN-LAST:event_productTableMouseClicked

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        // TODO add your handling code here:
        if (!validateFields()) {
            addToCart();
            lblFinalPrice.setText(finalTotalPrice + "");
        } else {
            JOptionPane.showMessageDialog(null, "All fields are Required.");
        }
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void cartTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartTableMouseClicked
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "Do you want remove this product.", "selected", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
            int index = cartTable.getSelectedRow();
            finalTotalPrice = finalTotalPrice - Double.parseDouble(model.getValueAt(index, 5).toString());
            ((DefaultTableModel) cartTable.getModel()).removeRow(index);
            lblFinalPrice.setText(String.valueOf(finalTotalPrice));
        }
    }//GEN-LAST:event_cartTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JTable cartTable;
    private javax.swing.JTable customerTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblFinalPrice;
    private javax.swing.JTable productTable;
    private javax.swing.JTextField txtCustomerEmail;
    private javax.swing.JTextField txtCustomerMobileNumber;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtOrderQuantity;
    private javax.swing.JTextField txtProductDescription;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtProductPrice;
    // End of variables declaration//GEN-END:variables
}
