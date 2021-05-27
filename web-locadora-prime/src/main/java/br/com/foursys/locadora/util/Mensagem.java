
package br.com.foursys.locadora.util;

/**
 * Clase para armazenar as mensagems das telas do sistema
 * @author Kalleo Leandro dos Santos Leal
 * @since 03/05/2021
 * @version 1.0
 */
public class Mensagem {

	//Filme
	/*
	 *	Mensagens de erro 
	 * */	
	public static String ERRO_SALVAR_FILME = "Erro ao gravar filme!";
	public static String ERRO_ALTERAR_FILME = "Erro ao alterar o filme";	
	public static String ERRO_PESQUISAR_FILME = "Erro ao pesquisar Filmes";
	public static String ERRO_EXCLUIR_FILME = "Erro ao excluir o filme!";
	
	//Mensagens de confirmação
	public static String FILME_SALVO = "Filme gravado com sucesso!";
	public static String FILME_ALTERADO = "Filme alterado com sucesso!";
	public static String FILME_EXCLUIDO = "Filme excluido com sucesso!";
	
	//Mensagens de teste
	
	public static String nomeVazio = "Informe o nome, campo obrigatório!";
	public static String valorVazio = "Informe o valor, campo obrigatório!";
	public static String disponivelVazio = "Informe a disponibilidade, campo obrigatório!";
	public static String promocaoVazio = "Informe a promoção, campo obrigatório!";
	public static String valorPromocaoVazio = "Informe o valor da promoção, obrigatório!";
	public static String diretorVazio = "Informe o diretor, campo obrigatório!";
	public static String anoLancamentoVazio = "Informe o ano de lançamento, campo obrigatório!";
	public static String faixaEtariaVazia = "Informe a faixa etária, campo obrigatório!";
	public static String generoVazio = "Informe um gênero, campo obrigatório!";
	
	public static String valorInvalido = "Informe um valor válido!";
	public static String valorPromocaoInvalido = "Informe um valor da promoção válido!";
	public static String anoLancamentoInvalido = "Informe um ano de lançamento válido";
	public static String faixaEtariaInvalida = "Informe uma faixa etária válida";
	
	public static String anoVazio = "Informe um ano, campo obrigatório";
	public static String montanteVazio = "Não há lançamentos para o ano informado!";
		
	
	
	
	//Funcionário
	/*
	 *	Mensagens de erro 
	 * */
	public static String ERRO_SALVAR_FUNCIONARIO = "Erro ao cadastrar o funcionario";
	public static String ERRO_PESQUISAR_FUNCIONARIO = "Erro ao pesquisar Funcionários";
	public static String ERRO_ALTERAR_FUNCIONARIO = "Erro ao alterar o funcionario";
	public static String ERRO_EXCLUIR_FUNCIONARIO = "Erro ao excluir o funcionário";
	
	public static String ERRO_SALVAR_CLIENTE = "Erro ao cadastrar o cliente";
	public static String ERRO_PESQUISAR_CLIENTE = "Erro ao pesquisar cliente";
	public static String ERRO_ALTERAR_CLIENTE = "Erro ao alterar o cliente";
	public static String ERRO_EXCLUIR_CLIENTE = "Erro ao excluir o cliente";
	
	//Mensagens de confirmação
	public static String FUNCIONARIO_SALVO = "Funcionario gravado com sucesso!";
	public static String FUNCIONARIO_ALTERADO = "Funcionario gravado com sucesso!";
	public static String FUNCIONARIO_EXCLUIDO = "Funcionario excluido com sucesso!";
	
	public static String CLIENTE_SALVO = "Cliente gravado com sucesso!";
	public static String CLIENTE_ALTERADO = "Cliente gravado com sucesso!";
	public static String CLIENTE_EXCLUIDO = "Cliente excluido com sucesso!";
	
	//Mensagens de teste
	public static String cpfVazio = "Informe um cpf, campo obrigatório!";
	public static String cpfInvalido = "Informe um cpf válido";	
	public static String rgVazio = "Informe um rg, campo obrigatório!";
	public static String rgInvalido = "Informe um rg válido";
	public static String dataNascimentoVazia = "Informe uma data de nascimento, campo obrigatório!";
	public static String idadeVazia = "Informe uma idade, campo obrigatório!";
	public static String idadeInvalida = "Informe uma idade válida!";
	public static String sexoVazio = "Informe um sexo, campo obrigatório!";
	public static String logradouroVazio = "Informe um logradouro, campo obrigatório!";
	public static String enderecoVazio = "Informe um endereço, campo obrigatório!";
	public static String numeroVazio = "Informe um número, campo obrigatório!";
	public static String numeroInvalido = "Informe um número válido!";
	public static String bairroVazio = "Informe um bairro, campo obrigatório!";
	public static String cidadeVazio = "Informe uma cidade, campo obrigatório!";
	public static String estadoVazio = "Informe um estado, campo obrigatório!";
	public static String cepVazio = "Informe um cep, campo obrigatório!";	
	public static String telefonevazio = "Informe um telefone, campo obrigatório!";
	public static String celularVazio = "Informe um celular, campo obrigatório!";
	public static String emailVazio = "Informe um email, campo obrigatório!";
	public static String perfilAcessoVazio = "Informe um perfil de acesso, campo obrigatório!";
	public static String loginVazio = "Informe um login, campo obrigatório!";
	public static String senhaVazio = "Informe um senha, campo obrigatório!";
	
	//Locações
	public static String CLIENTE_VAZIO = "Informe um cliente!";
	public static String FILME_VAZIO = "Selecione um filme!";
	public static String FORMA_PAGAMENTO_VAZIO = "Selecione uma forma de pagamento";
	public static String DATA_DEVOLUCAO_VAZIO = "Informe a data de devolução!";
	public static String FILME_N_PERMITIDO = "Filme não permitido para o cliente selecionado!";
	public static String LOCACAO_SALVO = "Locação efetuada com sucesso!";
	public static String LOCACAO_ERRO = "Erro ao salvar locação!";
	
	//Devoluções
	public static String LOCACAO_CARREGAR_ERRO = "Erro ao carregar locações!";
	public static String DEVOLUCAO_SALVO = "Devolução efetuada com sucesso!";
	public static String DEVOLUCAO_ERRO_SALVO = "Erro ao gravar devolução!";
	public static String DEVOLUCAO_ATRASO = "Atraso na entrega!";
	
	//Lista
	public static String DATA_INICIO_VAZIO = "Informe a data inicial para pesquisa!";
}
