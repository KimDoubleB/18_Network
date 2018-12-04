import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.shape.Line;

public class ChatServer {

   private static final int PORT = 9007;

   private static HashMap<String, PrintWriter> nameWriters = new HashMap<String, PrintWriter>();

   public static void main(String[] args) throws Exception {
      System.out.println("The chat server is running.");
      ServerSocket listener = new ServerSocket(PORT);
      try {
         while (true) {
            new Handler(listener.accept()).start();
         }
      } finally {
         listener.close();
      }
   }

   private static class Handler extends Thread {
      private String id;
      private Socket socket; // we can communicate using this socket.
      private BufferedReader in;
      private PrintWriter out;
      private Database db = new Database();

      public Handler(Socket socket) {

         this.socket = socket;
      }

      public void run() {
         try {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {

               String input = in.readLine();

               if (input.startsWith("LOGIN")) {
                  String[] client = input.substring(5).split("/");
                  

                  if (db.login(client[0], client[1]))
                  {
                      out.println("ACCEPT ");
                  }
                  else {
                     
                     out.println("DENY ");
                     continue;
                  }

                  // 원래는 이름 값을 넣어야되나, HashMap의 중복을 막기위해서 ID를 사용하였습니다.
                  id = client[0];
                  // if(clientName.isEmpty())
                  // continue;
                  //

                  if (!(nameWriters.containsKey(id) || id.equals("null") || id.equals(""))) {

                     nameWriters.put(id, out); // put data(id, output stream) in HashMap
                     break;
                  }
               } else if (input.startsWith("SIGNUP")) {
                  String[] client = input.substring(6).split("/");
                  System.out.println(client[0] + " " + client[1] + " " + client[2]);
                  if (db.Signup(client[0], client[1], client[2])) {
                     
                      out.println("ACCEPT");
                  }
                  else
                  { 
                    out.println("DENY");
                  }
               }

            }

            // 간소화시키기 위해서 while문 2개로 분할
            // 로그인과 프로젝트 분할
            // 프로젝트 시작
            while (true) {

               String input = in.readLine();

               if (input.startsWith("PROJECTIN")) {
                  ArrayList<String> result = db.ProjectDB(id);
                  out.println("PROJECTNUM " + result.size());
                  for (int i = 0; i < result.size(); i++)
                     out.println("PROJECTINFO " + result.get(i));

                  out.println("PROJECTEND");

               } else if (input.startsWith("PROJECTCREATE")) {
                  String project_name;
                  int project_deadline;
                  ArrayList<String> project_team = new ArrayList<String>();
                  while (true) {

                     input = in.readLine();
                     if (input.startsWith("PROJECTNAME")) {
                        project_name = input.substring(12);
                        System.out.println(project_name);
                     } else if (input.startsWith("PROJECTDEAD")) {
                        project_deadline = new Integer(input.substring(12));
                        System.out.println(project_deadline);
                     } else if (input.startsWith("PROJECTID")) {
                        ArrayList<String> idlist = db.IDlist();

                        for (int i = 0; i < idlist.size(); i++) {
                           out.println("PROJECTIDLIST " + idlist.get(i));
                        }
                        out.println("IDEND");
                        
                        while(true) {
                           input = in.readLine();
                           if (input.startsWith("IDCHECK")) {
                              if (db.IDcheck(input.substring(8))) {
                                 out.println("IDACCEPT");
                              } else {
                                 out.println("IDDENY");
                              }
                           }
                           else if(input.startsWith("IDSEND")) {
                              while(true) {
                                 input = in.readLine();
                                 if(input.startsWith("MEMBER")) {
                                    project_team.add(input.substring(7));
                                 }
                                 else if(input.startsWith("IDEND")) {
                                    break;
                                 }
                              }
                              break;
                           }
                        }
                        //여기서 이제 사람에 따른 롤 지정부분 쓰면 된다.
                        // else if 시작
                     } 
                     
                  }
               } // 2018.11.16 금요일까지 한 곳
            }

            // for (String nameKey : nameWriters.keySet()) {
            // // By getting output stream using get(key) method,
            // // server can broadcast 'entering message'.
            // nameWriters.get(nameKey).println("ENTRY ********** [" + id + "] get entry
            // **********");
            // }
            //
            // while (true) {
            //
            // String input = in.readLine();
            //
            // if (input == null || input.equals("")) {
            // // if data is null or empty, receive from client again.
            // continue;
            // } else if (input.startsWith("MESS")) {
            // // if data starts with "MESS", it means message to broadcast all clients.
            // for (String nameKey : nameWriters.keySet()) {
            // // By getting output stream using get(key) method,
            // // server can broadcast message except "MESS ".
            // nameWriters.get(nameKey).println("MESSAGE " + id + " : " +
            // input.substring(5));
            // }
            // } else if (input.startsWith("WHIS")) {
            //
            // String[] whis = input.substring(5).split("/");
            //
            // if (nameWriters.containsKey(whis[0])) {
            // nameWriters.get(whis[0])
            // .println("MESSAGE [Whisper]" + id + "->" + whis[0] + " : " + whis[1]);
            // out.println("MESSAGE [Whisper] " + id + "->" + whis[0] + " : " + whis[1]);
            // } else {
            // // ERROR message
            // out.println("MESSAGE ERROR; There is no one with that id.");
            // }
            // }
            // }
         } catch (IOException e) {
            System.out.println(e);
         } finally {

            if (id != null || out != null) {
               nameWriters.remove(id);
            }
            try {
               for (String nameKey : nameWriters.keySet()) {
                  nameWriters.get(nameKey).println("OUT ********** [" + id + "] left the chat **********");
               }
               socket.close();
            } catch (IOException e) {
            }

         }
      }
   }
}