package com.wenance.bitcoin.dao;

import com.wenance.bitcoin.model.Bitcoin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface BitcoinDao extends CrudRepository<Bitcoin, Long> {

    Optional<Bitcoin> findByCreateDate(Date fecha);

    @Query(value="SELECT MAX(bitcoin.price)" +
            "  FROM Bitcoin bitcoin")
    String getMaxPrice();
}
