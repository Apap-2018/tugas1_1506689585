package com.apap.tugas1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class PegawaiController {
	@Autowired
	private JabatanService jabatanService;

	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private InstansiService instansiService;

	@Autowired
	private ProvinsiService provinsiService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String home(Model model) {
		List<JabatanModel> allJabatan = jabatanService.getAllJabatan();
		List<InstansiModel> allInstansi = instansiService.getAllInstansi();
		model.addAttribute("allJabatan", allJabatan);
		model.addAttribute("allInstansi", allInstansi);
		return "home";
	}
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String lihatPegawai(@RequestParam(value = "nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		model.addAttribute("pegawai", pegawai);
		double gajiBersih = pegawaiService.getGajiPegawai(nip);
		model.addAttribute("gajiBersih", gajiBersih);
		return "show-pegawai";
	}
	
	@RequestMapping(value= "/pegawai/termuda-tertua", method = RequestMethod.GET)
	private String lihatTermudaTertua(@RequestParam(value="id") Long id, Model model) {
		PegawaiModel pegawaiTermuda = pegawaiService.pegawaiTermuda(id);
		PegawaiModel pegawaiTertua = pegawaiService.pegawaiTertua(id);

		double gajiPegawaiMuda = pegawaiService.getGajiPegawai(pegawaiTermuda.getNip());
		double gajiPegawaiTua = pegawaiService.getGajiPegawai(pegawaiTertua.getNip());
		
		model.addAttribute("pegawaiTermuda", pegawaiTermuda);
		model.addAttribute("pegawaiTertua", pegawaiTertua);
		
		model.addAttribute("gajiPegawaiMuda", gajiPegawaiMuda);
		model.addAttribute("gajiPegawaiTua", gajiPegawaiTua);
		return "see-tertua-termuda";
	}
	
    @RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
    private String findPegawaiSubmit(@RequestParam(value="idProvinsi", required=false) Long idProvinsi, @RequestParam(value="idJabatan", required=false) Long idJabatan, @RequestParam(value="idInstansi", required=false) Long idInstansi, Model model) {
        List<JabatanModel> allJabatan = jabatanService.getAllJabatan();
        model.addAttribute("allJabatan", allJabatan);
    	
    	List<ProvinsiModel> allProvinsi = provinsiService.getAll();
        model.addAttribute("allProvinsi", allProvinsi);

        List<InstansiModel> allInstansi = instansiService.getAllInstansi();
        model.addAttribute("allInstansi", allInstansi);

        List<PegawaiModel> pegawai = new ArrayList<>();
        
        if      (idProvinsi != null && idInstansi == null) pegawai = pegawaiService.getPegawaiByProvinsi(provinsiService.getProvinsi(idProvinsi));
        else if (idJabatan  != null && idInstansi == null) pegawai = pegawaiService.getPegawaiByJabatan(jabatanService.getJabatanDetailById(idJabatan));
        else if (idProvinsi != null && idJabatan  != null) pegawai = pegawaiService.getPegawaiByProvinsiDanJabatan(provinsiService.getProvinsi(idProvinsi), jabatanService.getJabatanDetailById(idJabatan));
        else if (idJabatan  == null && idInstansi != null) pegawai = pegawaiService.getPegawaiByInstansi(instansiService.getInstansi(idInstansi));
        else if (idJabatan  != null && idInstansi != null) pegawai = pegawaiService.getPegawaiByInstansiDanJabatan(instansiService.getInstansi(idInstansi), jabatanService.getJabatanDetailById(idJabatan));

        model.addAttribute("pegawai", pegawai);  
        return "search-pegawai";   
    }
}
