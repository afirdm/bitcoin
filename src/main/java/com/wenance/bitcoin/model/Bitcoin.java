package com.wenance.bitcoin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Bitcoin")
public class Bitcoin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdSeq")
	@SequenceGenerator(name = "IdSeq", sequenceName = "SEQ_BITCOIN", allocationSize = 1)
	private Long id;

	@Column(name = "lprice", nullable = false)
	private String price;

	@Column(name = "curr1", nullable = false)
	private String currency1;

	@Column(name = "curr2", nullable = false)
	private String currency2;

	@Column(name = "create_date", nullable = false)
	private Date createDate;

}
