package animal.beans;

/**
 * @author xbitt = twilkens
 * CIS171 - Spring 2023
 * Apr 4, 2023
 */


import java.sql.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xbitt
 *
 */
@Entity
@Data
@NoArgsConstructor
public class Animals {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String breed;
	private String species;
	private int age;
	private Date arrivalDate;
	private boolean goodWithKids;
	
	
	public Animals(String name, String species, int age) {
		super();
		this.name = name;
		this.species = species;
		this.age = age;
	}
	
	public Animals(String name, String breed, String species, int age, Date arrivalDate, boolean goodWithKids) {
		super();
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.arrivalDate = arrivalDate;
		
		this.species = species;
		this.goodWithKids = goodWithKids;
	}
	
	public Animals(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public Animals(String name) {
		this.name = name;
	}
}
	
