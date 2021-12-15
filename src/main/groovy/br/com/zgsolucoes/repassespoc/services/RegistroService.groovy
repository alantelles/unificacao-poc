package br.com.zgsolucoes.repassespoc.services

import br.com.zgsolucoes.repassespoc.domain.Agrupamento
import br.com.zgsolucoes.repassespoc.enums.ConfigAgrupamento
import br.com.zgsolucoes.repassespoc.enums.EstadoAnalise
import io.micronaut.data.repository.CrudRepository

interface RegistroService<T> {

    CrudRepository<T, UUID> getRepo()

    ConfigAgrupamento getConfigAgrupamento()

    List<T> listarTodos()

    Optional<T> encontrarUm(UUID id)

    T salvar(Map<String, String> obj)

    List<T> listarPorEstadoAnalise(EstadoAnalise estadoAnalise)

    List<T> listarPorAgrupamento(Agrupamento agrupamento)

    T ajustarAgrupamento(UUID registroId, Agrupamento agrupamento)

    T desagrupar(UUID id)

    Agrupamento tornarReferencia(UUID id)

    List<Agrupamento> inferirAgrupamento(T registro)

    List<T> desagruparTodos(Agrupamento agrupamento)

}
