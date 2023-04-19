package animal.beans;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class ContactForm {
    private String name;
    private String email;
    private String reason;
    private String animalName;
    private String message;

    public ContactForm(String name, String email, String reason,String animalName, String message) {
        this.name = name;
        this.email = email;
        this.reason = reason;
        this.animalName = animalName;
        this.message = message;
    }
  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
