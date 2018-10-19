package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;

public interface JabatanService {
	JabatanDb getJabatanDb();
	void addJabatan(JabatanModel jabatan);
	List<JabatanModel> getAllJabatan();
	void ubahJabatan(JabatanModel jabatan);
	void hapusJabatanDetailById(Long id);
	JabatanModel getJabatanDetailById(Long id);
}