/**
 * 
 */
package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.FormaPagamentoController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe para controlar as funções e métodos das telas de locação
 * 
 * @author Kalleo Leandro dos Santos Leal
 * @since 05/05/2021
 * @version 1.0
 */

@ManagedBean(name = "locacaoBacking")
@ViewScoped
public class LocacaoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	// Atributos de datas

	private Date dataAtual;

	private String dataLocacao;
	private Date dataDevolucao;
	
	private Date dataMaxima;
	
	// Atributos de cliente
	private String codigo;
	private String nomeCliente;
	private String cpf;
	private Cliente clienteSelecionado;

	// Atributos de filme
	private int filmeEscolhido;
	private Filme filmeSelecionado;

	// Atributos de locação
	private Locacao locacao;

	// Atributos de listas
	private ArrayList<Filme> listaFilmes;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Filme> listaFilmesTabela;
	private ArrayList<FormaPagamento> listaFormaDePagamento;

	// Atributos de forma de pagamento
	private int formaDePagamento;
	private String valor;
	private double valorTotal;

	// Atributos de controle
	private boolean bloqueio;
	private int indexTab;

	public LocacaoBacking() {
		carregaFormasDePagamento();
		carregarDataAtual();
		carregaDataMaxima();
		clienteSelecionado = new Cliente();
		listaFilmesTabela = new ArrayList<Filme>();
		valorTotal = 0;
		setBloqueio(true);
	}

	public boolean isBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(boolean bloqueio) {
		this.bloqueio = bloqueio;
	}

	public int getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(int formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public Date getDataMaxima() {
		return dataMaxima;
	}

	public void setDataMaxima(Date dataMaxima) {
		this.dataMaxima = dataMaxima;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getFilmeEscolhido() {
		return filmeEscolhido;
	}

	public void setFilmeEscolhido(int filmeEscolhido) {
		this.filmeEscolhido = filmeEscolhido;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}	

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}

	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public ArrayList<Filme> getListaFilmesTabela() {
		return listaFilmesTabela;
	}

	public void setListaFilmesTabela(ArrayList<Filme> listaFilmesTabela) {
		this.listaFilmesTabela = listaFilmesTabela;
	}

	public ArrayList<FormaPagamento> getListaFormaDePagamento() {
		return listaFormaDePagamento;
	}

	public void setListaFormaDePagamento(ArrayList<FormaPagamento> listaFormaDePagamento) {
		this.listaFormaDePagamento = listaFormaDePagamento;
	}

	public int getIndexTab() {
		return indexTab;
	}

	public void setIndexTab(int indexTab) {
		this.indexTab = indexTab;
	}

	public String cadastroLocacao() {
		limpaCampos();
		return "";
	}

	/*
	 * salvar
	 */

	public void salvar() {
		if (validar()) {
			try {
				getLocacao();
				new LocacaoController().salvar(locacao);
				salvarLocacaoFilmes();
				limpaCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_LOCACAO,Mensagem.LOCACAO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.LOCACAO_ERRO);
			}
		}
	}

	private void getLocacao() {
		locacao = new Locacao();

		locacao.setDataLocacao(dataLocacao);
		locacao.setDataDevolucao(getDateToString(dataDevolucao));

		locacao.setValor(valorTotal);
		locacao.setDevolvido("Não");

		locacao.setFuncionarioIdFuncionario(LoginBacking.funcionarioLogado);
		locacao.setFormaPagamentoIdFormaPagamento(getFormaPagamentoItem());
		locacao.setClienteIdCliente(clienteSelecionado);

	}

	private boolean validar() {

		if (clienteSelecionado == null) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.CLIENTE_VAZIO);
			return false;
		}

		if (Valida.isIntZero(listaFilmesTabela.size())) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.FILME_VAZIO);
			return false;
		}

		if (Valida.isIntZero(formaDePagamento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.FORMA_PAGAMENTO_VAZIO);
			return false;
		}

		if (Valida.isDateNull(dataDevolucao)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.DATA_DEVOLUCAO_VAZIO);
			return false;
		}
		return true;
	}

	/*
	 * Método para limpar os campos de preenchimento
	 */

	public void cancelar() {
		limpaCampos();
		setBloqueio(true);
	}

	private void limpaCampos() {
		setNomeCliente(null);
		setCpf(null);
		clienteSelecionado = new Cliente();		
		listaFilmes.clear();
		listaFilmesTabela.clear();
		listaClientes.clear();
		setFormaDePagamento(0);
		valorTotal = 0;
		setValor(null);
		setIndexTab(0);
		setBloqueio(true);
	}	

	private void carregaFormasDePagamento() {

		try {
			listaFormaDePagamento = new FormaPagamentoController().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	private void carregaFilmes() {
		try {
			listaFilmes = new FilmeController().buscarDisponiveis("SIM");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String carregaClienteSelecionado() {
		try {
			listaClientes = new ClienteController().buscarTodos();
			for (Cliente c : listaClientes) {

				if (c.getCpf().equals(cpf)) {
					clienteSelecionado = c;
				}
			}
			setBloqueio(false);
			carregaFilmes();
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.ERRO_PESQUISAR_CLIENTE);
		}
		return "";
	}

	private void carregarDataAtual() {
		dataAtual = new Date();
		dataLocacao = getDateToString(dataAtual);
	}

	public void adicionarFilme() {
		if (filmeEscolhido > 0) {
			int index = listaFilmes.indexOf(new Filme(filmeEscolhido));
			Filme filme = listaFilmes.get(index);

			if (filme.getFaixaEtaria() > getClienteSelecionado().getIdade()) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.FILME_N_PERMITIDO);
			} else {
				listaFilmesTabela.add(filme);
				listaFilmes.remove(filme);

				Collections.sort(listaFilmesTabela, Filme.FilmeComparator);

				if (filme.getValorPromocao() != null) {
					valorTotal += filme.getValorPromocao();
					valor = NumberFormat.getCurrencyInstance().format(filme.getValorPromocao());
				} else {
					valorTotal += filme.getValor();
					valor = NumberFormat.getCurrencyInstance().format(filme.getValor());
				}				
			}

		} else {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.FILME_VAZIO);
		}
	}
	
	

	public void excluir() {
		listaFilmesTabela.remove(filmeSelecionado);
		listaFilmes.add(filmeSelecionado);

		Collections.sort(listaFilmes, Filme.FilmeComparator);

		if (filmeSelecionado.getPromocao().equals("Sim")) {
			valorTotal -= filmeSelecionado.getValorPromocao();
		} else {
			valorTotal -= filmeSelecionado.getValor();
		}
		valor = NumberFormat.getCurrencyInstance().format(valorTotal);

	}

	/*
	 * Método para controlar a função do botão sair
	 */

	private FormaPagamento getFormaPagamentoItem() {
		int indice = listaFormaDePagamento.indexOf(new FormaPagamento(formaDePagamento));
		return listaFormaDePagamento.get(indice);
	}

	public void desbloqueioFilmes() {
		if (clienteSelecionado != null) {
			setBloqueio(false);
		} else {
			setBloqueio(true);
		}
		limpaCampos();
	}
	
	private void salvarLocacaoFilmes() {
		for (Filme filme : listaFilmesTabela) {
			try {
				LocacaoFilme locacaoFilme = new LocacaoFilme();
				locacaoFilme.setFilmeIdFilme(filme);
				locacaoFilme.setLocacaoIdLocacao(locacao);

				new LocacaoFilmeController().salvar(locacaoFilme);

				filme.setDisponivel("Não");
				new FilmeController().salvar(filme);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void carregaDataMaxima()
	{
		dataMaxima = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dataMaxima); 
		c.add(Calendar.DATE, 30);
		dataMaxima = c.getTime();
	}

	public void sair() {
		limpaCampos();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getDateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(date);
	}
}
