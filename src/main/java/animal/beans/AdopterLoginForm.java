package animal.beans;


import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AdopterLoginForm {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
