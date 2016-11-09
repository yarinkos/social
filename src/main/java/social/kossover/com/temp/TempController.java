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

package social.kossover.com.temp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import social.kossover.com.model.User;
import social.kossover.com.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TempController {


    @Autowired
    private UserRepository repository;

    /*@Secured({"ROLE_ADMIN"})
    @RequestMapping("/resource")
    public Map<String, Object> home(@AuthenticationPrincipal UserDetails userDetails) {
        //User user = repository.findOne("{#: #}", User.USERNAME, userDetails.getUsername()).as(User.class);
        User user = repository.findByUsername(userDetails.getUsername());
        Map<String, Object> model = new HashMap<>();
        model.put("roles", user.getRoles());
        return model;
    }*/

    @RequestMapping("/greeting5")
    public Map<String, Object> home1(@AuthenticationPrincipal UserDetails userDetails) {
        //User user = repository.findOne("{#: #}", User.USERNAME, userDetails.getUsername()).as(User.class);
        User user = repository.findByUsername(userDetails.getUsername());
        Map<String, Object> model = new HashMap<>();
        model.put("roles", user.getRoles());
        return model;
    }

    @RequestMapping("/greeting6")
    public Greeting greeting(/*@RequestParam(value="name", defaultValue="World") String name*/) {
        return new Greeting(3434,
                String.format("Dfdf", "name"));
    }

    @RequestMapping(value = "country", method = RequestMethod.GET)
    public ModelAndView country() {
        return new ModelAndView("countryForm", "country", new Country());
    }

    @RequestMapping(value = "saveCountry", method = RequestMethod.POST)
    public ModelMap saveCountry(@ModelAttribute("country") Country country, ModelMap model) {
        model.addAttribute("countryName", country.getCountryName());
        model.addAttribute("pmName", country.getPmName());
        return model;
    }

    @RequestMapping(value = "saveCountry1", method = RequestMethod.POST)
    public ModelMap saveCountry1( Country country, ModelMap model) {
        model.addAttribute("countryName", country.getCountryName());
        model.addAttribute("pmName", country.getPmName());
        return model;
    }





}
