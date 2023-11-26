package smartjob.cl.systemUsers.entity;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name= "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String name;

    private String email;

    private String password;

    private LocalDateTime dateCreated;

    private LocalDateTime lastModified;

    private LocalDateTime lastLogin;

    @Column(name = "userIsActive", columnDefinition = "boolean default false")
    private Boolean userIsActive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "fk_user_id", referencedColumnName = "user_id")
    private List<PhonesEntity> phones;


}
