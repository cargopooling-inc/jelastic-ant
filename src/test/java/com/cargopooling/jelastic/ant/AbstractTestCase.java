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
import org.junit.Before;

public class AbstractTestCase {

    protected Configuration configuration;

    @Before
    public void setUp() throws Exception {

        configuration = new Configuration();

        configuration.setPlatformAppId( "1dd8d191d38fff45e62564fcf67fdcd6" );
        configuration.setApihoster( "https://app.jelastic.provider.name" );
        configuration.setEmail( "your@email.ext" );
        configuration.setPassword( "password" );
        configuration.setEnvironment( "environment domain" );


    }

}
