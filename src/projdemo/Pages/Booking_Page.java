/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projdemo.Pages;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import projdemo.Booking;
import projdemo.reg_User;

/**
 *
 * @author OMA
 */
public class Booking_Page extends javax.swing.JFrame {

    /**
     * Creates new form Booking_Page
     */
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    DefaultTableModel model = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Booking_Page() throws SQLException {
        initComponents();
        conn = Register_User.ConnectDB();
        if(Login_Page.e.equals("Admin")){
            TablePanel.setVisible(true);
            SearchPanel.setVisible(true);
        }
        if(Login_Page.e.equals("User")){
            TablePanel.setVisible(true);
            SearchPanel.setVisible(true);
        }
        Object col[] = {"BookingID","UserID", "UserName","Booking_Date","Room_No","NoOfPC","Session","Time"};
        model.setColumnIdentifiers(col);
        BookTable.setModel(model);
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        showTable();
        DateFormat format = new SimpleDateFormat("MM/dd/yy");
        bookdateChooser.setDateFormat(format);
        dateChooser.setDateFormat(format);
        SpinnerNumberModel smodel = new SpinnerNumberModel(1,1,5,1);
        Spinner.setModel(smodel);
//        conn.close();
    }

    public ArrayList<reg_User> userList() {
        ArrayList<reg_User> userList = new ArrayList<>();
        try {
            String q1 = "SELECT * FROM REGISTERED_USER";
            try (Statement st = conn.createStatement()) {
                rs = st.executeQuery(q1);
                reg_User user;
                while (rs.next()) {
                    user = new reg_User(rs.getInt("UserID"), rs.getString("UserName"), rs.getString("Name"), rs.getString("Password"), rs.getInt("Contact_No"), rs.getString("Email"),
                            rs.getString("Reg_Date"), rs.getString("Sec_Q"), rs.getString("Answer"));
                    userList.add(user);
                }
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return userList;
    }

    public ArrayList<Booking> bookList() {
//        conn = Register_User.ConnectDB();
        String sql = null;
        ArrayList<reg_User> u_List = userList();
        for (reg_User n : u_List) {
//            System.out.println(n);
        }
        ArrayList<Booking> bookList = new ArrayList<>();
        try {

            if(Login_Page.e.equals("User")){
                sql = "SELECT * FROM BOOKINGS WHERE USERID = "+Login_Page.id;
            }
            else{
                sql = "SELECT * FROM BOOKINGS";
            }
            try (Statement st = conn.createStatement()) {
                rs = st.executeQuery(sql);
                Booking booking;
                while (rs.next()) {
                    booking = new Booking(rs.getInt("BookingID"), rs.getInt("UserID"), rs.getString("UserName"), rs.getString("Booking_Date"), rs.getInt("Room_No"),
                            rs.getInt("NoOfPC"), rs.getString("Session"), rs.getString("Time"));
                    bookList.add(booking);

                }
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return bookList;
    }

    private void UserSearch() {
        conn = Register_User.ConnectDB();
//        UserIDField.setEnabled(true);
//        UserNameField.setEnabled(true);
//        NameField.setEnabled(true);
//        PassField.setEnabled(true);
//        ContactNoField.setEnabled(true);
//        EmailField.setEnabled(true);
//        dateChooser.setEnabled(true);
//        AnswerField.setEnabled(true);
//        SecurityQuestionBox.setEnabled(true);

        if (UserIDField.getText().isEmpty()) {
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(frame, "Enter UserID");
        } else {
            try {
                String sql = "SELECT * FROM REGISTERED_USER WHERE USERID=" + UserIDField.getText();
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    UserIDField.setText(rs.getString("UserID"));
                    UserNameField.setText(rs.getString("UserName"));
                    NameField.setText(rs.getString("Name"));
                    ContactNoField.setText(rs.getString("Contact_No"));
                    EmailField.setText(rs.getString("Email"));
                    setDateChooser(rs.getString("Reg_Date"));
                    //dateChooser.setText(rs.getString("Reg_Date"));
                }
                pst.close();
                rs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Found");
            }
        }
    }

    private void showTable() {
//        conn = Register_User.ConnectDB();
        ArrayList<Booking> list = bookList();
        for (Booking n : list) {
//            System.out.println(n);
        }
        DefaultTableModel mod = (DefaultTableModel) BookTable.getModel();
        Object[] row = new Object[8];
        for (int i = 0; i < list.size(); ++i) {
            row[0] = list.get(i).getBookingNo();
            row[1] = list.get(i).getUserID();
            row[2] = list.get(i).getUserName();
            row[3] = list.get(i).getBooking_date();
            row[4] = list.get(i).getRoom_no();
            row[5] = list.get(i).getNoOfPC();
            row[6] = list.get(i).getSession();
            row[7] = list.get(i).getTime();
            mod.addRow(row);
        }
    }

    public String getBookDateChooser() {
        SimpleDateFormat s = new SimpleDateFormat("MM/dd/yy");
        java.util.Date d = bookdateChooser.getSelectedDate().getTime();
        String str = s.format(d);
        return str;
    }

    public void setDateChooser(String str) {
        try {
            DateFormat df = new SimpleDateFormat("MM/dd/yy");
            Date dobj = new Date();
            dobj = df.parse(str);
            df.format(dobj);
            Calendar c = Calendar.getInstance();
            dateChooser.setDateFormat(df);
            c.setTime(dobj);
            dateChooser.setSelectedDate(c);
        } catch (ParseException ex) {
            Logger.getLogger(Booking_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setBookDateChooser(String str) {
        try {
            DateFormat mp = new SimpleDateFormat("MM/dd/yy");
            Date dj = new Date();
            dj = mp.parse(str);
            mp.format(dj);
            Calendar c = Calendar.getInstance();
            bookdateChooser.setDateFormat(mp);
            c.setTime(dj);
            bookdateChooser.setSelectedDate(c);
        } catch (ParseException ex) {
            Logger.getLogger(Booking_Page.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        Reg_Table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        bookdateChooser = new datechooser.beans.DateChooserCombo();
        UserNameField2 = new javax.swing.JTextField();
        UserIDField1 = new javax.swing.JTextField();
        BookIDField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        RadioButton1 = new javax.swing.JRadioButton();
        RadioButton2 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Spinner = new javax.swing.JSpinner();
        RoomNoBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        HourBox = new javax.swing.JComboBox();
        THourBox = new javax.swing.JComboBox();
        SearchPanel = new javax.swing.JPanel();
        bookingSearchLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        dateChooser = new datechooser.beans.DateChooserCombo();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        NameField = new javax.swing.JTextField();
        UserIDField = new javax.swing.JTextField();
        EmailField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        ContactNoField = new javax.swing.JTextField();
        UserNameField = new javax.swing.JTextField();
        UserSearchLabel = new javax.swing.JLabel();
        Button = new javax.swing.JButton();
        ModifyButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        AddButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        ClearButton = new javax.swing.JButton();
        TablePanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        BookTable = new javax.swing.JTable();

        Reg_Table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Reg_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UserID", "UserName", "Name", "Password", "Contact_No", "Email", "Registration_Date", "Security_Question", "Answer"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Reg_Table.setEditingColumn(0);
        Reg_Table.setEditingRow(0);
        Reg_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Reg_TableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Reg_Table);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/gzonebig.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 6, -1, -1));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("BookingID:");

        bookdateChooser.setEnabled(false);
        bookdateChooser.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

        UserNameField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        UserNameField2.setEnabled(false);

        UserIDField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        UserIDField1.setEnabled(false);
        UserIDField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserIDField1ActionPerformed(evt);
            }
        });

        BookIDField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BookIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookIDFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("UserName:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("UserID:");

        RadioButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        RadioButton1.setText("1 hour");
        RadioButton1.setEnabled(false);
        RadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton1ActionPerformed(evt);
            }
        });

        RadioButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        RadioButton2.setText("2 hours");
        RadioButton2.setEnabled(false);
        RadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Booking Date:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Room No:");

        Spinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Spinner.setAutoscrolls(true);
        Spinner.setEnabled(false);
        Spinner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SpinnerMousePressed(evt);
            }
        });

        RoomNoBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        RoomNoBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        RoomNoBox.setEnabled(false);
        RoomNoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomNoBoxActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("No of PC's:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Session Duration:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Time:");

        HourBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        HourBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12 - 1", "1 - 2", "2 - 3", "3 - 4", "4 - 5" }));
        HourBox.setEnabled(false);
        HourBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HourBoxActionPerformed(evt);
            }
        });

        THourBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        THourBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12 - 2", "1 - 3", "2 - 4", "3 - 5" }));
        THourBox.setEnabled(false);
        THourBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                THourBoxActionPerformed(evt);
            }
        });

        bookingSearchLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bookingSearchLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/icons8-search-18.png"))); // NOI18N
        bookingSearchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookingSearchLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout SearchPanelLayout = new javax.swing.GroupLayout(SearchPanel);
        SearchPanel.setLayout(SearchPanelLayout);
        SearchPanelLayout.setHorizontalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bookingSearchLabel)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        SearchPanelLayout.setVerticalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bookingSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bookdateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UserIDField1)
                    .addComponent(UserNameField2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(RoomNoBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(RadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(RadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(BookIDField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(HourBox, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(THourBox, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(BookIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(SearchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserIDField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserNameField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bookdateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RoomNoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(RadioButton1)
                    .addComponent(RadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(THourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 480, -1));

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("UserID:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("UserName:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Contact No:");

        dateChooser.setEnabled(false);
        dateChooser.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Name:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Registration Date:");

        NameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NameField.setEnabled(false);
        NameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameFieldActionPerformed(evt);
            }
        });

        UserIDField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        EmailField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EmailField.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Email: ");

        ContactNoField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ContactNoField.setEnabled(false);
        ContactNoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContactNoFieldActionPerformed(evt);
            }
        });

        UserNameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        UserNameField.setEnabled(false);
        UserNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserNameFieldActionPerformed(evt);
            }
        });

        UserSearchLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/icons8-search-18.png"))); // NOI18N
        UserSearchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserSearchLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EmailField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ContactNoField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserNameField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserIDField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserSearchLabel)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(UserIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(UserNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ContactNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(124, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, 357));

        Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/Rightarrow.png"))); // NOI18N
        Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 70, 32));

        ModifyButton.setText("Modify Booking");
        ModifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifyButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ModifyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1112, 287, 112, 36));

        DeleteButton.setText("Delete Booking");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(DeleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1112, 341, 112, 36));

        AddButton.setText("Add Booking");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });
        getContentPane().add(AddButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1112, 233, 112, 36));

        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ExitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1112, 449, 112, 36));

        ClearButton.setText("Clear");
        ClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ClearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1112, 395, 112, 36));

        BookTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BookingID", "UserID", "UserName", "Booking_Date", "Room_No", "NoOfPC", "Session", "Game"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        BookTable.setEditingColumn(0);
        BookTable.setEditingRow(0);
        BookTable.getTableHeader().setReorderingAllowed(false);
        BookTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(BookTable);

        javax.swing.GroupLayout TablePanelLayout = new javax.swing.GroupLayout(TablePanel);
        TablePanel.setLayout(TablePanelLayout);
        TablePanelLayout.setHorizontalGroup(
            TablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TablePanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        TablePanelLayout.setVerticalGroup(
            TablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TablePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        getContentPane().add(TablePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 1320, 200));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UserIDField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserIDField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserIDField1ActionPerformed

    private void bookingSearchLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingSearchLabelMouseClicked
        // TODO add your handling code here:
        Connection conn = Register_User.ConnectDB();
        if (BookIDField.getText().isEmpty()) {
//            System.out.println("A");
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(frame, "Enter BookingID");
//            System.out.println("B");
        } else {
            try {
                String sql = "SELECT * FROM BOOKINGS WHERE BOOKINGID=" + BookIDField.getText();
//                System.out.println(BookIDField.getText());
//                System.out.println("C");
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    BookIDField.setText(rs.getString("BookingID"));
//                System.out.println("D");
                    UserIDField1.setText(rs.getString("UserID"));
//                System.out.println("D.5");
                    UserNameField2.setText(rs.getString("UserName"));
//                System.out.println("D>5");
                    setBookDateChooser(rs.getString("Booking_Date"));
//                System.out.println("E");
                    for (int i = 0; i < RoomNoBox.getItemCount(); ++i) {
//                    System.out.println("F");
                        if (RoomNoBox.getItemAt(i).toString().equals(rs.getString("Room_No"))) {
                            RoomNoBox.setSelectedIndex(i);
//                        System.out.println("F.5");
                        }
                    }
                    Spinner.setValue(rs.getInt("NoOfPC"));
//                System.out.println("F>5");
                    if (rs.getString("Session").equals("1 Hour")) {
//                    System.out.println("G");
                        RadioButton2.setSelected(false);
                        RadioButton1.setSelected(true);
                        THourBox.setEnabled(false);
                        HourBox.setEnabled(true);
                        for (int i = 0; i < HourBox.getItemCount(); ++i) {
//                        System.out.println("H");
                            if (HourBox.getItemAt(i).toString().equals(rs.getString("Time"))) {
//                            System.out.println("I");
                                HourBox.setSelectedIndex(i);
                            }
                        }
                    } else {
//                    System.out.println("J");
                        RadioButton2.setSelected(true);
                        RadioButton1.setSelected(false);
                        HourBox.setEnabled(false);
                        THourBox.setEnabled(true);
                        for (int i = 0; i < THourBox.getItemCount(); ++i) {
//                        System.out.println("K");
                            if (THourBox.getItemAt(i).toString().equals(rs.getString("Time"))) {
//                            System.out.println("L");
                                THourBox.setSelectedIndex(i);
                            }
                        }
                    }
                }
//                System.out.println("M");
                pst.close();
                rs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No Booking Found");
            }
        }
    }//GEN-LAST:event_bookingSearchLabelMouseClicked

    private void RoomNoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomNoBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomNoBoxActionPerformed

    private void RadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton1ActionPerformed
        // TODO add your handling code here:
        RadioButton1.setSelected(true);
        RadioButton2.setSelected(false);
        THourBox.setEnabled(false);
        HourBox.setEnabled(true);
    }//GEN-LAST:event_RadioButton1ActionPerformed

    private void SpinnerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SpinnerMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SpinnerMousePressed

    private void HourBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HourBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HourBoxActionPerformed

    private void NameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameFieldActionPerformed

    private void ContactNoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContactNoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ContactNoFieldActionPerformed

    private void UserNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserNameFieldActionPerformed

    private void UserSearchLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserSearchLabelMouseClicked
        // TODO add your handling code here:
        conn = Register_User.ConnectDB();
