package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;

@Controller
public class PegawaiController {
	@Autowired
	private JabatanService jabatanService;

	@Autowired
	private PegawaiService pegawaiService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String home(Model model) {
		List<JabatanModel> allJabatan = jabatanService.getAllJabatan();
		model.addAttribute("allJabatan", allJabatan);
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
}
