package com.wenance.bitcoin.service;

import com.wenance.bitcoin.dao.BitcoinDao;
import com.wenance.bitcoin.dto.BitcoinDto;
import com.wenance.bitcoin.model.Bitcoin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BitcoinService {

	@Value("${api.wenance.url}")
	private String urlWenance;

	@Value("${api.wenance.endpoint}")
	private String endpointGetBitcoin;

	@Autowired
	private BitcoinDao dao;

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(BitcoinService.class);


	public void saveBitcoinPrice() {
		BitcoinDto dto = getBitcoinPrice();
		Bitcoin bitcoin = Bitcoin.builder()
				.price(dto.getLprice())
				.currency1(dto.getCurr1())
				.currency2(dto.getCurr2())
				.createDate(new Timestamp(System.currentTimeMillis()))
				.build();
		dao.save(bitcoin);
	}

	public BitcoinDto getBitcoinPrice() {
		URI url = UriComponentsBuilder.fromHttpUrl(urlWenance)
				.path(endpointGetBitcoin)
				.build().encode().toUri();

		LOGGER.debug("getBitcoinPrice - url [{}]", url);

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);

		BitcoinDto result = null;
		try {
			ResponseEntity<BitcoinDto> response = restTemplate.exchange(url,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<BitcoinDto>() {
					});
			if (response.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
				LOGGER.debug("getBitcoinPrice - without results");
			}
			result = response.getBody();
		} catch (Exception e) {

			LOGGER.error("getBitcoinPrice - service error [" + url + "]", e);
		}

		return result;
	}

	public BitcoinDto getBitcoinPriceByDate(Timestamp fecha) {
		return dao.findByCreateDate(fecha).stream().map(bitcoin -> {
			return BitcoinDto.builder()
					.id(bitcoin.getId())
					.lprice(bitcoin.getPrice())
					.curr1(bitcoin.getCurrency1())
					.curr2(bitcoin.getCurrency2())
					.createDate(bitcoin.getCreateDate())
					.build();
		}).findFirst().orElse(null);
	}
}
