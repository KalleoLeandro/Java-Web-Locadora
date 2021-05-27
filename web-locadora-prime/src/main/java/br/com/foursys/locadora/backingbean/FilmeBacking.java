package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.util.Genero;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe responsável por controlar os componentes de front-end da tela
 * @author Kalleo Leandro dos Santos Leal
 * @since 27/04/2021
 * @version 1.0
 */

@ManagedBean(name = "filmeBacking")
@SessionScoped
public class FilmeBacking implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//Atributos da tela de cadastro
	
	
	private String nome;
	private String valor;
	private String disponivel;
	private String promocao;
	private String valorPromocao;	
	private String diretor;
	private String anoLancamento;
	private String faixaEtaria;
	private String genero;
	
	//Atributos da tela de consulta
	private String nomePesquisar;
	private Filme filmeSelecionado;	
	
	private Filme filme;
	private ArrayList<Filme> listaFilmes;
	
	private ArrayList<String> listaGeneros;	
	
	public ArrayList<String> getListaGeneros() {
		return listaGeneros;
	}

	public void setListaGeneros(ArrayList<String> listaGeneros) {
		this.listaGeneros = listaGeneros;
	}

	public FilmeBacking()
	{
		listaFilmes = new ArrayList<Filme>();
		carregarGeneros();
		limpaCampos();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}
	public String getPromocao() {
		return promocao;
	}
	public void setPromocao(String promocao) {
		this.promocao = promocao;
	}
	public String getValorPromocao() {
		return valorPromocao;
	}
	public void setValorPromocao(String valorPromocao) {
		this.valorPromocao = valorPromocao;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	public String getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	public String getFaixaEtaria() {
		return faixaEtaria;
	}
	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}
	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}	
	
	public String getNomePesquisar() {
		return nomePesquisar;
	}
	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}	
	
	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}
	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}	
	
	
	/*
	 * M�todo que captura a a��o do bot�o CADASTRAR na tela cad-filme.jsp
	 */
	
	
	public void cadastrar()
	{
		if(validar())
		{
			//salvar os dados do filme na base
			try
			{
				getFilme();
				new FilmeController().salvar(filme);
				limpaCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FILME,Mensagem.FILME_SALVO);
			}
			catch(Exception e)
			{
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.ERRO_SALVAR_FILME);
			}			
		}
		
	}
	
	public void alterarFilme()
	{
		if(validar())
		{
			//salvar os dados do filme na base
			try
			{
				getFilmeAlterar();
				new FilmeController().salvar(filme);
				limpaCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_ALTERADO);
			}
			catch(Exception e)
			{
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.ERRO_ALTERAR_FILME);
			}			
		}		
	}
	
	private boolean validar()
	{
		if(Valida.isEmptyOrNull(nome))
		{
			//exibe uma mensagem de erro
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.nomeVazio);
			return false;
		}
		if(!Valida.isDouble(valor))
		{
			//exibe uma mensagem de erro
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.valorVazio);
			return false;
		}
		else if(Valida.isDoubleZero(Double.parseDouble(valor)))
		{
			//exibe uma mensagem de erro
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.valorInvalido);
			return false;
		}
		if(Valida.isEmptyOrNull(disponivel))
		{
			//exibe uma mensagem de erro
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.disponivelVazio);
			return false;
		}
		if(Valida.isEmptyOrNull(promocao))
		{
			//exibe uma mensagem de erro
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.promocaoVazio);
			return false;
		}
		if(promocao.equals("SIM"))
		{
			if(!Valida.isDouble(valorPromocao))
			{
				//exibe uma mensagem de erro
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.valorPromocaoVazio);
				return false;
			}
			else if(Valida.isDoubleZero(Double.parseDouble(valorPromocao)))
			{
				//exibe uma mensagem de erro
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.valorPromocaoInvalido);
				return false;
			}			
		}		
		if(Valida.isEmptyOrNull(diretor))
		{
			//exibe uma mensagem de erro
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.diretorVazio);
			return false;
		}
		
		if(!Valida.isInteger(anoLancamento))
		{
			//exibe uma mensagem de erro
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.anoLancamentoVazio);
			return false;
		}
		else if(Valida.isIntZero(Integer.parseInt(anoLancamento)))
		{
			//exibe uma mensagem de erro
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.anoLancamentoInvalido);
			return false;		
		}
		
		if(!Valida.isInteger(faixaEtaria))
		{
			//exibe uma mensagem de erro
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.faixaEtariaVazia);
			return false;
		}
		else if(Valida.isIntZero(Integer.parseInt(faixaEtaria)))
		{
			//exibe uma mensagem de erro
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.faixaEtariaInvalida);
			return false;
		}		
		if(Valida.isEmptyOrNull(genero))
		{
			//exibe uma mensagem de erro
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.generoVazio);
			return false;
		}	
		
		return true;
		
	}
	
	/*
	 * M�todo que captura a a��o do bot�o CANCELAR na tela cad-filme.jsp
	 * */
	
	public void cancelar()
	{
		limpaCampos();
	}
	
	/*
	 * M�todo que captura a a��o do bot�o SAIR na tela cad-filme.jsp 
	 * */
	public void sair()
	{
		limpaCampos();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/*
	 * M�todo para abrir a tela de cadastro
	 * */
	
	public String cadastroFilme()
	{
		limpaCampos();
		return "";
	}
	
	/*
	 * M�todo para abrir a tela de consulta
	 * */
	
	public String consultarFilme()
	{
		nomePesquisar = null;
		listaFilmes.clear();
		return "";
	}
	
	/*
	 * M�todo para retornar um objeto filme
	 * */
	
	private void getFilme()
	{
		filme =  new Filme();
		filme.setNome(nome);
		filme.setValor(Double.parseDouble(valor));
		filme.setDisponivel(disponivel);
		filme.setPromocao(promocao);
		if(promocao.equals("SIM"))
		{
			filme.setValorPromocao(Double.parseDouble(valorPromocao));
		}
		filme.setDiretor(diretor);
		filme.setAnoLancamento(anoLancamento);
		filme.setFaixaEtaria(Integer.parseInt(faixaEtaria));
		filme.setGenero(genero);		
	}
	
	private void limpaCampos()
	{
		setNome(null);
		setValor(null);
		setDisponivel(null);
		setPromocao(null);
		setValorPromocao(null);
		setFaixaEtaria(null);
		setDiretor(null);
		setAnoLancamento(null);
		setGenero(null);
		listaFilmes.clear();
	}
	
	
	/*
	 * M�todo para pesquisar filme
	 * */
	
	public String pesquisar()
	{
		try
		{
			listaFilmes = new FilmeController().buscarPorNome(nomePesquisar);
		}
		catch(Exception e)
		{
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.ERRO_PESQUISAR_FILME);
		}
		return "";
	}
	
	public void redirecionar(String opcao)
	{	filmeRedirecionado();
		if(opcao.equals("alterar"))
		{
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("alt-filme.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("detalha-filme.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void filmeRedirecionado()
	{
		nome = filmeSelecionado.getNome();
		valor = filmeSelecionado.getValor()+"";
		disponivel = filmeSelecionado.getDisponivel();
		promocao = filmeSelecionado.getPromocao();
		if(filmeSelecionado.getValorPromocao() == null)
		{
			valorPromocao = "";
		}
		else
		{
			valorPromocao = filmeSelecionado.getValorPromocao()+"";
		}
		
		diretor = filmeSelecionado.getDiretor();
		faixaEtaria = filmeSelecionado.getFaixaEtaria()+ "";
		anoLancamento = filmeSelecionado.getAnoLancamento();
		genero = filmeSelecionado.getGenero();
	}
	
	public void voltar()
	{
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("list-filme.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir()
	{
		try
		{
			new FilmeController().excluir(filmeSelecionado);
			limpaCampos();
			JSFUtil.addInfoMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_EXCLUIDO);
		}
		catch(Exception e)
		{
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.ERRO_EXCLUIR_FILME);
		}				
	}
	
	private void getFilmeAlterar()
	{
		filme =  filmeSelecionado;
		filme.setNome(nome);
		filme.setValor(Double.parseDouble(valor));
		filme.setDisponivel(disponivel);
		filme.setPromocao(promocao);
		if(promocao.equals("SIM"))
		{
			filme.setValorPromocao(Double.parseDouble(valorPromocao));
		}
		filme.setDiretor(diretor);
		filme.setAnoLancamento(anoLancamento);
		filme.setFaixaEtaria(Integer.parseInt(faixaEtaria));
		filme.setGenero(genero);		
	}
	
	/*
	 * Método para carregar a lista de gêneros
	 * */
	
	public void carregarGeneros()
	{
		listaGeneros = new ArrayList<String>();
		for(Genero g: Genero.values())
		{
			listaGeneros.add(g.getDescricao());	
		}
	}

}
