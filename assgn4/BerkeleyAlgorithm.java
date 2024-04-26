import java.time.LocalDate;
import java.util.Scanner;

public class BerkeleyAlgorithm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of clients in your network: ");
        int clientCount = sc.nextInt();
        sc.nextLine();

        int[] timeInMinutes = new int[1 + clientCount]; // 1 server + clientCount clients

        for (int i = 0; i < timeInMinutes.length; i++) {
            if (i == 0) {
                System.out.print("Enter time displayed in Server (HH:mm): ");
            } else {
                System.out.print("Enter time displayed in Client " + i + " (HH:mm): ");
            }

            String time = sc.nextLine();
            timeInMinutes[i] = convertToMinutes(time);
        }

        System.out.println("\nBefore Synchronization");
        displayTime(timeInMinutes, "");

        berkeleyAlgorithm(timeInMinutes);

        System.out.println("\nAfter Synchronization");
        displayTime(timeInMinutes, "Synchronized ");

        sc.close();
    }

    public static int convertToMinutes(String time) {
        int hours = Integer.parseInt(time.substring(0, 2)); 
        int minutes = Integer.parseInt(time.substring(3)); 
        return hours * 60 + minutes;
    }
    

    public static void berkeleyAlgorithm(int[] timeInMinutes) {
        int n = timeInMinutes.length;

        // Calculating the average time including the server's time
        int avg = 0;
        for (int i = 0; i < n; i++) {
            avg += timeInMinutes[i];
        }
        avg /= n;

        // Adjusting the time for all clocks
        for (int i = 0; i < n; i++) {
            int offset = avg - timeInMinutes[i];
            timeInMinutes[i] += offset;
            System.out.println("Clock " + i + " adjustment = " + offset + " minutes"); // Displaying adjustment value
        }
    }

    private static void displayTime(int[] timeInMinutes, String prefix) {
        LocalDate currentDate = LocalDate.now();
        System.out.println(prefix + "Server Clock:\t" + formatTime(timeInMinutes[0]) + " | "+currentDate);
        for (int i = 1; i < timeInMinutes.length; i++) {
            System.out.println(prefix + "Client " + i + " Clock:\t" + formatTime(timeInMinutes[i])+ " | "+currentDate);
        }
        System.out.println();
    }

    private static String formatTime(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        return String.format("%02d:%02d", hours, mins);
    }
}
