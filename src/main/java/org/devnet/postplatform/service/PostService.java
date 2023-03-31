package org.devnet.postplatform.service;

import lombok.RequiredArgsConstructor;
import org.devnet.postplatform.exception.PostNotFoundException;
import org.devnet.postplatform.model.Post;
import org.devnet.postplatform.repository.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<Post> findAll(Integer pageNo, Integer pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        return postRepository
                .findAllByIdWithTags(postRepository
                        .findAllPostIds(pageable));
    }

    @Transactional(readOnly = true)
    public Post findById(Long id) {
        Optional<Post> optPost = postRepository.findPostById(id);
        if (optPost.isPresent()) {
            optPost = postRepository.findPostByIdWithTags(id);
            if (optPost.isPresent()) {
                return optPost.get();
            }
        }
        String msg = String.format("Post with id %d not found", id);
        throw new PostNotFoundException(msg);
    }
}
