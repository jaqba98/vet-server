package com.olejarczykjakub.vet_server.service;

import com.olejarczykjakub.vet_server.model.Users;
import com.olejarczykjakub.vet_server.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;

    public boolean existUser(Users users) {
        val homeAccountId = users.getHomeAccountId();
        return usersRepository.findByHomeAccountId(homeAccountId).isPresent();
    }

    public void createUser(Users users) {
        usersRepository.save(users);
    }
}
