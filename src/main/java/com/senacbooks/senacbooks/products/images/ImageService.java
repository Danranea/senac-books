package com.senacbooks.senacbooks.products.images;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.senacbooks.senacbooks.s3.S3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        URL url = s3Service.uploadFile(file);
//        String url = "http://books.google.com/books/content?id=Red7DwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";

        ImageEntity img = new ImageEntity();

        img.setImgUrl(url.toString());
        img.setStatus(true);
//        img.setImgUrl(url);
        img = repository.save(img);

        return new UriDTO(img.getId(), url.toString(), img.getPrincipal(), img.getStatus());
    }

    public ImageEntity getImage(Long imageId) {
        return repository.findById(imageId).orElseThrow();
    }

    public String delete(Long id) {
        Optional<ImageEntity> obj = repository.findById(id);
        ImageEntity entity = obj.orElseThrow();
        String retorno;
        if (entity.getStatus()) {
            entity.setStatus(false);
            entity = repository.save(entity);
            retorno = "Imagem " + entity.getId() + " inativado com sucesso.";
        }else{
            entity.setStatus(true);
            entity = repository.save(entity);
            retorno = "Imagem " + entity.getId() + " reativado com sucesso.";
        }
        return retorno;
    }
}
