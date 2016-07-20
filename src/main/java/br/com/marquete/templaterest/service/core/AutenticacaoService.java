package br.com.marquete.templaterest.service.core;

import br.com.marquete.templaterest.core.ajudante.AutenticacaoAjudante;
import br.com.marquete.templaterest.core.ajudante.DataHoraAjudante;
import br.com.marquete.templaterest.dao.core.PermissaoDAO;
import br.com.marquete.templaterest.dao.core.ServicoWebDAO;
import br.com.marquete.templaterest.dao.core.TokenDAO;
import br.com.marquete.templaterest.entidade.core.Permissao;
import br.com.marquete.templaterest.entidade.core.ServicoWeb;
import br.com.marquete.templaterest.entidade.core.Token;
import br.com.marquete.templaterest.enumeracao.core.MetodoHTTPEnum;
import br.com.marquete.templaterest.enumeracao.core.PerfilPermissaoEnum;
import br.com.marquete.templaterest.enumeracao.core.ResultadoValidacaoTokenEnum;
import br.com.marquete.templaterest.enumeracao.core.StatusTokenEnum;

public class AutenticacaoService {

	TokenDAO tokenDao;
	ServicoWebDAO servicoDao;
	PermissaoDAO permissaoDao;

	public AutenticacaoService() {
		this.tokenDao = new TokenDAO();
		this.servicoDao = new ServicoWebDAO();
		this.permissaoDao = new PermissaoDAO();
	}

	public ResultadoValidacaoTokenEnum verificaToken(String uri, MetodoHTTPEnum metodoHttp) {

		String hash = AutenticacaoAjudante.obterHash(uri);
		String identificadorUriServico = AutenticacaoAjudante.obterIdentificadorUriServico(uri);

		Token token = tokenDao.buscar(hash);

		if (token == null) {

			return ResultadoValidacaoTokenEnum.HASH_INVALIDO;

		} else {

			if (token.getStatusToken() == StatusTokenEnum.INATIVO) {

				return ResultadoValidacaoTokenEnum.TOKEN_INATIVO;

			} else if (token.getValidade() != null && DataHoraAjudante.isDataHoraMenorQueAtual(token.getValidade())) {

				return ResultadoValidacaoTokenEnum.TOKEN_VALIDADE_EXPIRADA;

			} else if (token.getPerfilPermissao() != PerfilPermissaoEnum.MANUAL) {

				return verificaPermissaoTokenNaoManual(token, metodoHttp);

			} else if (token.getPerfilPermissao() == PerfilPermissaoEnum.MANUAL) {

				return verificaPermissaoTokenManual(token, metodoHttp, identificadorUriServico);

			}

		}

		return ResultadoValidacaoTokenEnum.TOKEN_VALIDO;

	}

	private ResultadoValidacaoTokenEnum verificaPermissaoTokenNaoManual(Token token, MetodoHTTPEnum metodoHttp) {

		if (token.getPerfilPermissao() == PerfilPermissaoEnum.GET) {

			if (metodoHttp != MetodoHTTPEnum.GET) {
				return ResultadoValidacaoTokenEnum.TOKEN_SEM_PERMISSAO;
			}

		} else if (token.getPerfilPermissao() == PerfilPermissaoEnum.GET_POST) {

			if (metodoHttp != MetodoHTTPEnum.GET && metodoHttp != MetodoHTTPEnum.POST) {
				return ResultadoValidacaoTokenEnum.TOKEN_SEM_PERMISSAO;
			}

		} else if (token.getPerfilPermissao() == PerfilPermissaoEnum.GET_PUT) {

			if (metodoHttp != MetodoHTTPEnum.GET && metodoHttp != MetodoHTTPEnum.PUT) {
				return ResultadoValidacaoTokenEnum.TOKEN_SEM_PERMISSAO;
			}

		} else if (token.getPerfilPermissao() == PerfilPermissaoEnum.GET_PUT_POST) {

			if (metodoHttp != MetodoHTTPEnum.GET && metodoHttp != MetodoHTTPEnum.PUT
					&& metodoHttp != MetodoHTTPEnum.POST) {
				return ResultadoValidacaoTokenEnum.TOKEN_SEM_PERMISSAO;
			}

		}

		return ResultadoValidacaoTokenEnum.TOKEN_VALIDO;
	}

	private ResultadoValidacaoTokenEnum verificaPermissaoTokenManual(Token token, MetodoHTTPEnum metodoHttp,
			String identificadorUriServico) {

		ServicoWeb servicoWeb = servicoDao.buscar(identificadorUriServico);
		Permissao permissao = permissaoDao.buscar(token, servicoWeb, metodoHttp);

		if (permissao == null) {
			return ResultadoValidacaoTokenEnum.TOKEN_SEM_PERMISSAO;
		} else {
			return ResultadoValidacaoTokenEnum.TOKEN_VALIDO;
		}

	}

}
