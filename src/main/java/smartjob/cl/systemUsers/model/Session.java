package smartjob.cl.systemUsers.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Session {

    private String sessionId;
    private String token;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private boolean isActive;

}
