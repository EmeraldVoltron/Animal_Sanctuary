/**
 * @author Abigail Boggs - amboggs
 * CIS175 - Spring 2023
 * Apr 16, 2023
 */
package animal.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

<<<<<<< Updated upstream
=======
import animal.beans.Address;
import animal.beans.Adopter;
import animal.beans.AdopterApplicationForm;
import animal.beans.ContactMessage;
>>>>>>> Stashed changes
import animal.beans.Employee;
import animal.beans.EmployeeLoginForm;
import animal.repository.EmployeeRepository;
import jakarta.servlet.http.HttpSession;

/**
 * @author abbyb
 *
 */
@Controller
public class EmployeeWebController {
	@Autowired
	EmployeeRepository employeeRepository;
	
	//AMB 4/11 - added employee login webcontrollers
    @GetMapping("/registerEmployee")
    public String showEmployeeRegistrationForm(Model model) {
    	model.addAttribute("employee", new Employee());
        return "/registerEmployee";
    }

    @PostMapping("/registerEmployee")
    public String submitEmployeeRegistrationForm(@ModelAttribute("employee") @Valid Employee employee, BindingResult result){
    	if(result.hasErrors()) {
    		return "/registerEmployee";
    	}
    	employeeRepository.save(employee);
    	return "redirect:/employeeLogin";
    }
    
	@GetMapping("/employees/{username}")
	public ResponseEntity<Employee> getEmployeeByUsername(@PathVariable String username) {
		Optional<Employee> optionalEmployee = employeeRepository.findByUsername(username);

	    if (optionalEmployee.isPresent()) {
	    	Employee employee = optionalEmployee.get();
	        return new ResponseEntity<>(employee, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

    //gets employeelogin link and shows the login form
    @GetMapping("/employeeLogin")
    public String showEmployeeLoginForm(Model model) {
        model.addAttribute("employeeLoginForm", new EmployeeLoginForm());
        return "employeeLogin";
    }
	    
    //when form is submitted, if the form has errors it is returned back to the login form, if it is set to go, then the employee
    //is redirected to the employee menu
    @PostMapping("/employeeLogin")
    public String submitEmployeeLoginForm(@ModelAttribute("employeeLoginForm") @Valid EmployeeLoginForm employeeLoginForm, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "employeeLogin";
        }
        Optional<Employee> optionalEmployee = employeeRepository.findByUsername(employeeLoginForm.getUsername());
        if (optionalEmployee.isPresent() && optionalEmployee.get().getPassword().equals(employeeLoginForm.getPassword())) {
            Employee employee = optionalEmployee.get();
            session.setAttribute("employee", employee);
            return "redirect:/employeeMenu";
        } else {
            result.rejectValue("password", "error.invalidLogin", "Invalid username or password.");
            return "employeeLogin";
        }
    }
     
    @GetMapping("/employeeMenu")
    public String showEmployeeMenu(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            return "redirect:/employeeLogin";
        }
        model.addAttribute("employee", employee);
        return "employeeMenu";
    }
    
    
    @GetMapping("/employeeLogout")
    public String employeeLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/employeeLogin";
    }
<<<<<<< Updated upstream
=======
    
    @GetMapping("/updatePasswordEmployee")
    public String showEmployeeUpdatePasswordForm(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            return "redirect:/employeeLogin";
        }
        model.addAttribute("employee", employee);
        return "updatePasswordEmployee";
    }
    
    @PostMapping("/employee/updatePassword")
    public String updateEmployeePassword(@RequestParam String oldPassword, @RequestParam String newPassword, HttpSession session, Model model) {
    	Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            return "redirect:/employeeLogin";
        }
        if (!employee.getPassword().equals(oldPassword)) {
            model.addAttribute("error", "Incorrect old password");
            model.addAttribute("employee", employee);
            return "employeeMenu";
        }
        employee.setPassword(newPassword);
        employeeRepository.save(employee);
        
        session.setAttribute("employee", employee);
        model.addAttribute("employee", employee);
        model.addAttribute("message", "Password updated successfully");
        model.addAttribute("showPopup", true);
        return "updatePasswordEmployee";
    }
    
    @GetMapping("/updateEmailEmployee")
    public String showUpdateEmailForm(Model model, HttpSession session) {
    	Employee employee = (Employee) session.getAttribute("employee");
    	if (employee == null) {
    		return "redirect:/employeeLogin";
    }
    	model.addAttribute("employee", employee);
    	model.addAttribute("email", employee.getEmail());
    	return "updateEmailEmployee";
    }
    
    @PostMapping("/employee/updateEmail")
    public String updateEmployeeEmail(@ModelAttribute String email, HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            return "redirect:/employeeLogin";
        }
        employee.setEmail(email);
        employeeRepository.save(employee);
        session.setAttribute("employee", employee);
        model.addAttribute("employee", employee);
        model.addAttribute("message", "Email updated successfully");
        model.addAttribute("showPopup", true);
        return "updateEmailEmployee";
    }
    
    @GetMapping("/updatePhoneEmployee")
    public String showUpdateEmployeeForm(Model model, HttpSession session) {
    	Employee employee = (Employee) session.getAttribute("employee");
    	if (employee == null) {
    		return "redirect:/employeeLogin";
    }
    	model.addAttribute("employee", employee);
    	model.addAttribute("phone", employee.getPhone());
    	return "updatePhoneEmployee";
    }
    
    @PostMapping("/employee/updatePhone")
    public String updateAdopterAddress(@ModelAttribute String phone, HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            return "redirect:/employeeLogin";
        }
        employee.setPhone(phone);
        employeeRepository.save(employee);
        session.setAttribute("employee", employee);
        model.addAttribute("employee", employee);
        model.addAttribute("message", "Email updated successfully");
        model.addAttribute("showPopup", true);
        return "updatePhoneEmployee";
    }
    
>>>>>>> Stashed changes
}
