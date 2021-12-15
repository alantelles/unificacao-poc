package br.com.zgsolucoes.repassespoc.repositories

import br.com.zgsolucoes.repassespoc.domain.Agrupamento
import br.com.zgsolucoes.repassespoc.enums.ConfigAgrupamento
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface AgrupamentoRepository extends CrudRepository<Agrupamento, UUID> {

    List<Agrupamento> findByConfigAgrupamento(ConfigAgrupamento configAgrupamento)
    Optional<Agrupamento> findById(UUID id)
    Agrupamento save(Agrupamento agrupamento)
    Optional<Agrupamento> findByReferencia(UUID registroId)
    void deleteById(UUID id)

}
