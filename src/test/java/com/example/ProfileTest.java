package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by yshpyluk on 4/23/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles
public class ProfileTest {

	@Autowired
	Environment env;

	@Test
	public void qwe() {
		System.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME);
	}
}
