
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.*;
import java.util.HashSet;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ChatServer {

    static PrintWriter log = null;
    private static final int PORT = 9007; //Default Port Number
    public static int port_number = 0; //The port number that you add to make the port number different when you create the socket.

    private static HashMap<String, PrintWriter> nameWriters = new HashMap<String, PrintWriter>();

    // Store information about the people participating in the dynamic allocation chat--> Name, chatting, connection time, number of shared URLs, and code count.
    static ArrayList<String[]> username = new ArrayList<String[]>(100);

    // A function that checks URL  INPUT --> string, OUTPUT --> URL
    public static ArrayList<String> extractUrls(String text) {
        ArrayList<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find()) {
            containedUrls.add(text.substring(urlMatcher.start(0), urlMatcher.end(0)));
        }

        return containedUrls;
    }
    // End of URL check function

    /*-----------------------------------------------*/
    // If you have more than three semicolons, look at it as one code.
    // Input value String & return value Number of codes.
    // a line of reference
    public static int Code_checknumber(String str1) {
        int code_check = 0;
        int code_number = 0;
        int code_temp = 0;
        int code_flag = 0;
        int code_space = 5;

        for (int i = 0; i < str1.length(); i++) {
            if (Character.getType(str1.charAt(i)) == 5) {
                continue;
            }

            code_temp = i;

            while (Character.getType(str1.charAt(code_temp)) != 5) {

                if (str1.charAt(code_temp) == ';' && code_flag == 0) {
                    code_flag = 1;
                    code_check++;
                }
                if (code_check >= 3) {
                    code_number++;
                    code_check = 0;
                }

                if (code_flag == 1) {
                    code_space--;
                }
                if (code_flag == 1 && code_space == 0) {
                    code_space = 5;
                    code_flag = 0;
                }

                code_temp++;
                if (code_temp == str1.length()) {
                    break;
                }

            }
            i = code_temp;
        }
        return code_number;
    }
    // Terminate the code_check function

    /*-----------------------------------------------*/
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT); //create and get into the socket
        try {
            while (true) {
                new Handler(listener.accept()).start(); //start program
            }
        } finally {
            listener.close();
        }
    }

    private static class Handler extends Thread {

        private String id;
        private String pName;
        private Socket socket; // we can communicate using this socket.
        // init streams
        private BufferedReader in;
        private PrintWriter out;
        private Database db = new Database(); //To work with DB

        public Handler(Socket socket) {

            this.socket = socket;
        }

        public void run() {

            try {
                // init streams
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                //start to communicate with client
                while (true) {

                    String input = in.readLine();

                    //if client click the button "Log in"
                    if (input.startsWith("LOGIN")) {
                        String[] client = input.substring(5).split("/"); //get the information of id and password
                        System.out.println(client[0] + " " + client[1]);

                        //check the information with person DB
                        if (db.login(client[0], client[1])) {
                            out.println("ACCEPT ");
                        } else {
                            out.println("DENY ");
                            continue;
                        }

                        id = client[0];
                        break;
                        //if client click the button "SIGN UP"
                    } else if (input.startsWith("SIGNUP")) {
                        String[] client = input.substring(6).split("/"); //get information of id, password, name
                        System.out.println(client[0] + " " + client[1] + " " + client[2]);

                        //check the information with person inDB
                        if (db.Signup(client[0], client[1], client[2])) {
                            out.println("ACCEPT");
                        } else {
                            out.println("DENY");
                        }
                    }

                }

                ArrayList<String> RRR = new ArrayList<String>(); //Creates a new array list in string format to obtain information from the selected project.
                int s = 0; //Initialization of count
                String Pro_number = null; //Initialization of selected project's project number
                String DeletePro_number = null; //Initialization of selected project's to delete project number
                String Pro_role = null; //Initialization of selected project's role
                String Pro_email = null; //Initialization of selected project's professor email
                String Pro_date = null; //Initialization of selected project's deadline date

                //Initiate communication with client
                while (true) {
                    String input = in.readLine();
                    //to display the information project, server gives to client the information of project
                    if (input.startsWith("PROJECTIN")) {
                        RRR = db.ProjectDB(id); //Gets and saves project information from that ID.

                        out.println("CLIENTID " + id); //give to client who am I
                        out.println("PROJECTNUM " + RRR.size()); //give to client the number of project
                        //give to client the information of project name, it's role and deadline
                        for (int i = 0; i < RRR.size(); i++) {
                            out.println("PROJECTINFO " + RRR.get(i));
                        }

                        //finish all kind of information of making project
                        out.println("PROJECTEND");

                        //when client wants to make project
                    } else if (input.startsWith("PROJECTCREATE")) {
                        String project_name = ""; //Initialization of project name
                        String project_deadline = ""; //Initialization of deadline
                        String project_email = ""; //Initialization of email
                        String project_startline = ""; //Initialization of start date
                        ArrayList<String> project_team = new ArrayList<String>(); //Creates a new array list in string format to obtain information from the selected IDs.

                        //Initiate communication with client when create the project
                        while (true) {

                            input = in.readLine();
                            if (input.startsWith("PROJECTNAME")) {
                                project_name = input.substring(12); //get information of project name
                                System.out.println(project_name);
                            } else if (input.startsWith("PROJECTDEAD")) {
                                project_deadline = input.substring(12); //get information of deadline
                                System.out.println(project_deadline);
                            } else if (input.startsWith("myid")) {
                                out.println(id); //send the information who am I
                            } else if (input.startsWith("PROJECTEMAIL")) {
                                project_email = input.substring(13); //get information of email for send to professor
                                System.out.println(project_email);
                            } else if (input.startsWith("PROJECTSTART")) {
                                project_startline = input.substring(13); //get information of start date
                            } else if (input.startsWith("PROJECTID")) {
                                ArrayList<String> idlist = db.IDlist(); //get ID list from project

                                //Send IDs who are in this program to the client.
                                for (int i = 0; i < idlist.size(); i++) {
                                    out.println("PROJECTIDLIST " + idlist.get(i));
                                }
                                out.println("IDEND");

                                //Initiate communication with client to check ID
                                while (true) {
                                    input = in.readLine();
                                    //to check ID
                                    if (input.startsWith("IDCHECK")) {
                                        if (db.IDcheck(input.substring(8))) {
                                            out.println("IDACCEPT");
                                        } else {
                                            out.println("IDDENY");
                                        }
                                        
                                        //when client click the button "OK" to finish to make project
                                    } else if (input.startsWith("IDSEND")) {

                                        //Initiate communication with client to add member into project
                                        while (true) {
                                            input = in.readLine();
                                            //when user wants to add members to project
                                            if (input.startsWith("MEMBER")) {
                                                System.out.println(input.substring(7));
                                                project_team.add(input.substring(7)); //add the selected IDs to create projet into arrayList 
                                                
                                                //When all information is entered, it appears in the iterative statement with the information that ends with the client.
                                            } else if (input.startsWith("IDEND")) {
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                //Add a project database with the information
                                String p_num = db.InsertProject(project_name, project_deadline, project_email, project_startline);
                                //At this time, create a new txt file to read the information in the chat window.
                                PrintWriter outputStream = new PrintWriter(p_num + ".txt");

                                //Add the pp database with the information given
                                //If it is possible to add to the database, it will appear in a repeating statement.
                                if (db.InsertPP(project_team, p_num)) {
                                    break; //finally making the project is done.
                                } else {
                                    System.out.println("Error!");
                                }
                            }

                        }
                    } // When a user selects a project from the project table
                    else if (input.startsWith("GIVE")) {
                        s = Integer.parseInt(input.substring(5));
                        String[] getPro = RRR.get(s).split("/");
                        Pro_role = getPro[1]; //have the corresponding role to move to the chat room.
                        Pro_email = getPro[4]; //have the corresponding pro_email to move to the chat room.
                        Pro_date = getPro[2]; //have the corresponding deadline date to move to the chat room.
                        Pro_number = getPro[3]; //have the corresponding pro_number to move to the chat room.

                        out.println("NUMBER " + Pro_number); //Send the project number to the client.

                        //When user wants to get rid of the project
                    } else if (input.startsWith("DELETE")) {
                        s = Integer.parseInt(input.substring(7));
                        String[] getPro = RRR.get(s).split("/");
                        DeletePro_number = getPro[3]; //Have the pro_number that user wants to delete.
                        System.out.println("DeletePro is " + DeletePro_number);
                        db.DeleteDB(DeletePro_number); //Delete the project from the DB.

                        //When a user clicks on the project, he or she can start chatting with a new socket.
                    } else if (input.startsWith("CHATINFO")) {
                        pName = input.substring(9); //get selected project name
                        port_number++; //plus port number for get into chat room
                        out.println("CHATACCEPTED " + port_number); //send to client the information of new port number
                        ServerSocket chatSocket = new ServerSocket(PORT + port_number); //create new socket

   
                        try {
                            new Handler2(chatSocket.accept(), id, Pro_number, Pro_date, Pro_email, Pro_role).start();

                            // Name redundancy check required. The array space should be increased sideways.
                            int flag_name = 0;
                            for (int i = 0; i < username.size(); i++) {
                                if (username.get(i)[0].contains(id + "`" + Pro_number + "`")) {
                                    flag_name = 1;
                                }
                            }
                            if (flag_name != 1) {
                                username.add(new String[]{id + "`" + Pro_number + "`", "0", "0", "0", "0", "0", "0"});

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            chatSocket.close();
                        }
                    } else if (input.startsWith("VOTE")) {
                        for (int i = 0; i < db.Votelist(Pro_number).size(); i++) {
                            out.println("VOTELIST " + db.Votelist(Pro_number).get(i));
                        }
                        out.println("VOTEEND");

                        while (true) {
                            input = in.readLine();

                            if (input.startsWith("VOTECREATE")) {

                                String vote_c = input.substring(10);
                                if (db.Votecheck(vote_c, Pro_number)) {
                                    out.println("VOTEACCEPT");

                                } else {
                                    out.println("VOTEDENY");
                                }

                            } else if (input.startsWith("VOTECHOICE")) {

                                String[] vote_c = input.substring(10).split("/");
                                System.out.println(input + "!!!!!!!!!!");
                                String vote_n = vote_c[0];

                                int vote_count = Integer.parseInt(vote_c[1]);
                                System.out.println(vote_count + "@@@@@@@@@@@@@@2");

                                String choice_name = input.substring(10);
                                System.out.println(choice_name + "##############");

                                input = in.readLine();
                                System.out.println(input);
                                String[] vote_choice_name = input.substring(7).split("/");
                                int num = 1;
                                for (int i = 0; i < vote_count; i++) {
                                    db.InsertVote(vote_n, num, i + 1, vote_choice_name[i]);

                                }
                            } else if (input.startsWith("VOTEEDIT")) {

                            } else if (input.startsWith("VOTEVOTE")) {
                                break;
                            }
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println(e);
            } finally {

//				if (id != null || out != null) {
//					nameWriters.remove(id);
//				}
//				try {
//					for (String nameKey : nameWriters.keySet()) {
//						nameWriters.get(nameKey).println("OUT ********** [" + id + "] left the chat **********");
//					}
//					socket.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
            }
        }
    }

    private static class Handler2 extends Thread {

        private static String filepath;
        private Socket socket2;
        // init streams
        private BufferedReader in;
        private PrintWriter out;
        private String line, id, pNum, pDate, pEmail, pRole;
        private Database db = new Database();

        public Handler2(Socket socket, String id, String pNum, String pDate, String pEmail, String pRole) {
            this.socket2 = socket;
            this.id = id;
            this.pNum = pNum;
            this.pDate = pDate;
            this.pEmail = pEmail;
            this.pRole = pRole;

            System.out.println("--------------------");

            System.out.println(pDate);
            System.out.println(pEmail);
            System.out.println(pRole);

            System.out.println(id + " " + pNum);
            try {
                // init streams
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                nameWriters.put(pNum + " " + id, out);

            } catch (IOException e) {
                e.printStackTrace();
            }
            File path = new File("");
            String pathStr = path.getAbsolutePath();//read file path from your PC
            System.out.println(pathStr);
            filepath = pathStr + "\\" + pNum + ".txt";
        }

        public void run() {

            long stay_time;
            long start_time = System.currentTimeMillis();
            System.out.println(start_time);

            FileReader fReader = null;
            String filename = pNum + ".txt";
            try {//read from file and broadcast that contents before starting
                fReader = new FileReader(filepath);
                // init streams
                BufferedReader brr = new BufferedReader(fReader);
                String conversation = brr.readLine();
                while (conversation != null) {
                    out.println("con " + conversation);
                    conversation = brr.readLine();
                }

                fReader.close();
            } catch (Exception e) {
                System.out.println("file not founded");
                System.exit(0);
            }

            int index_of_person = 0;
            try {
                //bad language list
                String[] bad_Language = {"FUCK", "fuck"};
                
                int count = 0;
                while (true) {
                    for (int k = 0; k < username.size(); k++) {
                        System.out.println(username.get(k)[0] + " -> number:" + username.get(k)[1] + " Stay_time: "
                                + username.get(k)[2] + " URL:" + username.get(k)[3] + " CODE:"
                                + username.get(k)[4]);
                    }

                    String input = in.readLine();
                    System.out.println("input:" + input);
                    index_of_person = input.lastIndexOf("`");

                    if (input.startsWith("MESS")) {

                        // Filter badwords in input
                        for (int i = 0; i < bad_Language.length; i++) {
                            input = input.replaceAll(bad_Language[i], "***");
                        }

                        // Check URL in input
                        ArrayList<String> extractedUrls = extractUrls(input.substring(index_of_person + 1));

                        // Check nubmer of code in input
                        int coding_number = Code_checknumber(input.substring(index_of_person + 1));

                           //Store number of code, URL, stay time, chat count & Professor Email in username(using Arraylist)
                        for (int k = 0; k < username.size(); k++) {
                            if (username.get(k)[0].startsWith(input.substring(5, index_of_person + 1))) {
                                System.out.println("come in");
                                username.get(k)[5] = "" + pEmail;
                                System.out.println(pEmail);
                                System.out.println(pDate);
                                System.out.println(username.get(k)[5]);

                                int num = Integer.parseInt(username.get(k)[1]);
                                num++;
                                username.get(k)[1] = "" + num;
                                System.out.println("num: " + num);

                                int num_3 = Integer.parseInt(username.get(k)[3]);
                                int num_4 = Integer.parseInt(username.get(k)[4]);
                                num_3 = num_3 + extractedUrls.size();
                                num_4 = num_4 + coding_number;

                                username.get(k)[3] = "" + num_3; // URL count
                                username.get(k)[4] = "" + num_4; // Coding count

                            }
                        }

                        String[] message = input.substring(5).split("`");

                        // Do not perform the following procedure if it is an empty message.
                        if (message.length > 2) {
                            if (message[2].startsWith("/")) { // If it is a whisper function -> Defined protocol.
                                int index = message[2].substring(1).indexOf('/');
                                if (index > 0) {
                                    // whisper to client information
                                    String whisID = message[2].substring(1, index + 1);

                                    // If there is an ID of whispering target, the whisper function is executed.
                                    if (nameWriters.containsKey(pNum + " " + whisID)) {
                                        //In the message transmission, make it appear in the message window of sender and receiver
                                        nameWriters.get(pNum + " " + whisID).println("MESS [Whisper] " + message[0] + ": " + message[2].substring(index + 2));
                                        nameWriters.get(pNum + " " + id).println("MESS [Whisper] " + message[0] + ": " + message[2].substring(index + 2));
                                        continue;
                                    }
                                }
                            } else if (message[2].startsWith("<")) { // If it is a role function -> Defined protocol.
                                int index = message[2].substring(1).indexOf('>');
                                if (index > 0) {
                                    String RoleName = message[2].substring(1, index + 1);

                                    if (RoleName.equals(pRole)) {
                                        // First, broadcast to everyone. -> Allows the client to send messages according to their role.
                                        String message2 = "ROLE " + RoleName + "`[Role] " + message[0] + ": " + message[2].substring(RoleName.length()+2);
                                        System.out.println(message2);
                                        for (String nameKey : nameWriters.keySet()) {
                                            String[] temp = nameKey.split(" ");
                                            if (temp[0].equals(pNum)) {
                                                nameWriters.get(nameKey).println(message2);

                                            }
                                        }
                                    }
                                    continue;
                                }
                            }

                            String message2 = "MESS " + message[0] + ": " + message[2];

                            log = new PrintWriter(new FileOutputStream(filename, true));//write to file name and text by line
                            log.println(message[0] + ": " + message[2]);
                            log.close();

                            // send message (normal form)
                            for (String nameKey : nameWriters.keySet()) {
                                String[] temp = nameKey.split(" ");
                                if (temp[0].equals(pNum)) {
                                    nameWriters.get(nameKey).println(message2);

                                }
                            }
                        }

                    } else if (input.startsWith("EXIT")) {
                        long end_time = System.currentTimeMillis();
                        stay_time = (long) ((end_time - start_time) / 1000.0);
                        System.out.println(input.substring(5, index_of_person + 1));

                        for (int k = 0; k < username.size(); k++) {
                            if (username.get(k)[0].equals(input.substring(5, index_of_person + 1))) {
                                System.out.println("Exit come in");
                                long num_2 = Integer.parseInt(username.get(k)[2]);
                                num_2 = num_2 + stay_time;
                                username.get(k)[2] = "" + num_2;
                            }
                        }
                        nameWriters.remove(pNum + " " + id);
                    } else if (input.startsWith("END")) {
                        String host = "smtp.naver.com";//mall API 
                        final String user = "tree9295@naver.com";//My mail address id
                        final String password = "gG123789";//my maill address password
                        String to = "";//email address of receiver which is read from database later. 

                        for (int k = 0; k < username.size(); k++) {
                            System.out.println("mail come in");
                            if (username.get(k)[0].startsWith(input.substring(4))) {
                                System.out.println("mail come in");
                                to = username.get(k)[5];//email address of receiver which is read from database
                                System.out.println(to);
                            }

                        }

                        // Get the session object
                        Properties props = new Properties();//Properties for using mail API
                        
                        props.put("mail.smtp.host", host);
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.user", user);
                        props.put("mail.smtp.password", password);
                        props.put("mail.debug", "true");
                        props.put("mail.smtp.debug", "true");
                        props.put("mail.smtp.port", "587");
                        props.put("mail.smtp.ssl.trust", "smtp.naver.com");

                        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(user, password);
                            }
                        });//End authentication for naver(imap/smtp)
                        
                        System.out.println("end start");

                        try {
                            MimeMessage message = new MimeMessage(session);

                            message.setFrom(new InternetAddress(user));
                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                            // Subject
                            message.setSubject("[project#] Contribution");//Title of mail
                            String contribution = "";//contents of mail
                            int[] total = new int[username.size()];
                            //Calculate sum of each person's score(as below)
                            for (int k = 0; k < username.size(); k++) {

                                int temp = Integer.parseInt(username.get(k)[1]);
                                temp = temp * 3;//the number is weighted for calculating contribution
                                total[k] = total[k] + temp;
                                username.get(k)[1] = "" + temp;//the number of chat

                                temp = Integer.parseInt(username.get(k)[2]);
                                temp = temp * 2;
                                total[k] = total[k] + temp;
                                username.get(k)[2] = "" + temp;//the number of log time

                                temp = Integer.parseInt(username.get(k)[3]);
                                temp = temp * 2;
                                total[k] = total[k] + temp;
                                username.get(k)[3] = "" + temp;//the number of URL

                                temp = Integer.parseInt(username.get(k)[4]);
                                temp = temp * 4;
                                total[k] = total[k] + temp;
                                username.get(k)[4] = "" + temp;//the number of CODE

                            }
                            int sum = 0;
                             
                            //Calculate sum of each person score in project to be end   
                            for (int k = 0; k < username.size(); k++) {
                                int index_first1 = username.get(k)[0].indexOf("`");
                                if (username.get(k)[0].substring(index_first1 + 1).startsWith(pNum)) {
                                    sum = sum + total[k];
                                }
                            }

                            //calculate contribution of persons with %
                            for (int k = 0; k < username.size(); k++) {

                                if (username.get(k)[0].substring(username.get(k)[0].indexOf("`") + 1).startsWith(pNum)) {
                                    String id_name = db.getName(username.get(k)[0].substring(0, username.get(k)[0].indexOf("`")));
                                    contribution = contribution + id_name + ": " + (("" + Math.round(((double) total[k] / sum * 100))) + "%" + "\n");
                                    //System.out.println("contri "+(double)(total[k]/sum*100));
                                    // System.out.println("pleasse "+contribution);
                                }
                            }
                            
                            message.setText(contribution);//set text to send email
                            
                            Transport.send(message);//send  email to person who 'To'
                            System.out.println("message sent successfully...");
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
