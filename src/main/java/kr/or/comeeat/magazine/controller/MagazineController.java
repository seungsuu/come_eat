package kr.or.comeeat.magazine.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.comeeat.FileUtil;
import kr.or.comeeat.magazine.model.service.MagazineService;
import kr.or.comeeat.magazine.model.vo.Magazine;

@Controller
@RequestMapping(value="/magazine")
public class MagazineController {
	@Autowired
	private MagazineService	magazineService;
	@Autowired
	private FileUtil fileUtil;
	@Value("${file.root]")
	private String root;
	
	@GetMapping(value="/list")
	private String magazineList() {
		int totalCount = magazineService.totalCount();
		return "magazine/magazineList";
	}
	
	
	
	@GetMapping(value="/magazineWriteFrm")
	public String magazineWriteFrm() {
		return "magazine/magazineWriteFrm";
	}

	@PostMapping(value="/write")
	public String write(Magazine m, MultipartFile imageFile, Model model) {
		String savepath = root+"magazine/";
		String filepath = fileUtil.getFilepath(savepath, imageFile.getOriginalFilename());
		m.setFilepath(filepath);
		File upFile = new File(savepath+filepath);
		try {
			imageFile.transferTo(upFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		int result = magazineService.insertMagazine(m); 
		if(result>0) {
			model.addAttribute("title", "작성완료");
			model.addAttribute("msg", "게시글 작성 완료!");
			model.addAttribute("icon", "success");
		}else {
			model.addAttribute("title", "작성실패");
			model.addAttribute("msg", "에러에러");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc", "/magazine/list");
		return "common/msg";
	}
	
		
}
