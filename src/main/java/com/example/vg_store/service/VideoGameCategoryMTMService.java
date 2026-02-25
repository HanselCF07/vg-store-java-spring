package com.example.vg_store.service;

import com.example.vg_store.repository.VideoGameCategoryMTMRepository;
import com.example.vg_store.repository.VideoGameRepository;
import com.example.vg_store.repository.VideoGameCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.vg_store.model.VideoGame;
import com.example.vg_store.model.VideoGameCategory;
import com.example.vg_store.model.VideoGameCategoryMTM;
import com.example.vg_store.dto.VideoGameCategoryMTMDTO;
import com.example.vg_store.mapper.VideoGameCategoryMTMMapper;


@Service
public class VideoGameCategoryMTMService {
    private final VideoGameCategoryMTMRepository repository;
    private final VideoGameRepository videoGameRepository;
    private final VideoGameCategoryRepository categoryRepository;

    public VideoGameCategoryMTMService(VideoGameCategoryMTMRepository repository,
                                       VideoGameRepository videoGameRepository,
                                       VideoGameCategoryRepository categoryRepository) {
        this.repository = repository;
        this.videoGameRepository = videoGameRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<VideoGameCategoryMTMDTO> getByCategories(List<Integer> categoryIds, Pageable pageable) {
        return repository.findByCategory_IdInAndRecordStatus(categoryIds, 1, pageable)
                .map(VideoGameCategoryMTMMapper::toDTO);
    }

    public VideoGameCategoryMTMDTO save(VideoGameCategoryMTMDTO dto) {
        VideoGame videoGame = videoGameRepository.findById(dto.getVideoGameId())
                .orElseThrow(() -> new IllegalArgumentException("VideoGame no encontrado"));
        VideoGameCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category no encontrada"));

        VideoGameCategoryMTM entity = VideoGameCategoryMTMMapper.toEntity(dto, videoGame, category);
        return VideoGameCategoryMTMMapper.toDTO(repository.save(entity));
    }

    public VideoGameCategoryMTMDTO update(Integer id, VideoGameCategoryMTMDTO dto) {
        VideoGameCategoryMTM existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Relación no encontrada"));

        VideoGame videoGame = videoGameRepository.findById(dto.getVideoGameId())
                .orElseThrow(() -> new IllegalArgumentException("VideoGame no encontrado"));
        VideoGameCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category no encontrada"));

        existente.setVideoGame(videoGame);
        existente.setCategory(category);
        existente.setRecordStatus(dto.getRecordStatus());

        return VideoGameCategoryMTMMapper.toDTO(repository.save(existente));
    }

    public boolean delete(Integer id) {
        VideoGameCategoryMTM existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Registro no encontrado"));
        if (existente == null) {
            return false;
        }
        existente.setRecordStatus(0);
        return true;
    }
}