package com.example.vg_store.service;
import org.springframework.stereotype.Service;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.vg_store.model.VideoGame;
import com.example.vg_store.model.VideoGameDeveloper;
import com.example.vg_store.dto.VideoGameDTO;
import com.example.vg_store.mapper.VideoGameMapper;
import com.example.vg_store.repository.VideoGameRepository;
import com.example.vg_store.repository.VideoGameDeveloperRepository;
// import java.util.List;


@Service
public class VideoGameService {
    private final VideoGameRepository videoGameRepository;
    private final VideoGameDeveloperRepository developerRepository;

    public VideoGameService(VideoGameRepository videoGameRepository,
                            VideoGameDeveloperRepository developerRepository) {
        this.videoGameRepository = videoGameRepository;
        this.developerRepository = developerRepository;
    }

    // Listar todos los videojuegos activos con paginación
    public Page<VideoGameDTO> getActiveVideoGames(Pageable pageable) {
        return videoGameRepository.findByRecordStatus(1, pageable)
                .map(VideoGameMapper::toDTO);
    }

    // Listar videojuegos activos filtrados por developerId con paginación
    public Page<VideoGameDTO> getActiveVideoGamesByDeveloper(Integer developerId, Pageable pageable) {
        return videoGameRepository.findByRecordStatusAndDeveloper_Id(1, developerId, pageable)
                .map(VideoGameMapper::toDTO);
    }

    public VideoGameDTO getByPublicId(String publicId) {
        VideoGame entity = videoGameRepository.findByPublicId(publicId);
        if (entity == null || entity.getRecordStatus() != 1) {
            return null; // no existe o está inactivo
        }
        return VideoGameMapper.toDTO(entity);
    }


    public VideoGameDTO save(VideoGameDTO dto) {
            VideoGameDeveloper developer = null;
            if (dto.getDeveloperId() != null) {
                developer = developerRepository.findById(dto.getDeveloperId())
                        .orElseThrow(() -> new IllegalArgumentException("Developer no encontrado"));
            }

            VideoGame entity = VideoGameMapper.toEntity(dto, developer);
            VideoGame saved = videoGameRepository.save(entity);

            // Generar publicId si no existe
            if (saved.getPublicId() == null) {
                String basePart = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
                saved.setPublicId(basePart + saved.getId());
                saved = videoGameRepository.save(saved); // actualizar con publicId
            }

            return VideoGameMapper.toDTO(saved);
    }

    public VideoGameDTO update(String publicId, VideoGameDTO dto) {
        VideoGame existente = videoGameRepository.findByPublicId(publicId);
        if (existente == null) {
            return null;
        }
        VideoGameDeveloper developer = null;
        if (dto.getDeveloperId() != null) {
            developer = developerRepository.findById(dto.getDeveloperId())
                    .orElseThrow(() -> new IllegalArgumentException("Developer no encontrado"));
        }
        VideoGameMapper.updateEntity(existente, dto, developer);
        VideoGame actualizado = videoGameRepository.save(existente);
        return VideoGameMapper.toDTO(actualizado);
    }

    public boolean delete(String publicId) {
        VideoGame existente = videoGameRepository.findByPublicId(publicId);
        if (existente == null) {
            return false;
        }
        existente.setRecordStatus(0);
        //videoGameRepository.delete(existente);
        return true;
    }
}

