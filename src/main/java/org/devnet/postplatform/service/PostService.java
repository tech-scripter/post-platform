package org.devnet.postplatform.service;

import lombok.RequiredArgsConstructor;
import org.devnet.postplatform.model.Post;
import org.devnet.postplatform.repository.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
