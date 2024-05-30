package Additions;

import java.awt.*;

public class Puzzles {
    private Image img;
    private String question;
    private String answer;

    public Puzzles(Image img, String question, String answer) {
        this.img = img;
        this.question = question;
        this.answer = answer;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
