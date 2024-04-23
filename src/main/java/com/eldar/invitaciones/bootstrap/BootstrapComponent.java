package com.eldar.invitaciones.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class BootstrapComponent {

    private final PersonBootstrap personBootstrap;


    @PostConstruct
    public void run() {
        personBootstrap.run();
    }


}
