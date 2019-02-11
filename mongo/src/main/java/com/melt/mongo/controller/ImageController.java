package com.melt.mongo.controller;

import com.melt.mongo.domain.Image;
import com.melt.mongo.repo.ImageRepo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageRepo imageRepo;

    @GetMapping
    public String getFormToLoadImage(Model model) {
        model.addAttribute("message", "Maximum 5Mb");
        return "image";
    }

    @PostMapping
    public String loadImage(@RequestParam("description") String description,
                            @RequestParam("file") MultipartFile file) {
        byte[] bytes = getBytesForSave(file);
        imageRepo.save(new Image(description, bytes));
        return "redirect:/image";
    }

    @GetMapping("/{id}")
    public void renderImage(@PathVariable("id") String id, HttpServletResponse response) {
        response.setContentType("image/jpeg");
        Image image = imageRepo.findById(Long.valueOf(id));

        byte[] bytes = getBytesForLoad(image);

        InputStream is = new ByteArrayInputStream(bytes);
        try {
          IOUtils.copy(is, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] getBytesForLoad(Image image) {
        byte[] result = new byte[image.getImage().length];
        int i = 0;
        for (byte b : image.getImage()) {
            result[i++] = b;
        }
        return result;
    }

    private byte[] getBytesForSave(@RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = new byte[file.getBytes().length];
            int i = 0;
            for (Byte b : file.getBytes()) {
                bytes[i++] = b;
            }
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
