package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.dao.LocacaoFilmeDAO;

/**
 * Classe respons�vel por acessar o objeto DAO e efetuar altera��es e consulta na base de dados
 * @author Kalleo Leandro dos Santos Leal
 * @since 27/04/2021
 * @version 1.0
 */

public class LocacaoFilmeController {
	public void salvar(LocacaoFilme locacaoFilme)
	{
		try
		{
			new LocacaoFilmeDAO().salvar(locacaoFilme);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<LocacaoFilme> buscarPorLocacao(Locacao locacao)
    {
        //Lista auxiliar
        ArrayList<LocacaoFilme> retorno = new ArrayList<LocacaoFilme>();
        try
        {
        	retorno = new LocacaoFilmeDAO().buscarPorLocacao(locacao); 	
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		return retorno;
	}
}
