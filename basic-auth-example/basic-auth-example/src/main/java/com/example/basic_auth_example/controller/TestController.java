package com.example.basic_auth_example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Kennzeichnet diese Klasse als REST-Controller (Spring kümmert sich um die JSON-Antworten)
@RequestMapping("/api") // Basis-URL für alle Endpunkte in dieser Klasse: "/api"
public class TestController {

    @GetMapping("/test") // Definiert den Endpunkt "/api/test", der eine GET-Anfrage verarbeitet
    public String testEndpoint() {
        // Gibt eine einfache Textnachricht zurück, wenn die Authentifizierung erfolgreich war
        return "Erfolgreich authentifiziert!";
    }
}
