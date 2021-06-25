package com.dlightsaber.member.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegisterTests {

	@Test
	public void test() {

		Register reg = new Register();
		reg.setFirstName("Nikita");
		reg.setLastName("Thakare");
		reg.setConfirmPassword("abc");
		reg.setPassword("abc");
		reg.setEmail("abc@gmail.com");
		reg.setAddress("phase1");
		reg.setCity("Pune");
		reg.setZip("12345");
		reg.set_id(null);
		reg.setPhoneNumber("9411020202");
		assertEquals("9411020202", reg.getPhoneNumber());
		assertEquals(null, reg.get_id());
		assertEquals("Nikita", reg.getFirstName());
		assertEquals("Thakare", reg.getLastName());
		assertEquals("abc", reg.getConfirmPassword());
		assertEquals("abc", reg.getPassword());
		assertEquals("abc@gmail.com", reg.getEmail());
		assertEquals("phase1", reg.getAddress());
		assertEquals("Pune", reg.getCity());
		assertEquals("12345", reg.getZip());
	}

}
