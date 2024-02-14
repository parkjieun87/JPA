package jpaproject.jpashop.model.item;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jpaproject.jpashop.exception.NotEnoughStockException;
import jpaproject.jpashop.model.Category;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter 
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public class Item {
	
	@Id @GeneratedValue
	@Column(name="item_id")
	private Long id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();

	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
	
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if(restStock<0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = restStock;
	}
}
