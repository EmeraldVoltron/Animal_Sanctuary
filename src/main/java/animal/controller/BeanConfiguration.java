/**
 * @author xbitt = twilkens
 * CIS171 - Spring 2023
 * Apr 4, 2023
 */
package animal.controller;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import animal.beans.Address;
import animal.beans.Adopter;
import animal.beans.Animals;



@Configuration
public class BeanConfiguration {
	
	@Bean
	public Adopter adopter() {
		Adopter bean = new Adopter("Tate Wilkens");
		return bean;
	}
	
	@Bean
	public Address address() {
		Address bean = new Address("2640 1st Ave S APT 110", "Altoona", "IA");
		return bean;
	}
	
	@Bean
	public Animals animals() {
		Animals bean = new Animals("Caribou");
		return bean;
	}

}
