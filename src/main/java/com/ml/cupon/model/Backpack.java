package com.ml.cupon.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Backpack {

	private float maxWeight;
	private ElementBackpack[] elements;

	private float weight;
	private int benefit;

	public Backpack(float maxWeight, int numberElements) {
		this.maxWeight = maxWeight;
		this.elements = new ElementBackpack[numberElements];
		this.benefit = 0;
		this.weight = 0;
	}

	/**
	 * We clean the backpack
	 * 
	 * @param item
	 */
	public void addElement(ElementBackpack item) {

		for (int i = 0; i < this.elements.length; i++) {
			if (this.elements[i] == null) {
				this.elements[i] = item;
				this.benefit += item.getBenefit();
				this.weight += item.getWeight();
				break;
			}
		}
	}

	/**
	 * Limpiamos la mochila
	 */
	public void clearBakcpack() {
		this.weight = 0;
		this.benefit = 0;
		for (int i = 0; i < this.elements.length; i++) {
			this.elements[i] = null;
		}
	}

	/**
	 * Delete element entered by parameter
	 * 
	 * @param element
	 */
	public void deleteElement(ElementBackpack element) {
		for (int i = 0; i < this.elements.length; i++) {
			if (this.elements[i].equals(element)) {
				this.elements[i] = null;
				this.weight -= element.getWeight();
				this.benefit -= element.getBenefit();
				break;
			}
		}
	}

	/**
	 * Indicates if an element exists
	 * 
	 * @param element
	 * @return
	 */
	public boolean existElement(ElementBackpack element) {
		boolean status = false;
		for (int i = 0; i < this.elements.length; i++) {
			if (this.elements[i] != null && this.elements[i].equals(element)) {
				status = true;
			}
		}
		return status;
	}

}
