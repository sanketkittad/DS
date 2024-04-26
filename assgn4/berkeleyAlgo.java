import java.util.*;
import java.time.*;
import java.lang.Math;
public class berkeleyAlgo{
    public static String serverTime;
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of clients: ");
        int n=sc.nextInt();
        long timeInMinutes[]=new long[n];
        sc.nextLine();
        for(int i=0;i<n;++i){
            System.out.print("Enter time at client"+(i+1)+": (format:- HH:MM)");
            String time=sc.nextLine();
            long currTimeInMinutes=convertToMinutes(time);
            timeInMinutes[i]=currTimeInMinutes;
        }
        System.out.println("Before sync");
        displayTime(timeInMinutes);
        String time=LocalTime.now().toString();
        serverTime=time.substring(0,5);
        System.out.println("Time at server: "+serverTime);
        BerkeleyAlgorithm(timeInMinutes);
        System.out.println("After sync");
        displayTime(timeInMinutes);
        System.out.println("Time at server: "+serverTime);        
    }
    static String formatTime(long minutes){
        long hours = minutes / 60;
        long mins = minutes % 60;
        return String.format("%02d:%02d", hours, mins);
    }
    static void displayTime(long timeInMinutes[]){
        for(int i=0;i<timeInMinutes.length;++i){
            System.out.println("Time at client"+(i+1)+": "+formatTime(timeInMinutes[i]));
        }
        
    }
    static long convertToMinutes(String time){
        String hours=time.substring(0,2),minutes=time.substring(3);
        long currTimeInMinutes=(Long.parseLong(hours)*60)+(Long.parseLong(minutes));
        return currTimeInMinutes;
    }
    static void BerkeleyAlgorithm(long timeInMinutes[]){
        long serverMinutes=convertToMinutes(serverTime);
        long differences=0;
        for(int i=0;i<timeInMinutes.length;++i){
            long diff=Math.abs(serverMinutes-timeInMinutes[i]);
            differences+=diff;
        }
        long avgdiff=differences/(timeInMinutes.length);
        for(int i=0;i<timeInMinutes.length;++i){
            long offset=avgdiff-timeInMinutes[i];
            timeInMinutes[i]+=offset;
        }
        long minutes=convertToMinutes(serverTime);
        long offset=avgdiff-minutes;
        minutes+=offset;
        serverTime=formatTime(minutes);
    }
}