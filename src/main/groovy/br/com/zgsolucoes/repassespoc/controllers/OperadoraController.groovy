package br.com.zgsolucoes.repassespoc.controllers

import br.com.zgsolucoes.repassespoc.domain.Agrupamento
import br.com.zgsolucoes.repassespoc.domain.Operadora
import br.com.zgsolucoes.repassespoc.enums.EstadoAnalise
import br.com.zgsolucoes.repassespoc.services.OperadoraService
import io.micronaut.core.annotation.Nullable
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Inject

@Controller("/registros/operadora")
class OperadoraController {

    @Inject
    OperadoraService service

    @Get
    HttpResponse<List<Operadora>> listarTodos(@Nullable EstadoAnalise status) {
        if (status) {
            return HttpResponse.ok(service.listarPorEstadoAnalise(status))
        }
        return HttpResponse.ok(service.listarTodos())
    }

    @Put("/{id}/desagrupar")
    HttpResponse<Operadora> desagrupar(UUID id) {
        return HttpResponse.ok(service.desagrupar(id))
    }

    @Get("/{id}")
    HttpResponse<Operadora> encontrarUm(UUID id) {
        return HttpResponse.ok(
                service.encontrarUm(id).orElseThrow(
                        e -> new HttpStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado")
                )
        )
    }

    @Put("/{id}/referencia")
    HttpResponse<Agrupamento> tornarReferencia(UUID id) {
        return HttpResponse.ok(service.tornarReferencia(id))
    }

    @Post
    HttpResponse<Operadora> salvar(@Body Map<String, String> body) {
        return HttpResponse.ok(service.salvar(body))
    }

}
