package com.wenance.bitcoin.dao;

import com.wenance.bitcoin.model.Bitcoin;
import org.springframework.data.repository.CrudRepository;

public interface BitcoinDao extends CrudRepository<Bitcoin, Long> {

}
