public class Car extends Vehicle{
    public Car(){
        time = 2;
        isEmergency = false;
    }
    
    public int getTime(){
        return time;
    }
    
    public boolean getIsEmergency(){
        return isEmergency;
    }
}