package sg.edu.nus.iss.CurrencyAPI_JsonPath.Models;

import java.util.Map;
import java.util.Set;

import jakarta.json.JsonObject;
import net.minidev.json.JSONObject;

public class Country {

    private String alpha3;
    private String currencyId;
    private String currencyName;
    private String currencySymbol;
    private String id;
    private String name;

    public String getAlpha3() {
        return alpha3;
    }
    public void setAlpha3(String alpha3) {
        this.alpha3 = alpha3;
    }
    public String getCurrencyId() {
        return currencyId;
    }
    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }
    public String getCurrencySymbol() {
        return currencySymbol;
    }
    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCurrencyName() {
        return currencyName;
    }
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
    @Override
    public String toString() {
        return "Country [currencyId=" + currencyId + ", id=" + id + ", name=" + name + "]";
    }

    public static Country createModel(Map<String, String> countryMap){
        Country country = new Country();

        country.setAlpha3(countryMap.get("alpha3"));
        country.setCurrencyId(countryMap.get("currencyId"));
        country.setCurrencyName(countryMap.get("currencyName"));
        country.setCurrencyName(countryMap.get("currencyName"));
        country.setCurrencySymbol(countryMap.get("currencySymbol"));
        country.setId(countryMap.get("id"));
        country.setName(countryMap.get("name"));

        return country;

    }

    

    
    
}
