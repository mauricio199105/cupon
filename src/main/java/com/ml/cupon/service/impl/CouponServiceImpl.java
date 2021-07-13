package com.ml.cupon.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.cupon.consumer.service.ConsumerService;
import com.ml.cupon.dto.CouponDTO;
import com.ml.cupon.dto.CouponDtoResponse;
import com.ml.cupon.dto.ItemResponseDTO;
import com.ml.cupon.service.CouponService;
import com.ml.cupon.service.UtilityService;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private UtilityService utilityService;
	@Autowired
	private ConsumerService consumerService;

	public CouponServiceImpl(UtilityService utilService, ConsumerService consumidorService) {
		this.utilityService = utilService;
		this.consumerService = consumidorService;
	}

	public CouponDtoResponse evaluateCoupon(CouponDTO cupon) {
		
		CouponDtoResponse coupon = new CouponDtoResponse();
		List<ItemResponseDTO> itemsList = this.consumerService.getItems(cupon);
		
		if (!itemsList.isEmpty()) {
			Map<String, Float> items = itemsList.stream().filter(item -> item.getCode() == 200)
					.map(item -> item.getBody())
					.collect(Collectors.toMap(item -> item.getId(), item -> item.getPrice()));

			List<String> itemsEvaluated = this.utilityService.calculate(items, cupon.getAmount());

			float montoUsado = 0.00f;
			String[] productos = new String[itemsEvaluated.size() - 1];

			if (!itemsEvaluated.isEmpty()) {
				montoUsado = Float.valueOf(itemsEvaluated.get(itemsEvaluated.size() - 1));
				itemsEvaluated.remove(itemsEvaluated.get(itemsEvaluated.size() - 1));
				productos = itemsEvaluated.toArray(new String[itemsEvaluated.size()]);
			}

			coupon = CouponDtoResponse.builder().total(montoUsado).item_ids(productos).build();

		}

		return coupon;

	}

}
