import java.util.Collections;
import java.util.List;
/**
* PRODUCT do padrão Builder.
* Objeto imutável: só é criado por um RelatorioBuilder já validado.
* O construtor é package-private para impedir criação direta pelo Client.
*/
public class Relatorio {
    // Obrigatórios
    private final String titulo;
    private final String periodo;
    private final String autor;

    // Conteúdo (pelo menos um gráfico ou uma tabela)
    private final List<String> graficos;
    private final List<String> tabelas;

    // Opcionais
    private final String cabecalho;
    private final String rodape;
    private final boolean numeracaoPaginas;
    private final int totalPaginas;
    private final boolean destinadoImpressao;

    // Com valor padrão (PDF)
    private final FormatoExportacao formato;

    Relatorio(String titulo, String periodo, String autor,
            List<String> graficos, List<String> tabelas,
            String cabecalho, String rodape,
            boolean numeracaoPaginas, int totalPaginas,
            boolean destinadoImpressao, FormatoExportacao formato) {

        this.titulo = titulo;
        this.periodo = periodo;
        this.autor = autor;
        this.graficos = Collections.unmodifiableList(graficos);
        this.tabelas = Collections.unmodifiableList(tabelas);
        this.cabecalho = cabecalho;
        this.rodape = rodape;
        this.numeracaoPaginas = numeracaoPaginas;
        this.totalPaginas = totalPaginas;
        this.destinadoImpressao = destinadoImpressao;
        this.formato = formato;
    }

    public String getTitulo() { return titulo; }
    public String getPeriodo() { return periodo; }
    public String getAutor() { return autor; }
    public List<String> getGraficos() { return graficos; }
    public List<String> getTabelas() { return tabelas; }
    public String getCabecalho() { return cabecalho; }
    public String getRodape() { return rodape; }
    public boolean isNumeracaoPaginas() { return numeracaoPaginas; }
    public int getTotalPaginas() { return totalPaginas; }
    public boolean isDestinadoImpressao() { return destinadoImpressao; }
    public FormatoExportacao getFormato() { return formato; }
    
    public void exibir() {
    System.out.println("==================================================");
    if (cabecalho != null) {
        System.out.println("[Cabeçalho] " + cabecalho);
        System.out.println("--------------------------------------------------");
    }

    System.out.println("Título: " + titulo);
    System.out.println("Período : " + periodo);
    System.out.println("Autor: " + autor);
    System.out.println("Gráficos : " + (graficos.isEmpty() ? "-" : graficos));
    System.out.println("Tabelas : " + (tabelas.isEmpty() ? "-" : tabelas));
    System.out.println("Formato : " + formato + (destinadoImpressao ? " (destinado à impressão)" : ""));
    System.out.println("Páginas : " + totalPaginas + (numeracaoPaginas ? " (com numeração)" : " (sem numeração)"));
   
    if (rodape != null) {
        System.out.println("--------------------------------------------------");
        System.out.println("[Rodapé] " + rodape);
    }
    
    System.out.println("==================================================");
    }
}