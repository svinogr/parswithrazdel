package info.upump.parswithrazdel;

import android.content.Context;
import android.content.res.AssetManager;

import java.util.List;

import info.upump.parswithrazdel.entity.Question;


public abstract class ReaderItem {
    protected String name;
    protected Context context;
    protected AssetManager assetManager;

    public ReaderItem(String name, Context context) {
        this.name = name;
        this.context = context;
        assetManager = context.getAssets();
    }

    abstract List<Question> getResultReader();
}
