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


