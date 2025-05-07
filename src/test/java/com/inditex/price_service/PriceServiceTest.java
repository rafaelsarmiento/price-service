package com.inditex.price_service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceServiceTest {
	
	private static final String URI = "/api/v1/price/{brandId}/{productId}?date={date}";
	
	@Test
	public void test1FromSpecification(@Autowired MockMvcTester mvc) {
		LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0); // 14/06/2020 10:00:00
		testDateWithExpectedResult(mvc, date, "/output/test1.json");
	}
	
	@Test
	public void test2FromSpecification(@Autowired MockMvcTester mvc) {
		LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0); // 14/06/2020 16:00:00
		testDateWithExpectedResult(mvc, date, "/output/test2.json");
	}
	
	@Test
	public void test3FromSpecification(@Autowired MockMvcTester mvc) {
		LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0); // 14/06/2020 21:00:00
		testDateWithExpectedResult(mvc, date, "/output/test3.json");
	}
	
	@Test
	public void test4FromSpecification(@Autowired MockMvcTester mvc) {
		LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0); // 15/06/2020 10:00:00
		testDateWithExpectedResult(mvc, date, "/output/test4.json");
	}
	
	@Test
	public void test5FromSpecification(@Autowired MockMvcTester mvc) {
		LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0); // 16/06/2020 21:00:00
		testDateWithExpectedResult(mvc, date, "/output/test5.json");
	}
	
	// Additional testing not included in the specification
	@Test
	public void testWrongParameters_return400(@Autowired MockMvcTester mvc) {
		assertThat(mvc.get().uri(URI, 123, 1234, "2020-06-14"))
			.hasStatus(HttpStatus.BAD_REQUEST)
			.bodyText()
			.isNotBlank();
		
	}
	
	@Test
	public void testNotFound_return404(@Autowired MockMvcTester mvc) {
		assertThat(exectTest(mvc, null))
			.hasStatus(HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void testLessThanEqual(@Autowired MockMvcTester mvc) {
		LocalDateTime date = LocalDateTime.of(2020, 6, 15, 0, 0); // 15/06/2020 00:00:00
		testDateWithExpectedResult(mvc, date, "/output/test4.json");		
	}
	
	@Test
	public void testGreaterThanEqual(@Autowired MockMvcTester mvc) {
		LocalDateTime date = LocalDateTime.of(2020, 6, 14, 18, 30); // 14/06/2020 18:30:00
		testDateWithExpectedResult(mvc, date, "/output/test2.json");		
	}	
	
	
	private void testDateWithExpectedResult(MockMvcTester mvc, LocalDateTime date, String expectedResultFile) {		
		assertThat(exectTest(mvc, date))
			.hasStatusOk()
			.hasContentTypeCompatibleWith(MediaType.APPLICATION_JSON)
			.bodyJson()
			.isStrictlyEqualTo(new ClassPathResource(expectedResultFile));
	}	
	
	private static MockMvcRequestBuilder exectTest(MockMvcTester mvc, LocalDateTime date) { 
		long brandId = 1;
		long productId = 35455;
		
		return mvc.get().uri(URI, brandId, productId, date);		
	}
}
