package smartjob.cl.systemUsers.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserSession {

    private Long sessionId;
    private String token;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private boolean isActive;

}
