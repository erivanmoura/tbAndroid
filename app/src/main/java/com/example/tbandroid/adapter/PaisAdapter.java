package com.example.tbandroid.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tbandroid.R;
import com.example.tbandroid.dao.PaisDatabase;
import com.example.tbandroid.model.Pais;

import java.util.List;

public class PaisAdapter extends BaseAdapter {

    final private String TAG = "PaisAdapter";
    List<Pais> paisList;
    Activity activity;
    Context context;

    TextView tv_pais;
    TextView tv_continente;
    ImageView iv_visit;

    public PaisAdapter(List<Pais> paisList, Activity activity) {
        this.paisList = paisList;
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }

    public void clearList(){
        this.paisList.clear();
    }

    @Override
    public int getCount(){
        return paisList.size();
    }

    @Override
    public Object getItem(int position){
        return paisList.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        @SuppressLint("ViewHolder") View view = activity.getLayoutInflater().inflate(R.layout.activity_list_item, parent, false);
        final Pais pais = (Pais) getItem(position);
        tv_pais = view.findViewById(R.id.tv_pais);
        tv_continente = view.findViewById(R.id.tv_continente);
        iv_visit = view.findViewById(R.id.iv_visit);

        tv_pais.setText(pais.getName());
        tv_continente.setText(pais.getRegion());

        new CheckVisitAsyncTask(context).execute(pais);

        if (position % 2 == 0) {
            view.setBackgroundColor(Color.rgb(220,240,230));
            view.invalidate();
        }

        return view;
    }
    class CheckVisitAsyncTask extends AsyncTask<Pais, Void, Void> {
        Context context;

        CheckVisitAsyncTask(Context context){ this.context = context; }

        @Override
        protected Void doInBackground(Pais... paises) {
            Pais pais = paises[0];
            List<Pais> list = PaisDatabase.getInstance(context).getDao().getPais(pais.getName());
            if (list.size() > 0) {
                iv_visit.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_fav_on_24dp));
            } else {
                iv_visit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_fav_off_24dp));
            }
            iv_visit.invalidate();
            return null;
        }
    }

}
