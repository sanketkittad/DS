import java.rmi.*;

public class AddClient {
    public static void main(String args[]) {
        try {
            // Construct the RMI URL to locate the remote server
            String url = "rmi://" + args[0] + "/AddServer";

            // Look up the remote object from the RMI registry using the URL
            AddIntf addIntf = (AddIntf) Naming.lookup(url);

            // Display the first number provided as a command-line argument
            System.out.println("The first number is: " + args[1]);
            double num1 = Double.parseDouble(args[1]);

            // Display the second number provided as a command-line argument
            System.out.println("The second number is: " + args[2]);
            double num2 = Double.parseDouble(args[2]);

            // Invoke the remote method to add the two numbers
            double sum = addIntf.add(num1, num2);

            // Display the result of the addition operation
            System.out.println("The sum is: " + sum);
        } catch (Exception e) {
            // Handle any exceptions that might occur during RMI communication
            System.out.println("Exception occurred: " + e);
        }
    }
}
