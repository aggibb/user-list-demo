package name.aggibb.userlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by agibb on 2016-10-05.
 */
@Component
public class DatabaseLoader implements CommandLineRunner {
    private UserRepository repository;

    @Autowired
    public DatabaseLoader(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        this.repository.save(new User("Arnold", "Palmer", LocalDate.of(1980, 4, 1)));
        this.repository.save(new User("Sally", "Watkins", LocalDate.of(1981, 9, 12)));
        this.repository.save(new User("Chris", "White", LocalDate.of(1969, 1, 31)));
        this.repository.save(new User("Sally", "Arnold", LocalDate.of(2001, 7, 18)));
    }

}
