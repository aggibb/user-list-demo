package name.aggibb.userlist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by agibb on 2016-10-05.
 */
@Data
@Entity
public class User {
    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    @Transient
    @JsonIgnore
    private final DateTimeFormatter dateOfBirthFormat = DateTimeFormatter.ofPattern("YYYY-MM-dd");

    private User(){};

    public User(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public User(String firstName, String lastName, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, dateOfBirthFormat);
    }
}
