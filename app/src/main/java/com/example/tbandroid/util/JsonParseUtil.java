package com.example.tbandroid.util;

import com.example.tbandroid.model.Pais;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParseUtil {
    public static List<Pais> getListaLaboratorio(String json) throws JSONException {
        List<Pais> paises = new ArrayList<Pais>();
        JSONArray array = new JSONArray(json);
        for(int i=0;i < array.length();i++) {
            JSONObject laboratorio = (JSONObject) array.get(i);
            laboratorio.get("nome_fantasia");
        }
        return paises;
    }
    public static List<Pais> getListaLaboratorioGson(String json) throws JSONException {
        Gson gson = new Gson();
        List<Pais> paises = new ArrayList<Pais>();
        return paises;
    }
}
