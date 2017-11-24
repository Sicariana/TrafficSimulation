import java.util.Arrays;
import java.util.Random;

public class TrafficLight{
    // These arrays will not be sorted
    static Vehicle[] n = new Vehicle[5];
    static Vehicle[] s = new Vehicle[5];
    static Vehicle[] e = new Vehicle[5];
    static Vehicle[] w = new Vehicle[5];
    
    // These arrays will be sorted so null is at the end
    static Vehicle[] north = new Vehicle[5];
    static Vehicle[] south = new Vehicle[5];
    static Vehicle[] east = new Vehicle[5];
    static Vehicle[] west = new Vehicle[5];
   
    public boolean running; // checks if simulation is running
    public boolean NS; // checks if north/south traffic is moving
    public boolean EW; // checks if east/west traffic is moving
    public static int time; // duration of green light. must be at least 4
    
    // Default constructor for TrafficLight
    public TrafficLight(){
        running = true;
        NS = true;
        EW = false;
        time = 5;
        Vehicle[] vehicleTypes = {new Car(), new Truck(), new Semi(), null};
        Random random = new Random();
        int index = random.nextInt(vehicleTypes.length);
        
        // Randomly fill each direction with 1-5 cars, trucks, and semis
        for (int i = 0; i < 5; i++){
            n[i] = vehicleTypes[index];
            index = random.nextInt(vehicleTypes.length);
            s[i] = vehicleTypes[index];
            index = random.nextInt(vehicleTypes.length);
            e[i] = vehicleTypes[index];
            index = random.nextInt(vehicleTypes.length);
            w[i] = vehicleTypes[index];
            index = random.nextInt(vehicleTypes.length);
        }
        
 
        
       
        int j = 0;
        // If a value in n is null, move it to the end of north
        for (int i = 0; i < 5; i++){
            if (n[i] != null){
                north[j] = n[i];
                j++;
            }
        }
        j = 0;
        // If a value in s is null, move it to the end of south
        for (int i = 0; i < 5; i++){
            if (s[i] != null){
                south[j] = s[i];
                j++;
            }
        }
        j = 0;
        // If a value in e is null, move it to the end of east
        for (int i = 0; i < 5; i++){
            if (e[i] != null){
                east[j] = e[i];
                j++;
            }
        }
        j = 0;
        // If a value in w is null, move it to the end of west
        for (int i = 0; i < 5; i++){
            if (w[i] != null){
                west[j] = w[i];
                j++;
            }
        }
    }
    
    // Print the road
    public static void printRoad(){
        String blank1 = "              | ";
        String blank2 = " |              ";
        String vmedian = " : ";
        String mid = "      ";
        
        String[] northTraffic = new String[5];
        String[] southTraffic = new String[5];
        String[] eastTraffic = new String[5];
        String[] westTraffic = new String[5];
        
        for (int i = 0; i < 5; i++){
            if (north[i] instanceof Car){
                northTraffic[i] = "C";
            } else if (north[i] instanceof Semi){
                northTraffic[i] = "S";
            } else if (north[i] instanceof Truck){
                northTraffic[i] = "T";
            } else if (north[i] instanceof Emergency){
                northTraffic[i] = "E";
            } else {
                northTraffic[i] = " ";
            }
        }
        
        for (int i = 0; i < 5; i++){
            if (south[i] instanceof Car){
                southTraffic[i] = "C";
            } else if (south[i] instanceof Semi){
                southTraffic[i] = "S";
            } else if (south[i] instanceof Truck){
                southTraffic[i] = "T";
            } else if (south[i] instanceof Emergency){
                southTraffic[i] = "E";
            } else {
                southTraffic[i] = " ";
            }
        }
        
        for (int i = 0; i < 5; i++){
            if (east[i] instanceof Car){
                eastTraffic[i] = " C ";
            } else if (east[i] instanceof Semi){
                eastTraffic[i] = " S ";
            } else if (east[i] instanceof Truck){
                eastTraffic[i] = " T ";
            } else if (east[i] instanceof Emergency){
                eastTraffic[i] = " E ";
            } else {
                eastTraffic[i] = "   ";
            }
        }
        
        for (int i = 0; i < 5; i++){
            if (west[i] instanceof Car){
                westTraffic[i] = " C ";
            } else if (west[i] instanceof Semi){
                westTraffic[i] = " S ";
            } else if (west[i] instanceof Truck){
                westTraffic[i] = " T ";
            } else if (west[i] instanceof Emergency){
                westTraffic[i] = " E ";
            } else {
                westTraffic[i] = "   ";
            }
        }
        
        System.out.println(blank1 + northTraffic[4] + vmedian + " " + blank2);
        System.out.println(blank1 + northTraffic[3] + vmedian + " " + blank2);
        System.out.println(blank1 + northTraffic[2] + vmedian + " " + blank2);
        System.out.println(blank1 + northTraffic[1] + vmedian + " " + blank2);
        System.out.println(blank1 + northTraffic[0] + vmedian + " " + blank2);
        System.out.println("--------------|       |------------");
        System.out.println("                " + mid + eastTraffic[0] + eastTraffic[1] + eastTraffic[2] + eastTraffic[3] + eastTraffic[4]);
        System.out.println("- - - - - - -          - - - - - - -");
        System.out.println(westTraffic[4] + westTraffic[3] + westTraffic[2] + westTraffic[1] + westTraffic[0] + mid + "                ");
        System.out.println("--------------|       |------------");
        System.out.println(blank1 + " " + vmedian + southTraffic[0] + blank2);
        System.out.println(blank1 + " " + vmedian + southTraffic[1] + blank2);
        System.out.println(blank1 + " " + vmedian + southTraffic[2] + blank2);
        System.out.println(blank1 + " " + vmedian + southTraffic[3] + blank2);
        System.out.println(blank1 + " " + vmedian + southTraffic[4] + blank2);
    }
    
