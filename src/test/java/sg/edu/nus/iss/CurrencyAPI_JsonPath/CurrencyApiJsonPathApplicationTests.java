package sg.edu.nus.iss.CurrencyAPI_JsonPath;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import sg.edu.nus.iss.CurrencyAPI_JsonPath.Controllers.CurrencyConverterController;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyApiJsonPathApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private CurrencyConverterController cControl;

	@Test
	void contextLoads() throws Exception {

		assertNotNull(cControl);
	}

	@Test
	void shouldReturnCountry() throws Exception {
		this.mvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Singapore")));
	}

	@Test
	void checkConversion() throws Exception{
		this.mvc.perform(get("/rates?from=SG&to=JP&amount=500&flag=true")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Singapore"))).andExpect(content().string(containsString("Japan")))
				.andExpect(result -> assertFalse(result.getResponse().getContentAsString().contains("Korea")));
	}



	

}
