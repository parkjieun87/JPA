package jpaproject.jpashop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jpaproject.jpashop.model.item.Item;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="order_item")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
	
	@Id@GeneratedValue
	@Column(name="order_item_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;
	
	private int orderPrice;
	private int count;
	
	//생성 메서드
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);
		
		item.removeStock(count);
		return orderItem;
	}
	
	//주문취소
	public void cancle() {
		getItem().addStock(count);
	}
	
	//전체가격 조회
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}
}