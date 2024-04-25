import java.rmi.*;
public interface AddIntf extends Remote{
    double add(double num1,double num2)throws Exception;
}