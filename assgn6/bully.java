import java.util.*;
public class bully{
    int number;
    boolean availablity[];
    int coordinator;
    
    public bully(int max){
        coordinator=max;
        availablity=new boolean[max];
        
        number=max;
        for(int i=0;i<max;++i){
            availablity[i]=true;
            System.out.print("Process "+(i+1)+" created\n");
        }
        System.out.println("Process "+(coordinator)+" is coordinator");
    }
    void displayProcess(){
        for(int i=0;i<number;++i){
            if(availablity[i]){
                System.out.println("Process "+(i+1)+" is active");
            }else{
                System.out.println("Process "+(i+1)+" is not active");
            }
        }
    }
    void activate(int id){
        if(availablity[id-1]){
            System.out.println("Process "+(id)+" is already active");
        }else{
            System.out.println("Process "+(id)+" is now active");
        }
        availablity[id-1]=true;
    }
    void deactivate(int id){
        if(!availablity[id-1]){
            System.out.println("Process "+(id)+" is already deactivated");
        }else{
            System.out.println("Process "+(id)+" is now deactivated");
        }
        availablity[id-1]=false;
    }
    
    void initElection(int initiator){
        for(int first=initiator;first<=number;){
            boolean fd=false;
            for(int second=first+1;second<=number;++second){
                System.out.print("Tried P"+first+" to P"+second+" :");
                if(availablity[second-1])
                {
                    System.out.print("Success\n");
                    first=second;
                    fd=true;
                    break;
                }else{
                    System.out.print("Fail\n");
                    
                }
            }
            if(fd)
            coordinator=first;
            if(!fd)++first;
        }
    }
    int getCoordinator(){
        return coordinator;
    }
    public static void main(String args[]) {
        bully r = null;
        int max_processes = 0, process_id = 0;
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("Ring Algorithm");
            System.out.println("1. Create processes");
            System.out.println("2. Display processes");
            System.out.println("3. Up a process");
            System.out.println("4. Down a process");
            System.out.println("5. Run election algorithm");
            System.out.println("6. Exit Program");
            System.out.print("Enter your choice:- ");
            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.print("Enter the total number of processes:- ");
                    max_processes = sc.nextInt();
                    r = new bully(max_processes);
                    break;
                case 2:
                    r.displayProcess();
                    break;
                case 3:
                    System.out.print("Enter the process to up:- ");
                    process_id = sc.nextInt();
                    r.activate(process_id);
                    break;
                case 4:
                    System.out.print("Enter the process to down:- ");
                    process_id = sc.nextInt();
                    r.deactivate(process_id);
                    break;
                case 5:
                    System.out.print("Enter the process which will initiate election:- ");
                    process_id = sc.nextInt();
                    r.initElection(process_id);
                    System.out.println("P"+process_id+" has elected P"+r.getCoordinator()+" as coordinator");
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error in choice. Please try again.");
                    break;
            }
        }
    }
}