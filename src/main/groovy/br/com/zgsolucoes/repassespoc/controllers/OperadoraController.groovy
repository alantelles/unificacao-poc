package br.com.zgsolucoes.repassespoc.controllers

import br.com.zgsolucoes.repassespoc.domain.Operadora
import br.com.zgsolucoes.repassespoc.services.OperadoraService
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Inject

@Controller("/registros/operadora")
class OperadoraController {

    @Inject
    OperadoraService service

    @Get
    HttpResponse<List<Operadora>> listarTodos() {
        return HttpResponse.ok(service.listarTodos())
    }

    @Get("/{id}")
    HttpResponse<Operadora> encontrarUm(UUID id) {
        return HttpResponse.ok(
                service.encontrarUm(id).orElseThrow(
                        e -> new HttpStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado")
                )
        )
    }

    @Post
    HttpResponse<Operadora> salvar(@Body Map<String, String> body) {
        return HttpResponse.ok(service.salvar(body))
    }

}
