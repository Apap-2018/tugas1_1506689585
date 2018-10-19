package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService {
	@Autowired
	private JabatanDb jabatanDb;

		@Override
		public JabatanDb getJabatanDb() {
			return jabatanDb;
		}

		@Override
		public JabatanModel getJabatanDetailById(Long id) {
			return jabatanDb.getOne(id);
		}
		
		@Override
		public List<JabatanModel> getAllJabatan() {
			return jabatanDb.findAll();
		}
		
		@Override
		public void addJabatan(JabatanModel jabatan) {
			jabatanDb.save(jabatan);
		}

		@Override
		public void ubahJabatan(JabatanModel jabatan) {
			JabatanModel ubahJabatan = jabatanDb.getOne(jabatan.getId());
			ubahJabatan.setNama(jabatan.getNama());
			ubahJabatan.setGajiPokok(jabatan.getGajiPokok());
			ubahJabatan.setDeskripsi(jabatan.getDeskripsi());
		}

		@Override
		public void hapusJabatanDetailById(Long id) {
			jabatanDb.deleteById(id);
		}

}
