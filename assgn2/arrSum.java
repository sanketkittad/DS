import mpi.*;

public class arrSum{
    public static void main(String args[]){
        MPI.Init(args);
        int unitsize=4;
        int size=MPI.COMM_WORLD.Size();
        int rank=MPI.COMM_WORLD.Rank();
        int array[]=new int[unitsize*size];
        
        int root=0;
        int reciever[]=new int[unitsize];
        int fin_reciever[]=new int[size];
        if(root==rank){
            for(int i=0;i<array.length;++i){
                array[i]=i+1;
            }
        }
        MPI.COMM_WORLD.Scatter(
            array,
            0,
            unitsize,
            MPI.INT,
            reciever,
            0,
            unitsize,
            MPI.INT,
            root
        );
        int localSum=1;
        for(int i=0;i<unitsize;++i){
            localSum+=reciever[i];
        }

        System.out.println("Local sum at "+rank+" is "+localSum);
        reciever[0]=localSum;
        MPI.COMM_WORLD.Gather(
            reciever,
            0,
            1,
            MPI.INT,
            fin_reciever,
            0,
            1,
            MPI.INT,
            root
        );
        if(root==rank){
            int globalSum=0;
            for(int i=0;i<size;++i){
                globalSum+=fin_reciever[i];
            }
            System.out.println("Global sum is "+globalSum);
        }
        MPI.Finalize();
    }
}