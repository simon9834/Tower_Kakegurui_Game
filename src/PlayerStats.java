public class PlayerStats {
    private int staminaDecrease = 0;
    public enum Skins{
        GUY, GIRL, WEIRD
    }
    public int getStamina() {
        return staminaDecrease;
    }

    public void setStamina(int stamina) {
        this.staminaDecrease = stamina;
    }
}
