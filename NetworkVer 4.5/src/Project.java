/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Project extends javax.swing.JFrame {

    // init streams
    BufferedReader in;
    PrintWriter out;
    String line;
    String id;
    String pname = "none";
    ArrayList<project_user> list = new ArrayList<project_user>();
    public int projectNum;
    int giveRow=-1;

    public Project(BufferedReader inStream, PrintWriter outStream) {
        // get streams through parameter of constructor
        in = inStream;
        out = outStream;
        //  this.id = id;
        // init window
        initComponents();

        ProjectInit();
        addInfoTable();

//        new SendThread(in, out, chatArea).start();
//        SendThread st = new SendThread(in, out, chatArea);
//        st.start();
//
//        chatClient.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // To help server to distinguish message and whisper, add "MESS" in front of "MESSAGE"
//                out.println("MESS " + chatClient.getText());
//                chatClient.setText("");
//            }
//        });
    }

    // Project initialization --> get the information of project that fits the trump.
    public void ProjectInit() {
        // set non-editable

        // send to inform that cllient is in project in.
        out.println("PROJECTIN ");
        try {
            while (true) {
                line = in.readLine();

                if (line.startsWith("CLIENTID")) {
                    id = line.substring(9);
                    NameLabel.setText(id + " !");
                } else if (line.startsWith("PROJECTNUM")) {
                    // get the project number
                    projectNum = new Integer(line.substring(11));
                } else if (line.startsWith("PROJECTINFO")) {
                    // get the information of project ( project name, project role that client do, project deadline )
                    String[] projectData = line.substring(12).split("/");
                    list.add(new project_user(projectData[0], projectData[1], projectData[2], projectData[3], projectData[4]));
                    System.out.println(projectData[0] + " " + projectData[1] + " " + projectData[2] + " " + projectData[3]);
                } else if (line.startsWith("PROJECTEND")) {
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Add information about the imported project to the table.
    public void addInfoTable() {

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Cannot resize the table.
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        Object rowData[] = new Object[3];

        System.out.println(list.size());
        // add information to table
        for (int i = 0; i < list.size(); i++) {
            rowData[0] = list.get(i).project_name;
            rowData[1] = list.get(i).project_role;
            rowData[2] = list.get(i).project_date;
            model.addRow(rowData);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        ProjectIndex = new javax.swing.JPanel();
        tableScroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        create_button = new javax.swing.JButton();
        Confirm = new javax.swing.JButton();
        Delete = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ProjectIndex.setBackground(new java.awt.Color(51, 0, 0));
        ProjectIndex.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableScroll.setForeground(new java.awt.Color(255, 255, 255));
        tableScroll.setToolTipText("");
        tableScroll.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableScroll.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        table.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Name", "Role", "DeadLine"
            }
        ){
            public boolean isCellEditable(int i, int c) {
                return false;
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        tableScroll.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setHeaderValue("Name");
            table.getColumnModel().getColumn(1).setHeaderValue("Role");
            table.getColumnModel().getColumn(2).setHeaderValue("Date");
        }

        ProjectIndex.add(tableScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 400, 430));

        jLabel2.setBackground(new java.awt.Color(51, 0, 51));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("List");
        ProjectIndex.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        NameLabel.setFont(new java.awt.Font("Arial", 1, 21)); // NOI18N
        NameLabel.setForeground(new java.awt.Color(255, 255, 255));
        NameLabel.setText("-");
        ProjectIndex.add(NameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 160, -1));

        create_button.setBackground(new java.awt.Color(51, 0, 0));
        create_button.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        create_button.setForeground(new java.awt.Color(255, 255, 255));
        create_button.setText("Create");
        create_button.setBorder(null);
        create_button.setMaximumSize(new java.awt.Dimension(60, 30));
        create_button.setMinimumSize(new java.awt.Dimension(60, 30));
        create_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_buttonActionPerformed(evt);
            }
        });
        ProjectIndex.add(create_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, 160, 40));

        Confirm.setBackground(new java.awt.Color(51, 0, 0));
        Confirm.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Confirm.setForeground(new java.awt.Color(255, 255, 255));
        Confirm.setText("Confirm");
        Confirm.setBorder(null);
        Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmActionPerformed(evt);
            }
        });
        ProjectIndex.add(Confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 510, 90, 30));

        Delete.setBackground(new java.awt.Color(51, 0, 0));
        Delete.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Delete.setForeground(new java.awt.Color(255, 255, 255));
        Delete.setText("Delete");
        Delete.setBorder(null);
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });
        ProjectIndex.add(Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 90, 30));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\hig97\\Desktop\\네트워크 그림\\icons8_Down_Arrow_27px.png")); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ProjectIndex.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 30, 30));

        jLabel3.setBackground(new java.awt.Color(51, 0, 51));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Welcome");
        ProjectIndex.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\hig97\\Desktop\\네트워크 그림\\icons8_Contacts_35px.png")); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ProjectIndex.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 60, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProjectIndex, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProjectIndex, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        int indexrow = table.getSelectedRow();
        int indexcol = table.getSelectedColumn();
        String pNum;
        // The client gets the selected value.
        // Retrieves all values of a row, not a single value.
        String clickPname = (String) table.getValueAt(indexrow, 0); // project name
        String clickProle = (String) table.getValueAt(indexrow, 1); // project role
        String clickPdate = (String) table.getValueAt(indexrow, 2); // project date
        System.out.println(clickPname + " " + clickProle + " " + clickPdate);
        
        //There is a separate button to delete from the GUI.
        //It depends on whether the Delete checkbox is selected or not.
        if (Delete.isSelected()) {
            giveRow=indexrow;
        } else {
            //When a user selects a project from the project table
            out.println("GIVE " + indexrow);

            try {
                line = in.readLine(); // receive data from server
                if (line.startsWith("NUMBER")) {
                    // get the project number that client clicks
                    pNum = line.substring(7);

                    pname = clickPname;
                    out.println("CHATINFO " + clickPname);  // send name information of project

                    line = in.readLine();
                    if (line.startsWith("CHATACCEPTED")) {
                        // when server accept client
                        int port = 9007 + new Integer(line.substring(13));  // define new port number (multiRoom)
                        // Create a multi-room by defining a new thread. -> Make a new connection between the server and the client. 
                        Runnable chat = new Chatting(port, id, pNum, clickPdate, clickProle);
                        Thread t = new Thread(chat);
                        t.start();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tableMouseClicked

    private void create_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_buttonActionPerformed
        // send to server that client go to project create window
        out.println("PROJECTCREATE ");
        dispose(); //turn off a window
        new CreateProject(in, out).setVisible(true);

    }//GEN-LAST:event_create_buttonActionPerformed

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
        //If user selected the Delete check box, the value of 'giveRow' will be different and you will delete it.
         if(giveRow!=-1){
            out.println("DELETE " + giveRow);
            System.out.println("@@@@@@@@@@@@//" + giveRow);
            
            dispose(); //turn off a window
            new Project(in, out).setVisible(true);
        }
    }//GEN-LAST:event_ConfirmActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Confirm;
    private javax.swing.JCheckBox Delete;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JPanel ProjectIndex;
    private javax.swing.JButton create_button;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    private javax.swing.JTable table;
    private javax.swing.JScrollPane tableScroll;
    // End of variables declaration//GEN-END:variables
}
