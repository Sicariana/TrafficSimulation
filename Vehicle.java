public abstract class Vehicle{
    public int time;
    public boolean isEmergency;
    
    public Vehicle(){
        time = 0;
        isEmergency = false;
    }
    
    public Vehicle(int time, boolean isEmergency){
        this.time = time;
        this.isEmergency = isEmergency;
    }
    
    abstract int getTime();
    abstract boolean getIsEmergency();
}