import java.rmi.*;
public interface AddIntf extends Remote {
    double add(double a,double b)throws Exception;
    
};