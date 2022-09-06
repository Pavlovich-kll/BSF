package com.assigment.bank.repositoryIT.Initializer;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Класс, где создается контейнер для каждого отдельного теста
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(value = "classpath:application-test.yml")
public abstract class ContainerDB {

    @ClassRule
    public static TestPostgresContainer postgres = TestPostgresContainer
            .getInstance();
}
