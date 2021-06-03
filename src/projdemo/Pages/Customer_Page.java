/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projdemo.Pages;

/**
 *
 * @author OMA
 */

import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableModel;
import projdemo.Customer;
import projdemo.reg_User;


public class Customer_Page extends javax.swing.JFrame {

    /**
     * Creates new form Customer_Page
     */
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    DefaultTableModel model = new DefaultTableModel(){
        
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    
    String type;
    
    public Customer_Page() {
        initComponents();
        conn = Register_User.ConnectDB();
        Object col[] = {"CustomerID","CustomerName","Date","UserID","Room_No","NoOfPC","Session","Time","Card_No","Exp_Date","Amount"};
        model.setColumnIdentifiers(col);
        CustTable.setModel(model);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        DateFormat format = new SimpleDateFormat("MM/dd/yy");
        dateChooser.setDateFormat(format);
        ExpdateChooser.setDateFormat(format);
        setexpdatenull();
        ExpdateChooser.setEnabled(false);
         SpinnerNumberModel smodel = new SpinnerNumberModel(1,1,5,1);
        Spinner.setModel(smodel);
        showTable();
    }

    public ArrayList<reg_User> userList() {
        ArrayList<reg_User> userList = new ArrayList<>();
        conn = Register_User.ConnectDB();
        try {
            String q1 = "SELECT * FROM REGISTERED_USER";
            Statement st = conn.createStatement();
            rs = st.executeQuery(q1);
            reg_User user;
            while (rs.next()) {
                user = new reg_User(rs.getInt("UserID"), rs.getString("UserName"), rs.getString("Name"), rs.getString("Password"), rs.getInt("Contact_No"), rs.getString("Email"),
                        rs.getString("Reg_Date"), rs.getString("Sec_Q"), rs.getString("Answer"));
                userList.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return userList;
    }
    
   
    public ArrayList<Customer> custList() {
        ArrayList<Customer> custList = new ArrayList<>();
        conn = Register_User.ConnectDB();
        try {
            String q1 = "SELECT * FROM CUSTOMERS";
            try (Statement st = conn.createStatement()) {
                rs = st.executeQuery(q1);
                Customer cust;
                while (rs.next()) {
                    cust = new Customer(rs.getInt("CustomerID"), rs.getString("CustomerName"), rs.getString("Date"), rs.getInt("UserID"),
                            rs.getInt("Room_No"), rs.getInt("NoOfPC"),rs.getString("Session"), rs.getString("Time"),
                            rs.getInt("Card_No"),rs.getString("Exp_Date"),rs.getInt("Amount"));
                    custList.add(cust);
                }
                rs.close();
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return custList;
    }
    
    
    public String getDateChooser() {
        SimpleDateFormat s = new SimpleDateFormat("MM/dd/yy");
        java.util.Date d = dateChooser.getSelectedDate().getTime();
        String str = s.format(d);
        return str;
    }
    
    private void showTable(){
        ArrayList<Customer> list = custList();
//        for(Customer n: list)
//            System.out.println(n);
        DefaultTableModel mod = (DefaultTableModel)CustTable.getModel();  
        Object[] row = new Object[11];
        for(int i=0; i<list.size(); ++i){
            row[0] = list.get(i).getCustomer_id();
            row[1] = list.get(i).getCustomername();
            row[2] = list.get(i).getDate();
            row[3] = list.get(i).getUser_id();
            row[4] = list.get(i).getRoom_no();
            row[5] = list.get(i).getNoOfPC();
            row[6] = list.get(i).getSession();
            row[7] = list.get(i).getTime();
            row[8] = list.get(i).getCard_no();
            row[9] = list.get(i).getExpDate();
            row[10] = list.get(i).getAmount();
            mod.addRow(row);
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
        AddButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        CustTable = new javax.swing.JTable();
        DeleteButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        ClearButton = new javax.swing.JButton();
        EditButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dateChooser = new datechooser.beans.DateChooserCombo();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        CustomerIDField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        UserIDField = new javax.swing.JTextField();
        CusNameField = new javax.swing.JTextField();
        SearchLabel = new javax.swing.JLabel();
        RoomNoBox = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        RadioButton4 = new javax.swing.JRadioButton();
        Spinner = new javax.swing.JSpinner();
        RadioButton3 = new javax.swing.JRadioButton();
        THourBox = new javax.swing.JComboBox();
        RadioButton1 = new javax.swing.JRadioButton();
        RadioButton2 = new javax.swing.JRadioButton();
        RadioButton5 = new javax.swing.JRadioButton();
        RadioButton6 = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        CardNoField = new javax.swing.JTextField();
        ExpdateChooser = new datechooser.beans.DateChooserCombo();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        AmountField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        HourBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/gzonebig.png"))); // NOI18N

        AddButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AddButton.setText("Add");
        AddButton.setEnabled(false);
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        CustTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CustTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CustomerID", "CustomerName", "Date", "UserID", "Room_No", "NoOfPC", "Session", "Time", "Card_No", "Exp_Date", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        CustTable.setEditingColumn(0);
        CustTable.setEditingRow(0);
        CustTable.getTableHeader().setReorderingAllowed(false);
        CustTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CustTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(CustTable);

        DeleteButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        DeleteButton.setText("Delete");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        ExitButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        ClearButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ClearButton.setText("Clear");
        ClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearButtonActionPerformed(evt);
            }
        });

        EditButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EditButton.setText("Edit");
        EditButton.setEnabled(false);
        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("CustomerID:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("CustomerName:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("UserID:");

        dateChooser.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Date:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Session:");

        CustomerIDField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Room No:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Time:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Type:");

        UserIDField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        UserIDField.setEnabled(false);
        UserIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserIDFieldActionPerformed(evt);
            }
        });

        CusNameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CusNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CusNameFieldActionPerformed(evt);
            }
        });

        SearchLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/icons8-search-18.png"))); // NOI18N
        SearchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchLabelMouseClicked(evt);
            }
        });

        RoomNoBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        RoomNoBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        RoomNoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomNoBoxActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("No of PC's:");

        RadioButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        RadioButton4.setText("Registered");
        RadioButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioButton4MouseClicked(evt);
            }
        });
        RadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton4ActionPerformed(evt);
            }
        });

        Spinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Spinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));

        RadioButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        RadioButton3.setSelected(true);
        RadioButton3.setText("Visitor");
        RadioButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioButton3MouseClicked(evt);
            }
        });

        THourBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        THourBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12 - 2", "1 - 3", "2 - 4", "3 - 5", " " }));
        THourBox.setEnabled(false);
        THourBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                THourBoxActionPerformed(evt);
            }
        });

        RadioButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        RadioButton1.setSelected(true);
        RadioButton1.setText("1 Hour");
        RadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioButton1MouseClicked(evt);
            }
        });
        RadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton1ActionPerformed(evt);
            }
        });

        RadioButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        RadioButton2.setText("2 Hours");
        RadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioButton2MouseClicked(evt);
            }
        });
        RadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton2ActionPerformed(evt);
            }
        });

        RadioButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        RadioButton5.setSelected(true);
        RadioButton5.setText("Cash");
        RadioButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioButton5MouseClicked(evt);
            }
        });

        RadioButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        RadioButton6.setText("Credit Card");
        RadioButton6.setEnabled(false);
        RadioButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioButton6MouseClicked(evt);
            }
        });
        RadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton6ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Payment Type:");

        CardNoField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CardNoField.setEnabled(false);

        ExpdateChooser.setEnabled(false);
        ExpdateChooser.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Card No:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Expiration Date:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Amount:");

        AmountField.setEditable(false);
        AmountField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Calculate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        HourBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        HourBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12 - 1", "1 - 2", "2 - 3", "3 - 4", "4 - 5" }));
        HourBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HourBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CustomerIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(SearchLabel))
                            .addComponent(CusNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(RoomNoBox, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(RadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(RadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(HourBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(THourBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(RadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(23, 23, 23)
                                    .addComponent(RadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(UserIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(AmountField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ExpdateChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CardNoField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(RadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(8, 8, 8)
                                    .addComponent(RadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomerIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CusNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RadioButton3)
                    .addComponent(RadioButton4))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RoomNoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RadioButton1)
                        .addComponent(RadioButton2)))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(THourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RadioButton5)
                    .addComponent(RadioButton6))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CardNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExpdateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(AmountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(EditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(ClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(512, 512, 512)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        // TODO add your handling code here:
//        String sql = "INSERT INTO CUSTOMERS VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = Register_User.ConnectDB();
            CallableStatement cs = conn.prepareCall("{call PR_ADD_CUST(?,?,?,?,?,?,?,?,?,?,?)}");
//            pst = conn.prepareStatement(sql);
            cs.setString(1, CustomerIDField.getText());
            cs.setString(2, CusNameField.getText());
            SimpleDateFormat s = new SimpleDateFormat("MM/dd/yy");
            java.util.Date d = dateChooser.getSelectedDate().getTime();
            String str = s.format(d);
            cs.setString(3, str);
            if(UserIDField.getText().isEmpty()){
                cs.setString(4, "");
            }else{
                cs.setString(4, UserIDField.getText());
            }
            cs.setString(5, RoomNoBox.getSelectedItem().toString());
            cs.setString(6, Spinner.getValue().toString());
            if (RadioButton1.isSelected()) {
                cs.setString(7, RadioButton1.getLabel());
                cs.setString(8, HourBox.getSelectedItem().toString());
                String l = "INSERT INTO SESSIONS(ROOM_NO,PC_NO,"+"\""+"Date"+"\""+","+"\""+"Time"+"\")VALUES(?,?,?,?)";
                PreparedStatement p = conn.prepareStatement(l);
                p.setString(1, RoomNoBox.getSelectedItem().toString());
                p.setString(2, Spinner.getValue().toString());
                p.setString(3, getDateChooser());
                if (RadioButton1.isSelected()) {
                    p.setString(4, HourBox.getSelectedItem().toString());
                } else if (RadioButton2.isSelected()) {
                    p.setString(4, THourBox.getSelectedItem().toString());
                }
                p.execute();
            } else if (RadioButton2.isSelected()) {
                cs.setString(7, RadioButton2.getLabel());
                cs.setString(8, THourBox.getSelectedItem().toString());
                String t1 = "";
                String t2 = "";
                if (THourBox.getSelectedItem().equals("12 - 2")) {
                    t1 = "12 - 1";
                    t2 = "1 - 2";
                } else if (THourBox.getSelectedItem().equals("1 - 3")) {
                    t1 = "1 - 2";
                    t2 = "2 - 3";
                } else if (THourBox.getSelectedItem().equals("2 - 4")) {
                    t1 = "2 - 3";
                    t2 = "3 - 4";
                } else if (THourBox.getSelectedItem().equals("3 - 5")) {
                    t1 = "3 - 4";
                    t2 = "4 - 5";
                }
                String lo = "INSERT INTO SESSIONS(ROOM_NO,PC_NO,"+"\""+"Date"+"\""+","+"\""+"Time"+"\")VALUES(?,?,?,?)";
                PreparedStatement st= conn.prepareStatement(lo);
                st.setString(1, RoomNoBox.getSelectedItem().toString());
                st.setString(2, Spinner.getValue().toString());
                st.setString(3, getDateChooser());
                st.setString(4, t1);
                st.execute();

                String m = "INSERT INTO SESSIONS(ROOM_NO,PC_NO,"+"\""+"Date"+"\""+","+"\""+"Time"+"\")VALUES(?,?,?,?)";
                st = conn.prepareStatement(m);
                st.setString(1, RoomNoBox.getSelectedItem().toString());
                st.setString(2, Spinner.getValue().toString());
                st.setString(3, getDateChooser());
                st.setString(4, t2);
                st.execute();

            }
            if (CardNoField.getText().isEmpty()) {
                cs.setString(9, "");
            } else {
                cs.setString(9, CardNoField.getText());
            }
            java.util.Date ed = ExpdateChooser.getSelectedDate().getTime();
            String tam = s.format(ed);
            cs.setString(10, tam);
            cs.setString(11, AmountField.getText());
            cs.execute();
            JOptionPane.showMessageDialog(null, "System Insertion Completed");
            model.setRowCount(0);
            showTable();
            EditButton.setEnabled(false);
            AddButton.setEnabled(false);
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_AddButtonActionPerformed

    private void CustTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustTableMouseClicked
        // TODO add your handling code here:
        int index = CustTable.getSelectedRow();
        TableModel m = CustTable.getModel();
        CustomerIDField.setText(m.getValueAt(index, 0).toString());
        CusNameField.setText((String) m.getValueAt(index, 1));
        //dateChooser.setText((String)m.getValueAt(index,2));
        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date dateobj = new Date();
        try {
            dateobj = df.parse((String) m.getValueAt(index, 2));
        } catch (ParseException ex) {
            Logger.getLogger(Customer_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
        df.format(dateobj);
        dateChooser.setDateFormat(df);
        Calendar c = Calendar.getInstance();
        c.setTime(dateobj);
        dateChooser.setSelectedDate(c);
        if (!m.getValueAt(index, 3).toString().equals("0")) {
            RadioButton3.setSelected(false);
            RadioButton4.setSelected(true);
            UserIDField.setEnabled(true);
            UserIDField.setText(m.getValueAt(index, 3).toString());
        } else {
            RadioButton4.setSelected(false);
            RadioButton3.setSelected(true);
            UserIDField.setEnabled(false);
            RadioButton5.setSelected(true);
            RadioButton6.setEnabled(false);
            RadioButton6.setSelected(false);
            CardNoField.setEnabled(false);
            CardNoField.setText(null);
            setexpdatenull();
            ExpdateChooser.setEnabled(false);
            UserIDField.setText(null);
        }
        for (int i = 0; i < RoomNoBox.getItemCount(); ++i) {
            if (RoomNoBox.getItemAt(i).toString().equals(m.getValueAt(index, 4).toString())) {
                RoomNoBox.setSelectedIndex(i);
            }
        }
//        System.out.println("V");
        Spinner.setValue(Integer.parseInt(m.getValueAt(index, 5).toString()));
        if (m.getValueAt(index, 6).toString().equals("1 Hour")) {
//            System.out.println("W");
            RadioButton2.setSelected(false);
            RadioButton1.setSelected(true);
            HourBox.setEnabled(true);
            THourBox.setEnabled(false);
            for (int i = 0; i < HourBox.getItemCount(); ++i) {
                if (HourBox.getItemAt(i).toString().equals(m.getValueAt(index, 7).toString())) {
                    HourBox.setSelectedIndex(i);
                }
            }
        } else {
//            System.out.println("X");
            RadioButton2.setSelected(true);
            RadioButton1.setSelected(false);
            THourBox.setEnabled(true);
            HourBox.setEnabled(false);
            for (int i = 0; i < THourBox.getItemCount(); ++i) {
                if (THourBox.getItemAt(i).toString().equals(m.getValueAt(index, 7).toString())) {
                    THourBox.setSelectedIndex(i);
                }
            }           
        }
//         System.out.println("Y");
//            System.out.println(m.getValueAt(index, 8));
        if ((Integer.parseInt(m.getValueAt(index, 8).toString()) == 0)) {
//                System.out.println("A");
                RadioButton6.setSelected(false);
                RadioButton5.setSelected(true);
                setexpdatenull();
                CardNoField.setEnabled(false);
                ExpdateChooser.setEnabled(false);
                CardNoField.setText(null);

            } else {
//                System.out.println("B");
                RadioButton5.setSelected(false);
                RadioButton6.setSelected(true);
                RadioButton6.setEnabled(true);
                CardNoField.setEnabled(true);
                ExpdateChooser.setEnabled(true);
                CardNoField.setText(m.getValueAt(index, 8).toString());
                df = new SimpleDateFormat("MM/dd/yy");
                c = Calendar.getInstance();
                Date dobj = new Date();
                try {
                    dobj = df.parse((String) m.getValueAt(index, 9));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                df.format(dobj);
                ExpdateChooser.setDateFormat(df);
                c.setTime(dobj);
                ExpdateChooser.setSelectedDate(c);
            }
        AmountField.setText(m.getValueAt(index, 10).toString());
    }//GEN-LAST:event_CustTableMouseClicked

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        // TODO add your handling code here:
        conn = Register_User.ConnectDB();
        if (CustTable.getSelectedRow() != 0) {
            int opt = JOptionPane.showConfirmDialog(null, "Are you sure you want to Delete?", "Delete", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                try {
                    int row = CustTable.getSelectedRow();
                    String str = CustTable.getModel().getValueAt(row, 0).toString();
                    String sql = "DELETE FROM CUSTOMERS WHERE CustomerID=" + str;
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleted");
                    model.setRowCount(0);
                    showTable();
                    
                    
                    
//                    System.out.println("Try");
                    try {
                        if (RadioButton2.isSelected()) {
                            String t1 = "";
                            String t2 = "";
//                            System.out.println("a");
                            if (THourBox.getSelectedItem().equals("12 - 2")) {
                                t1 = "12 - 1";
                                t2 = "1 - 2";
                            } else if (THourBox.getSelectedItem().equals("1 - 3")) {
                                t1 = "1 - 2";
                                t2 = "2 - 3";
                            } else if (THourBox.getSelectedItem().equals("2 - 4")) {
                                t1 = "2 - 3";
                                t2 = "3 - 4";
                            } else if (THourBox.getSelectedItem().equals("3 - 5")) {
                                t1 = "3 - 4";
                                t2 = "4 - 5";
                            }
                            String s = "DELETE FROM SESSIONS WHERE ROOM_NO = ? AND PC_NO = ? AND "+"\""+"Date"+"\""+" = ? AND "+"\""+"Time"+"\""+" = ?";
//                            System.out.println("A");
//                            String s = "INSERT INTO Session(Room_No,PC_No,Date,Time)VALUES(?,?,?,?)";
                            conn = Register_User.ConnectDB();
                            pst = conn.prepareStatement(s);
//                            System.out.println("a1");
                            pst.setString(1, RoomNoBox.getSelectedItem().toString());
//                            System.out.print(RoomNoBox.getSelectedItem().toString()+" ");
                            pst.setString(2, Spinner.getValue().toString());
//                            System.out.print(Spinner.getValue().toString()+" ");
                            pst.setString(3, getDateChooser());
//                            System.out.print(getDateChooser()+" ");
                            pst.setString(4, t1);
//                            System.out.println(t1);
                            pst.executeUpdate();
                            pst.close();

//                            System.out.println("b");
                            String m = "DELETE FROM SESSIONS WHERE ROOM_NO = ? AND PC_NO = ? AND "+"\""+"Date"+"\""+" = ? AND "+"\""+"Time"+"\""+" = ?";
//                            String m = "INSERT INTO Session(Room_No,PC_No,Date,Time)VALUES(?,?,?,?)";
//                            System.out.println("B");
                            pst = conn.prepareStatement(m);
                            pst.setString(1, RoomNoBox.getSelectedItem().toString());
//                            System.out.print(RoomNoBox.getSelectedItem().toString()+" ");
                            pst.setString(2, Spinner.getValue().toString());
//                            System.out.print(Spinner.getValue().toString()+" ");
                            pst.setString(3, getDateChooser());
//                            System.out.println(getDateChooser()+" ");
                            pst.setString(4, t2);
//                            System.out.println(t2);
                            pst.executeUpdate();
                            pst.close();
                        } else if (RadioButton1.isSelected()) {
                            String s = "DELETE FROM SESSIONS WHERE ROOM_NO = ? AND PC_NO = ? AND "+"\""+"Date"+"\""+" = ? AND "+"\""+"Time"+"\""+" = ?";
//                            String s = "INSERT INTO Session(Room_No,PC_No,Date,Time)VALUES(?,?,?,?)";
//                            System.out.println("C");
                            conn = Register_User.ConnectDB();
                            pst = conn.prepareStatement(s);
                            pst.setString(1, RoomNoBox.getSelectedItem().toString());
//                            System.out.print(RoomNoBox.getSelectedItem().toString()+" ");
                            pst.setString(2, Spinner.getValue().toString());
//                            System.out.print(Spinner.getValue().toString()+" ");
                            pst.setString(3, getDateChooser());
//                            System.out.print(getDateChooser()+" ");
                            if (RadioButton1.isSelected()) {
                                pst.setString(4, HourBox.getSelectedItem().toString());
//                                System.out.println(HourBox.getSelectedItem().toString());
                            } else if (RadioButton2.isSelected()) {
                                pst.setString(4, THourBox.getSelectedItem().toString());
//                                System.out.println(THourBox.getSelectedItem().toString());
                            }
                            pst.executeUpdate();
                            pst.close();
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    CustomerIDField.setText(null);
                    CusNameField.setText(null);
                    DateFormat df = new SimpleDateFormat("MM/dd/yy");
                    Date dateobj = new Date();
                    df.format(dateobj);
                    dateChooser.setDateFormat(df);
                    Calendar c = Calendar.getInstance();
                    c.setTime(dateobj);
//                dateChooser.setCurrent(c);
//                dateChooser.getCurrent().setTime(dateobj);
//                dateChooser.setDateFormat(df);
                    dateChooser.setSelectedDate(c);
                    UserIDField.setEnabled(false);
                    RadioButton1.setSelected(true);
                    RadioButton2.setSelected(false);
                    RadioButton3.setSelected(true);
                    RadioButton4.setSelected(false);
                    UserIDField.setText(null);
                    RoomNoBox.setSelectedIndex(0);
                    Spinner.setValue(1);
                    HourBox.setEnabled(true);
                    HourBox.setSelectedIndex(0);
                    THourBox.setEnabled(false);
                    THourBox.setSelectedIndex(0);
                    RadioButton5.setSelected(true);
                    RadioButton6.setSelected(false);
                    CardNoField.setText(null);
                    setexpdatenull();
                    CardNoField.setEnabled(false);
                    ExpdateChooser.setEnabled(false);
                    AmountField.setText(null);
                    CustTable.clearSelection();
                    rs.close();
                    pst.close();
                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select record to delete");
        }
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        // TODO add your handling code here:
        HomeAdmin ob = new HomeAdmin();
        ob.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed
        // TODO add your handling code here:
        CustomerIDField.setText(null);
        CusNameField.setText(null);
        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date dateobj = new Date();
        df.format(dateobj);
        dateChooser.setDateFormat(df);
        Calendar c = Calendar.getInstance();
        c.setTime(dateobj);
//        dateChooser.setCurrent(c);
//        dateChooser.getCurrent().setTime(dateobj);
//        dateChooser.setDateFormat(df);
        dateChooser.setSelectedDate(c); 
//        System.out.println(dateChooser.getCurrent().getTime());
        RadioButton1.setSelected(true);
        RadioButton2.setSelected(false);
        RadioButton3.setSelected(true);
        RadioButton4.setSelected(false);
        UserIDField.setText(null);
        RoomNoBox.setSelectedIndex(0);
        Spinner.setValue(1);
        HourBox.setEnabled(true);
        HourBox.setSelectedIndex(0);
        THourBox.setEnabled(false);
        THourBox.setSelectedIndex(0);
        RadioButton5.setSelected(true);
        RadioButton6.setSelected(false);
        UserIDField.setEnabled(false);
        CardNoField.setText(null);
        CardNoField.setEnabled(false);
        setexpdatenull();
        ExpdateChooser.setEnabled(false);
        AmountField.setText(null);
        CustTable.clearSelection();
        
        
    }//GEN-LAST:event_ClearButtonActionPerformed

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonActionPerformed
        // TODO add your handling code here:
        conn = Register_User.ConnectDB();
        try {
            String str = CustomerIDField.getText();
//            String sql = "UPDATE CUSTOMERS SET CustomerName=?,"+"\""+"Date"+"\""+"=?,UserID=?,Room_No=?,NoOfPC=?,"+"\""+"Session"+"\""+"=?,"+"\""+"Time"+"\""+"=?,Card_No=?,Exp_Date=?,Amount=?"
//                    + " WHERE CustomerID=" + str;
//            pst = conn.prepareStatement(sql);
            CallableStatement cs = conn.prepareCall("{call PR_UPD_CUST(?,?,?,?,?,?,?,?,?,?,"+str+")}");
            cs.setString(1, CusNameField.getText());
            SimpleDateFormat s = new SimpleDateFormat("MM/dd/yy");
            java.util.Date d = dateChooser.getSelectedDate().getTime();
            String r = s.format(d);
            cs.setString(2, r);
            cs.setString(3, UserIDField.getText());
            cs.setString(4, RoomNoBox.getSelectedItem().toString());
            cs.setString(5, Spinner.getValue().toString());
            if (RadioButton1.isSelected()) {
                cs.setString(6, RadioButton1.getLabel());
                cs.setString(7, HourBox.getSelectedItem().toString());
                
            } else if (RadioButton2.isSelected()) {
                cs.setString(6, RadioButton2.getLabel());
                cs.setString(7, THourBox.getSelectedItem().toString());
            }
//            String label;
//            if(RadioButton1.isSelected()){
//                label = RadioButton1.getLabel();
//            }
//            else{
//                label = RadioButton2.getLabel();
//            }
            //pst.setString(6, label);
            cs.setString(8, CardNoField.getText());
            java.util.Date ed = ExpdateChooser.getSelectedDate().getTime();
            String tam = s.format(ed);
            cs.setString(9, tam);
            cs.setString(10, AmountField.getText());
            cs.executeUpdate();

            JOptionPane.showMessageDialog(null, "Update Completed");
            model.setRowCount(0);
            showTable();
//            rs.close();
//            pst.close();
//            conn.close();
            EditButton.setEnabled(false);
            AddButton.setEnabled(false);
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_EditButtonActionPerformed

    private void UserIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserIDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserIDFieldActionPerformed

    private void CusNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CusNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CusNameFieldActionPerformed

    private void SearchLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchLabelMouseClicked
        // TODO add your handling code here:
        conn = Register_User.ConnectDB();
        if (CustomerIDField.getText().isEmpty()) {
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(frame, "Enter CustomerID");
        } else {
            try {
                String sql = "SELECT * FROM CUSTOMERS WHERE CUSTOMERID=" + CustomerIDField.getText();
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    CustomerIDField.setText(rs.getString("CustomerID"));
                    CusNameField.setText(rs.getString("CustomerName"));
                    DateFormat df = new SimpleDateFormat("MM/dd/yy");
                    Date dateobj = new Date();
                    try {
                        dateobj = df.parse(rs.getString("Date"));
                    } catch (ParseException ex) {
                        Logger.getLogger(Customer_Page.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    df.format(dateobj);
                    dateChooser.setDateFormat(df);
                    Calendar c = Calendar.getInstance();
                    c.setTime(dateobj);
                    dateChooser.setSelectedDate(c);
                    if (rs.getInt("UserID") != 0) {
                        RadioButton3.setSelected(false);
                        RadioButton4.setSelected(true);
                        UserIDField.setEnabled(true);
                        UserIDField.setText(rs.getString("UserID"));
                    } else {
                        RadioButton4.setSelected(false);
                        RadioButton3.setSelected(true);
                        UserIDField.setEnabled(false);
                        UserIDField.setText(null);
                    }
                    for (int i = 0; i < RoomNoBox.getItemCount(); ++i) {
                        if (RoomNoBox.getItemAt(i).toString().equals(rs.getString("Room_No"))) {
                            RoomNoBox.setSelectedIndex(i);
                        }
                    }
//                System.out.println(rs.getString("Session"));
                    Spinner.setValue(Integer.parseInt(rs.getString("NoOfPC")));
                    if (rs.getString("Session").equals("1 Hour")) {
//                    System.out.println("W");
                        RadioButton2.setSelected(false);
                        RadioButton1.setSelected(true);
                        THourBox.setEnabled(false);
                        HourBox.setEnabled(true);
                        for (int i = 0; i < HourBox.getItemCount(); ++i) {
                            if (HourBox.getItemAt(i).toString().equals(rs.getString("Time"))) {
                                HourBox.setSelectedIndex(i);
                            }
                        }
                    } else {
//                    System.out.println("X");
                        RadioButton2.setSelected(true);
                        RadioButton1.setSelected(false);
                        HourBox.setEnabled(false);
                        THourBox.setEnabled(true);
                        for (int i = 0; i < THourBox.getItemCount(); ++i) {
                            if (THourBox.getItemAt(i).toString().equals(rs.getString("Time"))) {
                                THourBox.setSelectedIndex(i);
                            }
                        }
                    }
//                System.out.println("Y");
                    if (rs.getInt("CARD_NO") == 0){
//                        System.out.println("P");
                        RadioButton6.setSelected(false);
                        RadioButton5.setSelected(true);
                        CardNoField.setEnabled(false);
                        setexpdatenull();
                        ExpdateChooser.setEnabled(false);
                        CardNoField.setText(null);
//                    Date dobj = new Date();
//                    df.format(dobj);
//                    dateChooser.setDateFormat(df);
//                    c.setTime(dobj);
//                    ExpdateChooser.setSelectedDate(c);
                    } else {
                        RadioButton5.setSelected(false);
                        RadioButton6.setSelected(true);
                        RadioButton6.setEnabled(true);
                        CardNoField.setEnabled(true);
                        ExpdateChooser.setEnabled(true);
                        CardNoField.setText(rs.getString("Card_No"));
                        Date dobj = new Date();
                        try {
                            dobj = df.parse(rs.getString("Exp_Date"));
                        } catch (ParseException ex) {
                            Logger.getLogger(Customer_Page.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        df.format(dobj);
                        ExpdateChooser.setDateFormat(df);
                        c.setTime(dobj);
                        ExpdateChooser.setSelectedDate(c);
                    }
                    AmountField.setText(rs.getString("Amount"));
                    for (int n = 0; n < CustTable.getRowCount(); ++n) {
                        if (model.getValueAt(n, 0).toString().equals(CustomerIDField.getText())) {
                            CustTable.getSelectionModel().setSelectionInterval(n, n);
                        }
                    }
                }
                rs.close();
                pst.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Not Found");
            }
        }
    }//GEN-LAST:event_SearchLabelMouseClicked

    private void RoomNoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomNoBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomNoBoxActionPerformed

    private void THourBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_THourBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_THourBoxActionPerformed

    private void RadioButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioButton4MouseClicked
        // TODO add your handling code here:
        RadioButton4.setSelected(true);
        UserIDField.setText(type);
        UserIDField.setEnabled(true);
        RadioButton3.setSelected(false);
        RadioButton6.setEnabled(true);
        RadioButton5.setSelected(true);

    }//GEN-LAST:event_RadioButton4MouseClicked

    public void setexpdatenull(){
        DateFormat so = new SimpleDateFormat("MM/dd/yy");
        Date dado = new Date();
        so.format(dado);
        ExpdateChooser.setDateFormat(so);
        Calendar la = Calendar.getInstance();
        la.setTime(dado);
        ExpdateChooser.setSelectedDate(la);
    }
    
    private void RadioButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioButton3MouseClicked
        // TODO add your handling code here:
        RadioButton3.setSelected(true);
        type = UserIDField.getText();
        UserIDField.setEnabled(false);
        UserIDField.setText(null);
        RadioButton4.setSelected(false);
        RadioButton6.setEnabled(false);
        CardNoField.setEnabled(false);
        CardNoField.setEnabled(false);
        this.setexpdatenull();
        ExpdateChooser.setEnabled(false);
        RadioButton6.setSelected(false);
        RadioButton5.setSelected(true);
    }//GEN-LAST:event_RadioButton3MouseClicked

    private void RadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioButton1MouseClicked
        // TODO add your handling code here:
        RadioButton2.setSelected(false);
        RadioButton1.setSelected(true);
        THourBox.setEnabled(false);
        HourBox.setEnabled(true);
    }//GEN-LAST:event_RadioButton1MouseClicked

    private void RadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioButton2MouseClicked
        // TODO add your handling code here:
        RadioButton1.setSelected(false);
        RadioButton2.setSelected(true);
        HourBox.setEnabled(false);
        THourBox.setEnabled(true);
    }//GEN-LAST:event_RadioButton2MouseClicked

    private void RadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioButton4ActionPerformed

    private String cNo;
    private Calendar cal;
    private void RadioButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioButton5MouseClicked
        // TODO add your handling code here:
        RadioButton5.setSelected(true);
        if (RadioButton5.isSelected()) {
            cNo = UserIDField.getText();
            CardNoField.setEnabled(false);
            setexpdatenull();
            ExpdateChooser.setEnabled(false);
            CardNoField.setText(null);
            RadioButton6.setSelected(false);
        }
    }//GEN-LAST:event_RadioButton5MouseClicked

    private void RadioButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioButton6MouseClicked
        // TODO add your handling code here:
        RadioButton6.setSelected(true);
        if (RadioButton6.isEnabled()) {
            CardNoField.setEnabled(true);
            ExpdateChooser.setEnabled(true);
            ExpdateChooser.setSelectedDate(cal);
            CardNoField.setText(cNo);
            RadioButton5.setSelected(false);
        }
    }//GEN-LAST:event_RadioButton6MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String s;
        if(RadioButton1.isSelected()){
            int amount = 80;
            s = ""+amount;
            AmountField.setText(s);
        }
        else if(RadioButton2.isSelected() ){
            int amount = 160;
            s = ""+amount;
            AmountField.setText(s);
        }
        AddButton.setEnabled(true);
        EditButton.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void HourBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HourBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HourBoxActionPerformed

    private void RadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioButton1ActionPerformed

    private void RadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioButton2ActionPerformed

    private void RadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Customer_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Customer_Page().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JTextField AmountField;
    private javax.swing.JTextField CardNoField;
    private javax.swing.JButton ClearButton;
    private javax.swing.JTextField CusNameField;
    private javax.swing.JTable CustTable;
    private javax.swing.JTextField CustomerIDField;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton EditButton;
    private javax.swing.JButton ExitButton;
    private datechooser.beans.DateChooserCombo ExpdateChooser;
    private javax.swing.JComboBox HourBox;
    private javax.swing.JRadioButton RadioButton1;
    private javax.swing.JRadioButton RadioButton2;
    private javax.swing.JRadioButton RadioButton3;
    private javax.swing.JRadioButton RadioButton4;
    private javax.swing.JRadioButton RadioButton5;
    private javax.swing.JRadioButton RadioButton6;
    private javax.swing.JComboBox RoomNoBox;
    private javax.swing.JLabel SearchLabel;
    private javax.swing.JSpinner Spinner;
    private javax.swing.JComboBox THourBox;
    private javax.swing.JTextField UserIDField;
    private datechooser.beans.DateChooserCombo dateChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
