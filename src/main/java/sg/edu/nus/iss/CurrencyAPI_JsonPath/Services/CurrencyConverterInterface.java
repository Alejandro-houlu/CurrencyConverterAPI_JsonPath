package sg.edu.nus.iss.CurrencyAPI_JsonPath.Services;

import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.CurrencyAPI_JsonPath.Models.Country;

public interface CurrencyConverterInterface {

    public String accessAPI(String url);

    public List<Country> getAllCountries();

    public String createUrl_allCountries();

    public String createUrl_getConversionRates(String c1, String c2);

    public Country getCountryById(String id);

    public Map<String,Double> getConversionRates(Country from, Country to);

    public Double convert(Double d1, Double d2);


    
}
