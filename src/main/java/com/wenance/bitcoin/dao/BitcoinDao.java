package com.wenance.bitcoin.dao;

import com.wenance.bitcoin.model.Bitcoin;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

public interface BitcoinDao extends CrudRepository<Bitcoin, Long> {

    Optional<Bitcoin> findByCreateDate(Date fecha);
}
