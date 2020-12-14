package cnns.com.example.tp_entrainement_ppp;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FakeData {
    public static void insert_fake_data(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(ListeAttenteContract.ListeAttenteEntry.TITLE_NAME, "Ballon de basket");
        cv.put(ListeAttenteContract.ListeAttenteEntry.PRIORITY_NAME, "4");

        list.add(cv);

        cv = new ContentValues();
        cv.put(ListeAttenteContract.ListeAttenteEntry.TITLE_NAME, "Déguisement Lutin");
        cv.put(ListeAttenteContract.ListeAttenteEntry.PRIORITY_NAME, "2");

        list.add(cv);

        cv = new ContentValues();
        cv.put(ListeAttenteContract.ListeAttenteEntry.TITLE_NAME, "Dinausaures");
        cv.put(ListeAttenteContract.ListeAttenteEntry.PRIORITY_NAME, "3");
        list.add(cv);

        cv = new ContentValues();
        cv.put(ListeAttenteContract.ListeAttenteEntry.TITLE_NAME, "Voiturette");
        cv.put(ListeAttenteContract.ListeAttenteEntry.PRIORITY_NAME, "1");
        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (ListeAttenteContract.ListeAttenteEntry.TITLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(ListeAttenteContract.ListeAttenteEntry.TITLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

    }
}