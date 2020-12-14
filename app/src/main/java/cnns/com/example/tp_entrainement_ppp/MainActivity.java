package cnns.com.example.tp_entrainement_ppp;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int ADD_TASK_ID = 1;
    //private ListView lvTask;
    private RecyclerView rvTask;
    private TaskAdapter taskAdapter;

    static final String TUPLE_RECYCLER = "TUPLE";
    private ArrayList<TaskAdapter.TaskItem> itemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("####TEST CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTask = (RecyclerView) findViewById(R.id.rv_task);
        rvTask.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(this);
        rvTask.setAdapter(taskAdapter);

        // REMPLISSAGE AVEC LA BDD AU DEMARRAGE OU A CHAQUE ROTATE
        feedRecyclerAvecBase();

        // SWIPE
        ItemTouchHelper.SimpleCallback item_touch_helper_callback =

                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = (int)viewHolder.itemView.getTag();
                        taskAdapter.supprime(position);
                    }

                };
        new ItemTouchHelper(item_touch_helper_callback).attachToRecyclerView(rvTask);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        itemList = TaskAdapter.getM_data();
        outState.putParcelableArrayList(TUPLE_RECYCLER, itemList);
        System.out.println("####TEST SAVE");

    }
    protected void onStart() {
        super.onStart();
        System.out.println("####TEST START");
    }

    protected void onResume() {
        super.onResume();
        System.out.println("####TEST RESUME");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("####TEST PAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("####TEST STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("####TEST DESTROY");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch(itemId){
            case R.id.menu_todo_2:
//                Intent start_settings_activity = new Intent(this, SettingsActivity.class);
//                startActivity(start_settings_activity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void feedRecyclerAvecBase(){
        ListeAttenteDbHelper mDbHelper = new ListeAttenteDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                ListeAttenteContract.ListeAttenteEntry.TITLE_NAME,
                ListeAttenteContract.ListeAttenteEntry.PRIORITY_NAME

        };
        String selection = ListeAttenteContract.ListeAttenteEntry.TITLE_NAME;

// SI ON VEUT DESC OU ASC LE RES
        //String sortOrder = ListeAttenteContract.ListeAttenteEntry.TITLE_NAME + " DESC";

        Cursor cursor = db.query(
                ListeAttenteContract.ListeAttenteEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
//                selection,              // The columns for the WHERE clause
//                selectionArgs,          // The values for the WHERE clause
                null,
                null,
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        // TEST DE WHILE CLASSIQUE
        System.out.println("AAAAAcursor " + cursor);

        int indexTitre = 0;
        int indexPriorite = 0;
        while(cursor.moveToNext()) {
            indexTitre = cursor.getColumnIndexOrThrow("titre");
            indexPriorite = cursor.getColumnIndexOrThrow("val");
            String titre = cursor.getString(indexTitre);
            String priorite = cursor.getString(indexPriorite);
            System.out.println("AAAAA" + titre + priorite);
            taskAdapter.ajoute(titre, priorite);
        }
        cursor.close();
    }

    // SI ON VEUT DETRUIRE LA BDD A LA FIN
//    @Override
//    protected void onDestroy() {
//        ListeAttenteDbHelper mDbHelper = new ListeAttenteDbHelper(this);
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//        db.close();
//        super.onDestroy();
//    }
}