package com.cargopooling.jelastic.ant;

/**
 *
 *
 *    Copyright 2015 - Cargopoooling, Inc. - U.S.A.
 *    Author: Cristian Costantini
 *    www.cargopooling.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 *
 **/

import com.cargopooling.jelastic.ant.bean.Configuration;
import com.cargopooling.jelastic.ant.util.Authenticate;
import com.jelastic.api.users.response.AuthenticationResponse;
import org.apache.tools.ant.Task;

public class AbstractTask extends Task{

    protected final Configuration configuration = new Configuration();

    public String getEmail() {
        return configuration.getEmail();
    }

    public void setEmail(String email) {
        this.configuration.setEmail( email );
    }

    public String getPassword() {
        return configuration.getPassword();
    }

    public void setPassword(String password) {
        this.configuration.setPassword( password );
    }

    public String getContext() {
        return configuration.getContext();
    }

    public void setContext(String context) {
        this.configuration.setContext( context );
    }

    public String getEnvironment() {
        return configuration.getEnvironment();
    }

    public void setEnvironment(String environment) {
        this.configuration.setEnvironment( environment );
    }

    public String getApihoster() {
        return configuration.getApihoster();
    }

    public void setApihoster(String apihoster) {
        this.configuration.setApihoster( apihoster );
    }

    public String getPlatformAppId() {
        return configuration.getPlatformAppId();
    }

    public void setPlatformAppId(String platformAppId) {
        this.configuration.setPlatformAppId( platformAppId );
    }

    protected AuthenticationResponse signin(){

        Authenticate authenticateService = new Authenticate( configuration.getPlatformAppId() , configuration.getApihoster() );

        AuthenticationResponse response = authenticateService.signin( configuration.getEmail(), configuration.getPassword() );

        if( response.isOK() ) {

            log("------------------------------------------------------------------------");
            log("   Authentication : SUCCESS");
            log("          Session : " + response.getSession());
            log("              Uid : " + response.getUid());
            log("------------------------------------------------------------------------");

        }else {

            log("------------------------------------------------------------------------");
            log("   Authentication : FAILED");
            log("            Email : " + configuration.getEmail());
            log("            Error : " + response.getError());
            log("------------------------------------------------------------------------");

        }

        return response;
    }
    
}
