package com.ul.imagegallery.util;

import java.util.Base64;

public class ImageUtil {
    public String getImgData(byte[] byteData) {
        if(byteData == null){
            return null;
        }
        return Base64.getMimeEncoder().encodeToString(byteData);
    }
}
