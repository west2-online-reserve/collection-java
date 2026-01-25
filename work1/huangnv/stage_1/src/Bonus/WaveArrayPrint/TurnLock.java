package Bonus.WaveArrayPrint;

public class TurnLock {
    private boolean isFirstTurn;
    public TurnLock(boolean isFirstTurn) {
        this.isFirstTurn = isFirstTurn;
    }
    public synchronized boolean isFirstTurn(){
        return isFirstTurn;
    }
    public synchronized void setFirstTurn(boolean isFirstTurn) {
        this.isFirstTurn = isFirstTurn;


    }
}
