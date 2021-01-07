package com.wenance;

import com.wenance.bitcoin.controller.BitcoinController;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("h2")
@TestPropertySource(properties = "application-h2.yml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
public class BitcoinControllerTest {

	@Autowired
	private BitcoinController bitcoinController;
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getMetrics() throws Exception {
		String fechaDesde = "2021-01-06 02:00:20.943";
		String fechaHasta = "2021-01-06 02:00:20.943";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/wenance/bitcoin/metrics")
				.queryParam("fechaDesde", fechaDesde)
				.queryParam("fechaHasta", fechaHasta)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.average", is(32092.1)))
				.andExpect(jsonPath("$.maxPrice", is(48138.1)))
				.andExpect(jsonPath("$.percentage", is(33.0)));
	}

	@Test
	public void getMetricsBadRequest() throws Exception {
		String fechaDesde = "2021-01-06";
		String fechaHasta = "2021-01-06";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/wenance/bitcoin/metrics")
				.queryParam("fechaDesde", fechaDesde)
				.queryParam("fechaHasta", fechaHasta)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void getBitcoinPriceByDate() throws Exception {
		String fecha = "2021-01-06 02:00:20.943";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/wenance/bitcoin")
				.queryParam("fecha", fecha)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.id", is(200)))
				.andExpect(jsonPath("$.lprice", is(32092.1)))
				.andExpect(jsonPath("$.curr1", is("BTC")))
				.andExpect(jsonPath("$.curr2", is("USD")))
				.andExpect(jsonPath("$.createDate", is(fecha)));
	}

	@Test
	public void getBitcoinPriceByDateNoContent() throws Exception {
		String fecha = "2021-01-05 02:00:20.943";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/wenance/bitcoin")
				.queryParam("fecha", fecha)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	public void getBitcoinPriceByDateBadRequest() throws Exception {
		String fecha = "2021-01-05";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/wenance/bitcoin")
				.queryParam("fecha", fecha)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void saveBitcoin() {
		bitcoinController.saveBitcoinPrice();
	}

}
