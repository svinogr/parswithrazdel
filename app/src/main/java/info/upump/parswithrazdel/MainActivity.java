package info.upump.parswithrazdel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import info.upump.parswithrazdel.db.AnswerDAO;
import info.upump.parswithrazdel.db.PartDAO;
import info.upump.parswithrazdel.db.QuestionDAO;
import info.upump.parswithrazdel.db.QuestionDAO2;
import info.upump.parswithrazdel.entity.Answer;
import info.upump.parswithrazdel.entity.Part;
import info.upump.parswithrazdel.entity.Question;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView viewById = findViewById(R.id.text);

     /*   Part[] parts = new Part[]{
                new Part("Б.2.1", "Эксплуатация объектов нефтяной и газовой промышленности"),
                new Part("Б.2.2", "Ремонт нефтяных и газовых скважин"),
                new Part("Б.2.3", "Проектирование объектов нефтегазодобычи"),
                new Part("Б.2.4", "Ремонтные, монтажные и пусконаладочные работы на опасных производственных объектах нефтегазодобычи"),
                new Part("Б.2.6", "Бурение нефтяных и газовых скважин"),
                new Part("Б.2.7", "Магистральные нефтепроводы и нефтепродуктопроводы"),
                new Part("Б.2.8", "Магистральные газопроводы"),
                new Part("Б.2.9", "Аттестация руководителей и членов аттестационных комиссий организаций, эксплуатирующих магистральные трубопроводы для транспортировки жидкого аммиака"),
                new Part("Б.2.10", "Аттестация руководителей и специалистов организаций, эксплуатирующих нефтепромысловые трубопроводы для транспорта нефти и газа"),
                new Part("Б.2.11", "Производство, хранение и выдача сжиженного природного газа на ГРС МГ и АГНКС"),
                new Part("Б.2.12", "Подземные хранилища газа в пористых пластах"),
                new Part("Б.2.13", "Проектирование, Строительство, Реконструкция и Капитальный ремонт оъектов нефтяной и газовой промышленности"),
                new Part("Б.2.15", "Аттестация руководителей и специалистов организаций, эксплуатирующих компрессорные установки с поршневыми компрессорами, работающими на взрывоопасных и вредных газах"),
                new Part("Б.2.16", "Аттестация руководителей и специалистов организаций, эксплуатирующих стационарные компрессорные установки, воздухопроводы и газопроводы"),
                new Part("Б.2.18", "Разведка и разработка морских месторождений углеводородного сырья"),
        };     */

    /*    Part[] parts = new Part[]{
                new Part("Б.4.1", "Обогащение полезных ископаемых"),
                new Part("Б.4.3", "Разработка месторождений полезных ископаемых открытым способом"),
                new Part("Б.4.5", "Проектирование опасных производственных объектов горной промышленности")
        };  */
        Part[] parts = new Part[]{
                new Part("Б.12.1", "Взрывные работы в подземных выработках и на поверхности рудников (объектах горнорудной и нерудной  промышленности), угольных и сланцевых шахт, опасных (не опасных) по газу или пыли, и специальные взрывные работы")
//                new Part("Б.12.2", "Взрывные работы на открытых горных разработках и специальные взрывные работы")
//                new Part("Б.11.3", "Изготовление, монтаж, наладка, ремонт, техническое освидетельствование, реконструкция и эксплуатация технических устройств (машин и оборудования), применяемых на объектах хранения и переработки растительного сырья")
//                new Part("Г.3.3", " Эксплуатация гидроэлектростанций"
        };
   /*   Part[] parts = new Part[]{
                new Part("Б.7.1", "Эксплуатация сетей газораспределения и газопотребления"),
                new Part("Б.7.2", "Эксплуатация объектов, использующих сжиженные углеводородные газы"),
                new Part("Б.7.6", "Проектирование сетей газораспределения и газопотребления"),
              new Part("Б.7.8", "Технический надзор, строительство, реконструкция, капитальный ремонт объектов газораспределения и газопотребления"),
              new Part("Б.7.9", "Эксплуатация автогазозаправочных станций газомоторного топлива")
        };*/
     /*         Part[] parts = new Part[]{
                new Part("Б.8.21", "Эксплуатация котлов (паровых, водогрейных, с органическими и неорганическими теплоносителями) на опасных производственных объектах"),
                new Part("Б.8.22", "Эксплуатация трубопроводов пара и горячей воды на опасных производственных объектах"),
                new Part("Б.8.23", "Эксплуатация сосудов, работающих под давлением, на опасных производственных объектах")
        };*/
      /*  Part[] parts = new Part[]{
                new Part("Б.9.31", "Эксплуатация опасных производственных объектов, на которых применяются подъемные сооружения, предназначенные для подъема и перемещения грузов"),
                new Part("Б.9.32", "Эксплуатация опасных производственных объектов, на которых применяются подъемные сооружения, предназначенные для подъема и транспортировки людей"),
                new Part("Б.9.33", "Монтаж, наладка, ремонт, реконструкция или модернизация подъемных сооружений в процессе эксплуатации опасных производственных объектов")
        };*/

//        String[] nameItem = new String[]{"item"};


        PartDAO partDAO = new PartDAO(this);
        for (int i = 0; i < parts.length; i++) {
            int id = (int) partDAO.save(parts[i]);
            parts[i].setId(id);
        }

        RederMore readerItem;
        Reader reader = null;
        int res = 0;
        for (int i = 0; i < parts.length; i++) {
            readerItem = new RederMore("item" + i, this);
            readerItem.setPart(parts[i]);
            reader = new Reader(this, readerItem);
            try {
                reader.startReader();

                // reader.writeInDb();
                res += reader.getSiseResult();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

      /*  ReaderHTML readerItem = new ReaderHTML("item", this);
        readerItem.setPart(part);
        //        ReaderItem readerItem = new ReaderJSON("item.json", this);
        Reader reader = new Reader(this, readerItem);

        reader.startReader();

        reader.writeInDb();
    } catch(
    IOException e)

    {
        e.printStackTrace();
    }catch(
    JSONException e)

    {
        e.printStackTrace();
    }*/
        /*viewById.setText(String.valueOf(res));*/

//dt();
        System.out.println("end " + res);


    }

    private void dt() {
        int n = 9;
        QuestionDAO questionDAO1 = new QuestionDAO(this);
        AnswerDAO answerDAO = new AnswerDAO(this);
        List<Question> questions;
        /*     for(int i = 1 ; i < 10; i++) {*//**/
        String name = "questionnaire" + n + ".db";
        System.out.println(0 + " " + name);
        readBd(name);
        QuestionDAO2 questionDAO = new QuestionDAO2(this, name);
        questions = questionDAO.getQuestions();
        System.out.println(1 + " " + questions.size());


        for (Question q : questions) {
            int io = (int) questionDAO1.save(q, 11);
            q.setId(io);
            //   System.out.println(q.getAnswers().size());
            if (io == 0) {
                continue;
            }
            for (Answer a : q.getAnswers()) {
                a.setQuestion(q);
                answerDAO.save(a);

            }
        }
        /*   }*/
    }


    private void readBd(String name) {
        //    String DB_PATH = "/data/data/dt.org.deltatest/databases/" + DATABASE_NAME;        InputStream myInput = null;
        OutputStream myOutput = null;
        InputStream myInput = null;

        try {

            try {
                myInput = getAssets().open(name);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            String outFileName = "/data/data/info.upump.parswithrazdel/databases/" + name;
           /* File file = new File(outFileName);
            file.createNewFile();*/
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

}
