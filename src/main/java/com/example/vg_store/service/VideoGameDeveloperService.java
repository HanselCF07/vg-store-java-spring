package com.example.vg_store.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.vg_store.model.VideoGameDeveloper;
import com.example.vg_store.dto.VideoGameDeveloperDTO;
import com.example.vg_store.mapper.VideoGameDeveloperMapper;
import com.example.vg_store.repository.VideoGameDeveloperRepository;
import java.util.List;


@Service
public class VideoGameDeveloperService {
    private final VideoGameDeveloperRepository developerRepository;

    public VideoGameDeveloperService(VideoGameDeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    // Listar solo developers activos (record_status = 1)
    public List<VideoGameDeveloperDTO> getAllActive() {
        return developerRepository.findByRecordStatus(1)
                .stream()
                .map(VideoGameDeveloperMapper::toDTO)
                .toList();
    }

    // Obtener developer activo por ID
    public VideoGameDeveloperDTO getActiveById(Integer id) {
        return developerRepository.findByIdAndRecordStatus(id, 1)
                .map(VideoGameDeveloperMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public VideoGameDeveloperDTO save(VideoGameDeveloperDTO dto) {
        VideoGameDeveloper entity = VideoGameDeveloperMapper.toEntity(dto);
        VideoGameDeveloper saved = developerRepository.save(entity);
        return VideoGameDeveloperMapper.toDTO(saved);
    }

    public VideoGameDeveloperDTO update(Integer id, VideoGameDeveloperDTO dto) {
        VideoGameDeveloper existente = developerRepository.findByIdAndRecordStatus(id, 1).orElse(null);
        if (existente == null) {
            return null;
        }
        VideoGameDeveloperMapper.updateEntity(existente, dto);
        VideoGameDeveloper actualizado = developerRepository.save(existente);
        return VideoGameDeveloperMapper.toDTO(actualizado);
    }

    public boolean delete(Integer id) {
        VideoGameDeveloper existente = developerRepository.findByIdAndRecordStatus(id, 1).orElse(null);
        if (existente == null) {
            return false;
        }
        existente.setRecordStatus(0);
        // developerRepository.delete(existente);
        return true;
    }
}

