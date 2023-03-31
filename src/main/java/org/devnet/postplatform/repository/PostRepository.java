package org.devnet.postplatform.repository;

import org.devnet.postplatform.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Split the filtering and the fetching into two separate queries
    @Query(
            value = "select p.id from Post p",
            countQuery = "select count(p) from Post p"
    )
    List<Long> findAllPostIds(Pageable pageable);

    @Query(
            value = "select p " +
            "from Post p " +
            "left join fetch p.tags " +
                    "left join fetch p.author " +
            "where p.id in :postIds"
    )
    List<Post> findAllByIdWithTags(@Param("postIds") List<Long> postIds);
}
