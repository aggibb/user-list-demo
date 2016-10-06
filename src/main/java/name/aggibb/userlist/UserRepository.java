package name.aggibb.userlist;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by agibb on 2016-10-05.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    List<User> findUsersByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
