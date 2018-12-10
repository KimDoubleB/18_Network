

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Schedule_data {
    String name="";
    String memo="";
    Date event = new Date();
    ArrayList<Date> schedule = new ArrayList<>();
    boolean sucess = false;
    
    
    Schedule_data(String a)
    {
        this.name = a;
        this.event = new Date();
    }
    
    Schedule_data(String a, Date e)
    {
        this.name = a;
        this.event = e;
    }
    
    public void setMemo(String a)
    {
        this.memo = a;
    }
    public void setYMD(int y, int m, int d)
    {
        this.event.setYear(y);
        this.event.setMonth(m-1);
        this.event.setDate(d);
    }
    
    public String getDate()
    {
        String temp = "";
        String yy = Integer.toString(this.event.getYear()+1900);
        String mm = Integer.toString(this.event.getMonth()+1);
        String dd = Integer.toString(this.event.getDate());
        temp = yy+mm+dd;
        
        return temp;
    }
}
