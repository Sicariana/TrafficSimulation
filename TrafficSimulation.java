import java.util.Scanner;

public class TrafficSimulation extends TrafficLight{
    public static void main(String[] args){
        TrafficLight tl = new TrafficLight();
        Scanner scan = new Scanner(System.in);
        int i = 0;
        while (i < 10) {
        	printRoad();
        	System.out.println("Press ENTER to continue");
            scan.nextLine();
            tl.move();
            
            tl.noVehiclesPresent();
            i++;
        }
        System.out.println("PROGRAM END");
        scan.close();
       
    }
}
