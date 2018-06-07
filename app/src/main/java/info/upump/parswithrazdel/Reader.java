package info.upump.parswithrazdel;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.upump.parswithrazdel.db.AnswerDAO;
import info.upump.parswithrazdel.db.QuestionDAO;
import info.upump.parswithrazdel.entity.Answer;
import info.upump.parswithrazdel.entity.Question;


public class Reader {
    private ReaderItem iReader;
    private Context context;
    private List<Question> list = new ArrayList<>();
    private AssetManager am;
    private static String alphaRu = new String("абвгдеёжзиыйклмнопрстуфхцчшщьэюя");
    private static String[] alphaEn = {"a", "b", "v", "g", "d", "e", "yo", "g", "z", "i", "y", "i",
            "k", "l", "m", "n", "o", "p", "r", "s", "t", "u",
            "f", "h", "tz", "ch", "sh", "sh", "'", "e", "yu", "ya"};

    public Reader(Context context, ReaderItem iReader) {
        this.iReader = iReader;
        this.context = context;

    }

    public void startReader() throws IOException, JSONException {
        list = iReader.getResultReader();
    }

    public void writeInDb() {

        QuestionDAO questionDAO = new QuestionDAO(context);
        AnswerDAO answerDAO = new AnswerDAO(context);
        for (Question question : list) {
            int id = (int) questionDAO.save(question);
            question.setId(id);
            for (Answer answer : question.getAnswers()) {
                answer.setQuestion(question);
                answerDAO.save(answer);
            }
        }
    }
    public int getSiseResult(){
        return  list.size();
    }

}