    public void addVehicle(Vehicle[] direction, boolean isEmergency){
        if (direction[0] == null){
            if (isEmergency == true){
                direction[0] = new Emergency();
            } else {
                direction[0] = new Car();
            }
        } else if (direction[1] == null){
            if (isEmergency == true){
                direction[1] = new Emergency();
            } else {
                direction[1] = new Car();
            }
        } else if (direction[2] == null){
            if (isEmergency == true){
                direction[2] = new Emergency();
            } else {
                direction[2] = new Car();
            }
        } else if (direction[3] == null){
            if (isEmergency == true){
                direction[3] = new Emergency();
            } else {
                direction[3] = new Car();
            }
        } else if (direction[4] == null){
            if (isEmergency == true){
                direction[4] = new Emergency();
            } else {
                direction[4] = new Car();
            }
        } else {
            System.out.println("Road is full; cannot add vehicle.");
        }
        moveToFront(north);
        moveToFront(south);
        moveToFront(east);
        moveToFront(west);
    }
    
    // Check if no vehicles are left
    public boolean noVehiclesPresent(){
        boolean b = false;
        for (int i = 0; i < 5;){
            if (north[i] != null || south[i] != null || east[i] != null || west[i] != null){
                b = false;
                break;
            } else {
                b = true;
                running = false;
                break;
            }
        }
        return b;
    }
    
    // Check if there are any emergency vehicles 
    public boolean anyEmergencyPresent(){
        boolean b = false;
        for (int i = 0; i < 5; i++){
            if (north[i] instanceof Emergency || south[i] instanceof Emergency || east[i] instanceof Emergency || west[i] instanceof Emergency){
                b = true;
                break;
            } else {
                b = false;
            }
        }
        return b;
    }
    
    // Check if there are any emergency vehicles in a specific direction
    public boolean emergencyAt(Vehicle[] direction){
        boolean b = false;
        for (int i = 0; i < 5; i++){
            if (direction[i] instanceof Emergency){
                b = true;
                break;
            } else {
                b = false;
            }
        }
        return b;
    }
    
    // Make room at the front of the road for emergency vehicles by pushing all other vehicles back
    public void moveToFront(Vehicle[] direction){
    	int index = 0;
    	for (int i = 0; i < 5; i++) {
    		if (direction[i] instanceof Emergency) {
    			index = i;
    		}
    	}
    	
    	Vehicle temp = direction[index];
    	for (int j = index; j > 0; j--) {
    		direction[j] = direction[j-1];
    	}
    	
    	direction[0] = temp;
    }
    
    public void move() {
		// Check if there are any emergency vehicles. If so, run that direction.
		if (emergencyAt(north) || emergencyAt(south)) {
			NS = true;
		} else if (emergencyAt(east) || emergencyAt(west)) {
			EW = true;
		}
		
		if (NS) {
			for (int i = 0; i < 5; i++){
	            if (north[i] != null && south != null && north[i].getTime() >= south[i].getTime() && (time - north[i].getTime() >= 0)){
	                time -= north[i].getTime();
	                north[i] = null;
	                south[i] = null;
	            } else if (north[i] != null && south != null && south[i].getTime() > north[i].getTime() && (time - south[i].getTime() >= 0)) {
	            	time -= south[i].getTime();
	            	north[i] = null;
	            	south[i] = null;
	            } else if (north[i] != null && south[i] == null && (time - north[i].getTime() >= 0)) {
	            	time -= north[i].getTime();
	            	north[i] = null;
	            } else if (south[i] != null && north[i] == null && (time - south[i].getTime() >= 0)) {
	            	time -= south[i].getTime();
	            	south[i] = null;
	            } else {
	            	break;
	            }
	        }
	    	
    		
		} else {
			for (int j = 0; j < 5; j++){
	            if (east[j] != null && west != null && east[j].getTime() >= west[j].getTime() && (time - east[j].getTime() >= 0)){
	            	time -= east[j].getTime();
	                east[j] = null;
	                west[j] = null;
	            } else if (east[j] != null && west != null && west[j].getTime() > east[j].getTime() && (time - west[j].getTime() >= 0)) {
	            	time -= west[j].getTime();
	            	east[j] = null;
	            	west[j] = null;
	            } else if (east[j] != null && west[j] == null && (time - east[j].getTime() >= 0)) {
	            	time -= east[j].getTime();
	            	east[j] = null;
	            } else if (west[j] != null && east[j] == null && (time - west[j].getTime() >= 0)) {
	            	time -= west[j].getTime();
	            	west[j] = null;
	            } else {
	            	break;
	            }
	        }
    		
		}
		
		// If NS just ran, let EW run the next time, and vice versa.
		if (NS == true) {
			NS = false;
			EW = true;
		} else if (EW == true) {
			EW = false;
			NS = true;
		}
    }
}


/*
 * Keep track of how much time is left.
Start with the time constant.
When you remove a vehicle subtract its time from your running total.
If you remove 2 vehicles at once (because there’s one in both
active directions), only subtract the higher value one.
If a vehicle’s time is higher than the remaining time, do not remove it.
If both directions have a vehicle and only one has enough time,
remove only that one.
When time is 0, or there are no vehicles in the active directions, or there is not
enough time for the remaining vehicles, stop and switch the active directions.
If there are any vehicles left, add a new random one of any type.
If there are not, set running to false.
*/

