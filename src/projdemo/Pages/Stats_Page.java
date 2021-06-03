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
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.ResultSet;
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
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import projdemo.Room;
import projdemo.Session;


public class Stats_Page extends javax.swing.JFrame {

    /**
     * Creates new form Stats_Page
     */
     Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ResultSet ram = null;
    DefaultTableModel model = new DefaultTableModel(){
        
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    
    public Stats_Page() {
        initComponents();
        conn = Register_User.ConnectDB();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        DateFormat format = new SimpleDateFormat("MM/dd/yy");
        dateChooser.setDateFormat(format);
//        showButton();      
        
    }

    
    public ArrayList<Session> sList(){
        ArrayList<Session> sList = new ArrayList<>();
        conn = Register_User.ConnectDB();
        try{
            String sql = "SELECT * FROM SESSIONS";
            try(Statement st = conn.createStatement()){
                rs = st.executeQuery(sql);
                 Session session;
                while(rs.next()){
                    session = new Session(rs.getInt("Room_No"),rs.getInt("PC_No"),rs.getString("Date"),rs.getString("Time"));
                    sList.add(session);
                }
            }
            catch(Exception e){
                JFrame frame = new JFrame();
                frame.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(frame, e);
            }
        } catch (HeadlessException | SecurityException e) {
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(frame, e);
        }
        for (Session s : sList) {
//            System.out.println(s);
        }
        return sList;
    }
    
//     private void checkButton(JToggleButton b, int room_no, int pc_no){
//        ArrayList<Room> rlist = Resources_Page.rList();
//        for(Room r: rlist){
////            System.out.println(r);
//            if((r.getRoomNo() == room_no) && (r.getNoofPC() < pc_no)){
//                b.setEnabled(false);
//            }
//        }
//    }
//    
    

    private int rNo;
    private int pNo;

    private Session ses;

    public void showButton() {
        conn = Register_User.ConnectDB();
        ArrayList<Session> list = sList();
        String d = getDateChooser();
        String t = TimeBox.getSelectedItem().toString();
//        System.out.println(d + "" + t + ".");
        try {
            for (Session s : list) {
                if (s.getDate().equals(d) && s.getTime().equals(t)) {
                    ses = s;
                    rNo = ses.getRoom_No();
//                    System.out.println(rNo);
                    pNo = ses.getPC_No();
//            String sql = "SELECT * FROM Session WHERE Date=" + d + " AND Time=" + t;
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//            rNo = rs.getInt("Room_No");
//            pNo = rs.getInt("PC_No");

//                    System.out.println("Y  " + rNo + "   " + pNo);
                    if (rNo == 1) {
//                        System.out.println("1");
                        switch (pNo) {
                            case 1:
                                R1P1.setBackground(Color.red);
                                break;
                            case 2:
                                R1P2.setBackground(Color.red);
                                break;
                            case 3:
                                R1P3.setBackground(Color.red);
                                break;
                            case 4:
                                R1P4.setBackground(Color.red);
                                break;
                            case 5:
                                R1P5.setBackground(Color.red);
                                break;
                            default:
                                break;
                        }
                    } else if (rNo == 2) {
//                        System.out.println("2");
                        switch (pNo) {
                            case 1:
                                R2P1.setBackground(Color.red);
                                break;
                            case 2:
                                R2P2.setBackground(Color.red);
                                break;
                            case 3:
                                R2P3.setBackground(Color.red);
                                break;
                            case 4:
                                R2P4.setBackground(Color.red);
                                break;
                            case 5:
                                R2P5.setBackground(Color.red);
                                break;
                            default:
                                break;
                        }
                    } else if (rNo == 3) {
//                        System.out.println("3");
                        switch (pNo) {
                            case 1:
                                R3P1.setBackground(Color.red);
                                break;
                            case 2:
                                R3P2.setBackground(Color.red);
                                break;
                            case 3:
                                R3P3.setBackground(Color.red);
                                break;
                            case 4:
                                R3P4.setBackground(Color.red);
                                break;
                            case 5:
                                R3P5.setBackground(Color.red);
                                break;
                            default:
                                break;
                        }
                    } else if (rNo == 4) {
//                        System.out.println("4");
                        switch (pNo) {
                            case 1:
                                R4P1.setBackground(Color.red);
                                break;
                            case 2:
                                R4P2.setBackground(Color.red);
                                break;
                            case 3:
                                R4P3.setBackground(Color.red);
                                break;
                            case 4:
                                R4P4.setBackground(Color.red);
                                break;
                            case 5:
                                R4P5.setBackground(Color.red);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(frame, e);
        }

    }

    public String getDateChooser() {
        SimpleDateFormat s = new SimpleDateFormat("MM/dd/yy");
        java.util.Date d = dateChooser.getSelectedDate().getTime();
        String str = s.format(d);
        return str;
    }

    public void setDateChooser(String str) {
        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date dobj = new Date();
        try {
            dobj = df.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(Customer_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
        df.format(dobj);
        Calendar c = Calendar.getInstance();
        dateChooser.setDateFormat(df);
        c.setTime(dobj);
        dateChooser.setSelectedDate(c);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        dateChooser = new datechooser.beans.DateChooserCombo();
        TimeBox = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        R1P1 = new javax.swing.JToggleButton();
        R1P2 = new javax.swing.JToggleButton();
        R1P3 = new javax.swing.JToggleButton();
        R1P4 = new javax.swing.JToggleButton();
        R1P5 = new javax.swing.JToggleButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        R2P1 = new javax.swing.JToggleButton();
        R2P2 = new javax.swing.JToggleButton();
        R2P3 = new javax.swing.JToggleButton();
        R2P4 = new javax.swing.JToggleButton();
        R2P5 = new javax.swing.JToggleButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        R3P1 = new javax.swing.JToggleButton();
        R3P2 = new javax.swing.JToggleButton();
        R3P3 = new javax.swing.JToggleButton();
        R3P4 = new javax.swing.JToggleButton();
        R3P5 = new javax.swing.JToggleButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        R4P1 = new javax.swing.JToggleButton();
        R4P2 = new javax.swing.JToggleButton();
        R4P3 = new javax.swing.JToggleButton();
        R4P4 = new javax.swing.JToggleButton();
        R4P5 = new javax.swing.JToggleButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        SearchButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/gzonebig.png"))); // NOI18N
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 11, -1, -1));

        dateChooser.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 18));
        jPanel7.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 212, 259, 44));

        TimeBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TimeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12 - 1", "1 - 2", "2 - 3", "3 - 4", "4 - 5", " " }));
        TimeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimeBoxActionPerformed(evt);
            }
        });
        jPanel7.add(TimeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(725, 212, 241, 44));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Select Time Slot:");
        jPanel7.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 212, 153, 44));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Select Date:");
        jPanel7.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 212, 119, 33));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true), "Room 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(0, 0, 153))); // NOI18N

        R1P1.setBackground(new java.awt.Color(51, 255, 51));
        R1P1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R1P1.setSelected(true);
        R1P1.setEnabled(false);
        R1P1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1P1ActionPerformed(evt);
            }
        });

        R1P2.setBackground(new java.awt.Color(51, 255, 51));
        R1P2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R1P2.setSelected(true);
        R1P2.setEnabled(false);
        R1P2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1P2ActionPerformed(evt);
            }
        });

        R1P3.setBackground(new java.awt.Color(51, 255, 51));
        R1P3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R1P3.setSelected(true);
        R1P3.setEnabled(false);
        R1P3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1P3ActionPerformed(evt);
            }
        });

        R1P4.setBackground(new java.awt.Color(51, 255, 51));
        R1P4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R1P4.setSelected(true);
        R1P4.setEnabled(false);
        R1P4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1P4ActionPerformed(evt);
            }
        });

        R1P5.setBackground(new java.awt.Color(51, 255, 51));
        R1P5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R1P5.setSelected(true);
        R1P5.setEnabled(false);
        R1P5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1P5ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setText("PC No 1");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("PC No 2");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setText("PC No 3");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setText("PC No 4");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setText("PC No 5");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(R1P1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R1P2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R1P3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R1P4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R1P5, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jLabel30)
                .addGap(171, 171, 171)
                .addComponent(jLabel31)
                .addGap(179, 179, 179)
                .addComponent(jLabel32)
                .addGap(170, 170, 170)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addGap(113, 113, 113))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R1P1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R1P2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R1P3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R1P4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R1P5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addGap(47, 47, 47))
        );

        jPanel7.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 274, -1, 230));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true), "Room 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(0, 0, 153))); // NOI18N

        R2P1.setBackground(new java.awt.Color(51, 255, 51));
        R2P1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R2P1.setSelected(true);
        R2P1.setEnabled(false);
        R2P1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2P1ActionPerformed(evt);
            }
        });

        R2P2.setBackground(new java.awt.Color(51, 255, 51));
        R2P2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R2P2.setSelected(true);
        R2P2.setEnabled(false);
        R2P2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2P2ActionPerformed(evt);
            }
        });

        R2P3.setBackground(new java.awt.Color(51, 255, 51));
        R2P3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R2P3.setSelected(true);
        R2P3.setEnabled(false);
        R2P3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2P3ActionPerformed(evt);
            }
        });

        R2P4.setBackground(new java.awt.Color(51, 255, 51));
        R2P4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R2P4.setSelected(true);
        R2P4.setEnabled(false);
        R2P4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2P4ActionPerformed(evt);
            }
        });

        R2P5.setBackground(new java.awt.Color(51, 255, 51));
        R2P5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R2P5.setSelected(true);
        R2P5.setEnabled(false);
        R2P5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2P5ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setText("PC No 1");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setText("PC No 2");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setText("PC No 3");

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setText("PC No 4");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setText("PC No 5");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(R2P1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R2P2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R2P3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R2P4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R2P5, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel35)
                .addGap(170, 170, 170)
                .addComponent(jLabel36)
                .addGap(179, 179, 179)
                .addComponent(jLabel37)
                .addGap(170, 170, 170)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel39)
                .addGap(117, 117, 117))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R2P1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R2P2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R2P3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R2P4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R2P5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39))
                        .addGap(49, 49, 49))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 510, -1, 230));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true), "Room 3", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(0, 0, 153))); // NOI18N

        R3P1.setBackground(new java.awt.Color(51, 255, 51));
        R3P1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R3P1.setSelected(true);
        R3P1.setEnabled(false);
        R3P1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3P1ActionPerformed(evt);
            }
        });

        R3P2.setBackground(new java.awt.Color(51, 255, 51));
        R3P2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R3P2.setSelected(true);
        R3P2.setEnabled(false);
        R3P2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3P2ActionPerformed(evt);
            }
        });

        R3P3.setBackground(new java.awt.Color(51, 255, 51));
        R3P3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R3P3.setSelected(true);
        R3P3.setEnabled(false);
        R3P3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3P3ActionPerformed(evt);
            }
        });

        R3P4.setBackground(new java.awt.Color(51, 255, 51));
        R3P4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R3P4.setSelected(true);
        R3P4.setEnabled(false);
        R3P4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3P4ActionPerformed(evt);
            }
        });

        R3P5.setBackground(new java.awt.Color(51, 255, 51));
        R3P5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R3P5.setSelected(true);
        R3P5.setEnabled(false);
        R3P5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R3P5ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel40.setText("PC No 1");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setText("PC No 2");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel42.setText("PC No 3");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel43.setText("PC No 4");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel44.setText("PC No 5");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(R3P1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R3P2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R3P3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R3P4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R3P5, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel40)
                .addGap(171, 171, 171)
                .addComponent(jLabel41)
                .addGap(179, 179, 179)
                .addComponent(jLabel42)
                .addGap(170, 170, 170)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel44)
                .addGap(115, 115, 115))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R3P1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R3P2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R3P3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R3P4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R3P5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44))
                .addGap(54, 54, 54))
        );

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 746, -1, 232));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true), "Room 4", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(0, 0, 153))); // NOI18N

        R4P1.setBackground(new java.awt.Color(51, 255, 51));
        R4P1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R4P1.setSelected(true);
        R4P1.setEnabled(false);
        R4P1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4P1ActionPerformed(evt);
            }
        });

        R4P2.setBackground(new java.awt.Color(51, 255, 51));
        R4P2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R4P2.setSelected(true);
        R4P2.setEnabled(false);
        R4P2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4P2ActionPerformed(evt);
            }
        });

        R4P3.setBackground(new java.awt.Color(51, 255, 51));
        R4P3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R4P3.setSelected(true);
        R4P3.setEnabled(false);
        R4P3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4P3ActionPerformed(evt);
            }
        });

        R4P4.setBackground(new java.awt.Color(51, 255, 51));
        R4P4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R4P4.setSelected(true);
        R4P4.setEnabled(false);
        R4P4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4P4ActionPerformed(evt);
            }
        });

        R4P5.setBackground(new java.awt.Color(51, 255, 51));
        R4P5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/manatcomputer.png"))); // NOI18N
        R4P5.setSelected(true);
        R4P5.setEnabled(false);
        R4P5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R4P5ActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel45.setText("PC No 1");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel46.setText("PC No 2");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setText("PC No 3");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setText("PC No 4");

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel49.setText("PC No 5");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(R4P1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R4P2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R4P3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R4P4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(R4P5, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel45)
                .addGap(171, 171, 171)
                .addComponent(jLabel46)
                .addGap(179, 179, 179)
                .addComponent(jLabel47)
                .addGap(170, 170, 170)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel49)
                .addGap(117, 117, 117))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(R4P1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R4P2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R4P3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R4P4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(R4P5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47)
                    .addComponent(jLabel48)
                    .addComponent(jLabel49))
                .addGap(54, 54, 54))
        );

        jPanel7.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 989, -1, 234));

        SearchButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        SearchButton.setText("Search");
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });
        jPanel7.add(SearchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1075, 212, 133, 44));

        ExitButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });
        jPanel7.add(ExitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 70, 151, 43));

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/icon/redsquare.png"))); // NOI18N
        jLabel50.setText("Booked");
        jPanel7.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(1104, 45, 107, -1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/icon/greensquare.png"))); // NOI18N
        jLabel51.setText("Available");
        jPanel7.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(1104, 82, 119, -1));

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projdemo/icon/greysquare.png"))); // NOI18N
        jLabel52.setText("Unavailable");
        jPanel7.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(1105, 118, -1, 22));

        jScrollPane1.setViewportView(jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 511, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TimeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimeBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TimeBoxActionPerformed

    private void R1P1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1P1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R1P1ActionPerformed

    private void R1P2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1P2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R1P2ActionPerformed

    private void R1P3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1P3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R1P3ActionPerformed

    private void R1P4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1P4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R1P4ActionPerformed

    private void R1P5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1P5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R1P5ActionPerformed

    private void R2P1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2P1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R2P1ActionPerformed

    private void R2P2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2P2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R2P2ActionPerformed

    private void R2P3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2P3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R2P3ActionPerformed

    private void R2P4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2P4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R2P4ActionPerformed

    private void R2P5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2P5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R2P5ActionPerformed

    private void R3P1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3P1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R3P1ActionPerformed

    private void R3P2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3P2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R3P2ActionPerformed

    private void R3P3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3P3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R3P3ActionPerformed

    private void R3P4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3P4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R3P4ActionPerformed

    private void R3P5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R3P5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R3P5ActionPerformed

    private void R4P1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4P1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R4P1ActionPerformed

    private void R4P2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4P2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R4P2ActionPerformed

    private void R4P3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4P3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R4P3ActionPerformed

    private void R4P4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4P4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R4P4ActionPerformed

    private void R4P5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R4P5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R4P5ActionPerformed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        // TODO add your handling code here:       
        R1P1.setEnabled(true);
        R1P2.setEnabled(true);
        R1P3.setEnabled(true);
        R1P4.setEnabled(true);
        R1P5.setEnabled(true);
        R2P1.setEnabled(true);
        R2P2.setEnabled(true);
        R2P3.setEnabled(true);
        R2P4.setEnabled(true);
        R2P5.setEnabled(true);
        R3P1.setEnabled(true);
        R3P2.setEnabled(true);
        R3P3.setEnabled(true);
        R3P4.setEnabled(true);
        R3P5.setEnabled(true);
        R4P1.setEnabled(true);
        R4P2.setEnabled(true);
        R4P3.setEnabled(true);
        R4P4.setEnabled(true);
        R4P5.setEnabled(true);
        
//        checkButton(R1P1,1,1);
//        checkButton(R1P2,1,2);
//        checkButton(R1P3,1,3);
//        checkButton(R1P4,1,4);
//        checkButton(R1P5,1,5);
//        checkButton(R2P1,2,1);
//        checkButton(R2P2,2,2);
//        checkButton(R2P3,2,3);
//        checkButton(R2P4,2,4);
//        checkButton(R2P5,2,5);
//        checkButton(R3P1,3,1);
//        checkButton(R3P2,3,2);
//        checkButton(R3P3,3,3);
//        checkButton(R3P4,3,4);
//        checkButton(R3P5,3,5);
//        checkButton(R4P1,4,1);
//        checkButton(R4P2,4,2);
//        checkButton(R4P3,4,3);
//        checkButton(R4P4,4,4);
//        checkButton(R4P5,4,5);
                
        R1P1.setBackground(Color.green);
        R1P2.setBackground(Color.green);
        R1P3.setBackground(Color.green);
        R1P4.setBackground(Color.green);
        R1P5.setBackground(Color.green);
        R2P1.setBackground(Color.green);
        R2P2.setBackground(Color.green);
        R2P3.setBackground(Color.green);
        R2P4.setBackground(Color.green);
        R2P5.setBackground(Color.green);
        R3P1.setBackground(Color.green);
        R3P2.setBackground(Color.green);
        R3P3.setBackground(Color.green);
        R3P4.setBackground(Color.green);
        R3P5.setBackground(Color.green);
        R4P1.setBackground(Color.green);
        R4P2.setBackground(Color.green);
        R4P3.setBackground(Color.green);
        R4P4.setBackground(Color.green);
        R4P5.setBackground(Color.green);
        showButton();
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        // TODO add your handling code here:
        if(Login_Page.e.equals("Admin")){
            HomeAdmin ob = new HomeAdmin();
            ob.setVisible(true);
        } else if(Login_Page.e.equals("User")){
            HomeUser ob = new HomeUser(Login_Page.id);
            ob.setVisible(true);
        } else if(Login_Page.e.equals("Visitor")){
            HomeVisitor ob = new HomeVisitor();
            ob.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_ExitButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Stats_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stats_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stats_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stats_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Stats_Page().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitButton;
    private javax.swing.JToggleButton R1P1;
    private javax.swing.JToggleButton R1P2;
    private javax.swing.JToggleButton R1P3;
    private javax.swing.JToggleButton R1P4;
    private javax.swing.JToggleButton R1P5;
    private javax.swing.JToggleButton R2P1;
    private javax.swing.JToggleButton R2P2;
    private javax.swing.JToggleButton R2P3;
    private javax.swing.JToggleButton R2P4;
    private javax.swing.JToggleButton R2P5;
    private javax.swing.JToggleButton R3P1;
    private javax.swing.JToggleButton R3P2;
    private javax.swing.JToggleButton R3P3;
    private javax.swing.JToggleButton R3P4;
    private javax.swing.JToggleButton R3P5;
    private javax.swing.JToggleButton R4P1;
    private javax.swing.JToggleButton R4P2;
    private javax.swing.JToggleButton R4P3;
    private javax.swing.JToggleButton R4P4;
    private javax.swing.JToggleButton R4P5;
    private javax.swing.JButton SearchButton;
    private javax.swing.JComboBox TimeBox;
    private datechooser.beans.DateChooserCombo dateChooser;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
