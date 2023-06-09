/**
 *@author Riley Ahlrichs riahl12 - rpahlrichs
 *CIS175 - Spring 2023
 *Apr 18, 2023
 */
package animal.beans;

import javax.validation.constraints.NotEmpty;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author riley
 *
 */
@Data
@Entity
@Embeddable
@Table(name="adopter_application_form")
public class AdopterApplicationForm {
	@Id
	@GeneratedValue
	private long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String home;
	@NotEmpty
	private String street;
	@NotEmpty
	private String city;
	@NotEmpty
	private String state;
	@NotEmpty
	private String age;
	@NotEmpty
	private String adoptee;
	@NotEmpty
	private String status = "Pending";
}
