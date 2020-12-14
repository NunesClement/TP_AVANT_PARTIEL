package cnns.com.example.tp_entrainement_ppp;

import android.provider.BaseColumns;

public final class ListeAttenteContract {
    private ListeAttenteContract() {}
    /* Classe imbriquée pour la table */
    public static class ListeAttenteEntry implements BaseColumns {
        public static final String TABLE_NAME = "cadeaux";
        public static final String TITLE_NAME = "titre";
        public static final String PRIORITY_NAME = "priorite";
    }
}
