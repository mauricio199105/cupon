package com.ml.cupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ml.cupon.dto.CouponDTO;
import com.ml.cupon.dto.CouponDtoResponse;
import com.ml.cupon.service.CouponService;

@RestController
@RequestMapping("/")
public class CouponController {

	@Autowired
	private CouponService couponService;

	public CouponController(CouponService couponService) {
		this.couponService = couponService;

	}

	@PostMapping(value = "/coupon", produces = "application/json")
	public ResponseEntity<Object> evaluateCoupon(@RequestBody @Validated CouponDTO coupon) {
		if (this.validateParametersIn(coupon)) {
			CouponDtoResponse couponResult = this.couponService.evaluateCoupon(coupon);
			if (couponResult.getTotal() > 0) {
				return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(couponResult);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
						.body("Items not found!");
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
					.body("Check the income items please!");
		}

	}

	private boolean validateParametersIn(CouponDTO coupon) {
		boolean status = false;
		if ((coupon.getItem_ids() != null && coupon.getItem_ids().length > 0) && (coupon.getAmount() > 0f)) {
			status = true;
		}
		return status;
	}

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<Object> getIndex() {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Hola mercadolibre");
	}

}
