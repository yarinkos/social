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

package social.kossover.com.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Document
public class User {

    @Id
    private String id;
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ROLES = "roles";
    @Getter
    @Setter
    @NotEmpty @NotNull
    @Size(min=2, max=15)
    @Indexed(unique = true)
    @JsonProperty(USERNAME)
    private String username;

    @Getter
    @Setter
    @NotEmpty @NotNull
    @Size(min=6, max=12)
    @JsonProperty(PASSWORD)
    private String password;

    @Getter
    @Setter
    @NotEmpty @NotNull
    @Email
    @Indexed(unique = true)
    @JsonProperty(EMAIL)
    private String email;

    @Getter
    @Setter
    @JsonProperty(ROLES)
    /*@JsonSerialize(contentUsing = GrantedAuthoritySerializer.class)
    @JsonDeserialize(contentUsing = GrantedAuthorityDeserializer.class)*/
    private List<GrantedAuthority> roles ;




    public List<GrantedAuthority> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("password", password)
                .append("email", email)
                .append("roles", roles)
                .toString();
    }

}
