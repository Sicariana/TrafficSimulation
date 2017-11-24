public class Emergency extends Vehicle{
    public Emergency(){
        time = 1;
        isEmergency = true;
    }
    
    public int getTime(){
        return time;
    }
    
    public boolean getIsEmergency(){
        return isEmergency;
    }
}