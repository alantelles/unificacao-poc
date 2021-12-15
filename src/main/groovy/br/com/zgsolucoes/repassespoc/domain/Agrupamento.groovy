package br.com.zgsolucoes.repassespoc.domain

import br.com.zgsolucoes.repassespoc.enums.ConfigAgrupamento
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(includeNames = true)
@Table(name = "agrupamentos")
class Agrupamento extends ModeloAbstrato {

    @Enumerated(EnumType.STRING)
    ConfigAgrupamento configAgrupamento

    @NotNull
    UUID referencia

}
