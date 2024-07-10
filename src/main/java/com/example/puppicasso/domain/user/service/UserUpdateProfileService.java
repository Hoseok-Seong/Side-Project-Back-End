package com.example.puppicasso.domain.user.service;

import com.example.puppicasso.domain.ai.util.ImageUtil;
import com.example.puppicasso.domain.user.dao.UserFindDao;
import com.example.puppicasso.domain.user.entity.User;
import com.example.puppicasso.global.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class UserUpdateProfileService {
    private final UserFindDao userFindDao;

    public User updateProfile(final MyUserDetails myUserDetails, final MultipartFile file) {
        byte[] imageData = ImageUtil.encodeMultipartFileToBase64(file);

        final User user = userFindDao.findById(myUserDetails.getUser().getId());

        return user;
    }
}
