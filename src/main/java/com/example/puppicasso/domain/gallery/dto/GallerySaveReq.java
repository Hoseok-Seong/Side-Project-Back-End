package com.example.puppicasso.domain.gallery.dto;

import com.example.puppicasso.domain.gallery.entity.Gallery;
import com.example.puppicasso.domain.gallery.entity.Type;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GallerySaveReq {
    private Long userId;
    private String name;
    private Type type;
    private byte[] data;

    public GallerySaveReq(final MyUserDetails myUserDetails, final String imageName, final Type imageType, final byte[] imageData) {
        this.userId = myUserDetails.getUser().getId();
        this.name = imageName;
        this.type = imageType;
        this.data = imageData;
    }

    public Gallery toEntity() {
        return Gallery.builder()
                .userId(userId)
                .name(name)
                .type(type)
                .data(data)
                .build();
    }
}
