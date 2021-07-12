package com.ml.cupon.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Builder
public class ElementBackpack {

	private float weight;
	private int benefit;
	private String id;

	public ElementBackpack(float weight, int benefit, String id) {
		this.weight = weight;
		this.benefit = benefit;
		this.id = id;
	}


}
