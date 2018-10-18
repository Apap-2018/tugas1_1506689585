package com.apap.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.JabatanService;

@Controller
public class JabatanController {
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String addJabatan(Model model) {
		model.addAttribute("jabatan", new JabatanModel());
		return  "add-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String submitAddJabatan(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatanService.addJabatan(jabatan);
		model.addAttribute("jabatan", jabatan);
		return  "added-jabatan";
	}
	
	@RequestMapping(value= "/jabatan/view", method = RequestMethod.GET)
	private String lihatJabatan(@RequestParam("id") Long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id);
		model.addAttribute("jabatan", jabatan);
		return "see-jabatan";
	}
	
	@RequestMapping(value= "/jabatan/ubah", method = RequestMethod.GET)
	private String ubahJabatan(@RequestParam("id") Long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id);
		model.addAttribute("jabatan", jabatan);
		return "change-jabatan";
	}
	
	@RequestMapping(value= "/jabatan/ubah", method = RequestMethod.POST)
	private String submitUbahJabatan(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatan.setId(jabatan.getId());
		jabatanService.ubahJabatan(jabatan);
		return "submit-change-jabatan";
	}
}
