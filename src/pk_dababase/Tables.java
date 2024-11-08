package pk_dababase;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Tables {

    public static void main(String[] args) throws SQLException {
        Connection con = null;
        Statement st = null;
        try {
            con = ConnectionProvider.getCon();
            st = con.createStatement();
            /*
                st.executeUpdate("create table appuser(\n"
                    + "	appuser_pk int auto_increment primary key,\n"
                    + "    userRole varchar(50),\n"
                    + "    name varchar(150),\n"
                    + "    mobileNumber varchar(15),\n"
                    + "    email varchar(100),\n"
                    + "    password varchar(50),\n"
                    + "    address varchar(150),\n"
                    + "    status varchar(50)\n"
                    + ");");
            
            st.executeUpdate("insert into inventory.appuser(userRole,name,mobileNumber,email,password,address,status) \n"
                    + "	values('SuperAdmin' , 'Super Admin', '01122646784','superadmin@testgmail.com','admin','Egypt','true');");*/
            // Category Table;
            /*st.executeUpdate(
                    "create table category(\n"
                    + "	category_pk int auto_increment primary key,\n"
                    + "    name varchar(150) not null unique\n"
                    + ");"
            );*/

            // Create Product Table
            /*st.executeUpdate("create table product(\n"
                    + "	product_pk int auto_increment primary key,\n"
                    + "    name varchar(150) not null unique,\n"
                    + "    quantity int,\n"
                    + "    price double,\n"
                    + "    description varchar(350),\n"
                    + "    category_fk int,\n"
                    + "    key fk_category_pk (category_fk),\n"
                    + "    constraint fk_category_pk foreign key (category_fk) references category (category_pk) on delete cascade\n"
                    + ");");*/
            // Create Customer Table
            /* st.executeUpdate("create table customer(\n"
                    + "	customer_pk int auto_increment primary key,\n"
                    + "    name varchar(100) not null,\n"
                    + "    mobileNumber varchar(15),\n"
                    + "    email varchar(150)\n"
                    + ");");*/
            //Create order table
            st.executeUpdate("create table order_details(\n"
                    + "	order_pk int auto_increment primary key ,\n"
                    + "    order_id varchar(150),\n"
                    + "    order_date date,\n"
                    + "    totalPrice numeric(10,2) not null,\n"
                    + "	customer_fk int,\n"
                    + "    product_fk int,\n"
                    + "    productQuantity int not null,\n"
                    + "    key fk_customer_pk (customer_fk),\n"
                    + "    constraint fk_customer_pk foreign key (customer_fk) references customer (customer_pk),\n"
                    + "    key fk_product_pk (product_fk),\n"
                    + "    constraint fk_product_pk foreign key (product_fk) references product (product_pk)\n"
                    + ");");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            con.close();
            st.close();
        }
    }
}
