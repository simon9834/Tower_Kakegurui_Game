import java.awt.*;

public class TowerFloor {

    private int currentFloor;
    private Image floorImage;

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Image getFloorImage() {
        return floorImage;
    }

    public void setFloorImage(Image floorImage) {
        this.floorImage = floorImage;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
}
