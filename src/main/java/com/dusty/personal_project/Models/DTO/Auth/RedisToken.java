package com.dusty.personal_project.Models.DTO.Auth;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("RedisToken")
public class RedisToken implements Serializable {
    private UUID id;
    private String accessToken;
    private String refreshToken;
}
