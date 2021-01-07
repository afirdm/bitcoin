package com.wenance.bitcoin.controller;

import com.wenance.bitcoin.dto.BitcoinDto;
import com.wenance.bitcoin.service.BitcoinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@Api(tags="Wenance", description = "Operaciones de consulta y registro de información de los bitcoins")
@RestController
@RequestMapping("/wenance")
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

	@GetMapping("/bitcoin")
	@ApiOperation(value = "Obtener el precio del bitcoin en cierto timestamp")
	@ApiResponses(value={
			@ApiResponse(message = "En caso de obtener el precio del bitcoin se retorna HttpStatus.OK", code = 200),
			@ApiResponse(message = "En caso de no obtener el precio del bitcoin se retorna HttpStatus.NO_CONTENT", code = 204),
			@ApiResponse(message = "En caso de un error no definido se retorna HttpStatus.BAD_REQUEST", code = 400)
	})
	public ResponseEntity<?> getBitcoinPriceByDate(@RequestParam(name="fecha") Timestamp fecha) {
		BitcoinDto dto = new BitcoinDto();
		dto = bitcoinService.getBitcoinPriceByDate(fecha);

		if (dto == null) {
			return new ResponseEntity<>("No se encontro el precio del bitcoin", HttpStatus.NO_CONTENT);
		}
		LOGGER.trace("BitcoinController - getBitcoinPriceByDate response [{}]", dto);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("/bitcoin/max_price")
	@ApiOperation(value = "Obtener el valor máximo almacenado para toda la serie temporal disponible")
	@ApiResponses(value={
			@ApiResponse(message = "En caso de obtener el valor máximo del bitcoin se retorna HttpStatus.OK", code = 200),
			@ApiResponse(message = "En caso de un error no definido se retorna HttpStatus.BAD_REQUEST", code = 400)
	})
	public ResponseEntity<?> getBitcoinMaxPrice() {
		String maxPrice = bitcoinService.getBitcoinMaxPrice();
		LOGGER.trace("BitcoinController - getBitcoinMaxPrice response [{}]", maxPrice);
		return new ResponseEntity<>(maxPrice, HttpStatus.OK);
	}
}
