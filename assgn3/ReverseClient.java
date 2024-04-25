import ReverseModule.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.io.*;

public class ReverseClient {
    public static void main(String[] args) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);
            // Resolve the NameService
            NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

            // Specify the service name to look up
            String serviceName = "Reverse";

            // Resolve the Reverse service object from the NameService
            Reverse reverseService = ReverseHelper.narrow(ncRef.resolve_str(serviceName));

            // Prompt the user to enter a string
            System.out.println("Enter a string:");

            // Read the user input
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String inputString = br.readLine();

            // Call the remote method to reverse the string
            String reversedString = reverseService.reverse_string(inputString);

            // Display the reversed string
            System.out.println("Reversed String: " + reversedString);
        } catch (Exception e) {
            System.err.println("Exception in ReverseClient: " + e);
            e.printStackTrace();
        }
    }
}
