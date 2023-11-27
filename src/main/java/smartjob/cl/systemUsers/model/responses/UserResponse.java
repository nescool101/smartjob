package smartjob.cl.systemUsers.model.responses;

import lombok.*;
import smartjob.cl.systemUsers.model.UserSession;

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

    private String message;

    private String email;

    private UserSession session;



}
