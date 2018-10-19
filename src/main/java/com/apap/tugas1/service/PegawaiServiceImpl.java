package com.apap.tugas1.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.JabatanDb;
import com.apap.tugas1.repository.PegawaiDb;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
	@Autowired
	private PegawaiDb pegawaiDb;
	
	@Autowired
	private JabatanDb jabatanDb;
	
	@Autowired
	private PegawaiModel pegawaiModel;
	
	@Autowired
	private JabatanModel jabatanModel;

	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDb.findDetailByNip(nip);
	}

	@Override
	public double getGajiPegawai(String nip) {
		PegawaiModel pegawai = pegawaiDb.findDetailByNip(nip);
		double gajiTerbesar = 0;
		
		for(int i = 0; i <= pegawai.getAllJabatan().size();i++) {
			if(jabatanModel.getGajiPokok() > gajiTerbesar) {
				gajiTerbesar = jabatanModel.getGajiPokok();
			}
		}
		
		double tunjanganPegawai = pegawai.getInstansi().getProvinsi().getPresentaseTunjangan();
		double gajiBersih = gajiTerbesar + ((tunjanganPegawai/100) * gajiTerbesar);
		return gajiBersih;
	}
}
