package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcUserDaoTests extends BaseDaoTests{


    //todo: integration tests

    private static final User USER_1 = new User(1001,"bob", "plantlover", "admin");
    private static final User USER_2 = new User(1002,"john", "jellyfish", "USER");
    private static final User USER_3 = new User(1003,"dave", "waterbottle", "USER");
    private static final User USER_4 = new User(1004,"tom", "monitor", "USER");

    private JdbcUserDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserDao(jdbcTemplate);
    }

    @Test
    public void createNewUser() {
        boolean userCreated = sut.create("TEST_USER","test_password");
        Assert.assertTrue(userCreated);
        User user = sut.findByUsername("TEST_USER");
        Assert.assertEquals("TEST_USER", user.getUsername());
    }

    @Test
    public void findAllUsers() {
        List<User> users = sut.findAll();
      //todo debug shows as expected 4 and actual 2
        Assert.assertEquals(4, users.size());
        assertUserMatch(USER_1, users.get(0));
        assertUserMatch(USER_2, users.get(1));
        assertUserMatch(USER_3, users.get(2));
        assertUserMatch(USER_4, users.get(3));

    }

    @Test
    public void getUsername(){
        User user = sut.findByUsername("bob");
        assertUserMatch(USER_1, user);
    }


    private void assertUserMatch(User expected, User actual) {
        Assert.assertEquals(expected.getUsername(), actual.getUsername());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getAuthorities(), actual.getAuthorities());

    }
}
