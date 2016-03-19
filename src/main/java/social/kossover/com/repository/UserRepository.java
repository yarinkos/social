package social.kossover.com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import social.kossover.com.model.User;

public interface UserRepository extends MongoRepository<User, String> {

   /* public User findByFirstName(String firstName);
    public List<User> findByLastName(String lastName);*/

    public User findByUsername(String username);
   // public List<User> findById(String id);

}