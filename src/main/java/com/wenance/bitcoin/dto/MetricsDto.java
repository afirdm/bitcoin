package com.wenance.bitcoin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MetricsDto {
	private BigDecimal average;
	private BigDecimal maxPrice;
	private BigDecimal percentage;
}
