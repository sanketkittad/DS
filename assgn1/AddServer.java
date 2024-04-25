import java.rmi.*;
import java.rmi.server.*;
public class AddServer  extends UnicastRemoteObject implements AddIntf{
    public AddServer() throws Exception{
        super();
    }
    @Override
    public double add(double a,double b){
        return a+b;
    }
    public static void main(String args[]) {
        try {
            // Create an instance of the AddImpl object (the remote object)
            AddServer ad = new AddServer();

            // Bind the remote object to the RMI registry with the name "AddServer"
            Naming.rebind("AddServer", ad);

            System.out.println("AddServer is running and waiting for client requests...");
        } catch (Exception e) {
            // Handle any exceptions that might occur during server initialization
            System.err.println("Exception in AddServer: " + e);
            e.printStackTrace();
        }
    }
}
