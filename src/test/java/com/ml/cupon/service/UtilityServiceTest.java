package com.ml.cupon.service;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.ml.cupon.service.impl.UtilityServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UtilityServiceTest {

	@InjectMocks
	private UtilityServiceImpl utilityService;

	@BeforeEach
	public void setUp() {
		utilityService = new UtilityServiceImpl();
	}

	@Test
	public void calculateTest() {

		Map<String, Float> itemsMap = new HashMap<String, Float>();

		itemsMap.put("MLA803174898", 100f);
		itemsMap.put("MLA803174894", 1000f);
		itemsMap.put("MLA803174788", 100f);
		itemsMap.put("MLA803086664", 100f);
		itemsMap.put("MLA810645375", 16999f);
		itemsMap.put("MLA844702264", 100f);

		List<String> resultActual = new ArrayList<>();

		resultActual.add("MLA803086664");
		resultActual.add("MLA844702264");
		resultActual.add("MLA803174898");
		resultActual.add("MLA803174788");
		resultActual.add("MLA803174894");
		resultActual.add("1400.0");

		List<String> resultExpected = this.utilityService.calculate(itemsMap, 1500f);
		// assertThat(resultExpected, containsInAnyOrder(resultActual));
		assertEquals(resultExpected, resultActual);
	}

}
