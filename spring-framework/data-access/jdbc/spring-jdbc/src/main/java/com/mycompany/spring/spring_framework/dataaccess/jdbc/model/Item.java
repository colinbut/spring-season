/**
 * 
 */
package com.mycompany.spring.spring_framework.dataaccess.jdbc.model;

import java.math.BigDecimal;

/**
 * @author colin
 *
 */
public class Item {

	private int itemId;
	private String itemName;
	private BigDecimal itemPrice;
	
	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public BigDecimal getItemPrice() {
		return itemPrice;
	}
	
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public String toString() {
		return "Items [itemId=" + itemId + ", itemName=" + itemName
				+ ", itemPrice=" + itemPrice + "]";
	}
	
	
	
	
}
