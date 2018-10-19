package com.apap.tugas1.repository;

import java.sql.Date;
import java.util.List;
import com.apap.tugas1.model.PegawaiModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Long>{
	PegawaiModel findDetailByNip(String nip);
}