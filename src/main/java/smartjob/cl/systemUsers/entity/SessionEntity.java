package smartjob.cl.systemUsers.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name= "session")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    private String name;

    private String email;

    private String password;
    private String token;
    private LocalDateTime modified;
    private boolean active;

    private LocalDateTime dateCreated;

    private LocalDateTime lastModified;

    private LocalDateTime lastLogin;

    @Column(name = "userIsActive", columnDefinition = "boolean default false")
    private Boolean userIsActive;

}
