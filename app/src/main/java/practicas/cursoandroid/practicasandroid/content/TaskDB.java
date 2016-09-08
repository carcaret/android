package practicas.cursoandroid.practicasandroid.content;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TaskDB implements AutoCloseable {

    private final TaskDBHelper helper;
    private SQLiteDatabase db;

    public TaskDB(Context context) {
        helper = new TaskDBHelper(context, "tasks", null, 1);
    }

    public void open() {
        db = helper.getWritableDatabase();
    }

    @Override
    public void close() {
        db.close();
    }

    public void insert(String name, String desc) {
        ContentValues fields = new ContentValues();
        fields.put("NAME", name);
        fields.put("DESC", desc);
        fields.put("DATE", System.currentTimeMillis());
        db.insert("TASK", null, fields);
    }

    public Cursor findAll() {
        return db.query("TASK", null, null, null, null, null, null);
    }
}
