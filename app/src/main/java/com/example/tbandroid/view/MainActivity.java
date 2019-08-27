package com.example.tbandroid.view;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tbandroid.R;
import com.example.tbandroid.adapter.PaisAdapter;
import com.example.tbandroid.dao.PaisDatabase;
import com.example.tbandroid.model.Pais;
import com.example.tbandroid.util.NetworkUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MainActivity2";
    TextView tv_mensagem_central;
    ProgressBar pb_loading;
    ListView lv_paises;
    View updateView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_mensagem_central = findViewById(R.id.tv_mensagem_central);
        pb_loading = findViewById(R.id.pb_loading);
        lv_paises = findViewById(R.id.lv_paises);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"*** on Start ***");
        if (updateView != null) {
            updateView.invalidate();
            Log.d(TAG,"View not null. Name: ");
        } else {
            Log.d(TAG, "View null");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.africa:
                listPaises("Africa");
                break;
            case R.id.americas:
                listPaises("Americas");
                break;
            case R.id.asia:
                listPaises("Asia");
                break;
            case R.id.europe:
                listPaises("Europe");
                break;
            case R.id.oceania:
                listPaises("Oceania");
                break;
            case R.id.paises_a_visitar:
                listVisit();
                break;
            case R.id.menu_clear:
                clearText();
        }
        return super.onOptionsItemSelected(item);
    }

    public void clearText() {
        Log.d(TAG,"*** CLEAR ***");
        tv_mensagem_central.setText("");
        tv_mensagem_central.setVisibility(View.VISIBLE);
        lv_paises.setVisibility(View.GONE);
    }

    // lista de todos os países da região...
    public void listPaises(String regiao) {
        try{
            Log.d(TAG,"method listPaises. região: " + regiao);
            URL url = NetworkUtil.buildUrl(regiao);
            CallWebAsyncTask task = new CallWebAsyncTask();
            task.execute(url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    class CallWebAsyncTask extends AsyncTask<URL, Void, List<Pais>> {

        @Override
        protected List<Pais> doInBackground(URL... urls) {
            URL url = urls[0];
            Log.d(TAG, "url utilizada: " + url.toString());
            Object json = "";
            try {
                json = NetworkUtil.getResponseFromHttpUrl(url);
                Log.d(TAG, "async task retorno: " + json);
            } catch (IOException e) {
                Log.d(TAG, "pegou erro.."+e);
                e.printStackTrace();
            }
            TypeToken<List<Pais>> token = new TypeToken<List<Pais>>() {
            };
            if (json == null) {
                throw new AssertionError("objeto não pode ser nulo");
            }
            return new Gson().fromJson(json.toString(), token.getType());
        }

        @Override
        protected void onPreExecute() {
            showLoading();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Pais> paises) {
            if (paises == null) {
                tv_mensagem_central.setText("Houve um erro. Verifique sua internet.");
            } else {
                listarPaises(paises);
            }
            stopLoading();
        }
    }

    // lista de todos os países marcados como destinos para visitar...
    public void listVisit() {
        try{
            Log.d(TAG,"method listVisit");
            VisitAsyncTask task = new VisitAsyncTask(this);
            task.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    class VisitAsyncTask extends AsyncTask<Void, Void, List<Pais>>{
        Context context;

        VisitAsyncTask(Context context){
            this.context = context;
        }

        @Override
        protected List<Pais> doInBackground(Void... voids) {
            return PaisDatabase.getInstance(context).getDao().getAllPaises();
        }

        @Override
        protected void onPostExecute(List<Pais> paises) {
            if (paises.size() == 0) {
                clearList();
                tv_mensagem_central.setText("sem destinos para visitar!");
            } else {
                tv_mensagem_central.setText(null);
                listarPaises(paises);
            }

            super.onPostExecute(paises);
        }
    }

    public void showLoading() {
        tv_mensagem_central.setVisibility(View.GONE);
        lv_paises.setVisibility(View.GONE);
        pb_loading.setVisibility(View.VISIBLE);
    }

    public void stopLoading() {
        if (lv_paises.getCount() == 0) {
            tv_mensagem_central.setVisibility(View.VISIBLE);
            lv_paises.setVisibility(View.GONE);
        } else {
            tv_mensagem_central.setVisibility(View.GONE);
            lv_paises.setVisibility(View.VISIBLE);
        }
        pb_loading.setVisibility(View.GONE);
    }

    public ListView listarPaises(final List<Pais> paises){
        Log.d(TAG, "listarPaises");
        PaisAdapter adapter = new PaisAdapter(paises, this);
        lv_paises.setAdapter(adapter);
        lv_paises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "clicou no item...");
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("pais", (Parcelable) paises.get(position));
                startActivity(intent);
//                updateView = lv_paises.getChildAt(position);
            }

        });
        return lv_paises;
    }

    public void clearList(){
        lv_paises.setAdapter(null);
    }

}
