package com.example.vg_store.service;

import com.example.vg_store.model.VideoGameCategory;
import com.example.vg_store.repository.VideoGameCategoryRepository;
import com.example.vg_store.dto.VideoGameCategoryDTO;
import com.example.vg_store.mapper.VideoGameCategoryMapper;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class VideoGameCategoryService {
    private final VideoGameCategoryRepository repository;

    public VideoGameCategoryService(VideoGameCategoryRepository repository) {
        this.repository = repository;
    }

    public List<VideoGameCategoryDTO> getAllActive() {
        return repository.findByRecordStatus(1)
                .stream()
                .map(VideoGameCategoryMapper::toDTO)
                .toList();
    }

    public VideoGameCategoryDTO getActiveById(Integer id) {
        return repository.findByIdAndRecordStatus(id, 1)
                .map(VideoGameCategoryMapper::toDTO)
                .orElse(null);
    }

    public VideoGameCategoryDTO save(VideoGameCategoryDTO dto) {
            VideoGameCategory entity = VideoGameCategoryMapper.toEntity(dto);
            VideoGameCategory saved = repository.save(entity);
            return VideoGameCategoryMapper.toDTO(saved);
    }

    public VideoGameCategoryDTO update(Integer id, VideoGameCategoryDTO dto) {
        VideoGameCategory existente = repository.findByIdAndRecordStatus(id, 1).orElse(null);
        if (existente == null) {
            return null;
        }

        VideoGameCategoryMapper.updateEntity(existente, dto);
        VideoGameCategory actualizado = repository.save(existente);
        return VideoGameCategoryMapper.toDTO(actualizado);
    }

    public boolean delete(Integer id) {
        VideoGameCategory existente = repository.findByIdAndRecordStatus(id, 1).orElse(null);
        if (existente == null) {
            return false;
        }
        existente.setRecordStatus(0);
        //videoGameRepository.delete(existente);
        return true;
    }

}

