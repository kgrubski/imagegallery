package com.ul.imagegallery.services.picture;

import java.io.File;

public interface PictureService {
    void upload(byte[] file);

    byte[] getPicture();
}
