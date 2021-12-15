package br.com.zgsolucoes.repassespoc.repositories

import br.com.zgsolucoes.repassespoc.domain.Agrupamento
import br.com.zgsolucoes.repassespoc.domain.Operadora
import br.com.zgsolucoes.repassespoc.enums.EstadoAnalise
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface OperadoraRepository extends CrudRepository<Operadora, UUID> {

    List<Operadora> findAll()
    Optional<Operadora> findById(UUID id)

    Operadora save(Operadora operadora)
    List<Operadora> findByEstadoAnaliseAndIdIn(EstadoAnalise estadoAnalise, List<UUID> ids)
    List<Operadora> findByAgrupamento(Agrupamento agrupamento)
    Operadora update(Operadora operadora)

    List<Operadora> findByAnsAndCnpjAndIdIn(String ans, String cnpj, List<UUID> ids)

    List<Operadora> updateAll(List<Operadora> operadoras)

}
