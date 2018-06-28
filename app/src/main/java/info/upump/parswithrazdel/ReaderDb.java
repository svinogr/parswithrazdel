package info.upump.parswithrazdel;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import info.upump.parswithrazdel.db.QuestionDAO;
import info.upump.parswithrazdel.entity.Question;

public class ReaderDb extends ReaderItem {
    public ReaderDb(String name, Context context) {
        super(name, context);
    }

    private void readBd() {
        //    String DB_PATH = "/data/data/dt.org.deltatest/databases/" + DATABASE_NAME;        InputStream myInput = null;
        OutputStream myOutput = null;
        InputStream myInput = null;

        try {

            try {
                myInput = context.getAssets().open(name);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            String outFileName = "/data/data/info.upump.parswithrazdel/databases/" + name;
            myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    List<Question> getResultReader() {
        QuestionDAO questionDAO = new QuestionDAO(context);

        List<Question> questions = questionDAO.getQuestions();
        return questions;

    }
}
