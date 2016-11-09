package social.kossover.com.controllers.security.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import social.kossover.com.controllers.handlers.exepctions.BaseException;
import social.kossover.com.model.User;
import social.kossover.com.repository.UserRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yarin.kossover on 3/19/2016.
 */


//@ControllerAdvice //todo remove
@RestController
public class Registration {

    @Autowired
    private UserRepository userRepository;

    /*@RequestMapping(method = RequestMethod.POST, value = "/sighup")
    //@RequestMapping("/register")
    public void sighup(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("email") String email) {
        System.out.print(true);
        addUser(username,password,email);
    }*/


    //http://localhost:8080/signup
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/sighup")
    public User sighup(@Valid @ModelAttribute("user") User user) {
        userRepository.findByEmail(user.getEmail());
        System.out.print(true);

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER_ROLE"));
        user.setRoles(authorities);
       /* if (bindingResult.hasErrors()) {

            return "form";
        }*/
        if (userRepository.findByUsername(user.getUsername()) != null)
            throw new BaseException("username is already exists");
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new BaseException("Email address is already exists");
        try {
            addUser(user);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        }
        // return "Registration Done";
        return user;
    }


    private void addUser(User user) throws DuplicateKeyException {
        userRepository.save(user);
    }


}
