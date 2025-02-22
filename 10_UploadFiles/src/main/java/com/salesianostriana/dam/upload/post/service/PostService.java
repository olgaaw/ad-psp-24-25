package com.salesianostriana.dam.upload.post.service;


import com.salesianostriana.dam.upload.files.model.FileMetadata;
import com.salesianostriana.dam.upload.files.service.StorageService;
import com.salesianostriana.dam.upload.post.dto.CreatePostDto;
import com.salesianostriana.dam.upload.post.model.Post;
import com.salesianostriana.dam.upload.post.repo.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;
    private final StorageService storageService;

    @Transactional
    public Post save(CreatePostDto createPostDto, MultipartFile file) {
        FileMetadata fileMetadata = storageService.store(file);

        Post post = repository.save(
                Post.builder()
                        .title(createPostDto.getTitle())
                        .image(fileMetadata.getFilename())
                        .build()
        );
        return post;
    }

    public List<Post> findAll() { return repository.findAll(); }




}
