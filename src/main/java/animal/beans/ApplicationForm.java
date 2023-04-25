/**
 * @author Abigail Boggs - amboggs
 * CIS175 - Spring 2023
 * Apr 25, 2023
 */
package animal.beans;

import javax.validation.constraints.NotEmpty;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author abbyb
 *
 */
@Embeddable
@Data
@NoArgsConstructor
@Entity
public class ApplicationForm {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String home;
	private String street;
	private String city;
	private String state;
	private String age;
	private String adoptee;
	private String status;
	
	public ApplicationForm(String name, String home, String street, String city, String state, String age, String adoptee, String status) {
		this.name = name;
	    this.home = home;
	    this.street = street;
	    this.city = city;
	    this.state = state;
	    this.age = age;
	    this.adoptee = adoptee;
	    this.status = "pending";
	}
}
