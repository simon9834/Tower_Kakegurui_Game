package MainFunctions;

public class MainFunctions {

    private int currentFloor = 1;

    /**
     * this method returns the floor the player currently is on
     *
     * @return currentFloor
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * this method changes the variable currentFloor to the variable that was passed in to the method
     *
     * @param currentFloor
     */
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
}
