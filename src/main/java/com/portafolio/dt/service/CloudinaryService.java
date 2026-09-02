package com.portafolio.dt.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public Map upload(MultipartFile file, Long proyectoId, Long etapaId)
            throws IOException {

        String carpeta =
                "portafolio-design-thinking/proyectos/"
                + proyectoId
                + "/etapas/"
                + etapaId;

        return cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", carpeta,
                        "resource_type", "auto",
                        "use_filename", true,
                        "unique_filename", true
                )
        );
    }

    public void delete(String publicId, String resourceType)
            throws IOException {

        cloudinary.uploader().destroy(
                publicId,
                ObjectUtils.asMap(
                        "resource_type", resourceType
                )
        );
    }
}