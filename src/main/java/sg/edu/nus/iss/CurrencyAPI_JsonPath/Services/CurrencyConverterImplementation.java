package sg.edu.nus.iss.CurrencyAPI_JsonPath.Services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.CurrencyAPI_JsonPath.Models.Country;
import sg.edu.nus.iss.CurrencyAPI_JsonPath.Repo.CurrencyConverterRepository;

@Service
public class CurrencyConverterImplementation implements CurrencyConverterInterface {

    @Autowired
    CurrencyConverterRepository cRepo;

    private String apiUrl = "https://free.currconv.com";

    @Value("${OPEN_CURRENCY_MAP}")
    String apiKey;

    @Override
    public List<Country> getAllCountries() {

        String url = createUrl_allCountries(); 
        String resp = accessAPI(url);

        List<Country> allCountries = cRepo.getCountries(resp);
        allCountries = allCountries.stream().sorted((c1,c2)->c1.getName().compareTo(c2.getName())).toList();

        return allCountries;
    }


    @Override
    public String accessAPI(String url){

        RequestEntity<Void> req = RequestEntity.get(url).build();
        RestTemplate rTemplate = new RestTemplate();
        ResponseEntity<String> resp = rTemplate.exchange(req, String.class);

        return resp.getBody();
    }

    @Override
    public String createUrl_allCountries() {
        
        String url = UriComponentsBuilder.fromUriString(apiUrl)
                    .path("/api/v7/countries")
                    .queryParam("apiKey", apiKey)
                    .toUriString();

        return url;
    }

    @Override
    public String createUrl_getConversionRates(String c1, String c2){

        String param1 = c1 + "_" + c2;
        String param2 = c2 + "_" + c1;

        String url = UriComponentsBuilder.fromUriString(apiUrl)
                    .path("/api/v7/convert")
                    .queryParam("q", param1 + "," + param2)
                    .queryParam("compact", "ultra")
                    .queryParam("apiKey", apiKey)
                    .toUriString();
        return url;
    }

    @Override
    public Country getCountryById(String id) {

        String url = createUrl_allCountries();
        String resp = accessAPI(url);
        Country country = cRepo.getCountryById(resp, id);
        
        return country;
    }

    @Override
    public Map<String,Double> getConversionRates(Country from, Country to) {
        
        String url = createUrl_getConversionRates(from.getCurrencyId(), to.getCurrencyId());
        String resp = accessAPI(url);

        Map<String,Double> ratesMap = cRepo.getRates(resp);        

        return ratesMap;
    }


    @Override
    public Double convert(Double d1, Double d2) {    

        return d1 * d2;
    }
    
}
