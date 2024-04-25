import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class AddServer extends UnicastRemoteObject implements AddIntf{
    public AddServer() throws Exception{
        super();
    }
    @Override
    public double add(double num1,double num2){
        return num1+num2;
    }

    public static void main(String args[]){
        try{
            AddServer ad=new AddServer();
            Naming.rebind("AddServer",ad);
            System.out.println("Addition server started");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}