//        UserIDField.setEnabled(true);
//        UserNameField.setEnabled(true);
//        NameField.setEnabled(true);
//        PassField.setEnabled(true);
//        ContactNoField.setEnabled(true);
//        EmailField.setEnabled(true);
//        dateChooser.setEnabled(true);
//        AnswerField.setEnabled(true);
//        SecurityQuestionBox.setEnabled(true);

        if (UserIDField.getText().isEmpty()) {
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(frame, "Enter UserID");
        } else {
            try {
                String sql = "SELECT * FROM REGISTERED_USER WHERE USERID=" + UserIDField.getText();
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    UserIDField.setText(rs.getString("UserID"));
                    UserNameField.setText(rs.getString("UserName"));
                    NameField.setText(rs.getString("Name"));
                    ContactNoField.setText(rs.getString("Contact_No"));
                    EmailField.setText(rs.getString("Email"));
                    setDateChooser(rs.getString("Reg_Date"));
                    //dateChooser.setText(rs.getString("Reg_Date"));
                }
                pst.close();
                rs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Found");
            }
        }
    }//GEN-LAST:event_UserSearchLabelMouseClicked

    private void ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonActionPerformed
        // TODO add your handling code here:
        if (UserIDField.getText().isEmpty() || UserNameField.getText().isEmpty()) {
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(frame, "Enter UserID And Username");
        } else {
            UserIDField1.setText(UserIDField.getText());
            UserNameField2.setText(UserNameField.getText());
            bookdateChooser.setEnabled(true);
            RoomNoBox.setEnabled(true);
            HourBox.setEnabled(true);
            THourBox.setEnabled(false);
            Spinner.setEnabled(true);
            RadioButton1.setEnabled(true);
            RadioButton2.setEnabled(true);
        }
    }//GEN-LAST:event_ButtonActionPerformed

    private void BookIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookIDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BookIDFieldActionPerformed

    private void Reg_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Reg_TableMouseClicked
        // TODO add your handling code here:
        int index = Reg_Table.getSelectedRow();
        TableModel m = Reg_Table.getModel();
        UserIDField.setText(m.getValueAt(index, 0).toString());
        UserNameField.setText((String) m.getValueAt(index, 1));
        NameField.setText((String) m.getValueAt(index, 2));
        ContactNoField.setText(m.getValueAt(index, 4).toString());
        EmailField.setText((String) m.getValueAt(index, 5));
        setDateChooser(m.getValueAt(index, 6).toString());
