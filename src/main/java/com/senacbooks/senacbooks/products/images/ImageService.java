package com.senacbooks.senacbooks.products.images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;


    public List<ImageDTO> findAll(){
        List<ImageEntity> list = repository.findAll();

        return list.stream().map(x -> new ImageDTO(x)).collect(Collectors.toList());
    }

    public UriDTO uploadFile(MultipartFile file, Boolean principal) {
        String url = "teste";
        ImageEntity img = new ImageEntity();
        img.setImgUrl(url.toString());
        img.setPrincipal(principal);
        img = repository.save(img);

        return new UriDTO(url.toString(), img.getPrincipal());
    }

    public ImageEntity getImage(Long imageId) {
        return repository.findById(imageId).orElseThrow();
    }
}