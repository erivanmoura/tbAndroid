package com.example.tbandroid.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Pais implements Parcelable{


    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
//    @SerializedName("topLevelDomain")
//    @Expose
//    private List<String> topLevelDomain = null;
    @SerializedName("alpha2Code")
    @Expose
    private String alpha2Code;
    @SerializedName("alpha3Code")
    @Expose
    private String alpha3Code;
//    @SerializedName("callingCodes")
//    @Expose
//    private List<String> callingCodes = null;
    @SerializedName("capital")
    @Expose
    private String capital;
//    @SerializedName("altSpellings")
//    @Expose
//    private List<String> altSpellings = null;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("subregion")
    @Expose
    private String subregion;
    @SerializedName("population")
    @Expose
    private Integer population;
//    private Integer population;
//    @SerializedName("latlng")
//    @Expose
//    private List<Double> latlng = null;
    @SerializedName("demonym")
    @Expose
    private String demonym;
    @SerializedName("area")
    @Expose
    private Double area;
//    @SerializedName("gini")
//    @Expose
//    private Object gini;
//    @SerializedName("timezones")
//    @Expose
//    private List<String> timezones = null;
//    @SerializedName("borders")
//    @Expose
//    private List<Object> borders = null;
    @SerializedName("nativeName")
    @Expose
    private String nativeName;
    @SerializedName("numericCode")
    @Expose
    private String numericCode;
//    @SerializedName("currencies")
//    @Expose
//    private List<Currency> currencies = null;
//    @SerializedName("languages")
//    @Expose
//    private List<Language> languages = null;
//    @SerializedName("translations")
//    @Expose
//    private Translations translations;
    @SerializedName("flag")
    @Expose
    private String flag;
//    @SerializedName("regionalBlocs")
//    @Expose
//    private List<RegionalBloc> regionalBlocs = null;
    @SerializedName("cioc")
    @Expose
    private String cioc;
    private String visit;

    protected Pais(Parcel in) {
        id = in.readInt();
        name = in.readString();
//        topLevelDomain = in.createStringArrayList();
        alpha2Code = in.readString();
        alpha3Code = in.readString();
//        callingCodes = in.createStringArrayList();
        capital = in.readString();
//        altSpellings = in.createStringArrayList();
        region = in.readString();
        subregion = in.readString();
        if (in.readByte() == 0) {
            population = null;
        } else {
            population = in.readInt();
        }
        demonym = in.readString();
        if (in.readByte() == 0) {
            area = null;
        } else {
            area = in.readDouble();
        }
//        timezones = in.createStringArrayList();
        nativeName = in.readString();
        numericCode = in.readString();
        flag = in.readString();
        cioc = in.readString();
        visit = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
//        dest.writeStringList(topLevelDomain);
        dest.writeString(alpha2Code);
        dest.writeString(alpha3Code);
//        dest.writeStringList(callingCodes);
        dest.writeString(capital);
//        dest.writeStringList(altSpellings);
        dest.writeString(region);
        dest.writeString(subregion);
        if (population == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(population);
        }
        dest.writeString(demonym);
        if (area == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(area);
        }
//        dest.writeStringList(timezones);
        dest.writeString(nativeName);
        dest.writeString(numericCode);
        dest.writeString(flag);
        dest.writeString(cioc);
        dest.writeString(visit);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pais> CREATOR = new Creator<Pais>() {
        @Override
        public Pais createFromParcel(Parcel in) {
            return new Pais(in);
        }

        @Override
        public Pais[] newArray(int size) {
            return new Pais[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }
*/
    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }
/*
    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }
*/
    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
/*
    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }
*/
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
/*
    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }
*/
    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
/*
    public Object getGini() {
        return gini;
    }

    public void setGini(Object gini) {
        this.gini = gini;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<Object> getBorders() {
        return borders;
    }

    public void setBorders(List<Object> borders) {
        this.borders = borders;
    }
*/
    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    /*
    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }
*/
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
/*
    public List<RegionalBloc> getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(List<RegionalBloc> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }
*/
    public String getCioc() {
        return cioc;
    }

    public void setCioc(String cioc) {
        this.cioc = cioc;
    }

    public Pais() {
        this.visit = "";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }
}
