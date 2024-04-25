import java.util.*;
public class ring{
    int number;
    boolean availablity[];
    int coordinator;
    ArrayList<Integer> pid;
    public ring(int max){
        coordinator=max;
        availablity=new boolean[max];
        pid=new ArrayList<Integer>();
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
    void displayList(){
        System.out.print("[ ");
        for(Integer x : pid) {
            System.out.print(x + " ");
        }
        System.out.print(" ]\n");
    }
    void initElection(int initiator){
        if(availablity[initiator-1]){
            pid.add(initiator);
            System.out.print("Process "+initiator+" sends");
            displayList();
            int iter=initiator%number;
            while(iter!=initiator-1){
                if(availablity[iter]){
                    pid.add(iter+1);
                    System.out.print("Process "+(iter+1)+" sends");
                    displayList();
                }
                iter=(iter+1)%number;
            }
            coordinator=Collections.max(pid);
            System.out.println("Process P" + initiator + " has elected P" + coordinator + " as the coordinator");
            pid.clear();
        }
    }
    public static void main(String args[]) {
        ring r = null;
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
                    r = new ring(max_processes);
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