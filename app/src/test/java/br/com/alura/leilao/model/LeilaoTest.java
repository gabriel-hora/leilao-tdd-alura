package br.com.alura.leilao.model;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao console = new Leilao("Console");
    private final Usuario alex = new Usuario("Alex");

    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescricao() {
        // executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // testar resultado esperado
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

    @Test
    public void deve_DevolveMaiorLance_QuandRecebeMaisDeUmLanceEmOrdemDecrescente() {
        console.propoe(new Lance(alex, 10000.0));
        console.propoe(new Lance(new Usuario("Fran"), 9000.0));

        double maiorLanceDevolvidoDoCarro = console.getMaiorLance();

        Assert.assertEquals(10000.0, maiorLanceDevolvidoDoCarro, DELTA);
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
    public void deve_DevolveMenorLance_QuandRecebeMaisDeUmLanceEmOrdemDecrescente() {
        console.propoe(new Lance(alex, 10000.0));
        console.propoe(new Lance(new Usuario("Fran"), 9000.0));

        double menorLanceDevolvido = console.getMenorLance();

        Assert.assertEquals(9000.0, menorLanceDevolvido, DELTA);
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
}