package com.ul.imagegallery.database.repository;

import com.ul.imagegallery.database.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
