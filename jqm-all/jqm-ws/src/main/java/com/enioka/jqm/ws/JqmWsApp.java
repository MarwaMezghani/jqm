package com.enioka.jqm.ws;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/ws")
public class JqmWsApp extends ResourceConfig
{
    public JqmWsApp()
    {
        packages("com.enioka.jqm.ws");
    }
}
