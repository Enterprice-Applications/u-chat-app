package lk.ijse.dep11.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageTO implements Serializable {
    @NotBlank(message = "Chat message can't be empty")
    private String message;
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Invalid Email")
    private String email;

}