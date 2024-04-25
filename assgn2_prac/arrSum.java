import mpi.*;

public class arrSum{
    public static void main(String args[]){
        MPI.Init(args);
        int rank=MPI.COMM_WORLD.Rank();
        int size=MPI.COMM_WORLD.Size();
        int root=0;
        int unit_size=5;
        int sender[]=new int[unit_size*size];
        int reciever[]=new int[unit_size];
        int fin_reciever[]=new int[size];
        if(rank==root){
            for(int i=0;i<unit_size*size;++i){
                System.out.print("Element at "+i+": "+i+"\n");
                sender[i]=i;
            }
            
        }
        MPI.COMM_WORLD.Scatter(
            sender,
            0,
            unit_size,
            MPI.INT,
            reciever,
            0,
            unit_size,
            MPI.INT,
            root
        );
        for(int i=1;i<unit_size;++i){
            reciever[0]+=reciever[i];
        }
        System.out.print("Intermediate at "+rank+": "+reciever[0]+"\n");
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
            int total=0;
            for(int i=0;i<size;++i){
                total+=fin_reciever[i];
            }
            System.out.println("Sum is "+total);
        }
        MPI.Finalize();
    }
}