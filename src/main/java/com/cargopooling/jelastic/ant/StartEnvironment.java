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

import com.cargopooling.jelastic.ant.util.EnvironmentSearch;
import com.jelastic.api.Response;
import com.jelastic.api.environment.Environment;
import com.jelastic.api.users.response.AuthenticationResponse;

import org.apache.tools.ant.BuildException;

public class StartEnvironment extends AbstractTask{

    @Override
    public void execute() throws BuildException {

        Environment environmentService = new Environment( configuration.getPlatformAppId() );
        environmentService.setServerUrl( configuration.getApihoster() + "/1.0/" );

        AuthenticationResponse response = signin();

        if( response.isOK() ) {

            EnvironmentSearch environmentSearch = new EnvironmentSearch( configuration );

            final String session = response.getSession();

            final String envAppId = environmentSearch.find(session);

            Response result = environmentService.startEnv( envAppId, session );

            if( result.isOK() ){

                log("Environment start : SUCCESS" );

            }else{

                log("Environment start : FAILED" );
                log("            Error : " + result.getError() );

            }

        }

    }

}
