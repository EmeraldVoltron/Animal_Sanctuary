/**
 *@author Riley Ahlrichs riahl12 - rpahlrichs
 *CIS175 - Spring 2023
 *Apr 11, 2023
 */
package animal.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author riley
 *Employee class to gather basic information
 */
@Entity
@Data
@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String phone;
	private String email;
	private String username;
    private String password;
	
	public Employee(String name) {
		super();
		this.name = name;
	}
	
	public Employee(String name, String phone, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public Employee(long id, String name, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

}
