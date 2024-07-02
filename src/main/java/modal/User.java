package modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter 
@Entity
public class User {
    @Id
    int userId;
    String userName;
    int age;
}
