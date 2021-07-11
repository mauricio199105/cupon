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
	 * Este metodo permite adicionar el item
	 * 
	 * @param item
	 */
	public void addElement(ElementBackpack item) {

		for (int i = 0; i < this.elements.length; i++) {
			if (this.elements[i] == null) {
				this.elements[i] = item; // lo anade
				this.benefit += item.getBenefit(); // aumenta el beneficio
				this.weight += item.getWeight(); // Aumenta el peso
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
	 * Elimina elemento ingresado por parametro
	 * 
	 * @param element
	 */
	public void deleteElement(ElementBackpack element) {
		for (int i = 0; i < this.elements.length; i++) {
			if (this.elements[i].equals(element)) {
				this.elements[i] = null; // el elemento fuera
				this.weight -= element.getWeight(); // Reduce el peso
				this.benefit -= element.getBenefit(); // reduce el beneficio
				break;
			}
		}
	}

	/**
	 * Indica si existe un elemento
	 * 
	 * @param element
	 * @return
	 */
	public boolean existElement(ElementBackpack element) {
		boolean estado = false;
		for (int i = 0; i < this.elements.length; i++) {
			if (this.elements[i] != null && this.elements[i].equals(element)) {
				estado = true;
			}
		}
		return estado;
	}

}
