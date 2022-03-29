package com.ul.imagegallery.services.picture;

import com.ul.imagegallery.database.entity.Picture;
import com.ul.imagegallery.database.repository.PictureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@Transactional
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository pictureRepository;
    @Override
    public void upload(byte[] file) {

        Picture picture = new Picture();
        picture.setImage(file);
        pictureRepository.save(picture);
    }

    @Override
    public byte[] getPicture() {
        List<Picture> all = pictureRepository.findAll();
        Random random = new Random();
        return all.get(random.nextInt(all.size())).getImage();
    }
}
