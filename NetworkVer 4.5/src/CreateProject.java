/*
 * This is frame for create new project.
 * @Run : 'Project.java' -> Click 'create_botton' button.
 * @Get : From user ->  1)Project name
                        2)Deadline
                        3)Professor's email address
 * @Primary component : 1) (JTextField)DeadlineText : Input deadline, format is yyyymmdd
                        2) (JTextField)EmailText : Input professor's email addres, size is varchar(30)
                        3) (JTextField)PnameText : Input project name, size is varchar(20)
                        4) (JButton)nextButton : Save datas and call 'CreateProject_IDlist.java' frame to add project members
 */

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateProject extends javax.swing.JFrame {

    BufferedReader in;
    PrintWriter out;
    String line;

    public CreateProject(BufferedReader inStream, PrintWriter outStream) {
        in = inStream;
        out = outStream;
        //get inputstream and outputstream from 'Project.java'
        //and set 'CreateProject.java''s input/outputstream
        initComponents();//create frame
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nextButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        PnameText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        DeadlineText = new javax.swing.JTextField();
        EmailText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 204));
        setMinimumSize(new java.awt.Dimension(470, 0));

        jPanel1.setBackground(new java.awt.Color(36, 47, 65));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nextButton.setBackground(new java.awt.Color(36, 47, 65));
        nextButton.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        nextButton.setForeground(new java.awt.Color(255, 255, 255));
        nextButton.setText("NEXT");
        nextButton.setBorder(null);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        jPanel1.add(nextButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, 110, 44));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CREATE PROJECT");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 230, 44));

        PnameText.setBackground(new java.awt.Color(36, 47, 65));
        PnameText.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        PnameText.setForeground(new java.awt.Color(255, 255, 255));
        PnameText.setBorder(null);
        jPanel1.add(PnameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 200, 44));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dead Line");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("YYYYMMDD");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        DeadlineText.setBackground(new java.awt.Color(36, 47, 65));
        DeadlineText.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        DeadlineText.setForeground(new java.awt.Color(255, 255, 255));
        DeadlineText.setBorder(null);
        jPanel1.add(DeadlineText, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 200, 44));

        EmailText.setBackground(new java.awt.Color(36, 47, 65));
        EmailText.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        EmailText.setForeground(new java.awt.Color(255, 255, 255));
        EmailText.setBorder(null);
        jPanel1.add(EmailText, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 200, 44));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Professor Email");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Project Name");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, 44));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, -1, -1));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 200, 10));

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 200, 10));

        jSeparator4.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 200, 10));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\hig97\\Desktop\\네트워크 그림\\icons8_Arrow_35px.png")); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, 60, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        SimpleDateFormat YtoD = new SimpleDateFormat("YMMdd");
        String pname = PnameText.getText();
        String startDate = YtoD.format(new Date());
        System.out.println(startDate);
        String deadLine = DeadlineText.getText();
        String proEmail = EmailText.getText();
        //get project name, deadline, professor's email address from user.

        out.println("PROJECTNAME " + pname);
        out.println("PROJECTSTART " + startDate);
        out.println("PROJECTDEAD " + deadLine);
        out.println("PROJECTEMAIL " + proEmail);

        dispose();
        //close 'CreateProject.java'

        new CreateProject_IDlist(in, out).setVisible(true);
        //call 'CreateProject_IDlist.java' to add project members

    }//GEN-LAST:event_nextButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DeadlineText;
    private javax.swing.JTextField EmailText;
    private javax.swing.JTextField PnameText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton nextButton;
    // End of variables declaration//GEN-END:variables
}