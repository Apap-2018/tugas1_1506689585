package com.apap.tugas1.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDb;
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
	private InstansiDb instansiDb;

	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDb.findDetailByNip(nip);
	}

	@Override
	public double getGajiPegawai(String nip) {
		PegawaiModel pegawai = pegawaiDb.findDetailByNip(nip);
		double gajiTerbesar = 0;
		
		for(JabatanModel jabatanModel : pegawai.getAllJabatan()) {
			if(jabatanModel.getGajiPokok() > gajiTerbesar) {
				gajiTerbesar = jabatanModel.getGajiPokok();
			}
		}
		
		double tunjanganPegawai = pegawai.getInstansi().getProvinsi().getPresentaseTunjangan();
		double gajiBersih = gajiTerbesar + ((tunjanganPegawai/100) * gajiTerbesar);
		return gajiBersih;
	}

	@Override
	public InstansiModel getInstansiDetailById(Long id) {
		return instansiDb.getOne(id);
	}

	@Override
	public PegawaiModel pegawaiTertua(long id) {
		List<PegawaiModel> allPegawai = pegawaiDb.findAllByInstansi_Id(id);
		PegawaiModel pegawaiTua = allPegawai.get(0);
		
		for(PegawaiModel pegawai : allPegawai) {
			if(pegawaiTua.getTanggalLahir().compareTo(pegawai.getTanggalLahir()) >0) {
				pegawaiTua = pegawai;
			}
		}
		return pegawaiTua;
	}

	@Override
	public PegawaiModel pegawaiTermuda(long id) {
		List<PegawaiModel> allPegawai = pegawaiDb.findAllByInstansi_Id(id);
		PegawaiModel pegawaiMuda = allPegawai.get(0);
		
		for(PegawaiModel pegawai : allPegawai) {
			if(pegawaiMuda.getTanggalLahir().compareTo(pegawai.getTanggalLahir()) <0) {
				pegawaiMuda = pegawai;
			}
		}
		return pegawaiMuda;
	}

	@Override
	public List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan) {
		return jabatan.getAllPegawai();
	}

	@Override
	public List<PegawaiModel> getPegawaiByProvinsi(ProvinsiModel provinsi) {
		List<PegawaiModel> allPegawai = pegawaiDb.findAll();
		List<PegawaiModel> listPegawai = new ArrayList<>();
        
        for (PegawaiModel pegawai : allPegawai) {
            if (pegawai.getInstansi().getProvinsi().equals(provinsi)) {
                listPegawai.add(pegawai);
            }
        }
        return listPegawai;
	}

	@Override
	public List<PegawaiModel> getPegawaiByInstansiDanJabatan(InstansiModel instansi, JabatanModel jabatan) {
        List<PegawaiModel> listInstansi = this.getPegawaiByInstansi(instansi);
		List<PegawaiModel> listJabatan = this.getPegawaiByJabatan(jabatan);
        
        listJabatan.retainAll(listInstansi);
        return listJabatan;
	}

	@Override
	public List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi) {
		return pegawaiDb.findAllByInstansi(instansi);
	}

	@Override
	public List<PegawaiModel> getPegawaiByProvinsiDanJabatan(ProvinsiModel provinsi, JabatanModel jabatan) {
        List<PegawaiModel> listJabatan = this.getPegawaiByJabatan(jabatan);
		List<PegawaiModel> listProvinsi = this.getPegawaiByProvinsi(provinsi);

        listProvinsi.retainAll(listJabatan);
        return listProvinsi;
	}
}
