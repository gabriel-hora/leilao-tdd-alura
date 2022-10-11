package br.com.alura.leilao.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao console = new Leilao("Console");
    private final Usuario alex = new Usuario("Alex");

    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescricao() {
        String descricaoDevolvida = console.getDescricao();

        Assert.assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance() {
        console.propoe(new Lance(alex, 200.0));

        double maiorLanceDevolvidoDoConsole = console.getMaiorLance();

        Assert.assertEquals(200.0, maiorLanceDevolvidoDoConsole, DELTA);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        console.propoe(new Lance(alex, 100.0));
        console.propoe(new Lance(new Usuario("Fran"), 200.0));

        double maiorLanceDevolvidoDoComputador = console.getMaiorLance();

        Assert.assertEquals(200.0, maiorLanceDevolvidoDoComputador, DELTA);
    }

    // Menores Lances
    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance() {
        console.propoe(new Lance(alex, 200.0));

        double menorLanceDevolvido = console.getMenorLance();

        Assert.assertEquals(200.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        console.propoe(new Lance(alex, 100.0));
        console.propoe(new Lance(new Usuario("Fran"), 200.0));

        double menorLanceDevolvido = console.getMenorLance();

        Assert.assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuantoRecebeExatosTresLances(){
        console.propoe(new Lance(alex, 200.0));
        console.propoe(new Lance(new Usuario("fran"), 300.0));
        console.propoe(new Lance(alex, 400.0));

        List<Lance> tresMaioresLancesDevolvidos = console.tresMaioresLances();

        Assert.assertEquals(3, tresMaioresLancesDevolvidos.size());

        Assert.assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        Assert.assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        Assert.assertEquals(200.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances(){
        List<Lance> tresMaioresLancesDevolvidos = console.tresMaioresLances();

        Assert.assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance(){
        console.propoe(new Lance(alex, 200.0));

        List<Lance> tresMAioresLancesDevolvidos = console.tresMaioresLances();

        Assert.assertEquals(1, tresMAioresLancesDevolvidos.size());
        Assert.assertEquals(200.0, tresMAioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApeasDoisLances(){
        console.propoe(new Lance(alex, 200.0));
        console.propoe(new Lance(new Usuario("fran"), 300));

        List<Lance> tresMaioresLancesDevolvidos = console.tresMaioresLances();

        Assert.assertEquals(2, tresMaioresLancesDevolvidos.size());
        Assert.assertEquals(300.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        Assert.assertEquals(200.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances() {
        console.propoe(new Lance(alex, 300.0));
        console.propoe(new Lance(new Usuario("fran"), 400.0));
        console.propoe(new Lance(alex, 500.0));
        console.propoe(new Lance(new Usuario("fran"), 600.0));

        List<Lance> tresMaioresLancesParaQuatroLancesDevolvidos = console.tresMaioresLances();

        Assert.assertEquals(3, tresMaioresLancesParaQuatroLancesDevolvidos.size());
        Assert.assertEquals(600.0, tresMaioresLancesParaQuatroLancesDevolvidos.get(0).getValor(), DELTA);
        Assert.assertEquals(500.0, tresMaioresLancesParaQuatroLancesDevolvidos.get(1).getValor(), DELTA);
        Assert.assertEquals(400.0, tresMaioresLancesParaQuatroLancesDevolvidos.get(2).getValor(), DELTA);

        console.propoe(new Lance(alex, 700.0));

        List<Lance> tresMaioresLancesParaCincoLancesDevolvidos = console.tresMaioresLances();

        Assert.assertEquals(3, tresMaioresLancesParaCincoLancesDevolvidos.size());
        Assert.assertEquals(700.0, tresMaioresLancesParaCincoLancesDevolvidos.get(0).getValor(), DELTA);
        Assert.assertEquals(600.0, tresMaioresLancesParaCincoLancesDevolvidos.get(1).getValor(), DELTA);
        Assert.assertEquals(500.0, tresMaioresLancesParaCincoLancesDevolvidos.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaoTiverLances() {
        double maiorLanceDevolvido = console.getMaiorLance();

        Assert.assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLances() {
        double menorLanceDevolvido = console.getMenorLance();

        Assert.assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForMenorQueOMaiorLance() {
        console.propoe(new Lance(alex, 500.0));
        console.propoe(new Lance(new Usuario("fran"), 400.0));

        int quantidadeLancesDevolvida = console.quantidadeLances();

        Assert.assertEquals(1, quantidadeLancesDevolvida);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForOMesmoUsuarioDoUltimoLance() {
        console.propoe(new Lance(alex, 500.0));
        console.propoe(new Lance(alex, 600.0));

        int quantidadeLancesDevolvida = console.quantidadeLances();

        Assert.assertEquals(1, quantidadeLancesDevolvida);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoUsuarioDerCincoLance() {
        console.propoe(new Lance(alex, 100.0));
        console.propoe(new Lance(new Usuario("fran"), 200.0));

        console.propoe(new Lance(alex, 300.0));
        console.propoe(new Lance(new Usuario("fran"), 400.0));

        console.propoe(new Lance(alex, 500.0));
        console.propoe(new Lance(new Usuario("fran"), 600.0));

        console.propoe(new Lance(alex, 700.0));
        console.propoe(new Lance(new Usuario("fran"), 800.0));

        console.propoe(new Lance(alex, 900.0));
        console.propoe(new Lance(new Usuario("fran"), 1000.0));

        console.propoe(new Lance(alex, 1100.0));
        console.propoe(new Lance(new Usuario("fran"), 1200.0));

        int quantidadeLancesDevolvida = console.quantidadeLances();

        Assert.assertEquals(10, quantidadeLancesDevolvida);
    }
}