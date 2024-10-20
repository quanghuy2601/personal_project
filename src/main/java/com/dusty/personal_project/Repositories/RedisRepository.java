package com.dusty.personal_project.Repositories;

import com.dusty.personal_project.Models.DTO.Auth.RedisToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RedisRepository extends CrudRepository<RedisToken, UUID> {

}
