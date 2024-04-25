import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import ReverseModule.*;
import org.omg.PortableServer.*;

public class ReverseServer {
    public static void main(String[] args) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);

            // Get the Root POA and activate the POA manager
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            // Create the ReverseImpl servant object
            ReverseImpl reverseImpl = new ReverseImpl();

            // Obtain a reference to the servant
            Reverse reverseRef = ReverseHelper.narrow(rootPOA.servant_to_reference(reverseImpl));

            // Resolve the NameService
            NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

            // Bind the Reverse object to the NameService
            String serviceName = "Reverse";
            NameComponent[] name = ncRef.to_name(serviceName);
            ncRef.rebind(name, reverseRef);

            System.out.println("Reverse Server is running and waiting for requests...");

            // Start processing ORB requests
            orb.run();
        } catch (Exception e) {
            System.err.println("Exception in ReverseServer: " + e);
            e.printStackTrace();
        }
    }
}
