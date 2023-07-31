package com.camel.assignment;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserCamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:fetchUsers?period=10s")
            .setHeader("pageNo").constant(2)
            .setHeader("ids").constant(Arrays.asList(7, 8))
            .to("direct:getUsers")
            .split().body()
            .filter().jsonpath("$[?(@.id in $.headers.ids)]")
            .marshal().json(JsonLibrary.Jackson)
            .end()
            .log("Filtered Users: ${body}");
    }
}
