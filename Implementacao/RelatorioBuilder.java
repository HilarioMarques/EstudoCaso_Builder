/**
* BUILDER (interface abstrata) do padrão.
* Declara os passos de construção de um Relatorio e o método build().
* Os métodos de configuração retornam o próprio Builder (fluent interface),
* permitindo configuração progressiva sem construtor com muitos parâmetros.
*/
public interface RelatorioBuilder {

    // Passos obrigatórios
    RelatorioBuilder titulo(String titulo);
    RelatorioBuilder periodo(String periodo);
    RelatorioBuilder autor(String autor);

    // Passos opcionais
    RelatorioBuilder adicionarGrafico(String descricao);
    RelatorioBuilder adicionarTabela(String descricao);
    RelatorioBuilder cabecalho(String texto);
    RelatorioBuilder rodape(String texto);
    RelatorioBuilder numeracaoPaginas(boolean ativa);
    RelatorioBuilder totalPaginas(int total);
    RelatorioBuilder destinadoImpressao(boolean impressao);
    RelatorioBuilder formato(FormatoExportacao formato);
    
    /** Valida o estado acumulado e entrega o produto final. */
    Relatorio build();
}