/*
 * Copyright (c) 2019.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *
 */

package edu.sdsc.awesome.data.common.util;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;
import org.javaswift.joss.client.factory.AccountConfig;
import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.client.factory.AuthenticationMethod;
import org.javaswift.joss.model.Account;
import org.jclouds.ContextBuilder;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.jclouds.openstack.swift.v1.SwiftApi;



public class SwiftStorageConnector {

    private SwiftApi swiftApi;


    private String provider;
    private String identity;
    private String credential;


    public SwiftStorageConnector(SwiftApi swiftApi, String provider, String identity, String credential) {
        this.swiftApi = swiftApi;
        this.provider = provider;
        this.identity = identity;
        this.credential = credential;

        this.JcloudSWIFT();


    }

    public void JcloudSWIFT()
    {

        Iterable<Module> modules = ImmutableSet.<Module>of(
                new SLF4JLoggingModule());

        //String provider = "openstack-swift";
        //String identity = "chinalab:sudasgupta@ucsd.edu"; // tenantName:userName
        //String credential = "asansol2015#";

        swiftApi = ContextBuilder.newBuilder(provider)
                .endpoint("https://identity.cloud.sdsc.edu:5000/v2.0")
                .credentials(identity, credential)
                .modules(modules)
                .buildApi(SwiftApi.class);


    }



}


