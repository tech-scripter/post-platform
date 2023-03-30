package org.devnet.postplatform.service;

import lombok.RequiredArgsConstructor;
import org.devnet.postplatform.model.User;
import org.devnet.postplatform.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> page = userRepository.findAll(pageable);
        if (page.hasContent()) {
            return page.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
