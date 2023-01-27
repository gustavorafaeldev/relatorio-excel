package apirelatorios.Relatorio.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saude")
public class SaudeContoller {
	
	 
    @GetMapping("/health")
	public String teste() {
        return "OK Api 2.0";
    }
}
