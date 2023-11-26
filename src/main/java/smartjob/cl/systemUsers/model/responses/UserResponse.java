package smartjob.cl.systemUsers.model.responses;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long userId;

    private LocalDateTime created;

    private LocalDateTime modified;

    private LocalDateTime last_login;

    private String token;

    private Boolean isactive;

    private String message;


}
