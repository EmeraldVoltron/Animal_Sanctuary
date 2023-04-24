/**
 * @author xbitt = twilkens
 * CIS171 - Spring 2023
 * Apr 23, 2023
 */
package animal.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author xbitt
 *
 */
class EmployeeLoginFormTest {

	private EmployeeLoginForm employeeLoginForm;
    private String firstName = "John";
    private String lastName = "Doe";
    private String username = "johndoe";
    private String password = "password123";

    @Before
    public void setUp() throws Exception {
        employeeLoginForm = new EmployeeLoginForm();
        employeeLoginForm.setFirstname(firstName);
        employeeLoginForm.setLastname(lastName);
        employeeLoginForm.setUsername(username);
        employeeLoginForm.setPassword(password);
    }

    @Test
    public void testGettersSetters() {
        assertNotNull(employeeLoginForm);
        assertEquals(firstName, employeeLoginForm.getFirstname());
        assertEquals(lastName, employeeLoginForm.getLastname());
        assertEquals(username, employeeLoginForm.getUsername());
        assertEquals(password, employeeLoginForm.getPassword());
    }

}
