import java.util.*;
import mpi.*;
public class arrSum{
    public static void main(String args[]){
        MPI.Init(args);
        int size=MPI.COMM_WORLD.Size();
        int rank=MPI.COMM_WORLD.Rank();
        int unitsize=5;
        int array[]=new int[unitsize*size];
        for(int i=0;i<unitsize*size;++i){
            array[i]=i+1;
        }
        int root=0;
        int start=rank*unitsize;
        int end=start+unitsize;
        int localSum=0;
        for(int i=start;i<end;++i){
            localSum+=array[i];
        }
        int sender[]=new int[1];
        sender[0]=localSum;
        System.out.println("Local sum at "+rank+" is "+localSum);
        int reciever[]=new int[1];
        MPI.COMM_WORLD.Reduce(sender,0,reciever,0,1,MPI.INT,MPI.SUM,root);
        if(root==rank){
            int globalSum=reciever[0];
            System.out.println("Total sum is "+globalSum);
        }
        MPI.Finalize();
    }
}