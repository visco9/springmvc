package com.visco9;

import org.springframework.boot.context.embedded.*;
import org.springframework.stereotype.Component;

@Component
public class CustomizeEmbeddedServletContainer implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        //container.setPort(8080);
        //container.setContextPath("/springbootapp");
    }

}