package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.dao.FilmeDAO;

/**
 * Classe respons�vel por acessar o objeto DAO e efetuar altera��es e consulta na base de dados
 * @author Kalleo Leandro dos Santos Leal
 * @since 27/04/2021
 * @version 1.0
 */

public class FilmeController {
	public void salvar(Filme filme)
	{
		try
		{
			new FilmeDAO().salvar(filme);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Filme> buscarPorNome(String nome)
    {
        //Lista auxiliar
        ArrayList<Filme> retorno = new ArrayList<Filme>();
        try
        {
        	retorno = new FilmeDAO().buscarPorNome(nome);        	
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		return retorno;
	}
	
	public ArrayList<Filme> buscarTodos()
    {
        //Lista auxiliar
        ArrayList<Filme> retorno = new ArrayList<Filme>();
        try
        {
        	retorno = new FilmeDAO().buscarTodos();        	
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		return retorno;
	}
	
	public ArrayList<Filme> buscarDisponiveis(String disponivel)
    {
        //Lista auxiliar
        ArrayList<Filme> retorno = new ArrayList<Filme>();
        try
        {
        	retorno = new FilmeDAO().buscarDisponiveis(disponivel);        	
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		return retorno;
    }
	
	public void excluir(Filme filme)
	{
		try
		{
			new FilmeDAO().excluir(filme);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
