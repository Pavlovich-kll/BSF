package com.assigment.bank.service.implServices;

import com.assigment.bank.dto.UserDto;
import com.assigment.bank.entity.BankEntity;
import com.assigment.bank.entity.ContactEntity;
import com.assigment.bank.entity.UserEntity;
import com.assigment.bank.model.User;
import com.assigment.bank.repository.BankRepository;
import com.assigment.bank.repository.ContactRepository;
import com.assigment.bank.repository.UserRepository;
import com.assigment.bank.service.UserService;
import com.assigment.bank.service.converter.EntityToModelConverter;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final BankRepository bankRepository;
    private final ModelToEntityConverter toEntityConverter;
    private final EntityToModelConverter toModelConverter;

    public UserServiceImpl(UserRepository userRepository, ContactRepository contactRepository, BankRepository bankRepository, ModelToEntityConverter toEntityConverter, EntityToModelConverter toModelConverter) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.bankRepository = bankRepository;
        this.toEntityConverter = toEntityConverter;
        this.toModelConverter = toModelConverter;
    }


    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        Iterable<UserEntity> allUsers = userRepository.findAll();
        allUsers.forEach(user -> {
            if (user.getSavingsAccount() == null) {
                listUsers.add(toModelConverter.convertToUserNoAccountDomain(user));
            } else {
                listUsers.add(toModelConverter.convertToUserDomain(user));
            }
        });
        return listUsers;
    }

    @Override
    public User getUsersByNumber(Long userNumber) {

        Optional<UserEntity> user = userRepository.findById(userNumber);
        if (user.isPresent()) {
           UserEntity entity = user.get();
           return createUserEntity(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User № " + userNumber + " not found.");
        }
    }

    @Override
    public Long createUser(UserDto user) {
        ContactEntity newContact = toEntityConverter.convertToContactEntity(user.getContactInformation());
        ContactEntity contactEntity = contactRepository.saveAndFlush(newContact);

        UserEntity newUser = toEntityConverter.convertToUserEntity(user);
        newUser.setContactEntity(contactEntity);

        Optional<BankEntity> bank = bankRepository.findById(user.getBankId());
        newUser.setBank(bank.orElse(null));
        newUser.setContactEntity(contactEntity);

        Long userNumber = userRepository.saveAndFlush(newUser).getId();
        return userNumber;
    }

    @Override
    public void deleteUserAndAccount(Long userNumber) {
        userRepository.deleteById(userNumber);
    }

    private User createUserEntity(UserEntity entity) {
        User user;
        if (entity.getSavingsAccount() == null) {
            user = toModelConverter.convertToUserNoAccountDomain(entity);
        } else {
            user = toModelConverter.convertToUserDomain(entity);
        }
        return user;
    }
}
