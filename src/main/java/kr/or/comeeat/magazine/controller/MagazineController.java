package kr.or.comeeat.magazine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.comeeat.magazine.model.service.MagazineService;

@Controller
@RequestMapping(value="/magazine")
public class MagazineController {
	@Autowired
	private MagazineService	magazineService; 
}
