package br.com.zgsolucoes.repassespoc.controllers

import br.com.zgsolucoes.repassespoc.domain.Agrupamento
import br.com.zgsolucoes.repassespoc.domain.RegistroAbstrato
import br.com.zgsolucoes.repassespoc.enums.ConfigAgrupamento
import br.com.zgsolucoes.repassespoc.services.AgrupamentoService
import br.com.zgsolucoes.repassespoc.services.OperacoesAgrupamentoService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject

@Controller("/agrupamento")
class AgrupamentoController {

    @Inject
    AgrupamentoService service

    @Inject
    OperacoesAgrupamentoService operacoesAgrupamentoService

    @Get
    HttpResponse<List<Agrupamento>> listarPorEntidade(ConfigAgrupamento entidade) {
        return HttpResponse.ok(service.encontrarPorEntidade(entidade))
    }

    @Post
    HttpResponse<Agrupamento> salvar(@Body Map<String, Object> body) {
        return HttpResponse.ok(service.salvar(body))
    }

    @Get("/{id}/registros")
    HttpResponse<List<RegistroAbstrato>> listarRegistrosAgrupamento(UUID id) {
        return HttpResponse.ok(operacoesAgrupamentoService.listarRegistrosPorAgrupamento(id))

    }

    @Post("/{id}/agrupar/{registroId}")
    HttpResponse<RegistroAbstrato> agruparRegistro(UUID id, UUID registroId) {

        return HttpResponse.ok(operacoesAgrupamentoService.agruparRegistro(id, registroId))
    }

    @Delete("/{id}")
    HttpResponse deletarAgrupamento(UUID id) {
        operacoesAgrupamentoService.deletarAgrupamento(id)
        return HttpResponse.noContent()
    }


}
