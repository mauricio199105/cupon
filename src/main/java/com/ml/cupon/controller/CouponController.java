package com.ml.cupon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ml.cupon.dto.CouponDTO;
import com.ml.cupon.service.CouponService;

@RestController
@RequestMapping("/")
public class CouponController {

	private CouponService couponService;

	public CouponController(CouponService couponService) {
		this.couponService = couponService;

	}

	@PostMapping(value = "/coupon", produces = "application/json")
	public ResponseEntity<Object> evaluateCoupon(@RequestBody CouponDTO coupon) {
		CouponDTO couponResult = this.couponService.evaluateCoupon(coupon);
		if (couponResult.getAmount() > 0) {
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(couponResult);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
					.body("Items not found!.");
		}

	}
	

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<Object> getStats() {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Hola mercadolibre");
	}

}
