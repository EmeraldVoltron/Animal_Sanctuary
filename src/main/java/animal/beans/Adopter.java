package animal.beans;

/**
 * @author xbitt = twilkens
 * CIS171 - Spring 2023
 * Apr 4, 2023
 */


import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Adopter {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@Autowired
	private Address address;
	private boolean hasCriminalRecord = false;
	private String username;
    private String password;
	
	public Adopter(String name) {
	}
	
	public Adopter(String name, Address address, boolean hasCriminalRecord) {
		
	}
	
	public Adopter(Address address) {
		
	}

}
