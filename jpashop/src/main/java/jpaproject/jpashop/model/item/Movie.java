package jpaproject.jpashop.model.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("M")
public class Movie extends Item{

	private String director;
	private String actor;
}