package br.com.zgsolucoes.repassespoc.enums


enum ConfigAgrupamento {

    OPERADORA("OPERADORA", ['ans', 'cnpj'])

    List<String> camposReferencia = []
    String entidade

    ConfigAgrupamento(String entidade, List<String> camposReferencia) {
        this.entidade = entidade
        this.camposReferencia = camposReferencia
    }

}
