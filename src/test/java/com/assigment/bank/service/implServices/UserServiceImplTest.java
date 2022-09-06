package com.assigment.bank.service.implServices;

import com.assigment.bank.dto.UserDto;
import com.assigment.bank.entity.UserEntity;
import com.assigment.bank.exceptionHandler.exception.UserNotFoundException;
import com.assigment.bank.model.Account;
import com.assigment.bank.model.User;
import com.assigment.bank.repository.BankRepository;
import com.assigment.bank.repository.ContactRepository;
import com.assigment.bank.repository.UserRepository;
import com.assigment.bank.service.converter.EntityToModelConverter;
import com.assigment.bank.service.converter.ModelToEntityConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UserServiceImplTest extends AbstractServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ContactRepository contactRepository;
    @Mock
    private BankRepository bankRepository;
    @Mock
    private ModelToEntityConverter toEntityConverter;
    @Mock
    private EntityToModelConverter toModelConverter;

    @Spy
    @InjectMocks
    private UserServiceImpl userService;

    private User userNoAccount;
    private User userAccount;
    private Account account;

    @Before
    public void initUser() {
        account = Account.builder()
                .userNumber(1L)
                .accountBalance(BigDecimal.valueOf(10000))
                .accountStatus("enabled")
                .accountType("Dollar")
                .build();
        userNoAccount = User.builder()
                .userNumber(1L)
                .firstName("Kirill")
                .lastName("Pavlovich")
                .status("enabled")
                .build();
        userAccount = User.builder()
                .userNumber(1L)
                .firstName("Kirill")
                .lastName("Pavlovich")
                .status("enabled")
                .account(account)
                .build();
    }

    @Test
    public void getAllUsersNoAccount() {
        List<UserEntity> listUsers = new ArrayList<>();
        listUsers.add(userEntityNoAccount);
        when(userRepository.findAll()).thenReturn(listUsers);
        when(toModelConverter.convertToUserNoAccountDomain(any())).thenReturn(userNoAccount);
        List<User> resultList = userService.getAllUsers();
        Assert.assertNotNull(resultList);
    }

    @Test
    public void getAllUsersWithAccount() {
        List<UserEntity> listUsers = new ArrayList<>();
        listUsers.add(userEntityAccount);
        when(userRepository.findAll()).thenReturn(listUsers);
        when(toModelConverter.convertToUserDomain(any())).thenReturn(userAccount);
        List<User> resultList = userService.getAllUsers();
        Assert.assertNotNull(resultList);
    }

    @Test
    public void getUsersByNumberNoAccountTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userEntityNoAccount));
        when(toModelConverter.convertToUserNoAccountDomain(userEntityNoAccount)).thenReturn(userNoAccount);
        User usersByNumber = userService.getUsersByNumber(1L);
        Assert.assertEquals("Kirill", usersByNumber.getFirstName());
    }

    @Test
    public void getUsersByNumberWithAccountTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userEntityAccount));
        when(toModelConverter.convertToUserDomain(userEntityAccount)).thenReturn(userAccount);
        User usersByNumber = userService.getUsersByNumber(1L);
        Assert.assertEquals(BigDecimal.valueOf(10000), usersByNumber.getAccount().getAccountBalance());
    }

    @Test(expected = UserNotFoundException.class)
    public void getUsersByNumberWithExceptionTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        userService.getUsersByNumber(1L);
    }

    @Test
    public void createUser() {
        when(toEntityConverter.convertToUserEntity(any())).thenReturn(userEntityAccount);
        when(userRepository.saveAndFlush(any())).thenReturn(userEntityAccount);

        Long userResult = userService.createUser(new UserDto());
        Assert.assertEquals(1L, userResult.longValue());
    }

    @Test
    public void deleteUserAndAccount() {
        ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(userRepository).deleteById(valueCapture.capture());
        userService.deleteUserAndAccount(1L);
        Assert.assertEquals(1L, valueCapture.getValue().longValue());
    }
}