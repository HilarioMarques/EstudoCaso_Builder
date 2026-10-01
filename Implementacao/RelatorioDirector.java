/**
* DIRECTOR: encapsula receitas prontas de construção, reutilizáveis.
* Conhece a ORDEM e a combinação dos passos, mas não o detalhe de como
* cada passo é executado (isso é responsabilidade do Builder).
*/
public class RelatorioDirector {

    private final RelatorioBuilder builder;

    public RelatorioDirector(RelatorioBuilder builder) {
        this.builder = builder;
    }

    /** Receita: relatório executivo completo, pronto para impressão. */
    public Relatorio construirRelatorioImpresso(String titulo, String periodo, String autor) {
        return builder
            .titulo(titulo)
            .periodo(periodo)
            .autor(autor)
            .adicionarGrafico("Desempenho por setor")
            .adicionarTabela("Resumo de indicadores")
            .destinadoImpressao(true)
            .cabecalho("Relatório Corporativo")
            .rodape("Documento interno - uso restrito")
            .totalPaginas(8)
            .numeracaoPaginas(true)
            .formato(FormatoExportacao.DOCX)
            .build();
    }

    /** Receita: relatório mínimo, só com o necessário (usa formato padrão). */
    public Relatorio construirRelatorioSimples(String titulo, String periodo, String autor) {
        return builder
            .titulo(titulo)
            .periodo(periodo)
            .autor(autor)
            .adicionarTabela("Dados gerais")
            .build();
    }
}