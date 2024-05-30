package Additions;

public class Riddles {
    private String riddle;
    private String answer;

    public Riddles(String riddle, String answer) {
        this.riddle = riddle;
        this.answer = answer;
    }

    public String getRiddle() {
        return riddle;
    }

    public void setRiddle(String riddle) {
        this.riddle = riddle;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
