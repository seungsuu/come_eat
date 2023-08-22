package kr.or.comeeat.magazine.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.or.comeeat.FileUtil;
import kr.or.comeeat.magazine.model.service.MagazineService;
import kr.or.comeeat.magazine.model.vo.Magazine;
import kr.or.comeeat.magazine.model.vo.MagazineFile;

@Controller
@RequestMapping(value="/magazine")
public class MagazineController {
	@Autowired
	private MagazineService	magazineService;
	@Autowired
	private FileUtil fileUtil;
	@Value("${file.root}")
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
	public String write(Magazine m, MultipartFile[] upfile, Model model) {
		ArrayList<MagazineFile> fileList=null;
		if(!upfile[0].isEmpty()) {
			fileList = new ArrayList<MagazineFile>();
			String savepath = root+"magazine/";
			for(MultipartFile file : upfile) {
				String filename = file.getOriginalFilename();
				String filepath=fileUtil.getFilepath(savepath, filename);
				File uploadFile = new File(savepath+filepath);
				try {
					file.transferTo(uploadFile);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MagazineFile mf = new MagazineFile();
				mf.setMFilepath(filepath);
				fileList.add(mf);
			}
		}
		int result = magazineService.insertMagazine(m, fileList); 
		if((fileList == null&&result==1)||(fileList!=null && result==(fileList.size()+1))){
			model.addAttribute("title", "Magazine");
			model.addAttribute("msg", "매거진 작성 완료!");
			model.addAttribute("icon", "success");
		}else {
			model.addAttribute("title", "에러에ㅓㄹ에러");
			model.addAttribute("msg", "에러당");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc","/magazine/list");
		return "common/msg";
	}
	
	
	@GetMapping(value="/view")
	public String magazineView(int magazineNo, @SessionAttribute(required = false) Magazine m, Model model) {
		int memberNo = (m == null)? 0 : m.getMemberNo();
		m  = magazineService.selectOneMagazine(magazineNo, memberNo);
		return "magazine/view";
	}
	
	@ResponseBody
	@PostMapping(value="/editor", produces = "plain/text;charset=utf-8")
	public String editorUpload(MultipartFile file) {
		String savepath = root+"editor/";
		String filepath = fileUtil.getFilepath(savepath, file.getOriginalFilename());
		File image = new File(savepath+filepath);
		try {
			file.transferTo(image);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/editor/"+filepath;
	}
		
}
