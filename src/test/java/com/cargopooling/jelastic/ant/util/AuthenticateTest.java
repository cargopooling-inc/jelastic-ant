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
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cargopooling.jelastic.ant.AbstractTestCase;

import com.jelastic.api.users.response.AuthenticationResponse;

public class AuthenticateTest extends AbstractTestCase {

    private Logger logger = LoggerFactory.getLogger( AuthenticateTest.class );

    @Test
    public void signin(){

        Authenticate auth = new Authenticate( configuration.getPlatformAppId(), configuration.getApihoster() );

        AuthenticationResponse resp = auth.signin( configuration.getEmail(), configuration.getPassword() );

        Assert.assertTrue( resp.isOK() );

        logger.info( resp.getEmail() );
        logger.info( resp.getSession() );

    }

}
