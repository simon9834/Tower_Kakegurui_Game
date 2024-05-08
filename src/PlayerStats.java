public class PlayerStats {
    private int staminaLeft = 100;
    private int speed;
    private String actualPosition;
    private enum Skins{
        GUY, GIRL, WEIRD
    }

    public int getStamina() {
        return staminaLeft;
    }

    public void setStamina(int stamina) {
        this.staminaLeft = stamina;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(String actualPosition) {
        this.actualPosition = actualPosition;
    }
}
