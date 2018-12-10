

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class Voting extends javax.swing.JFrame  {
    
    BufferedReader in;
    PrintWriter out;
    
    
    ArrayList<JTextField> cont = new ArrayList<>();//create_pan -> choice txt
    ArrayList<JRadioButton> choice = new ArrayList<>();//vote_pan -> radio button
    ArrayList<JPanel> choice_pan = new ArrayList<>();//vote_pan -> choice panel
    ArrayList<Integer> Cur_size = new ArrayList<>();//the number of choice
    ArrayList<String> vote_name = new ArrayList<>();//name of vote name
    
    
    private int curdataSize=2;//the uumber of vote
    
    public Voting(BufferedReader inStream, PrintWriter outStream) throws IOException {
        in = inStream;
        out = outStream;
        
        initComponents();
        new_pan.setVisible(true);
        vote_pan.setVisible(false);
        voteList.setSize(350, 350);
        
        iniData();
        resetData();//receive vote data from server
             
        name_txt.setText("new_project");//set new project name
        
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                dispose();//turn off a window
            }
        });
    }
    
    private void resetData() throws IOException
    {
//        String input;
//        int i=0;
//        while (true) {
//            input = in.readLine();//get vote data (name + the number of choice)
//            System.out.println(input);
//            if (input.startsWith("VOTELIST")) {
//                String[] temp = input.substring(9).split("/");
//                System.out.println("votelist");
//                vote_name.add(i, temp[0]);
//                Cur_size.add(i++, Integer.parseInt(temp[1]));
//            } else if (input.startsWith("VOTEEND")) {
//                System.out.println("VOTEEND");
//                break;
//            }
//        }
//        curdataSize = vote_name.size();
//        
//        //set list
//        voteList.setModel(new DefaultListModel());
//        DefaultListModel model = (DefaultListModel) voteList.getModel();
//        for(int k=0; k<vote_name.size(); k++)
//        {
//            model.addElement(vote_name.get(k));
//        }
        
    }

    private void iniData()
    {
        choice.add(0,choice1);
        choice.add(1,choice2);
        choice.add(2,choice3);
        choice.add(3,choice4);
        choice.add(4,choice5);
        //vote_pan -> radio button
        
        cont.add(0,cont1_txt);
        cont.add(1,cont2_txt);
        cont.add(2,cont3_txt);
        cont.add(3,cont4_txt);
        cont.add(4,cont5_txt);
        //create_pan -> choice txt
        
        choice_pan.add(0,con1);
        choice_pan.add(1,con2);
        choice_pan.add(2,con3);
        choice_pan.add(3,con4);
        choice_pan.add(4,con5);
        //vote_pan -> choice panel
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        choice_group = new javax.swing.ButtonGroup();
        main = new javax.swing.JPanel();
        votescroll = new javax.swing.JScrollPane();
        voteList = new javax.swing.JList<>();
        main_Vote_but = new javax.swing.JButton();
        main_create_but = new javax.swing.JButton();
        main_edit_but = new javax.swing.JButton();
        new_pan = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        name_txt = new javax.swing.JTextField();
        cont1_txt = new javax.swing.JTextField();
        cont2_txt = new javax.swing.JTextField();
        cont3_pan = new javax.swing.JPanel();
        cont3_txt = new javax.swing.JTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        new_create = new javax.swing.JButton();
        new_edit = new javax.swing.JButton();
        plus = new javax.swing.JButton();
        minus = new javax.swing.JButton();
        cont4_pan = new javax.swing.JPanel();
        cont4_txt = new javax.swing.JTextField();
        cont5_pan = new javax.swing.JPanel();
        cont5_txt = new javax.swing.JTextField();
        vote_pan = new javax.swing.JPanel();
        vote_content_pan = new javax.swing.JPanel();
        con1 = new javax.swing.JPanel();
        choice1 = new javax.swing.JRadioButton();
        con2 = new javax.swing.JPanel();
        choice2 = new javax.swing.JRadioButton();
        con3 = new javax.swing.JPanel();
        choice3 = new javax.swing.JRadioButton();
        con4 = new javax.swing.JPanel();
        choice4 = new javax.swing.JRadioButton();
        con5 = new javax.swing.JPanel();
        choice5 = new javax.swing.JRadioButton();
        vote_vote_but = new javax.swing.JButton();
        main_close_but = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(36, 47, 65));
        main.setPreferredSize(new java.awt.Dimension(350, 500));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        votescroll.setPreferredSize(new java.awt.Dimension(369, 130));

        voteList.setBackground(new java.awt.Color(36, 47, 65));
        voteList.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        voteList.setForeground(new java.awt.Color(255, 255, 255));
        voteList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "vote 1", "vote 2", "vote 3", "vote 4", "vote 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        voteList.setMaximumSize(new java.awt.Dimension(350, 350));
        voteList.setMinimumSize(new java.awt.Dimension(350, 350));
        voteList.setSelectionBackground(new java.awt.Color(200, 200, 200));
        votescroll.setViewportView(voteList);

        main.add(votescroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 40, 310, 191));

        main_Vote_but.setBackground(new java.awt.Color(36, 47, 65));
        main_Vote_but.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        main_Vote_but.setForeground(new java.awt.Color(255, 255, 255));
        main_Vote_but.setText("Vote");
        main_Vote_but.setBorder(null);
        main_Vote_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_Vote_butActionPerformed(evt);
            }
        });
        main.add(main_Vote_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 70, 30));

        main_create_but.setBackground(new java.awt.Color(36, 47, 65));
        main_create_but.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        main_create_but.setForeground(new java.awt.Color(255, 255, 255));
        main_create_but.setText("Create");
        main_create_but.setBorder(null);
        main_create_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_create_butActionPerformed(evt);
            }
        });
        main.add(main_create_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 80, 30));

        main_edit_but.setBackground(new java.awt.Color(36, 47, 65));
        main_edit_but.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        main_edit_but.setForeground(new java.awt.Color(255, 255, 255));
        main_edit_but.setText("Edit");
        main_edit_but.setBorder(null);
        main_edit_but.setMaximumSize(new java.awt.Dimension(63, 23));
        main_edit_but.setMinimumSize(new java.awt.Dimension(63, 23));
        main_edit_but.setPreferredSize(new java.awt.Dimension(63, 23));
        main_edit_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_edit_butActionPerformed(evt);
            }
        });
        main.add(main_edit_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 60, 30));

        new_pan.setBackground(new java.awt.Color(36, 47, 65));
        new_pan.setForeground(new java.awt.Color(255, 255, 255));

        name.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        name.setForeground(new java.awt.Color(255, 255, 255));
        name.setText("name");

        name_txt.setText("new_project");
        name_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_txtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cont3_panLayout = new javax.swing.GroupLayout(cont3_pan);
        cont3_pan.setLayout(cont3_panLayout);
        cont3_panLayout.setHorizontalGroup(
            cont3_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cont3_panLayout.createSequentialGroup()
                .addComponent(cont3_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        cont3_panLayout.setVerticalGroup(
            cont3_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cont3_panLayout.createSequentialGroup()
                .addComponent(cont3_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLayeredPane1.setBackground(new java.awt.Color(100, 100, 100));

        new_create.setText("create");
        new_create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_createActionPerformed(evt);
            }
        });

        new_edit.setBackground(new java.awt.Color(36, 47, 65));
        new_edit.setForeground(new java.awt.Color(255, 255, 255));
        new_edit.setText("edit");
        new_edit.setBorder(null);
        new_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_editActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(new_create, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(new_edit, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(new_create, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(new_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(new_create, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(new_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        plus.setText("+");
        plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusActionPerformed(evt);
            }
        });

        minus.setText("-");
        minus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cont4_panLayout = new javax.swing.GroupLayout(cont4_pan);
        cont4_pan.setLayout(cont4_panLayout);
        cont4_panLayout.setHorizontalGroup(
            cont4_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cont4_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        cont4_panLayout.setVerticalGroup(
            cont4_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cont4_panLayout.createSequentialGroup()
                .addComponent(cont4_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cont5_panLayout = new javax.swing.GroupLayout(cont5_pan);
        cont5_pan.setLayout(cont5_panLayout);
        cont5_panLayout.setHorizontalGroup(
            cont5_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cont5_panLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cont5_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        cont5_panLayout.setVerticalGroup(
            cont5_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cont5_panLayout.createSequentialGroup()
                .addComponent(cont5_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout new_panLayout = new javax.swing.GroupLayout(new_pan);
        new_pan.setLayout(new_panLayout);
        new_panLayout.setHorizontalGroup(
            new_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(new_panLayout.createSequentialGroup()
                .addGroup(new_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(new_panLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(plus)
                        .addGap(18, 18, 18)
                        .addComponent(minus))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, new_panLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(name)
                        .addGap(45, 45, 45)
                        .addGroup(new_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cont2_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cont1_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cont5_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(new_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cont3_pan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cont4_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(50, 50, 50))
        );
        new_panLayout.setVerticalGroup(
            new_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(new_panLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(new_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(name)
                    .addComponent(name_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cont1_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cont2_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cont3_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cont4_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cont5_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(new_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(new_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(plus)
                        .addComponent(minus)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        main.add(new_pan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 287, -1, -1));

        vote_content_pan.setBackground(new java.awt.Color(200, 200, 200));

        con1.setBackground(new java.awt.Color(125, 125, 125));
        con1.setPreferredSize(new java.awt.Dimension(180, 40));

        choice_group.add(choice1);
        choice1.setText("jRadioButton1");
        choice1.setMaximumSize(new java.awt.Dimension(320, 30));
        choice1.setMinimumSize(new java.awt.Dimension(320, 30));
        choice1.setPreferredSize(new java.awt.Dimension(320, 30));

        javax.swing.GroupLayout con1Layout = new javax.swing.GroupLayout(con1);
        con1.setLayout(con1Layout);
        con1Layout.setHorizontalGroup(
            con1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(choice1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        con1Layout.setVerticalGroup(
            con1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(choice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        con2.setBackground(new java.awt.Color(125, 125, 125));
        con2.setPreferredSize(new java.awt.Dimension(180, 40));

        choice_group.add(choice2);
        choice2.setText("jRadioButton2");
        choice2.setMaximumSize(new java.awt.Dimension(320, 30));
        choice2.setMinimumSize(new java.awt.Dimension(320, 30));
        choice2.setPreferredSize(new java.awt.Dimension(320, 30));

        javax.swing.GroupLayout con2Layout = new javax.swing.GroupLayout(con2);
        con2.setLayout(con2Layout);
        con2Layout.setHorizontalGroup(
            con2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(choice2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        con2Layout.setVerticalGroup(
            con2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(con2Layout.createSequentialGroup()
                .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        con3.setBackground(new java.awt.Color(125, 125, 125));
        con3.setPreferredSize(new java.awt.Dimension(180, 40));

        choice_group.add(choice3);
        choice3.setText("jRadioButton3");
        choice3.setMaximumSize(new java.awt.Dimension(320, 30));
        choice3.setMinimumSize(new java.awt.Dimension(320, 30));
        choice3.setPreferredSize(new java.awt.Dimension(320, 30));

        javax.swing.GroupLayout con3Layout = new javax.swing.GroupLayout(con3);
        con3.setLayout(con3Layout);
        con3Layout.setHorizontalGroup(
            con3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(choice3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        con3Layout.setVerticalGroup(
            con3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(con3Layout.createSequentialGroup()
                .addComponent(choice3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        con4.setBackground(new java.awt.Color(125, 125, 125));
        con4.setPreferredSize(new java.awt.Dimension(180, 40));

        choice_group.add(choice4);
        choice4.setText("jRadioButton4");
        choice4.setMaximumSize(new java.awt.Dimension(320, 30));
        choice4.setMinimumSize(new java.awt.Dimension(320, 30));
        choice4.setPreferredSize(new java.awt.Dimension(320, 30));

        javax.swing.GroupLayout con4Layout = new javax.swing.GroupLayout(con4);
        con4.setLayout(con4Layout);
        con4Layout.setHorizontalGroup(
            con4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(choice4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        con4Layout.setVerticalGroup(
            con4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(con4Layout.createSequentialGroup()
                .addComponent(choice4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        con5.setBackground(new java.awt.Color(125, 125, 125));
        con5.setPreferredSize(new java.awt.Dimension(180, 40));

        choice_group.add(choice5);
        choice5.setText("jRadioButton5");
        choice5.setMaximumSize(new java.awt.Dimension(320, 30));
        choice5.setMinimumSize(new java.awt.Dimension(320, 30));
        choice5.setPreferredSize(new java.awt.Dimension(320, 30));

        javax.swing.GroupLayout con5Layout = new javax.swing.GroupLayout(con5);
        con5.setLayout(con5Layout);
        con5Layout.setHorizontalGroup(
            con5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(choice5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        con5Layout.setVerticalGroup(
            con5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(con5Layout.createSequentialGroup()
                .addComponent(choice5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        vote_vote_but.setText("vote");
        vote_vote_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vote_vote_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout vote_content_panLayout = new javax.swing.GroupLayout(vote_content_pan);
        vote_content_pan.setLayout(vote_content_panLayout);
        vote_content_panLayout.setHorizontalGroup(
            vote_content_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vote_content_panLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(vote_content_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(con5, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(con4, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(con1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(con2, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(con3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                .addGap(15, 15, 15))
            .addGroup(vote_content_panLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(vote_vote_but)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        vote_content_panLayout.setVerticalGroup(
            vote_content_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vote_content_panLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(con1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(con2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(con3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(con4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(con5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(vote_vote_but)
                .addContainerGap())
        );

        javax.swing.GroupLayout vote_panLayout = new javax.swing.GroupLayout(vote_pan);
        vote_pan.setLayout(vote_panLayout);
        vote_panLayout.setHorizontalGroup(
            vote_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vote_content_pan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        vote_panLayout.setVerticalGroup(
            vote_panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vote_panLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(vote_content_pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        main.add(vote_pan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 601, -1, -1));

        main_close_but.setBackground(new java.awt.Color(36, 47, 65));
        main_close_but.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        main_close_but.setForeground(new java.awt.Color(255, 255, 255));
        main_close_but.setText("Close");
        main_close_but.setBorder(null);
        main_close_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_close_butActionPerformed(evt);
            }
        });
        main.add(main_close_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 60, 30));

        jLabel9.setBackground(new java.awt.Color(36, 47, 65));
        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Vote ");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        main.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 90, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void main_create_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_create_butActionPerformed
        //when choose a vote in list, show create_pan
        
        new_pan.setVisible(true);
        vote_pan.setVisible(false);
        
        cont3_pan.setVisible(false);
        cont4_pan.setVisible(false);
        cont5_pan.setVisible(false);
        
        new_create.setVisible(true);
        new_edit.setVisible(false);
        
        name_txt.setText("new_project"+(curdataSize+1));
    }//GEN-LAST:event_main_create_butActionPerformed

    private int curSize=1;//the number of choice.(it start to 0)
    
    private void new_createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_createActionPerformed
//when choose a create button in create_pan, send new data to server        
        
        out.println("VOTECREATE"+name_txt.getText()+"/"+(curSize+1));
        //send ""VOTECREATE / name of new vote / the number of choice" to server
                
        try {
                System.out.println("NAME");
            if(in.readLine().startsWith("VOTEACCEPT"))//if there isn't same vote name, send vote data
            {
                System.out.println("VOTEACCEPT");
                int size = curSize+1;
                out.println("VOTECHOICE"+name_txt.getText()+"/"+size);
                
                String output="CONTENT";
                    System.out.println(output);
                    int flag=0;
                for(int i=0; i<=curSize; i++)
                {
                    if(flag==0)
                    {
                        output = output+cont.get(i).getText();
                        flag=1;
                    }
                    else
                    {
                        output = output+"/"+cont.get(i).getText();
                    }
                    System.out.println(output);
                }
                out.println(output);
                
                
                try {
                    resetData();//show new vote list
                } catch (IOException ex) {
                    Logger.getLogger(Voting.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(in.readLine().equals("VOTEDENY"))//if there is same vote name, show error window
            {
                System.out.println("VOTEDENY");
                JOptionPane.showMessageDialog(this, "Insert another vote name.");
        
            new_pan.setVisible(false);
            createInit();
            }
        } catch (IOException ex) {
            Logger.getLogger(Voting.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("vote end");
        
        
    }//GEN-LAST:event_new_createActionPerformed

    //initialize for new data
    private void createInit()
    {
        curSize = 1;//it start at 0
        cont3_pan.setVisible(false);
        cont4_pan.setVisible(false);
        cont5_pan.setVisible(false);
        cont1_txt.setText("");
        cont2_txt.setText("");
        cont3_txt.setText("");
        cont4_txt.setText("");
        cont5_txt.setText("");
        name_txt.setText("new_project"+(curdataSize+1));
    }
    
    //for decreasing choice
    private void minusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusActionPerformed
        switch (curSize) {
            case 2:
                cont3_pan.setVisible(false);
                curSize--;
                break;
            case 3:
                cont4_pan.setVisible(false);
                curSize--;
                break;
            case 4:
                cont5_pan.setVisible(false);
                curSize--;
                break;
            default:
                break;
        }
    }//GEN-LAST:event_minusActionPerformed

    //for increasing choice
    private void plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusActionPerformed
        switch (curSize) {
            case 1:
                cont3_pan.setVisible(true);
                curSize++;
                break;
            case 2:
                cont4_pan.setVisible(true);
                curSize++;
                break;
            case 3:
                cont5_pan.setVisible(true);
                curSize++;
                break;
            default:
                break;
        }
    }//GEN-LAST:event_plusActionPerformed

    private void main_edit_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_edit_butActionPerformed
//when user want to edit data        
        
        vote_pan.setVisible(false);
        new_edit.setVisible(true);
        new_create.setVisible(false);
        
        if(voteList.isSelectionEmpty())//if user doesn't choose vote, show error message
        {
            JOptionPane.showMessageDialog(this, "Select one in the vote list.");
        }
        else
        {
            String voteName = voteList.getSelectedValue();//receive vote name
            
            out.println("VOTEEDIT"+voteName);
            try {
                String input;
                
                name_txt.setText(voteName);
                
                while (true) {//receive vote choice data
                    input = in.readLine();
                    if (input.startsWith("EDITLIST")) {
                        String[] temp = input.substring(9).split("/");

                        for(int k=0; k<temp.length; k++)
                        {
                            cont.get(k).setText(temp[k]);
                        }
                        for(int k=temp.length; k<5; k++)
                        {
                            cont.get(k).setText("");
                        }

                        if(temp.length == 3)
                        {
                            cont3_pan.setVisible(true);
                        }
                        else if(temp.length == 4)
                        {
                            cont3_pan.setVisible(true);
                            cont4_pan.setVisible(true);
                        }
                        else if(temp.length == 5)
                        {
                            cont3_pan.setVisible(true);
                            cont4_pan.setVisible(true);
                            cont5_pan.setVisible(true);
                        }
                        
                        
                    } else{
                        System.out.println("EDITERROR");
                        break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Voting.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            new_pan.setVisible(true);//show create_pan
        }
    }//GEN-LAST:event_main_edit_butActionPerformed

    private void new_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_editActionPerformed
//when user want to save edited data, send to server the data        
        
        String voteName = voteList.getSelectedValue();
        out.println("VOTEEDITSAVE"+voteName+"/"+(curSize+1));
        //send to server "VOTEEDITSAVE / vote name to edit / the numeber of choice" 보냄
        
        try {
            if(in.readLine().equals("VOTEFIND"))//if there is a data to edit, send edited data to server
            {
                for(int i=0; i<=curSize; i++)
                {
                    out.println("CONTENT"+"/"+cont.get(i).getText());
                }
            }
            else if(in.readLine().equals("NAMENOTFIND"))//if there isn't a data to edit, show error message
            {
                JOptionPane.showMessageDialog(this, "Didn't find vote.");
            }
        } catch (IOException ex) {
            Logger.getLogger(Voting.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            resetData();//reset vote list
        } catch (IOException ex) {
            Logger.getLogger(Voting.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new_pan.setVisible(false);
        createInit();
    }//GEN-LAST:event_new_editActionPerformed

    private void main_Vote_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_Vote_butActionPerformed
        //when user wnant to vote
        
        if(voteList.isSelectionEmpty())//if user doesn't choose vote, show error message
        {
            JOptionPane.showMessageDialog(this, "Select one in the vote list.");  
        }
        else
        {
            choice_group.clearSelection();
            
            String voteName = voteList.getSelectedValue();
            
            out.println("VOTEMAIN"+voteName);
            try {
                String input;
                
                while (true) {//receive choice data from server
                    input = in.readLine();
                    if (input.startsWith("VOTELIST")) {
                        String[] temp = input.substring(9).split("/");

                        for(int i=temp.length; i<5; i++)
                        {
                            choice_pan.get(i).setVisible(false);
                        }
                        
                        for(int i=0; i<temp.length; i++)
                        {
                            choice_pan.get(i).setVisible(true);
                            choice.get(i).setText(temp[i]);//show choice
                        }
                    } else{
                        System.out.println("VOTEVIEWERROR");
                        break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Voting.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
            new_pan.setVisible(false);
            vote_pan.setVisible(true);
        }
    }//GEN-LAST:event_main_Vote_butActionPerformed

    private void vote_vote_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vote_vote_butActionPerformed
        //when user choose choice
        
        if(choice_group.getSelection() == null)//if user doesn't choose, show error message
        {
            JOptionPane.showMessageDialog(this, "Select one in the vote list.");  
        }
        else//send data of user choice
        {
            out.println("VOTEVOTE"+"/"+name_txt.getText());
            
            try {
                int count = Integer.parseInt(in.readLine());
                int choiceIndex = 0;
                for(int i=0; i<=count; i++)
                {
                    if(choice.get(i).isSelected())
                        choiceIndex = i;
                }
            
                out.println("VOTEAT"+choiceIndex);
                
            } catch (IOException ex) {
                Logger.getLogger(Voting.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_vote_vote_butActionPerformed

    private void main_close_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_close_butActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_main_close_butActionPerformed

    private void name_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_txtActionPerformed
  


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton choice1;
    private javax.swing.JRadioButton choice2;
    private javax.swing.JRadioButton choice3;
    private javax.swing.JRadioButton choice4;
    private javax.swing.JRadioButton choice5;
    private javax.swing.ButtonGroup choice_group;
    private javax.swing.JPanel con1;
    private javax.swing.JPanel con2;
    private javax.swing.JPanel con3;
    private javax.swing.JPanel con4;
    private javax.swing.JPanel con5;
    private javax.swing.JTextField cont1_txt;
    private javax.swing.JTextField cont2_txt;
    private javax.swing.JPanel cont3_pan;
    private javax.swing.JTextField cont3_txt;
    private javax.swing.JPanel cont4_pan;
    private javax.swing.JTextField cont4_txt;
    private javax.swing.JPanel cont5_pan;
    private javax.swing.JTextField cont5_txt;
    public javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel main;
    private javax.swing.JButton main_Vote_but;
    private javax.swing.JButton main_close_but;
    private javax.swing.JButton main_create_but;
    private javax.swing.JButton main_edit_but;
    private javax.swing.JButton minus;
    private javax.swing.JLabel name;
    private javax.swing.JTextField name_txt;
    private javax.swing.JButton new_create;
    private javax.swing.JButton new_edit;
    private javax.swing.JPanel new_pan;
    private javax.swing.JButton plus;
    private javax.swing.JList<String> voteList;
    private javax.swing.JPanel vote_content_pan;
    private javax.swing.JPanel vote_pan;
    private javax.swing.JButton vote_vote_but;
    private javax.swing.JScrollPane votescroll;
    // End of variables declaration//GEN-END:variables
}
