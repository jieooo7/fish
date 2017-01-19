package com.fish.view;

import com.fish.config.CommonData;
import com.fish.jpa.user.AdminRespository;
import com.fish.model.entity.user.AdminInfo;
import com.fish.securety.AESHelper;
import com.fish.storage.FileSystemStorageService;
import com.fish.storage.StorageFileNotFoundException;
import com.fish.storage.StorageProperties;
import com.fish.storage.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;
    @Autowired
    private StorageService videaStorageService;


    @Autowired
    private AdminRespository adminRespository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private StorageProperties properties;


    @GetMapping("/test/upload")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")//正则表示法,否则点号后面的会被截取
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @GetMapping("/videos/{filename:.+}")//正则表示法,否则点号后面的会被截取
    @ResponseBody
    public ResponseEntity<Resource> serveVideo(@PathVariable String filename) {
        this.videaStorageService.setDir(properties.getVideo());
        Resource file = videaStorageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }



    @PostMapping("/test/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/test/upload";
    }


    @PostMapping("/test/back")
    @ResponseBody
    public String backName(@CookieValue(value = "uid", defaultValue = "-1") String uid,
                           @RequestParam(value = "token", defaultValue = "") String token,
                           @RequestParam("file") MultipartFile file) {

        AdminInfo admin = adminRespository.findById(Integer.parseInt(AESHelper.decrypt(uid, CommonData.ENCRYPT_KEY)));
        String token_db=stringRedisTemplate.opsForValue().get("token");
        if(admin!=null&&token.equals(token_db)){

            storageService.store(file);
            return "/files/"+storageService.getName();
        }
        return "";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
