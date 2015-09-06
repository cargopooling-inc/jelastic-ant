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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cargopooling.jelastic.ant.AbstractTestCase;

import com.jelastic.api.users.response.AuthenticationResponse;


public class EnvironmentSearchTest extends AbstractTestCase{

    private AuthenticationResponse response;

    private Logger logger = LoggerFactory.getLogger( EnvironmentSearchTest.class );

    @Before
    public void auth() {

        Authenticate auth = new Authenticate( configuration.getPlatformAppId(), configuration.getApihoster() );

        this.response = auth.signin( configuration.getEmail(), configuration.getPassword() );

        logger.info("-----------------------------------------------------------");
        logger.info("                      SIGN IN");
        logger.info("-----------------------------------------------------------");
        logger.info("             Connected : " + response.isOK() );
        logger.info("              Session  : " + response.getSession() );
        logger.info("-----------------------------------------------------------");

    }

    @Test
    public void find(){

        // connect to API

        EnvironmentSearch envSearch = new EnvironmentSearch( configuration );

        String result = envSearch.find( response.getSession() );

        logger.info("-----------------------------------------------------------");
        logger.info("                   ENVIRONMENT FIND");
        logger.info("-----------------------------------------------------------");
        logger.info("      Environment Name : " + configuration.getEnvironment() );
        logger.info("    Environment Result : " + result );
        logger.info("-----------------------------------------------------------");

        Assert.assertNotEquals( "not-found", result );

    }

}
