package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.ProvinsiModel;

public interface ProvinsiService {
	List<ProvinsiModel> getAll();
	ProvinsiModel getProvinsi(long id);
	ProvinsiModel getProvinsi(String nama);
}
