
import java.util.ArrayList;

//class to get information of data of ID
public class project_user {
	public String project_name; 
	public String project_date;
	public String project_role;
	public String project_num;
        public String project_email;
        
	public project_user(String name, String role, String date, String num,String email) {
		project_name = name;
		project_date = date;
		project_role = role;
		project_num = num;
                project_email=email;
	}
}
