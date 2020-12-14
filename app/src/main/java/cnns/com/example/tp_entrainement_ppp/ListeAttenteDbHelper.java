package cnns.com.example.tp_entrainement_ppp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListeAttenteDbHelper extends SQLiteOpenHelper {
    // Si le schéma de la base changer, il faut incrémenter cette valeur
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "taches_bdd.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ListeAttenteContract.ListeAttenteEntry.TABLE_NAME + " (" +
                    ListeAttenteContract.ListeAttenteEntry._ID + " INTEGER PRIMARY KEY," +
                    ListeAttenteContract.ListeAttenteEntry.TITLE_NAME + " TEXT, "
                    + ListeAttenteContract.ListeAttenteEntry.PRIORITY_NAME+ " TEXT)";

    public ListeAttenteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        FakeData.insert_fake_data(db);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Constante String SQL_DELETE_ENTRIES à définir ..
        String SQL_DELETE_ENTRIES = "";
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVers) {
        onUpgrade(db, oldVersion, newVers);
    }

}