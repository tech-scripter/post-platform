package org.devnet.postplatform.payloads.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devnet.postplatform.model.PostStructure;
import org.devnet.postplatform.model.Tag;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
    private PostStructure structure;
    private UserResponse userResponse;
    private List<Tag> tags;
}
