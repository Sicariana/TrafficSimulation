public class Truck extends Vehicle{
    public Truck(){
        time = 3;
        isEmergency = false;
    }
    
    public int getTime(){
        return time;
    }
    
    public boolean getIsEmergency(){
        return isEmergency;
    }
}