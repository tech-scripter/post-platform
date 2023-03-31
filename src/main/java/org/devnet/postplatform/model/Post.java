package org.devnet.postplatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "post-tags",
                attributeNodes = {
                        @NamedAttributeNode("tags")
                }
        ),
        @NamedEntityGraph(
                name = "post-comments",
                attributeNodes = {
                        @NamedAttributeNode("comments")
                }
        )
})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Embedded
    private PostStructure structure;

    private Long likes;

    @ManyToOne
    private User author;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Comment> comments;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private List<Tag> tags;
}
