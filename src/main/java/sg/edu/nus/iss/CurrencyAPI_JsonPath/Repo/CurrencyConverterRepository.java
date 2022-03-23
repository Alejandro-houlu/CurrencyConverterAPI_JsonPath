package sg.edu.nus.iss.CurrencyAPI_JsonPath.Repo;


import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.JsonContext;

import org.springframework.stereotype.Repository;

import jakarta.json.JsonArray;
import net.minidev.json.JSONArray;
import sg.edu.nus.iss.CurrencyAPI_JsonPath.Models.Country;


@Repository
public class CurrencyConverterRepository {


    public List<Country> getCountries(String resp){
        
        String jsonCountries = "$['results'][*]";
        DocumentContext jsonContext = JsonPath.parse(resp);
        List<Map<String, String>> countries = jsonContext.read(jsonCountries);

        List<Country> allCountries = countries.stream().map(m->Country.createModel(m)).toList();


        return allCountries;
    }

    public Country getCountryById(String resp, String id){

        String query = String.format("$['results']['%s']",id);
        DocumentContext jsonContext = JsonPath.parse(resp);
        Map<String, String> countryMap = jsonContext.read(query);


        return Country.createModel(countryMap);

    }

    public Map<String,Double> getRates(String resp){

        DocumentContext jsonContext = JsonPath.parse(resp);
        Map<String, Double> ratesMap = jsonContext.read("$");

        return ratesMap;

    }
    
}
