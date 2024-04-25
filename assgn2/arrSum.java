import java.util.*;
import mpi.*;
public class arrSum{
    public static void main(String args[]){
        MPI.Init(args);
        int rank=MPI.COMM_WORLD.Rank();
        int size=MPI.COMM_WORLD.Size();
        int unitsize=5;
        int nums[]=new int[unitsize*size];
        int root=0;
        int recieved[]=new int[unitsize];
        int fin_recieved[]=new int[size];
        if(rank==root){
            for(int i=0;i<unitsize*size;++i){
                System.out.print("Element at "+i+": "+i+"\n");
                nums[i]=i;
            }
        }
        MPI.COMM_WORLD.Scatter(
            nums,
            0,
            unitsize,
            MPI.INT,
            recieved,
            0,
            unitsize,
            MPI.INT,
            root
        );
        for(int i=1;i<unitsize;++i){
            recieved[0]+=recieved[i];
        }
        System.out.println("Intermediate sum at "+rank+" is "+recieved[0]);
        MPI.COMM_WORLD.Gather(
            recieved,
            0,
            1,
            MPI.INT,
            fin_recieved,
            0,
            1,
            MPI.INT,
            root
        );
        if(rank==root){
            int tot=0;
            for(int i=0;i<size;++i){
                tot+=fin_recieved[i];
            }
            System.out.println("Sum is "+tot);
        }
        MPI.Finalize();
    }
}