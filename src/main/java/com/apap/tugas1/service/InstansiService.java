package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.InstansiModel;

public interface InstansiService {
	InstansiModel getInstansi (long id);
	List<InstansiModel> getAllInstansi();
}
