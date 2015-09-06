package com.cargopooling.jelastic.ant.util;

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

import com.jelastic.api.environment.Environment;
import com.jelastic.api.environment.response.EnvironmentInfoResponse;
import com.jelastic.api.environment.response.EnvironmentInfoResponses;

import java.util.Iterator;

public class EnvironmentSearch {

    private final Configuration configuration;
    private Environment environmentService;


    public EnvironmentSearch( Configuration configuration ){

        environmentService = new Environment( configuration.getPlatformAppId() );
        environmentService.setServerUrl( configuration.getApihoster() + "/1.0/" );

        this.configuration = configuration;

    }

    public String find( String session ){

        EnvironmentInfoResponses response = environmentService.getEnvs( session );

        Iterator<EnvironmentInfoResponse> iterator = response.getResponses().iterator();

        while ( iterator.hasNext() ){

            EnvironmentInfoResponse temp = iterator.next();

            if( temp.getAppNodes().getDomain().getDomain().equals( configuration.getEnvironment() ) ){

                return temp.getAppNodes().getAppid();

            }

        }

        return "not-found";
    }

}
