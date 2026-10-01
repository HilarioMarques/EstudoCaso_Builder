/**
* CLIENT: usa o Builder (diretamente ou por meio do Director)
* sem conhecer a estrutura interna de Relatorio.
*/
public class Main {

    public static void main(String[] args) {

        // ---------- Relatório 1: construção direta, configuração rica ----------
        System.out.println(">>> Relatório 1: Financeiro (Builder direto)");
        Relatorio r1 = new RelatorioPersonalizadoBuilder()
                .titulo("Relatório Financeiro Mensal")
                .periodo("Setembro/2026")
                .autor("Ana Souza")
                .adicionarGrafico("Receita x Despesa")
                .adicionarGrafico("Fluxo de caixa")
                .adicionarTabela("Balancete")
                .cabecalho("Setor Financeiro")
                .rodape("Confidencial")
                .totalPaginas(5)
                .numeracaoPaginas(true)
                .build();
        // formato não informado -> PDF (padrão)
        r1.exibir();

        // ---------- Relatório 2: configuração mínima, HTML ----------
        System.out.println("\n>>> Relatório 2: Vendas (mínimo, HTML)");
        Relatorio r2 = new RelatorioPersonalizadoBuilder()
                .titulo("Vendas da Semana")
                .periodo("23/09 a 29/09/2026")
                .autor("Carlos Lima")
                .adicionarTabela("Vendas por produto")
                .formato(FormatoExportacao.HTML)
                .build();
        r2.exibir();

        // ---------- Relatório 3: via Director (receita pronta para impressão) ----------
        System.out.println("\n>>> Relatório 3: Corporativo (Director, impressão, DOCX)");
        RelatorioDirector director = new RelatorioDirector(new RelatorioPersonalizadoBuilder());
        Relatorio r3 = director.construirRelatorioImpresso(
                "Relatório Anual de Atividades", "2026", "Diretoria de Operações");
        r3.exibir();

        // ---------- Tentativa 1: relatório inválido (sem gráfico e sem tabela) ----------
        System.out.println("\n>>> Tentativa inválida 1: sem gráfico e sem tabela");
        try {
            new RelatorioPersonalizadoBuilder()
                    .titulo("Relatório Vazio")
                    .periodo("Setembro/2026")
                    .autor("Fulano")
                    .build();
            System.out.println("ERRO: relatório inválido foi criado!");
        } catch (IllegalStateException e) {
            System.out.println("Construção bloqueada: " + e.getMessage());
        }

        // ---------- Tentativa 2: impressão sem cabeçalho/rodapé ----------
        System.out.println("\n>>> Tentativa inválida 2: impressão sem cabeçalho e rodapé");
        try {
            new RelatorioPersonalizadoBuilder()
                    .titulo("Relatório para Impressão")
                    .periodo("Setembro/2026")
                    .autor("Beltrano")
                    .adicionarGrafico("Produção")
                    .destinadoImpressao(true)
                    .build();
            System.out.println("ERRO: relatório inválido foi criado!");
        } catch (IllegalStateException e) {
            System.out.println("Construção bloqueada: " + e.getMessage());
        }

        // ---------- Tentativa 3: numeração com uma única página ----------
        System.out.println("\n>>> Tentativa inválida 3: numeração em relatório de 1 página");
        try {
            new RelatorioPersonalizadoBuilder()
                    .titulo("Relatório Curto")
                    .periodo("Setembro/2026")
                    .autor("Sicrano")
                    .adicionarTabela("Resumo")
                    .numeracaoPaginas(true)
                    .build();
            System.out.println("ERRO: relatório inválido foi criado!");
        } catch (IllegalStateException e) {
            System.out.println("Construção bloqueada: " + e.getMessage());
        }
    }
}