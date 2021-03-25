package com.senacbooks.senacbooks.products.images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import com.senacbooks.senacbooks.s3.S3Service;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    @Autowired
    private S3Service s3Service;

    public List<ImageDTO> findAll() {
        List<ImageEntity> list = repository.findAll();

        return list.stream().map(x -> new ImageDTO(x)).collect(Collectors.toList());
    }

    public UriDTO uploadFile(MultipartFile file) {
        // TODO voltar para s3 para apresentação pro professor
//        URL url = s3Service.uploadFile(file);
        String url = "http://books.google.com/books/content?id=Red7DwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
        ImageEntity img = new ImageEntity();
//        img.setImgUrl(url.toString());
        img.setImgUrl(url);
        img = repository.save(img);
        System.out.println(img.getId());
        System.out.println(img.getImgUrl());

        return new UriDTO(img.getId(), img.getImgUrl());
    }

    public ImageEntity getImage(Long imageId) {
        return repository.findById(imageId).orElseThrow();
    }
}
