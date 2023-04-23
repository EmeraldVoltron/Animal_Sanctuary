/**
 * @author Abigail Boggs - amboggs
 * CIS175 - Spring 2023
 * Apr 4, 2023
 */
package animal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import animal.beans.Animals;




/**
 * @author abbyb
 *
 */

//@Repository
public interface AnimalRepository extends JpaRepository<Animals, Long> {
	List<Animals> findByBreedContainingIgnoreCaseOrSpeciesContainingIgnoreCaseOrColorContainingIgnoreCase(String breed, String species, String color);


}




