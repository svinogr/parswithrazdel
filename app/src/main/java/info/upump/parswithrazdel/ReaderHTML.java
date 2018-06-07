package info.upump.parswithrazdel;

import android.content.Context;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import info.upump.parswithrazdel.entity.Answer;
import info.upump.parswithrazdel.entity.Part;
import info.upump.parswithrazdel.entity.Question;

public class ReaderHTML extends ReaderItem {


    public ReaderHTML(String name, Context context) {
        super(name, context);
    }
    private Part part;

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    @Override
    List<Question> getResultReader() {
        return parse();
    }

    private List<Question> parse() {
        List<Question> list = new ArrayList<>();
        Question question = null;
        String strForParse = read(name+ ".html");

        Document document = Jsoup.parse(strForParse);
        Elements aClass = document.body().getElementsByAttributeValue("class", "show-question watupro-unresolved");

        for (Element element : aClass) {
            question = new Question();
            question.setPart(part);
//            question.setBody(element.getElementsByTag("strong").text());
            question.setBody(element.getElementsByAttributeValue ("class","show-question-content").text());
            Elements elementsByAttributeValue = element.getElementsByTag("li");
            Answer answer = null;
            for (Element element1 : elementsByAttributeValue) {
                answer = new Answer();
                if (element1.hasClass("answer correct-answer")) {
                    answer.setBody(element1.text());
                    answer.setRight(1);
                } else {
                    answer.setBody(element1.text());
                    answer.setRight(0);
                }
                question.getAnswers().add(answer);
            }
            list.add(question);
        }
        return list;
    }


    private String read(String name) {
        String s;
        InputStreamReader scanner = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            scanner = new InputStreamReader(assetManager.open(name), "windows-1251");
            bufferedReader = new BufferedReader(scanner);

            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s);

            }
            scanner.close();
            bufferedReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
