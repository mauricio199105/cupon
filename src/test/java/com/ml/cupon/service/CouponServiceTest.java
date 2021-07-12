package com.ml.cupon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.ml.cupon.consumer.service.ConsumerService;
import com.ml.cupon.dto.CouponDTO;
import com.ml.cupon.dto.ItemDTO;
import com.ml.cupon.dto.ItemResponseDTO;
import com.ml.cupon.service.impl.CouponServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {

	@InjectMocks
	private CouponServiceImpl couponServiceImpl;

	@Mock
	private UtilityService utilityService;

	@Mock
	private ConsumerService consumerService;

	@BeforeTestClass
	public void setUp() {
		couponServiceImpl = new CouponServiceImpl(utilityService, consumerService);
	}

	@Test
	public void evaluateCouponTest() {

		String[] itemsIn = { "MLA803174898", "MLA803174894", "MLA803174788", "MLA803086664", "MLA810645375",
				"MLA844702264" };

		ItemDTO itemA = ItemDTO.builder().id("MLA803174898").price(100f).build();
		ItemDTO itemB = ItemDTO.builder().id("MLA803174894").price(1000f).build();
		ItemDTO itemC = ItemDTO.builder().id("MLA803174788").price(100f).build();
		ItemDTO itemD = ItemDTO.builder().id("MLA803086664").price(100f).build();
		ItemDTO itemE = ItemDTO.builder().id("MLA810645375").price(16999f).build();
		ItemDTO itemF = ItemDTO.builder().id("MLA844702264").price(100f).build();

		CouponDTO couponIn = CouponDTO.builder().item_ids(itemsIn).amount(1500f).build();
		List<ItemResponseDTO> itemsResponse = Arrays.asList(ItemResponseDTO.builder().code(200).body(itemA).build(),
				ItemResponseDTO.builder().code(200).body(itemB).build(),
				ItemResponseDTO.builder().code(200).body(itemC).build(),
				ItemResponseDTO.builder().code(200).body(itemD).build(),
				ItemResponseDTO.builder().code(200).body(itemE).build(),
				ItemResponseDTO.builder().code(200).body(itemF).build());

		Map<String, Float> itemsMap = new HashMap<String, Float>();

		itemsMap.put("MLA803174898", 100f);
		itemsMap.put("MLA803174898", 1000f);
		itemsMap.put("MLA803174898", 100f);
		itemsMap.put("MLA803174898", 100f);
		itemsMap.put("MLA803174898", 16999f);
		itemsMap.put("MLA803174898", 100f);

		List<String> resultActual = new ArrayList<>();

		resultActual.add("MLA803086664");
		resultActual.add("MLA844702264");
		resultActual.add("MLA803174788");
		resultActual.add("MLA803174898");
		resultActual.add("MLA803174894");
		resultActual.add("1400.0");

		String[] resultIds = { "MLA803086664", "MLA844702264", "MLA803174788", "MLA803174898", "MLA803174894" };
		CouponDTO couponActual = CouponDTO.builder().item_ids(resultIds).amount(1400f).build();

		when(this.consumerService.getItems(couponIn)).thenReturn(itemsResponse);
		when(this.utilityService.calculate(Mockito.anyMap(), Mockito.anyFloat())).thenReturn(resultActual);

		CouponDTO couponExpected = this.couponServiceImpl.evaluateCoupon(couponIn);

		assertEquals(couponExpected, couponActual);
		assertEquals(couponExpected, couponActual);

	}

	@Test
	public void evaluateCouponWhenOneItemIsEqualToAmountTest() {

		String[] itemsIn = { "MLA803174898", "MLA803174894", "MLA803174788", "MLA803086664", "MLA810645375",
				"MLA844702264" };

		ItemDTO itemA = ItemDTO.builder().id("MLA803174898").price(100f).build();
		ItemDTO itemB = ItemDTO.builder().id("MLA803174894").price(1000f).build();
		ItemDTO itemC = ItemDTO.builder().id("MLA803174788").price(100f).build();
		ItemDTO itemD = ItemDTO.builder().id("MLA803086664").price(100f).build();
		ItemDTO itemE = ItemDTO.builder().id("MLA810645375").price(16999f).build();
		ItemDTO itemF = ItemDTO.builder().id("MLA844702264").price(100f).build();

		CouponDTO couponIn = CouponDTO.builder().item_ids(itemsIn).amount(1000f).build();
		List<ItemResponseDTO> itemsResponse = Arrays.asList(ItemResponseDTO.builder().code(200).body(itemA).build(),
				ItemResponseDTO.builder().code(200).body(itemB).build(),
				ItemResponseDTO.builder().code(200).body(itemC).build(),
				ItemResponseDTO.builder().code(200).body(itemD).build(),
				ItemResponseDTO.builder().code(200).body(itemE).build(),
				ItemResponseDTO.builder().code(200).body(itemF).build());

		Map<String, Float> itemsMap = new HashMap<String, Float>();

		itemsMap.put("MLA803174898", 100f);
		itemsMap.put("MLA803174898", 1000f);
		itemsMap.put("MLA803174898", 100f);
		itemsMap.put("MLA803174898", 100f);
		itemsMap.put("MLA803174898", 16999f);
		itemsMap.put("MLA803174898", 100f);

		List<String> resultActual = new ArrayList<>();

	
		resultActual.add("MLA844702264");
		resultActual.add("1000.0");

		String[] resultIds = { "MLA844702264" };
		CouponDTO couponActual = CouponDTO.builder().item_ids(resultIds).amount(1000f).build();

		when(this.consumerService.getItems(couponIn)).thenReturn(itemsResponse);
		when(this.utilityService.calculate(Mockito.anyMap(), Mockito.anyFloat())).thenReturn(resultActual);

		CouponDTO couponExpected = this.couponServiceImpl.evaluateCoupon(couponIn);

		assertEquals(couponExpected, couponActual);
		assertEquals(couponExpected, couponActual);

	}
}
