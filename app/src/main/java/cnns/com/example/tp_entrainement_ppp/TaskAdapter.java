package cnns.com.example.tp_entrainement_ppp;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<cnns.com.example.tp_entrainement_ppp.TaskAdapter.TaskItem_ViewHolder> {
    private static ArrayList<TaskItem> m_data = new ArrayList<>();
    private Context m_context;

    public void ajoute(String title, String priorite){
        // TRAITEMENT ORIGINAL
        cnns.com.example.tp_entrainement_ppp.TaskAdapter.TaskItem ti = new cnns.com.example.tp_entrainement_ppp.TaskAdapter.TaskItem();

        ti.title = title;
        ti.priorite = priorite;

        m_data.add(ti); //méthode native dans l'adapteur
        // car le tableau démarre à 0
        this.notifyItemInserted(m_data.size()-1);

    }
    public void ajouteEnBase(String title, String priorite) {
        // TRAITEMENT DE L'AJOUT EN BDD
        ListeAttenteDbHelper mDbHelper = new ListeAttenteDbHelper(m_context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        if(db == null){
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(ListeAttenteContract.ListeAttenteEntry.TITLE_NAME, title);
        cv.put(ListeAttenteContract.ListeAttenteEntry.PRIORITY_NAME, priorite);

        try
        {
            db.beginTransaction();
            db.insert(ListeAttenteContract.ListeAttenteEntry.TABLE_NAME, null, cv);
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally {
            db.endTransaction();
        }
    }

    public static ArrayList<TaskItem> getM_data() {
        return m_data;
    }

    static class TaskItem implements Parcelable {
        String title;
        String priorite;

        public TaskItem() {}
        protected TaskItem(Parcel in) {
            title = in.readString();
            priorite = in.readString();
        }

        public static final Creator<TaskItem> CREATOR = new Creator<TaskItem>() {
            @Override
            public TaskItem createFromParcel(Parcel in) {
                return new TaskItem(in);
            }

            @Override
            public TaskItem[] newArray(int size) {
                return new TaskItem[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(title);
            parcel.writeString(priorite);
        }
    }
    public TaskAdapter(Context m_context) {
        this.m_data = new ArrayList<TaskItem>();
        this.m_context = m_context;
    }

    public void supprime(int position){
        System.out.println("A SUPPRIMER "+ m_data.get(position).priorite);
        System.out.println("A SUPPRIMER "+ m_data.get(position).title);

        supprimeEnBase(m_data.get(position).title);
        m_data.remove(position);
        this.notifyItemRemoved(position);
        this.notifyItemRangeChanged(position, getItemCount()- position);
    }

    public void supprimeToutVisuellement(){
        do{
            for(int i = 0; i < m_data.size(); i++){
                m_data.remove(i);
                this.notifyItemRemoved(i);
                this.notifyItemRangeChanged(i, m_data.size()-1);
            }
        }while (m_data.size() != 0);
        System.out.println("SIZZZE" + m_data.size());
    }

    public void supprimeEnBase(String title) {
        ListeAttenteDbHelper mDbHelper = new ListeAttenteDbHelper(m_context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        if(db == null){
            return;
        }


// ON PROTEGE LA TRANSACTION : meilleur pratique
        try {
            db.beginTransaction();
            db.delete(ListeAttenteContract.ListeAttenteEntry.TABLE_NAME,
                    "TITRE=?",
                    new String[]{title});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
        @NonNull
    @Override
    public TaskItem_ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(m_context).inflate(R.layout.ligne_task, parent, false);
        return new TaskItem_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( TaskItem_ViewHolder holder, int position) {
        //Récupèrer item
        TaskItem item = m_data.get(position);
        //mettre item
        holder.txtv_titre.setText(item.title);
        holder.txtv_priorite.setText(item.priorite);

        //stocker l'id avec la vue pour delete
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return m_data.size();
    }


    public class TaskItem_ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtv_titre;
        private TextView txtv_priorite;

        public TaskItem_ViewHolder(View itemView){
            super(itemView);
            txtv_titre = (TextView) itemView.findViewById(R.id.textNomTask);
            txtv_priorite = (TextView) itemView.findViewById(R.id.priorityTask);
        }
    }
}
