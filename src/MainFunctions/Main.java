package MainFunctions;

public class Main {
    public static void main(String[] args) {
        try {
            new MyJFrame();
        } catch (Exception e) {
            System.out.println("this wont break :P ");
            e.printStackTrace();
        }
    }
}