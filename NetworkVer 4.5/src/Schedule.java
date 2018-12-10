
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
     /*MM -> 연도 뒷 2자리
        dd -> 월 2자리
        W -> 달 기준 n주차
        w -> 연도 기준 n주차
        E -> 요일 축약형
        EEEE -> 요일 이름
        MMMM -> 달 text 형태
        a -> AM/PM
        H -> 24시간 기준 시
        h -> 12시간 기준 시
        m -> 분
        s -> 초
    */

public class Schedule extends javax.swing.JFrame {
    BufferedReader in;
    PrintWriter out;
    
    Date startDate = new Date();
    String startDay = "19000101";
    Date deadline = new Date();
    String deadLine = "21001212";
    Date today = new Date();
    int Dday;
    SimpleDateFormat YtoD = new SimpleDateFormat("Y.MM.dd");
    SimpleDateFormat YtoM = new SimpleDateFormat("Y.MM.dd a hh:mm");
    ArrayList<Schedule_data> schedule = new ArrayList<>();
    
    private int selectedIndex=-1;
   
    
    public Schedule(BufferedReader inStream, PrintWriter outStream) throws IOException {
        in = inStream;
        out = outStream;
        
        initComponents();
        //tempData();
        //start();
        //getDate();
        
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                dispose();//turn off a window
            }
        });
    }

    //get schedule data from server
    public void getDate() throws IOException
    {
        out.println("GETDATE");
        String input;
        while(true)
        {
            input = in.readLine();
            if(input.startsWith("DATADATE"))//if server send a schedule data
            {
                String[] temp = input.substring(9).split("/");
                Schedule_data event = new Schedule_data(temp[0]);
                int yy = Integer.parseInt(temp[1].substring(0,4));
                yy = yy-1900;
                int mm = Integer.parseInt(temp[1].substring(4,6));
                int dd = Integer.parseInt(temp[1].substring(6));
                event.setYMD(yy, mm,  dd);//save date
                event.setMemo(temp[2]);//save memo
                InSort(event);//insert into schedule arraylist
            }
            else if(input.startsWith("DATAEND"))
            {
                break;
            }
        }
    }
    
   /* public void tempData()//임시 데이터
    {
        startDate.setDate(1);
        startDate.setMonth(10);
        startDate.setYear(118);
        deadline.setDate(30);
        deadline.setMonth(10);
        deadline.setYear(118);
        Schedule_data start = new Schedule_data("start", startDate);
        schedule.add(schedule.size(), start);
        Schedule_data dead = new Schedule_data("dead", deadline);
        schedule.add(schedule.size(), dead);
        
        Schedule_data event1 = new Schedule_data("event1");
        event1.setYMD(18, 11, 25);
        InSort(event1);
        schedule.get(0).sucess=true;
        schedule.get(1).sucess=true;
        schedule.get(2).sucess=false;
        //printSch();
    }*/
    
    //set initial window.
    public void start()
    {
        long temp = (deadline.getTime() - startDate.getTime())/(1000*60*60*24);
        Dday = (int)temp;
        Dday_bar.setMaximum(Dday);
        //set D-day
        
        setTodayBar();
        setPlanBar();
        //set progress bars
        
        sucess_tog.setText("Not sucess.");
        
        List.setModel(new DefaultListModel());
        DefaultListModel model = (DefaultListModel) List.getModel();
        for(int i=0; i<schedule.size(); i++)
        {
            model.addElement(schedule.get(i).name);
        }
        //set list of schedule
    }
    
    public void setTodayBar()//set today bar. it shows when is today compare with D-day bar
    {
        long temp = (today.getTime() - startDate.getTime())/(1000*60*60*24);
        int Todday_temp = (int)temp;
        float Todday = (float)((float)Todday_temp/(float)Dday)*100;
        plan_bar.setValue((int)Todday);
        
    }
    
    public void setPlanBar()//set today bar. it shows how much completed compare with D-day bar
    {
        long temp = (schedule.get(curPlan()).event.getTime() - startDate.getTime())/(1000*60*60*24);
        int plan_temp = (int)temp;
        float Plan = (float)((float)plan_temp/(float)Dday)*100;
        plan_bar.setValue((int)Plan);
    }
    
    public int curPlan()//return index of continuity completed schedule.
    {
        int index = schedule.size()-1;
        for(int i=schedule.size()-1; i>=0; i--)
        {
            if(schedule.get(i).sucess==false)
                index = i-1;
        }
        if(index <0)
            index=0;
        return index;
    }
    
    public void InSort(Schedule_data newData)//insert into schedule arraylist.
    {
        int index=0;
        int flag=0;
        for(int i=0; i<schedule.size(); i++)
        {
            if(newData.event.compareTo(schedule.get(i).event)<0 && flag==0)
            {
                index=i;
                flag=1;
            }
        }
        schedule.add(schedule.size(), newData);
        for(int i=schedule.size()-1; i>index; i--)
        {
            Schedule_data temp = schedule.get(i);
            schedule.set(i,schedule.get(i-1));
            schedule.set(i-1,temp);
        }
        setPlanBar();
    }

  
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        name_txt = new javax.swing.JLabel();
        name_in = new javax.swing.JTextField();
        memo_txt = new javax.swing.JLabel();
        memo_scroll = new javax.swing.JScrollPane();
        memo_in = new javax.swing.JTextArea();
        sucess_tog = new javax.swing.JToggleButton();
        save_but = new javax.swing.JButton();
        today_bar = new javax.swing.JProgressBar();
        plan_bar = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        select_but = new javax.swing.JButton();
        Dday_bar = new javax.swing.JSlider();
        month_c = new javax.swing.JComboBox<>();
        year_c = new javax.swing.JSpinner();
        day_c = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        List = new javax.swing.JList<>();
        year_txt = new javax.swing.JLabel();
        month_txt = new javax.swing.JLabel();
        day_txt = new javax.swing.JLabel();
        Check_new = new javax.swing.JCheckBox();
        del_but = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        Commit = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(36, 47, 65));
        main.setForeground(new java.awt.Color(255, 255, 255));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name_txt.setForeground(new java.awt.Color(255, 255, 255));
        name_txt.setText("Name");
        main.add(name_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 44, 40, -1));

        name_in.setBackground(new java.awt.Color(36, 47, 65));
        name_in.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        name_in.setForeground(new java.awt.Color(255, 255, 255));
        name_in.setText("new");
        name_in.setBorder(null);
        main.add(name_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 210, -1));

        memo_txt.setForeground(new java.awt.Color(255, 255, 255));
        memo_txt.setText("Memo");
        main.add(memo_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 76, 40, -1));

        memo_scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        memo_in.setColumns(20);
        memo_in.setRows(5);
        memo_scroll.setViewportView(memo_in);

        main.add(memo_scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 99, 260, -1));

        sucess_tog.setText("Sucessed");
        sucess_tog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sucess_togActionPerformed(evt);
            }
        });
        main.add(sucess_tog, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 197, 260, 30));

        save_but.setText("Save");
        save_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_butActionPerformed(evt);
            }
        });
        main.add(save_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(307, 145, 80, 30));
        main.add(today_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 276, 607, -1));
        main.add(plan_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 300, 607, -1));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Today");
        main.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 273, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Plan");
        main.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 297, -1, -1));

        select_but.setText("Select");
        select_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_butActionPerformed(evt);
            }
        });
        main.add(select_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 145, 80, 30));

        Dday_bar.setMajorTickSpacing(5);
        Dday_bar.setMaximum(50);
        Dday_bar.setMinorTickSpacing(1);
        Dday_bar.setPaintLabels(true);
        Dday_bar.setPaintTicks(true);
        Dday_bar.setPaintTrack(false);
        Dday_bar.setToolTipText("");
        Dday_bar.setEnabled(false);
        main.add(Dday_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 321, 607, 47));

        month_c.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        main.add(month_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(389, 71, 70, 25));

        year_c.setValue(2018);
        main.add(year_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(389, 40, 70, 25));

        day_c.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        main.add(day_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(389, 102, 70, 25));

        List.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(List);

        main.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(491, 40, 200, 227));

        year_txt.setForeground(new java.awt.Color(255, 255, 255));
        year_txt.setText("Year");
        main.add(year_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 44, -1, -1));

        month_txt.setForeground(new java.awt.Color(255, 255, 255));
        month_txt.setText("Month");
        main.add(month_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 74, -1, -1));

        day_txt.setForeground(new java.awt.Color(255, 255, 255));
        day_txt.setText("Day");
        main.add(day_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 105, -1, -1));

        Check_new.setBackground(new java.awt.Color(36, 47, 65));
        Check_new.setForeground(new java.awt.Color(255, 255, 255));
        Check_new.setSelected(true);
        Check_new.setText("New");
        main.add(Check_new, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, -1, -1));

        del_but.setText("Delete");
        del_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_butActionPerformed(evt);
            }
        });
        main.add(del_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 181, 80, 30));

        Close.setForeground(new java.awt.Color(255, 255, 255));
        Close.setText("X");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });
        main.add(Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(696, 0, -1, -1));

        Commit.setText("Commit");
        Commit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CommitActionPerformed(evt);
            }
        });
        main.add(Commit, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 225, -1, -1));

        jLabel9.setBackground(new java.awt.Color(36, 47, 65));
        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Schedule ");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        main.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 180, -1));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        main.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 68, 210, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sucess_togActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sucess_togActionPerformed
        //set toggle button(sucessed/unsucessed)
        if(sucess_tog.isSelected())
        {
            sucess_tog.setText("Sucessed.");
        }
        else
        {
            sucess_tog.setText("Not sucess.");
        }
    }//GEN-LAST:event_sucess_togActionPerformed

    private void save_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_butActionPerformed
        //Temporary save data to schedule arraylist
        
        Schedule_data save;
        if(Check_new.isSelected())
        {
            save = new Schedule_data(name_in.getText());
        
            save.memo = memo_in.getText();
            save.sucess = sucess_tog.isSelected();
            save.event.setDate(day_c.getSelectedIndex()+1);
            save.event.setYear((int)year_c.getValue()-1900);
            save.event.setMonth(month_c.getSelectedIndex()-1);
            InSort(save);
        }
        else
        {
            save =schedule.get(selectedIndex);
        
            save.memo = memo_in.getText();
            save.sucess = sucess_tog.isSelected();
            save.event.setDate(day_c.getSelectedIndex()+1);
            save.event.setYear((int)year_c.getValue()-1900);
            save.event.setMonth(month_c.getSelectedIndex()-1);
        }
            
            List.setModel(new DefaultListModel());
            DefaultListModel model = (DefaultListModel) List.getModel();
            for(int i=0; i<schedule.size(); i++)
            {
                model.addElement(schedule.get(i).name);
            }
            
        setPlanBar();
    }//GEN-LAST:event_save_butActionPerformed

    private void select_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_butActionPerformed
        //show selected schedule's data
        
        if(List.isSelectionEmpty())//투표 데이터를 선택하지 않은 경우
        {
            JOptionPane.showMessageDialog(this, "Select one in the vote list.");
        }
        else
        {
            selectedIndex = List.getSelectedIndex();
            
            Schedule_data select = schedule.get(List.getSelectedIndex());
            name_in.setText(select.name);
            memo_in.setText(select.memo);
            sucess_tog.setSelected(select.sucess);
            if(sucess_tog.isSelected())
            {
                sucess_tog.setText("Sucessed.");
            }
            else
            {
                sucess_tog.setText("Not sucess.");
            }
            day_c.setSelectedIndex(select.event.getDate()-1);
            year_c.setValue(select.event.getYear()+1900);
            month_c.setSelectedIndex(select.event.getMonth()+1);
            List.clearSelection();
        }
        
    }//GEN-LAST:event_select_butActionPerformed

    private void del_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_butActionPerformed
        // delete selected data
        selectedIndex = List.getSelectedIndex();
        if(selectedIndex == 0 || selectedIndex == schedule.size()-1)
        {
            JOptionPane.showMessageDialog(this, "Select other one. You can't delete start and deadline");//오류창 표시
        }
        else
        {
            schedule.remove(selectedIndex);
            List.setModel(new DefaultListModel());
                DefaultListModel model = (DefaultListModel) List.getModel();
                for(int i=0; i<schedule.size(); i++)
                {
                    model.addElement(schedule.get(i).name);
                }
        }
            
        setPlanBar();
    }//GEN-LAST:event_del_butActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_CloseActionPerformed

    private void CommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CommitActionPerformed
        // send all data to server
        out.println("COMMITDATE");
        String input;
        
        for(int i=0; i<schedule.size(); i++)
        {
            out.println("COMMIT"+schedule.get(i).getDate());
        }
        out.println("COMMITEND");
        
    }//GEN-LAST:event_CommitActionPerformed


    //public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>


        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Schedule(BufferedReader inStream, PrintWriter outStream).setVisible(true);
            }
        });*/
   // }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Check_new;
    private javax.swing.JButton Close;
    private javax.swing.JButton Commit;
    private javax.swing.JSlider Dday_bar;
    private javax.swing.JList<String> List;
    private javax.swing.JComboBox<String> day_c;
    private javax.swing.JLabel day_txt;
    private javax.swing.JButton del_but;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel main;
    private javax.swing.JTextArea memo_in;
    private javax.swing.JScrollPane memo_scroll;
    private javax.swing.JLabel memo_txt;
    private javax.swing.JComboBox<String> month_c;
    private javax.swing.JLabel month_txt;
    private javax.swing.JTextField name_in;
    private javax.swing.JLabel name_txt;
    private javax.swing.JProgressBar plan_bar;
    private javax.swing.JButton save_but;
    private javax.swing.JButton select_but;
    private javax.swing.JToggleButton sucess_tog;
    private javax.swing.JProgressBar today_bar;
    private javax.swing.JSpinner year_c;
    private javax.swing.JLabel year_txt;
    // End of variables declaration//GEN-END:variables
}
