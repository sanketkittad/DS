
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf{
    private LocalTime localTime;
    ServerImpl(LocalTime localTime) throws RemoteException{
        this.localTime = localTime;
    }

    public LocalTime getTime() throws RemoteException{
        return this.localTime;
    }

    public void adjustTime(LocalTime serverTime,long diff) throws RemoteException{
        long serverNano = serverTime.toNanoOfDay();
        long localNano = this.getTime().toNanoOfDay();
        long adjusted = diff + serverNano;

        LocalTime adjustedTime = LocalTime.ofNanoOfDay(adjusted);
        this.localTime = adjustedTime;
        System.out.println("Updated Time is "+adjustedTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}
