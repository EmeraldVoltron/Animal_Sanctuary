/**
 * @author Abigail Boggs - amboggs
 * CIS175 - Spring 2023
 * Apr 16, 2023
 */
package animal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import animal.beans.Employee;

/**
 * @author abbyb
 *
 */
//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByUsername(String username);
}
