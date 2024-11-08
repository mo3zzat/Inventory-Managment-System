package pk_frames;

import java.awt.Color;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pk_inventorymanagment.User;

public class ManageUser extends javax.swing.JFrame {

    int appuserpk = 0;
    User user = new User();

    public ManageUser() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public boolean validateFields(String formType) {
        if (formType.equalsIgnoreCase("edit") && !txtName.getText().isEmpty() && !txtPhone.getText().isEmpty()
                && !txtEmail.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
            return false;
        } else if (formType.equalsIgnoreCase("new") && !txtName.getText().isEmpty() && !txtPhone.getText().isEmpty()
                && !txtEmail.getText().isEmpty() && !txtPassword.getText().isEmpty() && !txtAddress.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean isEmailValid() {
        String email = txtEmail.getText();

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (pat.matcher(email).matches()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Email is not Valid");
            return false;
        }
    }

    private void clear() {
        setVisible(false);
        new ManageUser().setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comobStatus = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Impact", 1, 36)); // NOI18N
        jLabel1.setText("Manage User");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 6, 215, -1));

        tableUser.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "User Roel", "Name", "Phone Numebr", "Email", "Password", "Address", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableUser);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 57, 484, 543));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setText("Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 57, 308, 28));

        txtName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 97, 325, 28));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setText("Phone Number");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 137, 308, 28));

        txtPhone.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        getContentPane().add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 177, 325, 28));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel4.setText("Email");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 217, 102, 28));

        txtEmail.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 257, 325, 28));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel5.setText("Address");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 92, 28));

        txtAddress.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        getContentPane().add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 420, 325, 28));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel6.setText("Status");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 451, 308, 28));

        comobStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        comobStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        getContentPane().add(comobStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 491, 134, 28));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel7.setText("Password");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 300, 158, 28));

        txtPassword.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 340, 325, 28));

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 537, -1, -1));

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 537, 90, -1));

        btnreset.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnreset.setText("Reset");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });
        getContentPane().add(btnreset, new org.netbeans.lib.awtextra.AbsoluteConstraints(678, 537, 80, -1));

        btnClose.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        getContentPane().add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(763, 537, 70, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pk_image/All_page_Background.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        user.getUserData(tableUser);
        btnUpdate.setEnabled(false);
    }//GEN-LAST:event_formComponentShown

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (!validateFields("new") && isEmailValid()) {
            User u = new User();
            u.setUserRole("Admin");
            u.setName(txtName.getText());
            u.setMobileNumber(txtPhone.getText());
            u.setEmail(txtEmail.getText());
            u.setPassword(String.valueOf(txtPassword.getPassword()));
            u.setAddress(txtAddress.getText());
            u.setStatus(comobStatus.getSelectedItem().toString());
            if (u.isEmailExist() && u.isPhoneExist()) {
                u.insert();
                clear();
            }
            //u.getUserData(tableUser);
        } else {
            JOptionPane.showMessageDialog(this, "All field are required");
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void tableUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUserMouseClicked
        int index = 0;

        DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
        index = tableUser.getSelectedRow();
        appuserpk = Integer.parseInt(model.getValueAt(index, 0).toString());
        txtName.setText(model.getValueAt(index, 2).toString());
        txtPhone.setText(model.getValueAt(index, 3).toString());
        txtEmail.setText(model.getValueAt(index, 4).toString());
        txtPassword.setText(model.getValueAt(index, 5).toString());
        txtPassword.setEditable(false);
        txtPassword.setBackground(Color.darkGray);
        txtAddress.setText(model.getValueAt(index, 6).toString());
        if (model.getValueAt(index, 7).toString().equals("Active")) {
            comobStatus.setSelectedIndex(0);
        } else {
            comobStatus.setSelectedIndex(1);
        }
        btnSave.setEnabled(false);
        btnUpdate.setEnabled(true);
    }//GEN-LAST:event_tableUserMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (!validateFields("edit") && isEmailValid()) {
            User u = new User();
            u.setAppuser_pk(appuserpk);
            u.setName(txtName.getText());
            u.setMobileNumber(txtPhone.getText());
            u.setEmail(txtEmail.getText());
            u.setAddress(txtAddress.getText());
            u.setStatus(comobStatus.getSelectedItem().toString());
            if (u.isEmailExist() && u.isPhoneExist()) {
                u.update();
                clear();
            }
        } else {
            JOptionPane.showMessageDialog(this, "All field are required");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnresetActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnreset;
    private javax.swing.JComboBox<String> comobStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUser;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
