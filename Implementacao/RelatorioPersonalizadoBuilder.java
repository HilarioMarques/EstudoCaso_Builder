import java.util.ArrayList;
import java.util.List;
/**
* CONCRETE BUILDER: implementa os passos de construção, guarda o estado
* parcial, aplica valores padrão e executa as validações em build().
*/
public class RelatorioPersonalizadoBuilder implements RelatorioBuilder {

    // Estado acumulado durante a construção
    private String titulo;
    private String periodo;
    private String autor;

    private final List<String> graficos = new ArrayList<>();
    private final List<String> tabelas = new ArrayList<>();
    private String cabecalho;
    private String rodape;
    private boolean numeracaoPaginas = false; // padrão: sem numeração
    private int totalPaginas = 1; // padrão: 1 página
    private boolean destinadoImpressao = false; // padrão: não é para impressão
    private FormatoExportacao formato = FormatoExportacao.PDF; // VALOR PADRÃO

    @Override
    public RelatorioBuilder adicionarGrafico(String descricao) {
        this.graficos.add(descricao);
        return this;
    }

    @Override
    public RelatorioBuilder adicionarTabela(String descricao) {
        this.tabelas.add(descricao);
        return this;
    }

    @Override
    public RelatorioBuilder cabecalho(String texto) {
        this.cabecalho = texto;
        return this;
    }

    @Override
    public RelatorioBuilder rodape(String texto) {
        this.rodape = texto;
        return this;
    }

    @Override
    public RelatorioBuilder numeracaoPaginas(boolean ativa) {
        this.numeracaoPaginas = ativa;
        return this;
    }

    @Override
    public RelatorioBuilder totalPaginas(int total) {
        this.totalPaginas = total;
        return this;
    }

    @Override
    public RelatorioBuilder destinadoImpressao(boolean impressao) {
        this.destinadoImpressao = impressao;
        return this;
    }

    @Override
    public RelatorioBuilder formato(FormatoExportacao formato) {
        // Se o cliente passar null, mantém-se o padrão (PDF)
        if (formato != null) {
            this.formato = formato;
        }
        return this;
    }
    
    @Override
    public Relatorio build() {
        validar();
        // Cópias defensivas: o builder pode ser reutilizado sem afetar o produto
        return new Relatorio(titulo.trim(), periodo.trim(), autor.trim(),
            new ArrayList<>(graficos), new ArrayList<>(tabelas),
            cabecalho, rodape, numeracaoPaginas, totalPaginas,
            destinadoImpressao, formato);
    }

    @Override
    public RelatorioBuilder titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    @Override
    public RelatorioBuilder periodo(String periodo) {
        this.periodo = periodo;
        return this;
    }

    @Override
    public RelatorioBuilder autor(String autor) {
        this.autor = autor;
        return this;
    }

    private void validar() {
        // Validação 1: atributos obrigatórios (título, período e autor)
        if (vazio(titulo) || vazio(periodo) || vazio(autor)) {
            throw new IllegalStateException("Título, período e autor são obrigatórios.");
        }

        // Validação 2: pelo menos um gráfico ou uma tabela
        if (graficos.isEmpty() && tabelas.isEmpty()) {
            throw new IllegalStateException("O relatório deve possuir pelo menos um gráfico ou uma tabela.");
        }

        // Validação 3: relatório para impressão exige cabeçalho e rodapé
        if (destinadoImpressao && (vazio(cabecalho) || vazio(rodape))) {
            throw new IllegalStateException("Relatórios destinados à impressão devem possuir cabeçalho e rodapé.");
        }

        // Validação 4: numeração só é permitida com mais de uma página
        if (numeracaoPaginas && totalPaginas <= 1) {
        throw new IllegalStateException("A numeração de páginas só pode ser usada em relatórios com mais de uma página.");
        }
    }

    private boolean vazio(String s) {
        return s == null || s.trim().isEmpty();
    }
}