//        dateChooser.setText((String) m.getValueAt(index, 6));
    }//GEN-LAST:event_Reg_TableMouseClicked

    private void BookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookTableMouseClicked
        // TODO add your handling code here:
        int index = BookTable.getSelectedRow();
        TableModel mo = BookTable.getModel();
        BookIDField.setText(mo.getValueAt(index, 0).toString());
        UserIDField1.setText(mo.getValueAt(index, 1).toString());
        UserIDField.setText(mo.getValueAt(index, 1).toString());
        UserSearch();
        UserNameField2.setText(mo.getValueAt(index, 2).toString());
        setBookDateChooser(mo.getValueAt(index, 3).toString());
        //bookdateChooser.setText(mo.getValueAt(index, 3).toString());
        for (int i = 0; i < RoomNoBox.getItemCount(); ++i) {
            if (RoomNoBox.getItemAt(i).toString().equals(mo.getValueAt(index, 4).toString())) {
                RoomNoBox.setSelectedIndex(i);
            }
        }
        Spinner.setValue(Integer.parseInt(mo.getValueAt(index, 5).toString()));
        if (mo.getValueAt(index, 6).toString().equals("1 hour")) {
//            System.out.println("W");
            RadioButton1.setSelected(true);
            RadioButton2.setSelected(false);
            THourBox.setEnabled(false);
            HourBox.setEnabled(true);
            for (int i = 0; i < HourBox.getItemCount(); ++i) {
//                System.out.println("Y");
                if (HourBox.getItemAt(i).toString().equals(mo.getValueAt(index, 7).toString())) {
                    HourBox.setSelectedIndex(i);
                }
            }
        } else {
//            System.out.println("X");
            RadioButton2.setSelected(true);
            RadioButton1.setSelected(false);
            HourBox.setEnabled(false);
            THourBox.setEnabled(true);
            for (int i = 0; i < THourBox.getItemCount(); ++i) {
//                System.out.println("Z");
                if (THourBox.getItemAt(i).toString().equals(mo.getValueAt(index, 7).toString())) {
                    THourBox.setSelectedIndex(i);
                }
            }
        }
        bookdateChooser.setEnabled(true);
        RoomNoBox.setEnabled(true);
        HourBox.setEnabled(true);
        Spinner.setEnabled(true);
        RadioButton1.setEnabled(true);
        RadioButton2.setEnabled(true);
    }//GEN-LAST:event_BookTableMouseClicked

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        // TODO add your handling code here:
        if (Login_Page.e.equals("Admin")) {
            HomeAdmin ob = new HomeAdmin();
            ob.setVisible(true);
            this.dispose();
        } else if (Login_Page.e.equals("User")) {
            HomeUser ob = new HomeUser(Login_Page.id);
            ob.setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_ExitButtonActionPerformed

    private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed
        // TODO add your handling code here:

//        System.out.println(getBookDateChooser());
        setDateChooser("12/12/12");
        setBookDateChooser("10/10/10");
        UserIDField.setText(null);
        UserNameField.setText(null);
        NameField.setText(null);
        ContactNoField.setText(null);
        EmailField.setText(null);
        BookIDField.setText(null);
        UserIDField1.setText(null);
        UserNameField2.setText(null);
        RoomNoBox.setSelectedItem(1);
        HourBox.setSelectedIndex(1);
        THourBox.setSelectedIndex(1);
        RadioButton1.setSelected(false);
        RadioButton2.setSelected(false);
        Spinner.setValue(0);
        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date dateobj = new Date();
        Calendar c = Calendar.getInstance();
        bookdateChooser.setSelectedDate(c);
        df.format(dateobj);
        dateChooser.setDateFormat(df);
        c.setTime(dateobj);
        dateChooser.setSelectedDate(c);
        RoomNoBox.setEnabled(false);
        HourBox.setEnabled(true);
        THourBox.setEnabled(false);
        Spinner.setEnabled(false);
        RadioButton1.setEnabled(false);
        RadioButton2.setEnabled(false);
    }//GEN-LAST:event_ClearButtonActionPerformed

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        // TODO add your handling code here:
//        conn = Register_User.ConnectDB();
        String sql = "INSERT INTO BOOKINGS VALUES(?,?,?,?,?,?,?,?)";
        try {

            pst = conn.prepareStatement(sql);
            pst.setString(1, BookIDField.getText());
            pst.setString(2, UserIDField1.getText());
            pst.setString(3, UserNameField2.getText());
            pst.setString(4, getBookDateChooser());
            pst.setString(5, RoomNoBox.getSelectedItem().toString());
            pst.setString(6, Spinner.getValue().toString());
            if (RadioButton1.isSelected()) {
                pst.setString(7, RadioButton1.getLabel());
                pst.setString(8, HourBox.getSelectedItem().toString());
            } else if (RadioButton2.isSelected()) {
                pst.setString(7, RadioButton2.getLabel());
                pst.setString(8, THourBox.getSelectedItem().toString());
            }
//            pst.setString(8, HourBox.getSelectedItem().toString());
            pst.execute();
            JOptionPane.showMessageDialog(null, "System Update Completed");
            pst.close();
            model.setRowCount(0);
            showTable();
            try {
                if (RadioButton2.isSelected()) {
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
                    String s = "INSERT INTO SESSIONS(Room_No,PC_No,"+"\""+"Date"+"\""+","+"\""+"Time"+"\""+")VALUES(?,?,?,?)";
                    pst = conn.prepareStatement(s);
                    pst.setString(1, RoomNoBox.getSelectedItem().toString());
                    pst.setString(2, Spinner.getValue().toString());
                    pst.setString(3, getBookDateChooser());
                    pst.setString(4, t1);
                    pst.execute();
                    pst.close();

                    String m = "INSERT INTO SESSIONS(Room_No,PC_No,"+"\""+"Date"+"\""+","+"\""+"Time"+"\""+")VALUES(?,?,?,?)";
                    pst = conn.prepareStatement(m);
                    pst.setString(1, RoomNoBox.getSelectedItem().toString());
                    pst.setString(2, Spinner.getValue().toString());
                    pst.setString(3, getBookDateChooser());
                    pst.setString(4, t2);
                    pst.execute();
                    pst.close();
                } else if (RadioButton1.isSelected()) {
                    String s = "INSERT INTO SESSIONS(Room_No,PC_No,"+"\""+"Date"+"\""+","+"\""+"Time"+"\""+")VALUES(?,?,?,?)";
                    pst = conn.prepareStatement(s);
                    pst.setString(1, RoomNoBox.getSelectedItem().toString());
                    pst.setString(2, Spinner.getValue().toString());
                    pst.setString(3, getBookDateChooser());
                    if (RadioButton1.isSelected()) {
                        pst.setString(4, HourBox.getSelectedItem().toString());
                    } else if (RadioButton2.isSelected()) {
                        pst.setString(4, THourBox.getSelectedItem().toString());
                    }
                    pst.execute();
                    pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_AddButtonActionPerformed

    private void ModifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifyButtonActionPerformed
        // TODO add your handling code here:
        conn = Register_User.ConnectDB();
        int row = BookTable.getSelectedRow();
        String str = BookTable.getModel().getValueAt(row, 0).toString();
        String sql = "UPDATE BOOKINGS SET UserID=?,UserName=?,Booking_Date=?,Room_No=?,NoOfPC=?,"+"\""+"Session"+"\""+"=?,"+"\""+"Time"+"\""+"=? WHERE BookingID=" + BookIDField.getText();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, UserIDField1.getText());
            pst.setString(2, UserNameField2.getText());
            pst.setString(3, getBookDateChooser());
            pst.setString(4, RoomNoBox.getSelectedItem().toString());
            pst.setString(5, Spinner.getValue().toString());
            if (RadioButton1.isSelected()) {
                pst.setString(6, RadioButton1.getLabel());
                pst.setString(7, HourBox.getSelectedItem().toString());
            } else if (RadioButton2.isSelected()) {
                pst.setString(6, RadioButton2.getLabel());
                pst.setString(7, THourBox.getSelectedItem().toString());
            }
//            pst.setString(7, HourBox.getSelectedItem().toString());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Booking Update Completed");
            model.setRowCount(0);
            showTable();
            pst.close();
            rs.close();
//            try {
//                if (RadioButton2.isSelected()) {
//                    String t1 = "";
//                    String t2 = "";
//                    if (THourBox.getSelectedItem().equals("12 - 2")) {
//                        t1 = "12 - 1";
//                        t2 = "1 - 2";
//                    } else if (THourBox.getSelectedItem().equals("1 - 3")) {
//                        t1 = "1 - 2";
//                        t2 = "2 - 3";
//                    } else if (THourBox.getSelectedItem().equals("2 - 4")) {
//                        t1 = "2 - 3";
//                        t2 = "3 - 4";
//                    } else if (THourBox.getSelectedItem().equals("3 - 5")) {
//                        t1 = "3 - 4";
//                        t2 = "4 - 5";
//                    }
//                    String s = "INSERT INTO Session(Room_No,PC_No,Date,Time)VALUES(?,?,?,?)";
//                    pst = conn.prepareStatement(s);
//                    pst.setString(1, RoomNoBox.getSelectedItem().toString());
//                    pst.setString(2, Spinner.getValue().toString());
//                    pst.setString(3, getBookDateChooser());
//                    pst.setString(4, t1);
//                    pst.execute();
//                    pst.close();
//
//                    String m = "INSERT INTO Session(Room_No,PC_No,Date,Time)VALUES(?,?,?,?)";
//                    pst = conn.prepareStatement(m);
//                    pst.setString(1, RoomNoBox.getSelectedItem().toString());
//                    pst.setString(2, Spinner.getValue().toString());
//                    pst.setString(3, getBookDateChooser());
//                    pst.setString(4, t2);
//                    pst.execute();
//                    pst.close();
//                } else if (RadioButton1.isSelected()) {
//                    String s = "INSERT INTO Session(Room_No,PC_No,Date,Time)VALUES(?,?,?,?)";
//                    pst = conn.prepareStatement(s);
//                    pst.setString(1, RoomNoBox.getSelectedItem().toString());
//                    pst.setString(2, Spinner.getValue().toString());
//                    pst.setString(3, getBookDateChooser());
//                    if (RadioButton1.isSelected()) {
//                        pst.setString(4, HourBox.getSelectedItem().toString());
//                    } else if (RadioButton2.isSelected()) {
//                        pst.setString(4, THourBox.getSelectedItem().toString());
//                    }
//                    pst.execute();
//                    pst.close();
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_ModifyButtonActionPerformed

    private void THourBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_THourBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_THourBoxActionPerformed

    private void RadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton2ActionPerformed
        // TODO add your handling code here:
        RadioButton2.setSelected(true);
        RadioButton1.setSelected(false);
        HourBox.setEnabled(false);
        THourBox.setEnabled(true);

    }//GEN-LAST:event_RadioButton2ActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        // TODO add your handling code here:
        conn = Register_User.ConnectDB();
        if (BookTable.getSelectedRow() != 0) {
            int opt = JOptionPane.showConfirmDialog(null, "Are you sure you want to Delete?", "Delete", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                try {
                    int row = BookTable.getSelectedRow();
                    String str = BookTable.getModel().getValueAt(row, 0).toString();
                    String sql = "DELETE FROM BOOKINGS WHERE BookingID=" + str;
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleted");
                    model.setRowCount(0);
                    showTable();
                    
                    
                    

                    try {
                        if (RadioButton2.isSelected()) {
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
                            String s = "DELETE FROM SESSIONS WHERE Room_No = ? AND PC_No = ? AND "+"\""+"Date"+"\""+" = ? AND "+"\""+"Time"+"\""+" = ?";
//                            System.out.println("A");
//                            String s = "INSERT INTO Session(Room_No,PC_No,Date,Time)VALUES(?,?,?,?)";
                            pst = conn.prepareStatement(s);
                            pst.setString(1, RoomNoBox.getSelectedItem().toString());
//                            System.out.print(RoomNoBox.getSelectedItem().toString()+" ");
                            pst.setString(2, Spinner.getValue().toString());
//                            System.out.print(Spinner.getValue().toString()+" ");
                            pst.setString(3, getBookDateChooser());
//                            System.out.print(getBookDateChooser()+" ");
                            pst.setString(4, t1);
//                            System.out.println(t1);
                            pst.executeUpdate();
                            pst.close();

                            String m = "DELETE FROM SESSIONS WHERE Room_No = ? AND PC_No = ? AND "+"\""+"Date"+"\""+" = ? AND "+"\""+"Time"+"\""+" = ?";
//                            String m = "INSERT INTO Session(Room_No,PC_No,Date,Time)VALUES(?,?,?,?)";
//                            System.out.println("B");
                            pst = conn.prepareStatement(m);
                            pst.setString(1, RoomNoBox.getSelectedItem().toString());
//                            System.out.print(RoomNoBox.getSelectedItem().toString()+" ");
                            pst.setString(2, Spinner.getValue().toString());
//                            System.out.print(Spinner.getValue().toString()+" ");
                            pst.setString(3, getBookDateChooser());
//                            System.out.println(getBookDateChooser()+" ");
                            pst.setString(4, t2);
//                            System.out.println(t2);
                            pst.executeUpdate();
                            pst.close();
                        } else if (RadioButton1.isSelected()) {
                            String s = "DELETE FROM SESSIONS WHERE Room_No = ? AND PC_No = ? AND "+"\""+"Date"+"\""+" = ? AND "+"\""+"Time"+"\""+" = ?";
//                            String s = "INSERT INTO Session(Room_No,PC_No,Date,Time)VALUES(?,?,?,?)";
//                            System.out.println("C");
                            pst = conn.prepareStatement(s);
                            pst.setString(1, RoomNoBox.getSelectedItem().toString());
//                            System.out.print(RoomNoBox.getSelectedItem().toString()+" ");
                            pst.setString(2, Spinner.getValue().toString());
//                            System.out.print(Spinner.getValue().toString()+" ");
                            pst.setString(3, getBookDateChooser());
//                            System.out.print(getBookDateChooser()+" ");
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


                    
//                    CustomerIDField.setText(null);
//                    CusNameField.setText(null);
//                    DateFormat df = new SimpleDateFormat("MM/dd/yy");
//                    Date dateobj = new Date();
//                    df.format(dateobj);
//                    dateChooser.setDateFormat(df);
//                    Calendar c = Calendar.getInstance();
//                    c.setTime(dateobj);
//                dateChooser.setCurrent(c);
//                dateChooser.getCurrent().setTime(dateobj);
//                dateChooser.setDateFormat(df);
//                    dateChooser.setSelectedDate(c);
//                    UserIDField.setEnabled(false);
//                    RadioButton1.setSelected(true);
//                    RadioButton2.setSelected(false);
//                    RadioButton3.setSelected(true);
//                    RadioButton4.setSelected(false);
//                    UserIDField.setText(null);
//                    RoomNoBox.setSelectedIndex(0);
//                    Spinner.setValue(1);
//                    HourBox.setEnabled(true);
//                    HourBox.setSelectedIndex(0);
//                    THourBox.setEnabled(false);
//                    THourBox.setSelectedIndex(0);
//                    RadioButton5.setSelected(true);
//                    RadioButton6.setSelected(false);
//                    CardNoField.setText(null);
//                    setexpdatenull();
//                    CardNoField.setEnabled(false);
//                    ExpdateChooser.setEnabled(false);
//                    AmountField.setText(null);
//                    CustTable.clearSelection();
//                    System.out.println(getBookDateChooser());
                    setDateChooser("12/12/12");
                    setBookDateChooser("10/10/10");
                    UserIDField.setText(null);
                    UserNameField.setText(null);
                    NameField.setText(null);
                    ContactNoField.setText(null);
                    EmailField.setText(null);
                    BookIDField.setText(null);
                    UserIDField1.setText(null);
                    UserNameField2.setText(null);
                    RoomNoBox.setSelectedItem(1);
                    HourBox.setSelectedIndex(1);
                    THourBox.setSelectedIndex(1);
                    RadioButton1.setSelected(false);
                    RadioButton2.setSelected(false);
                    Spinner.setValue(0);
                    DateFormat df = new SimpleDateFormat("MM/dd/yy");
                    Date dateobj = new Date();
                    Calendar c = Calendar.getInstance();
                    bookdateChooser.setSelectedDate(c);
                    df.format(dateobj);
                    dateChooser.setDateFormat(df);
                    c.setTime(dateobj);
                    dateChooser.setSelectedDate(c);
                    RoomNoBox.setEnabled(false);
                    HourBox.setEnabled(true);
                    THourBox.setEnabled(false);
                    Spinner.setEnabled(false);
                    RadioButton1.setEnabled(false);
                    RadioButton2.setEnabled(false);
                    rs.close();
                    pst.close();
//                    conn.close();
                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select record to delete");
        }
    }//GEN-LAST:event_DeleteButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Booking_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Booking_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Booking_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Booking_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Booking_Page().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Booking_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JTextField BookIDField;
    private javax.swing.JTable BookTable;
    private javax.swing.JButton Button;
    private javax.swing.JButton ClearButton;
    private javax.swing.JTextField ContactNoField;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JTextField EmailField;
    private javax.swing.JButton ExitButton;
    private javax.swing.JComboBox HourBox;
    private javax.swing.JButton ModifyButton;
    private javax.swing.JTextField NameField;
    private javax.swing.JRadioButton RadioButton1;
    private javax.swing.JRadioButton RadioButton2;
    private javax.swing.JTable Reg_Table;
    private javax.swing.JComboBox RoomNoBox;
    private javax.swing.JPanel SearchPanel;
    private javax.swing.JSpinner Spinner;
    private javax.swing.JComboBox THourBox;
    private javax.swing.JPanel TablePanel;
    private javax.swing.JTextField UserIDField;
    private javax.swing.JTextField UserIDField1;
    private javax.swing.JTextField UserNameField;
    private javax.swing.JTextField UserNameField2;
    private javax.swing.JLabel UserSearchLabel;
    private datechooser.beans.DateChooserCombo bookdateChooser;
    private javax.swing.JLabel bookingSearchLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private datechooser.beans.DateChooserCombo dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
