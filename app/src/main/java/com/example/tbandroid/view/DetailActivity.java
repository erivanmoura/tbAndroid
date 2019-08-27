package com.example.tbandroid.view;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.tbandroid.R;
import com.example.tbandroid.dao.PaisDatabase;
import com.example.tbandroid.model.Pais;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    final String TAG = "DetailActivity";
    Context context;
    Pais pais;

    TextView tv_nome_pais;
    TextView tv_capital;
    TextView tv_regiao;
    TextView tv_populacao;
    TextView tv_latitude_longitude;
    TextView tv_moeda;
    FloatingActionButton fab_visit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this.getApplicationContext();
        pais = intent.getParcelableExtra("pais");

        tv_nome_pais = findViewById(R.id.tv_nome_pais);
        tv_capital = findViewById(R.id.tv_capital);
        tv_regiao = findViewById(R.id.tv_regiao);
        tv_populacao = findViewById(R.id.tv_populacao);
        tv_latitude_longitude = findViewById(R.id.tv_latitude_longitude);
        tv_moeda = findViewById(R.id.tv_moeda);
        fab_visit = findViewById(R.id.fab_visit);

        tv_nome_pais.setText(pais.getName());
        tv_capital.setText(pais.getCapital());
        tv_regiao.setText(pais.getRegion());
        tv_populacao.setText(pais.getPopulation().toString());

        new GetPaisAsynTask(this.context).execute(pais.getName());

        fab_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pais.getVisit() == "X"){
                    pais.setVisit("");
                    fab_visit.setImageResource(R.drawable.ic_fav_off_24dp);
                }else {
                    pais.setVisit("X");
                    fab_visit.setImageResource(R.drawable.ic_fav_on_24dp);
                }
                new SetVisitAsyncTask(context).execute(pais);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.back:
                this.finish();
//return true;
//        }
        return super.onOptionsItemSelected(item);
    }


    class GetPaisAsynTask extends AsyncTask<String, Void, List<Pais>> {
        Context context;

        GetPaisAsynTask(Context context){
            this.context = context;
        }

        @Override
        protected List<Pais> doInBackground(String... strings) {
            return PaisDatabase.getInstance(this.context).getDao().getPais(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Pais> paises) {
            if (paises.size() > 0) {
                fab_visit.setImageResource(R.drawable.ic_fav_on_24dp);
                pais.setVisit("X");
            }
            super.onPostExecute(paises);
        }
    }

    class SetVisitAsyncTask extends AsyncTask<Pais, Void, Void> {
        Context context;

        SetVisitAsyncTask(Context context){ this.context = context; }

        @Override
        protected Void doInBackground(Pais... paises) {
            Pais pais = paises[0];
            if (pais.getVisit() == "X") {
                PaisDatabase.getInstance(context).getDao().insert(pais);
            } else {
                List<Pais> list = PaisDatabase.getInstance(context).getDao().getPais(pais.getName());
                for(Pais c: list) {
                    PaisDatabase.getInstance(context).getDao().delete(c);
                }
            }

            List<Pais> pList = PaisDatabase.getInstance(context).getDao().getAllPaises();

            Log.d(TAG, "Mostrar Lista");
            for(Pais c : pList){
                Log.d(TAG, "-->" + c.toString());
            }
            return null;
        }
    }



}
