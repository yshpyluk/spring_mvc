package com.example;

import com.example.domain.user.*;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yshpyluk on 5/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("mysql")
@AutoConfigureMockMvc
public class AssociationTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;

	@After
	public void tearDown() {
		userRepository.deleteAll();
		addressRepository.deleteAll();
	}

	@Test
	public void testPassportAssociation() {
		//Given
		Passport passport = Passport.builder().serialNumber("LS12345PD").build();
		Address address1 = Address.builder().buildingNumber(11).street("street1").build();
		Address address2 = Address.builder().buildingNumber(22).street("street2").build();
		addressRepository.save(address1);
		addressRepository.save(address2);

		List<Address> addresses = new ArrayList<>();
		addresses.add(address1);
		addresses.add(address2);

		List<String> projects = new ArrayList<>();
		projects.add("first");
		projects.add("second");

		LocalDate birthday = LocalDate.of(1987, Month.SEPTEMBER, 12);

		User user = User.builder().
				name("Test Name").
				passport(passport).
				addresses(addresses).
				projects(projects).
				role(UserRole.MANAGER).
				birthday(birthday).
				build();
		userRepository.save(user);

		User user1 = userRepository.findOne(user.getId());
	}
}
