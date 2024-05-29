public class PlayerStats {
    private int staminaDecrease = 0;
    private String actualPosition;
    private enum Skins{
        GUY, GIRL, WEIRD
    }

    public int getStamina() {
        return staminaDecrease;
    }

    public void setStamina(int stamina) {
        this.staminaDecrease = stamina;
    }

    public String getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(String actualPosition) {
        this.actualPosition = actualPosition;
    }
}
