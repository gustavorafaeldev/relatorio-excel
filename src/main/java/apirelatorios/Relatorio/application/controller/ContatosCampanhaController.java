package apirelatorios.Relatorio.application.controller;

import apirelatorios.Relatorio.application.request.ContatosCampanhaRequest;
import apirelatorios.Relatorio.application.service.ContatosCampanhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Scanner;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ContatosCampanhaController {

    private final ContatosCampanhaService service;
    Scanner scanner = new Scanner(System.in);

    @CrossOrigin("*")
    @PostMapping("/relatorio")
    public ResponseEntity<?> findDetalhado(
            @RequestBody ContatosCampanhaRequest request,
            @RequestParam(value = "type", required = false, defaultValue = "xlsx")
            String type, HttpServletResponse httpServletResponse) {


        return service.findDetalhado(request, type, httpServletResponse);
    }

    @CrossOrigin("*")
    @PostMapping("/relatorioUnico")
    public ResponseEntity<?> findUnico(
            @RequestBody ContatosCampanhaRequest request,
            @RequestParam(value = "type", required = false, defaultValue = "xlsx")
            String type, HttpServletResponse httpServletResponse) {

        return service.findUnico(request, type, httpServletResponse);
    }
}
