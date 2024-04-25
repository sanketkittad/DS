import java.rmi.*;
import java.util.*;

public class AddClient{
    public static void main(String args[]){
        try{
            String url="rmi://"+args[0]+"/AddServer";
            AddIntf ai=(AddIntf)Naming.lookup(url);
            // Display the first number provided as a command-line argument
            System.out.println("The first number is: " + args[1]);
            double num1 = Double.parseDouble(args[1]);

            // Display the second number provided as a command-line argument
            System.out.println("The second number is: " + args[2]);
            double num2 = Double.parseDouble(args[2]);

            // Invoke the remote method to add the two numbers
            double sum = ai.add(num1, num2);

            // Display the result of the addition operation
            System.out.println("The sum is: " + sum);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}