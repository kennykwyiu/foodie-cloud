package com.kenny.enums;

/**
 * @Description: Order status enum
 */
public enum OrderStatusEnum {

	WAIT_PAY(10, "Pending payment"),
	WAIT_DELIVER(20, "Paid, waiting for shipment"),
	WAIT_RECEIVE(30, "Shipped, waiting for receipt"),
	SUCCESS(40, "Transaction successful"),
	CLOSE(50, "Transaction closed");

	public final Integer type;
	public final String value;

	OrderStatusEnum(Integer type, String value){
		this.type = type;
		this.value = value;
	}

}
