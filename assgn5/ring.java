import java.util.*;

public class ring{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of nodes: ");
        int n=sc.nextInt();
        int token=0;
        System.out.println("Currently token at 0");
        System.out.print("Order is ");
        for(int i=0;i<n;++i){
            System.out.print(i+" ");
        }
        System.out.print("\n");
        try{
            while(true){
                System.out.print("Enter sender node: ");
                int s=sc.nextInt();
                System.out.print("\n");
                System.out.print("Enter reciever node: ");
                int r=sc.nextInt();
                System.out.print("\n");
                System.out.print("Enter data: ");
                int d=sc.nextInt();
                System.out.print("\n");
                System.out.println("Currently token at "+token);

                for(int i=token,j=token;i!=s;++j,i=(i+1)%n){
                    System.out.print(i+"->");
                }
                System.out.print("\n");
                System.out.println("Sender "+s+" starts sending");
                for(int i=(s+1)%n;i!=r;i=(i+1)%n){
                    System.out.print(i+"->");
                }
                System.out.print("\n");
                System.out.println("Reciever "+r+" recieved "+d);
                token=s;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}