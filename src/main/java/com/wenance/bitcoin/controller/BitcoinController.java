package com.wenance.bitcoin.controller;

import com.wenance.bitcoin.service.BitcoinService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="Wenance", description = "Operaciones de consulta y registro de información de los bitcoins")
@RestController
public class BitcoinController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BitcoinController.class);

	@Autowired
	private BitcoinService bitcoinService;

	@Scheduled(fixedDelay=5000)
	public void saveBitcoinPrice() {
		LOGGER.info("INICIO BitcoinController - saveBitcoinPrice");
		bitcoinService.saveBitcoinPrice();
		LOGGER.info("FIN BitcoinController - saveBitcoinPrice");
	}
}
