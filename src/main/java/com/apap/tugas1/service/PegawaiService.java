package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;

public interface PegawaiService {
	PegawaiModel getPegawaiDetailByNip(String nip);
	InstansiModel getInstansiDetailById(Long id);
	double getGajiPegawai(String nip);
	
	PegawaiModel pegawaiTertua(long id);
	PegawaiModel pegawaiTermuda(long id);
	
    List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan);
    List<PegawaiModel> getPegawaiByProvinsi(ProvinsiModel provinsi);
    List<PegawaiModel> getPegawaiByInstansiDanJabatan(InstansiModel instansi, JabatanModel jabatan);
    List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi);
    List<PegawaiModel> getPegawaiByProvinsiDanJabatan(ProvinsiModel provinsi, JabatanModel jabatan);
}
