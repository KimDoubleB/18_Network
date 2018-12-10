
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EventMain extends javax.swing.JFrame {
    BufferedReader in;
    PrintWriter out;
    public EventMain(BufferedReader inStream, PrintWriter outStream) {
        in = inStream;
        out = outStream;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        voting_pan = new javax.swing.JPanel();
        Voting = new javax.swing.JLabel();
        date_pan = new javax.swing.JPanel();
        date = new javax.swing.JLabel();
        close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        voting_pan.setBackground(new java.awt.Color(255, 255, 255));
        voting_pan.setPreferredSize(new java.awt.Dimension(150, 50));
        voting_pan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                voting_panMouseClicked(evt);
            }
        });

        Voting.setText("Voting");

        javax.swing.GroupLayout voting_panLayout = new javax.swing.GroupLayout(voting_pan);
        voting_pan.setLayout(voting_panLayout);
        voting_panLayout.setHorizontalGroup(
            voting_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(voting_panLayout.createSequentialGroup()
                .addGap(0, 70, Short.MAX_VALUE)
                .addComponent(Voting)
                .addGap(0, 70, Short.MAX_VALUE))
        );
        voting_panLayout.setVerticalGroup(
            voting_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(voting_panLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(Voting)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        date_pan.setBackground(new java.awt.Color(255, 255, 255));
        date_pan.setPreferredSize(new java.awt.Dimension(150, 50));
        date_pan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                date_panMouseClicked(evt);
            }
        });

        date.setText("Schedule");

        javax.swing.GroupLayout date_panLayout = new javax.swing.GroupLayout(date_pan);
        date_pan.setLayout(date_panLayout);
        date_panLayout.setHorizontalGroup(
            date_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(date_panLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(date)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        date_panLayout.setVerticalGroup(
            date_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(date_panLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(date)
                .addGap(15, 15, 15))
        );

        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(voting_pan, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addComponent(date_pan, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(close)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(voting_pan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_pan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(close)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voting_panMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_voting_panMouseClicked
        out.println("VOTE");
        
        Voting vote;
        try {
            vote = new Voting(in, out);
            vote.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(EventMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_voting_panMouseClicked

    private void date_panMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date_panMouseClicked
        out.println("SCHEDULE");
        
        Schedule sch;
        try {
            sch = new Schedule(in, out);
            sch.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(EventMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_date_panMouseClicked

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_closeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Voting;
    private javax.swing.JButton close;
    private javax.swing.JLabel date;
    private javax.swing.JPanel date_pan;
    private javax.swing.JPanel voting_pan;
    // End of variables declaration//GEN-END:variables
}
