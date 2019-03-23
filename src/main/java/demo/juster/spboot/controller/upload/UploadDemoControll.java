package demo.juster.spboot.controller.upload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/upload")
public class UploadDemoControll {

	static final String UPLOADED_FOLDER = "/src/main/resources/static/upload/";
	
	@RequestMapping("/up")
	public String index() {
	    return "/upload/index";
	}
	
	@RequestMapping("/ups")
	public String ups() {
	    return "/upload/multifiles";
	}
	
	@RequestMapping("/status")
	public String status() {
	    return "/upload/status";
	}
	

	@PostMapping("/file") 
	public String singleFileUpload(@RequestParam("file") MultipartFile file,
	            RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
		redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
		return "redirect:/upload/status";
		}
		try {
		// Get the file and save it somewhere
		byte[] bytes = file.getBytes();
		// UPLOADED_FOLDER 文件本地存储地址
		File rootPath = new File(ResourceUtils.getURL("classpath:").getPath());
		rootPath = rootPath.getParentFile().getParentFile().getAbsoluteFile();
		System.out.println(rootPath.getAbsolutePath());
		Path path = Paths.get(rootPath.getAbsolutePath()+UPLOADED_FOLDER + file.getOriginalFilename());
		System.out.println(path);
		Files.write(path, bytes);
		
		redirectAttributes.addFlashAttribute("message",
		"You successfully uploaded '" + file.getOriginalFilename() + "'");
		
		} catch (IOException e) {
		e.printStackTrace();
		}
		return "redirect:/upload/status";
	}
	
	

	@PostMapping("/uploadMultifiles")
	public String moreFileUpload(@RequestParam("file") MultipartFile[] files,
	                               RedirectAttributes redirectAttributes) {
	    if (files.length==0) {
	        redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
	        return "redirect:/upload/status";
	    }
	    for(MultipartFile file:files){
	        try {
	            byte[] bytes = file.getBytes();
	            File rootPath = new File(ResourceUtils.getURL("classpath:").getPath());
	            rootPath = rootPath.getParentFile().getParentFile().getAbsoluteFile();
	    		System.out.println(rootPath.getAbsolutePath());
	            Path path = Paths.get(rootPath.getAbsolutePath()+UPLOADED_FOLDER + file.getOriginalFilename());
	            System.out.println(path);
	            Files.write(path, bytes);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    redirectAttributes.addFlashAttribute("message", "You successfully uploaded all");
	    return "redirect:/upload/status";
	}
	
}
