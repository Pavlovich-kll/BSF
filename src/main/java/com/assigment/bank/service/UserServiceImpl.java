package com.assigment.bank.service;

import com.assigment.bank.entity.UserEntity;
import com.assigment.bank.model.User;
import com.assigment.bank.repository.UserRepository;
import com.assigment.bank.service.converter.EntityToModelConverter;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelToEntityConverter toEntityConverter;
    private final EntityToModelConverter toModelConverter;

    public UserServiceImpl(UserRepository userRepository, ModelToEntityConverter toEntityConverter, EntityToModelConverter toModelConverter) {
        this.userRepository = userRepository;
        this.toEntityConverter = toEntityConverter;
        this.toModelConverter = toModelConverter;
    }


    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        Iterable<UserEntity> allUsers = userRepository.findAll();
        allUsers.forEach(user -> {
            listUsers.add(toModelConverter.convertToUserDomain(user));
        });
        return listUsers;
    }

    @Override
    public UserEntity getUsersByNumber(Long userNumber) {
        Optional<UserEntity> user = userRepository.findByUserNumber(userNumber);
        return user.orElse(null);
    }

    @Override
    public void updateOrCreateUser(User user) {
        UserEntity newUser = toEntityConverter.convertToUserEntity(user);
        userRepository.save(newUser);
    }

    @Override
    public void deleteUserAndAccount(Long userNumber) {
        userRepository.deleteByUserNumber(userNumber);
    }
}
