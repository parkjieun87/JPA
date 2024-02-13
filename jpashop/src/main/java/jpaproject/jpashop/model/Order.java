package jpaproject.jpashop.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
	
	@Id@GeneratedValue
	@Column(name="order_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	@OneToMany(mappedBy = "Order")
	private List<OrderItems> orderItems = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name="delivery_id")
	private Delivery delivery;
	
	private LocalDate orderdate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	
	
	
	
}