package info.upump.parswithrazdel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

import info.upump.parswithrazdel.db.PartDAO;
import info.upump.parswithrazdel.entity.Part;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView viewById = findViewById(R.id.text);

        Part[] parts = new Part[]{
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
        };

        String[] nameItem = new String[]{"item"};


        PartDAO partDAO = new PartDAO(this);
        for (int i = 0; i < parts.length; i++) {
            int id = (int) partDAO.save(parts[i]);
            parts[i].setId(id);
        }

        ReaderHTML readerItem;
        Reader reader = null;
        int res = 0;
        for (int i = 0; i < parts.length; i++) {
            readerItem = new ReaderHTML("item" + i, this);
            readerItem.setPart(parts[i]);
            reader = new Reader(this, readerItem);
            try {
                reader.startReader();

                reader.writeInDb();
                res+=reader.getSiseResult();

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
        viewById.setText(String.valueOf(res));

    }
}
