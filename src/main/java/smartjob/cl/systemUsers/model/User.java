package smartjob.cl.systemUsers.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    private Long userId;
    private String name;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Formato de correo electrónico inválido")
    private String email;
    private String password;
    private List<Phones> phones;

}
