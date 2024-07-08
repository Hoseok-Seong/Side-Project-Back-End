package com.example.puppicasso.domain.token.service;

import com.example.puppicasso.domain.token.dao.RefreshTokenDao;
import com.example.puppicasso.domain.token.dao.RefreshTokenRedisRepository;
import com.example.puppicasso.domain.token.dto.NewAccessTokenReq;
import com.example.puppicasso.domain.token.dto.NewAccessTokenResp;
import com.example.puppicasso.domain.token.entity.RefreshTokenRedis;
import com.example.puppicasso.domain.token.exception.InvalidRefreshTokenException;
import com.example.puppicasso.domain.token.exception.RefreshTokenNotFoundException;
import com.example.puppicasso.domain.user.dao.UserFindDao;
import com.example.puppicasso.domain.user.entity.User;
import com.example.puppicasso.domain.user.exception.UserNotFoundException;
import com.example.puppicasso.domain.user.repository.UserRepository;
import com.example.puppicasso.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccessTokenGenerateService {

    private final UserRepository userRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final RefreshTokenDao refreshTokenDao;
    private final UserFindDao userFindDao;

    public ResponseEntity<?> generateNewAccessToken(final String userAgent, final NewAccessTokenReq newAccessTokenReq) {
        if (userRepository.existsById(newAccessTokenReq.getUserId())) {
            throw new UserNotFoundException(newAccessTokenReq.getUserId());
        }

        validateRefreshToken(newAccessTokenReq);

        final User user = userFindDao.findById(newAccessTokenReq.getUserId());
        final RefreshTokenRedis refreshTokenRedis = refreshTokenDao.findById(newAccessTokenReq.getUserId());

        String accessToken = JwtProvider.createAccessToken(user);

        // RTR 방식 사용
        String newRefreshToken = JwtProvider.createRefreshToken(user);
        refreshTokenRedis.updateRefreshToken(newRefreshToken);

        return ResponseEntity.ok().header(JwtProvider.ACCESS_TOKEN_HEADER, accessToken)
                .header(JwtProvider.REFRESH_TOKEN_HEADER, newRefreshToken).body(new NewAccessTokenResp(user));
    }

    public void validateRefreshToken(final NewAccessTokenReq newAccessTokenReq) {
        if (!refreshTokenRedisRepository.existsById(newAccessTokenReq.getUserId())) {
            throw new RefreshTokenNotFoundException(newAccessTokenReq.getUserId());
        }

        if (!refreshTokenDao.findById(newAccessTokenReq.getUserId()).getRefreshToken().equals(newAccessTokenReq.getRefreshToken())) {
            refreshTokenRedisRepository.delete(refreshTokenDao.findById(newAccessTokenReq.getUserId()));
            throw new InvalidRefreshTokenException(newAccessTokenReq.getRefreshToken());
        }
    }
}
