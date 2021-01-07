package com.wenance.bitcoin.dao;

import com.wenance.bitcoin.model.Bitcoin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface BitcoinDao extends CrudRepository<Bitcoin, Long> {

    Optional<Bitcoin> findByCreateDate(Timestamp fecha);

    @Query(value="SELECT MAX(bitcoin.price)" +
            "  FROM Bitcoin bitcoin")
    String getMaxPrice();

    List<Bitcoin> findByCreateDateBetween(Timestamp fechaDesde, Timestamp fechaHasta);
}
