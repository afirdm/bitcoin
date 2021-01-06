package com.wenance.bitcoin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BitcoinDto {
	private Long id;
	private String lprice;
	private String curr1;
	private String curr2;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp createDate;
}
