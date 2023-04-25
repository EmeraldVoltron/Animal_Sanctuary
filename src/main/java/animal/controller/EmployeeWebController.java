/**
 * @author Abigail Boggs - amboggs
 * CIS175 - Spring 2023
 * Apr 16, 2023
 */
package animal.controller;

import java.util.List;
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

import animal.beans.Adopter;
import animal.beans.ContactMessage;
import animal.beans.Employee;
import animal.beans.EmployeeLoginForm;
import animal.repository.AdopterRepository;
import animal.repository.ApplicationRepository;
import animal.repository.ContactMessageRepository;
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
	
	@Autowired
	private ContactMessageRepository contactMessageRepository;
	
	@Autowired
	AdopterRepository adopterRepository;
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	
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
	
	
	/**
	 * Register Employee Methods: 
	 */
	
    @GetMapping("/registerEmployee")
    public String showEmployeeRegistrationForm(Model model) {
    	model.addAttribute("employee", new Employee());
        return "/registerEmployee";
    }

    //registers employee by saving the employee fields into the repo
    @PostMapping("/registerEmployee")
    public String submitEmployeeRegistrationForm(@ModelAttribute("employee") @Valid Employee employee, BindingResult result){
    	if(result.hasErrors()) {
    		return "/registerEmployee";
    	}
    	employeeRepository.save(employee);
    	return "redirect:/employeeLogin";
    }
    
	
    /**
     * Employee Login Methods:
     */

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
    
    //show the employee menu
    @GetMapping("/employeeMenu")
    public String showEmployeeMenu(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            return "redirect:/employeeLogin";
        }
        model.addAttribute("employee", employee);
        return "employeeMenu";
    }
    
    //logout from session
    @GetMapping("/employeeLogout")
    public String employeeLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/employeeLogin";
    }
    
    /**
     * Update Employee Password methods:
     */
    
    @GetMapping("/updatePasswordEmployee")
    public String showEmployeeUpdatePasswordForm(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            return "redirect:/employeeLogin";
        }
        model.addAttribute("employee", employee);
        return "updatePasswordEmployee";
    }
    
    //get the old password and new password. Make sure the oldpassword matches the old password
    //then save the new password to the employee and safe to repo. 
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
    
    /**
     * Update Employee Email methods: 
     */
    
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
    
    //get new email and update current employee's email and save to repo
    @PostMapping("/employee/updateEmail")
    public String updateEmployeeEmail(@RequestParam String newEmail, HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            return "redirect:/employeeLogin";
        }
        employee.setEmail(newEmail);
        employeeRepository.save(employee);
        session.setAttribute("employee", employee);
        model.addAttribute("employee", employee);
        model.addAttribute("message", "Email updated successfully");
        model.addAttribute("showPopup", true);
        return "redirect:/employeeMenu";
    }
    
    /**
     * Update Employee phone number methods: 
     */
    
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
    
    //get the phone number and update the employee's number and save to repo
    @PostMapping("/employee/updatePhone")
    public String updateEmployeePhone(@RequestParam String newPhone, HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            return "redirect:/employeeLogin";
        }
        employee.setPhone(newPhone);
        employeeRepository.save(employee);
        session.setAttribute("employee", employee);
        model.addAttribute("employee", employee);
        model.addAttribute("message", "Phone number updated successfully");
        model.addAttribute("showPopup", true);
        return "redirect:/employeeMenu";
    }

    /**
     * Employee Message viewing methods:
     */
    
    
    @GetMapping("/messages")
    public String viewMessages(Model model) {
        List<ContactMessage> messages = contactMessageRepository.findAll();
        model.addAttribute("messages", messages);
        return "messages";
    }

    @GetMapping("/messages/{id}")
    public String viewMessageDetails(@PathVariable("id") Long id, Model model) {
        Optional<ContactMessage> message = contactMessageRepository.findById(id);
        if (message.isPresent()) {
            model.addAttribute("message", message.get());
            return "message_details";
        } else {
            return "redirect:/messages";
        }
    }

    //delete messages
    @PostMapping("/messages/delete/{id}")
    public String deleteMessage(@PathVariable("id") Long id) {
        contactMessageRepository.deleteById(id);
        return "redirect:/messages";
    }
    
    /**
     * View adopters from employee view
     */
    @GetMapping("/viewAdopters")
    public String viewAdoptersList(Model model) {
    	if(adopterRepository.findAll().isEmpty()) {
    		model.addAttribute("message", "No adopters in system.");
    	}
        model.addAttribute("adopters", adopterRepository.findAll());
        return "adoptersList";
    }
    
    //get the adopter id and delete it 
    @GetMapping("/deleteAdopter/{id}")
    public String deleteAdopter(@PathVariable("id") Long id) {
        Adopter a = adopterRepository.findById(id).orElse(null);
        adopterRepository.delete(a);
        return "redirect:/viewAdopters";
    }
    
    /**
     * RPA - View adoption applications from employee view
     */
    @GetMapping("/viewApplications")
    public String viewApplicationsList(Model model) {
    	if(applicationRepository.findAll().isEmpty()) {
    		return "redirect:/employeeMenu";
    	}
    	model.addAttribute("application", applicationRepository.findAll());
    	return "viewApplications";
    }
    
}
