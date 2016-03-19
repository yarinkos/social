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

package social.kossover.com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import social.kossover.com.model.User;
import social.kossover.com.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ResourceController {


    @Autowired
    private UserRepository repository;

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/resource")
    public Map<String, Object> home(@AuthenticationPrincipal UserDetails userDetails) {
        //User user = repository.findOne("{#: #}", User.USERNAME, userDetails.getUsername()).as(User.class);
        User user = repository.findByUsername(userDetails.getUsername());
        Map<String, Object> model = new HashMap<>();
        model.put("roles", user.getRoles());
        return model;
    }


    /*@RequestMapping("/login")
    public void greeting1(@RequestParam(value="name", defaultValue="World") String name) {
        System.out.print(true);
    }
*/
    @RequestMapping("/resource/do")
    public void greeting2(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.print(true);
    }


}
