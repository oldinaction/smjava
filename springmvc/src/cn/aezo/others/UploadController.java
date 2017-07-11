package cn.aezo.others;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


@Controller
public class UploadController {
	
	@RequestMapping("toUpload")
	public String toUpload() {
		return "upload";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") CommonsMultipartFile file) {//此处一定要写@RequestParam("file")
		System.out.println(file.getOriginalFilename());
		try {
			file.transferTo(new File("h:/demo/upload.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}

}
