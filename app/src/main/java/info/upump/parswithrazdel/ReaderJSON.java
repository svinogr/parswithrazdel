package info.upump.parswithrazdel;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import info.upump.parswithrazdel.entity.Answer;
import info.upump.parswithrazdel.entity.Part;
import info.upump.parswithrazdel.entity.Question;


public class ReaderJSON extends ReaderItem {

    public ReaderJSON(String name, Context context) {
        super(name, context);
    }

    @Override
    List<Question> getResultReader() {
        return parse();
    }

    private Part part;

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    private List<Question> parse() {
        JSONArray jsonArray = null;
        List<Question> list = new ArrayList<>();
        try {
            jsonArray = new JSONArray(read(name));


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Question question = new Question();
                question.setBody(jsonObject.get("question").toString());
                String img = (String) jsonObject.get("img");
                // if (!img.equals("имя картинки")) question.setImg(jsonObject.get("img").toString());
                question.setImg(jsonObject.get("img").toString());
                question.setPart(part);
//                setCategory("капитан");
                question.setComment(null);

                JSONArray answers = jsonObject.getJSONArray("answers");
                Set<Answer> answerSet = new HashSet<>();

                for (int k = 0; k < answers.length(); k++) {
                    Answer answer = new Answer();
                    JSONObject jsonObjectAnswer = (JSONObject) answers.get(k);
                    answer.setBody(jsonObjectAnswer.get("answer").toString());
                    if (jsonObjectAnswer.get("good").toString().equals("true")) {
                        answer.setRight(1);
                    } else answer.setRight(-1);
                    answer.setQuestion(question);
                    answerSet.add(answer);
                }
                for (Answer answer : answerSet) {
                    question.getAnswers().add(answer);
                }

                list.add(question);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
        return list;
    }


    private String read(String name) {
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(assetManager.open(name), "utf-8"));
            String line;


            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();
    }
}
