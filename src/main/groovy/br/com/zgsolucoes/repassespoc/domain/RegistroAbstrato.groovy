package br.com.zgsolucoes.repassespoc.domain

import br.com.zgsolucoes.repassespoc.enums.EstadoAnalise
import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.EqualsAndHashCode

import javax.persistence.*
import javax.validation.constraints.NotBlank

@MappedSuperclass
@EqualsAndHashCode
abstract class RegistroAbstrato extends ModeloAbstrato {

    @NotBlank
    String idRepoDados

    @NotBlank
    String codigoHis

    @Enumerated(EnumType.STRING)
    EstadoAnalise estadoAnalise = EstadoAnalise.EM_ANALISE

    @NotBlank
    String datasource

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "agrupamento_id")
    Agrupamento agrupamento

    @Transient
    UUID getAgrupamentoId() {
        return agrupamento?.id
    }

}
