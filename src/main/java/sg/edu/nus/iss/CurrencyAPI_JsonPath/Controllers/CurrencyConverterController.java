package sg.edu.nus.iss.CurrencyAPI_JsonPath.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.CurrencyAPI_JsonPath.Models.Country;
import sg.edu.nus.iss.CurrencyAPI_JsonPath.Repo.CurrencyConverterRepository;
import sg.edu.nus.iss.CurrencyAPI_JsonPath.Services.CurrencyConverterInterface;

@Controller
@RequestMapping(path="/", produces = "text/html")
public class CurrencyConverterController {

    @Autowired
    CurrencyConverterInterface cService;

    
    @GetMapping("/")
    public String getAllCountries(Model model){

        List<Country> allCountries= cService.getAllCountries();
        model.addAttribute("countryList", allCountries);
        
        return "index";
    }

    @GetMapping("/rates")
    public String convert(@RequestParam String from,
    @RequestParam String to, @RequestParam Double amount, @RequestParam Boolean flag, Model model){

        Country fromCountry = cService.getCountryById(from);
        Country toCountry = cService.getCountryById(to);

        Map<String,Double> ratesMap = cService.getConversionRates(fromCountry, toCountry);

        if(flag){        
        Double result = cService.convert(ratesMap.get(fromCountry.getCurrencyId() + "_" + toCountry.getCurrencyId()), amount);
        model.addAttribute("fromCountry", fromCountry);
        model.addAttribute("toCountry", toCountry);
        model.addAttribute("result", String.format("%.2f",result));
        model.addAttribute("amount", amount);
        }

        if(!flag){    
        Double result = cService.convert(ratesMap.get(toCountry.getCurrencyId() + "_" + fromCountry.getCurrencyId()), amount);
        model.addAttribute("fromCountry", toCountry);
        model.addAttribute("toCountry", fromCountry);
        model.addAttribute("result", String.format("%.2f",result));
        model.addAttribute("amount", amount);
        }

        return "result";
    }

}
