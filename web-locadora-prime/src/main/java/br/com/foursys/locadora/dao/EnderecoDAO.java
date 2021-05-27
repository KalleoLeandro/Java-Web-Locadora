package br.com.foursys.locadora.dao;

import org.hibernate.Session;

import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.util.HibernateUtil;

public class EnderecoDAO extends GenericDAO {

    public Endereco buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Endereco Endereco = (Endereco) sessao.get(Endereco.class, codigo);
        sessao.close();
        return Endereco;
    }
}
