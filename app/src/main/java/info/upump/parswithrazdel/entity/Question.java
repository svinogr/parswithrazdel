package info.upump.parswithrazdel.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by explo on 23.09.2017.
 */

public class Question {
    private int id;
    private int nunber;
    private String body;
    private String img;
    private List<Answer> answers = new ArrayList();
    private String comment;
    private Part part;

    public Question() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getNunber() {
        return this.nunber;
    }

    public void setNunber(int nunber) {
        this.nunber = nunber;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
