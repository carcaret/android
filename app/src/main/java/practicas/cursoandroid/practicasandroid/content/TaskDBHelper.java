package practicas.cursoandroid.practicasandroid.content;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import practicas.cursoandroid.practicasandroid.R;

public class TaskDBHelper extends SQLiteOpenHelper {

    private final Context context;

    public TaskDBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try (InputStream ddl = context.getResources().openRawResource(R.raw.ddl)) {
            Scanner read = new Scanner(ddl);
            StringBuilder sb = new StringBuilder();
            while(read.hasNext()) {
                sb.append(read.nextLine());
            }
            db.execSQL(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
