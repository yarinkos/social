/*
 * *
 *  * Copyright (c) 2015 Ivan Hristov
 *  * <p/>
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  * <p/>
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  * <p/>
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package social.kossover.com.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import social.kossover.com.model.User;
import social.kossover.com.repository.UserRepository;

@Service
public class MongoDBUserDetailsService implements UserDetailsService {




    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails loadedUser;

        try {
            //User user = repository.findOne("{#: #}", User.USERNAME, username).as(User.class);
            User user = repository.findByUsername(username);
            loadedUser = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles());
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }
}
