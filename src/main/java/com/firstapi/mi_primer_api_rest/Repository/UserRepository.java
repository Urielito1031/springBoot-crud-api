package com.firstapi.mi_primer_api_rest.Repository;

import org.springframework.data.repository.CrudRepository;

import com.firstapi.mi_primer_api_rest.Models.Entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

}
