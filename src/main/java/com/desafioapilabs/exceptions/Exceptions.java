package com.desafioapilabs.exceptions;

public interface Exceptions {

	public static String ID_DEVE_SER_NULO = "O id deve ser nulo";
	public static String ID_NAO_DEVE_SER_NULO = "O id não deve ser nulo";
	public static String OBRIGATORIO_NOME_EMAIL = "O nome e o email são obrigatórios";
	public static String EMAIL_JA_CADASTRADO = "Já existe um usuário cadastrado com este email";
	public static String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";
	public static String ERRO_AO_BUSCAR_PRODUTO = "Erro ao buscar Produto";
	public static String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
	public static String PRODUTO_JA_ESTA_EM_FAVORITOS = "Este produto já esta em sua lista de favoritos";
	public static String ID_PRODUTO_E_CLIENTE_OBRIGATORIOS = "Os ids do cliente e do produto são obrigatórios.";
}
