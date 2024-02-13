package jpaproject.jpashop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.criteria.Order;
import jpaproject.jpashop.model.item.Item;
import lombok.Data;

@Data
@Entity
public class OrderItems {
	
	@Id@GeneratedValue
	@Column(name="order_item_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	private int orderPrice;
	private int count;
}
