/**
 * @author Abigail Boggs - amboggs
 * CIS175 - Spring 2023
 * Apr 4, 2023
 */
package animal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import animal.beans.Adopter;
import animal.beans.Animals;
import animal.beans.Employee;




/**
 * @author abbyb
 *
 */
//@Repository
public interface AnimalRepository extends JpaRepository<Animals, Long>{
	
}
