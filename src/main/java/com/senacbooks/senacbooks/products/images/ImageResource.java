package com.senacbooks.senacbooks.products.images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/images")
public class ImageResource {

    @Autowired
    private ImageService service;

    @GetMapping
    public ResponseEntity<List<ImageDTO>> findAll(){
        List<ImageDTO> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/image")
    public ResponseEntity<UriDTO> uploadImage(@RequestParam("file") MultipartFile file) {
        UriDTO dto = service.uploadFile(file);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value="/image/{id}")
    public String inactivateImage(@PathVariable Long id){
        String retorno = service.delete(id);
        return retorno;
    }
}
