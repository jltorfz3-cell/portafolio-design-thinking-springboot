package com.portafolio.dt.controller;

import com.cloudinary.utils.ObjectUtils;
import com.portafolio.dt.model.Evidencia;
import com.portafolio.dt.model.TipoEvidencia;
import com.portafolio.dt.repository.EvidenciaRepository;
import com.portafolio.dt.service.CloudinaryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/evidencias")
public class EvidenciaUploadController {

    private final CloudinaryService cloudinaryService;
    private final EvidenciaRepository evidenciaRepository;

    public EvidenciaUploadController(
            CloudinaryService cloudinaryService,
            EvidenciaRepository evidenciaRepository) {

        this.cloudinaryService = cloudinaryService;
        this.evidenciaRepository = evidenciaRepository;
    }

    @PostMapping("/upload")
    @PreAuthorize("""
        hasAnyRole(
            'ADMIN',
            'DOCENTE',
            'ESTUDIANTE',
            'COORDINADOR'
        )
        """)
    public ResponseEntity<?> upload(

            @RequestParam("file")
            MultipartFile file,

            @RequestParam Long proyectoId,

            @RequestParam Long etapaId,

            @RequestParam(required = false)
            String descripcion) {

        try {

            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of(
                                "error",
                                "Debe seleccionar un archivo"
                        ));
            }

            Map result =
                    cloudinaryService.upload(
                            file,
                            proyectoId,
                            etapaId
                    );

            String url =
                    (String) result.get("secure_url");

            String publicId =
                    (String) result.get("public_id");

            String resourceType =
                    (String) result.get("resource_type");

            String format =
                    (String) result.get("format");

            Number bytes =
                    (Number) result.get("bytes");

            Evidencia evidencia = new Evidencia();

            evidencia.setProyectoId(proyectoId);
            evidencia.setEtapaId(etapaId);

            evidencia.setTipo(
                    determinarTipo(file.getContentType())
            );

            evidencia.setNombreArchivo(publicId);
            evidencia.setNombreOriginal(file.getOriginalFilename());
            evidencia.setMimeType(file.getContentType());

            evidencia.setTamanoBytes(
                    bytes != null
                            ? bytes.longValue()
                            : file.getSize()
            );

            evidencia.setUrlArchivo(url);
            evidencia.setDescripcion(descripcion);

            Evidencia guardada =
                    evidenciaRepository.save(evidencia);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of(
                            "mensaje",
                            "Archivo subido correctamente",

                            "id",
                            guardada.getId(),

                            "url",
                            url,

                            "publicId",
                            publicId,

                            "resourceType",
                            resourceType,

                            "format",
                            format,

                            "nombre",
                            file.getOriginalFilename()
                    ));

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error",
                            "No fue posible subir el archivo",

                            "detalle",
                            e.getMessage()
                    ));
        }
    }

    private TipoEvidencia determinarTipo(String contentType) {

        if (contentType == null) {
            return TipoEvidencia.OTRO;
        }

        if (contentType.startsWith("image/")) {
            return TipoEvidencia.IMAGEN;
        }

        if (contentType.startsWith("video/")) {
            return TipoEvidencia.VIDEO;
        }

        if (contentType.startsWith("audio/")) {
            return TipoEvidencia.AUDIO;
        }

        if (contentType.equals("application/pdf")
                || contentType.contains("document")
                || contentType.contains("word")) {

            return TipoEvidencia.DOCUMENTO;
        }

        return TipoEvidencia.OTRO;
    }
}