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

public class StopEnvironment extends AbstractTask{

    private Environment environmentService;

    @Override
    public void execute() throws BuildException {

        environmentService = new Environment( configuration.getPlatformAppId() );
        environmentService.setServerUrl( configuration.getApihoster() + "/1.0/" );

        EnvironmentSearch environmentSearch = new EnvironmentSearch(configuration);

        AuthenticationResponse response = signin();

        if( response.isOK() ) {

            final String session = response.getSession();

            final String envAppId = environmentSearch.find(session);

            Response result = environmentService.stopEnv( envAppId, session, 2 );

            if( result.isOK() ){

                log("Environment stop : SUCCESS" );

            }else{

                log("Environment stop  : FAILED" );
                log("            Error : " + result.getError() );

            }

        }

    }

}
