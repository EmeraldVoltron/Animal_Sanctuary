/**
 *@author Riley Ahlrichs riahl12 - rpahlrichs
 *CIS175 - Spring 2023
 *Apr 25, 2023
 */
package animal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import animal.beans.AdopterApplicationForm;
import animal.beans.ApplicationForm;

/**
 * @author riley
 *
 */
@Repository
public interface ApplicationRepository extends JpaRepository<AdopterApplicationForm, Long>{

}
