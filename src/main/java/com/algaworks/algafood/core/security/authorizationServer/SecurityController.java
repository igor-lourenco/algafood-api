package com.algaworks.algafood.core.security.authorizationServer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping(path = "/login")
    public String login(){
        return "pages/login"; // caminho da pagina: templates/pages/login.html
    }

    @GetMapping(path = "/oauth/confirm_access")
    public String aprovaLogin(){
        return "pages/approval"; // caminho da pagina: templates/pages/approval.html
    }




}
