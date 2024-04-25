
import java.rmi.Naming;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class C1 {
    public static void main(String args[]){
        try {
            ServerImpl serverImpl = new ServerImpl(LocalTime.parse(args[1],DateTimeFormatter.ofPattern("HH:mm:ss")));
            Naming.rebind(args[0], serverImpl);
        } catch (Exception ex) {
            System.out.println("Execption at clock "+ args[0]+ ex);
        }
    }
}
