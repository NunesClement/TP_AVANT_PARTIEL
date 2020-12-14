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
    private TextView message;
    String strEtat="";
    static final String TUPLE_RECYCLER = "TUPLE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("####TEST CREATE");
        super.onCreate(savedInstanceState);

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
}