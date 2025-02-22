package com.algaworks.algafood.api.controllers;


import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class HostCheckController {


    @GetMapping(path = "/host-check")
    public ResponseEntity<String> checkHost() throws UnknownHostException {

        JSONObject returnJson = new JSONObject()
            .put("hostAddress", InetAddress.getLocalHost().getHostAddress())
            .put("hostName", InetAddress.getLocalHost().getHostName());

        return ResponseEntity.status(HttpStatus.OK)
            .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
            .body(returnJson.toString());

    }

}
