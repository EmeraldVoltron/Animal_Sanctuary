/**
 *@author Riley Ahlrichs riahl12 - rpahlrichs
 *CIS175 - Spring 2023
 *Apr 18, 2023
 */
package animal.beans;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @author riley
 *
 */
@Data
public class AdopterApplicationForm {
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
}
