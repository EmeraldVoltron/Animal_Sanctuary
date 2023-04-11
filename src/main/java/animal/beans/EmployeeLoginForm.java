/**
 * @author Abigail Boggs - amboggs
 * CIS175 - Spring 2023
 * Apr 11, 2023
 */
package animal.beans;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @author abbyb
 *
 */
@Data
public class EmployeeLoginForm {
	@NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
