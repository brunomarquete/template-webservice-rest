package br.com.marquete.templaterest.core.ajudante;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import br.com.marquete.templaterest.dao.core.TokenDAO;
import br.com.marquete.templaterest.entidade.core.Token;
import br.com.marquete.templaterest.enumeracao.core.ResultadoValidacaoTokenEnum;

public class AutenticacaoAjudante {

	public static String gerarHash(String frase, String algoritmo) {
		try {
			MessageDigest md = MessageDigest.getInstance(algoritmo);
			md.update(frase.getBytes());
			return gerarStringHexadecimal(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	private static String gerarStringHexadecimal(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
			int parteBaixa = bytes[i] & 0xf;
			if (parteAlta == 0)
				s.append('0');
			s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		return s.toString();
	}

	public static String obterHash(String uri) {
		int posicaoPrimeiraBarra = uri.substring(1, uri.length()).indexOf("/");
		return uri.substring(1, posicaoPrimeiraBarra + 1);
	}
	
	public static String obterIdentificadorUriServico(String uri) {
		int posicaoPrimeiraBarra = uri.substring(1, uri.length()).indexOf("/");
		String identificacaoUriServico = uri.substring(posicaoPrimeiraBarra + 2, uri.length());
		return identificacaoUriServico;
	}

	public static boolean isHashValido(String hash) {

		TokenDAO dao = new TokenDAO();
		Token token = dao.buscar(hash);

		if (token == null) {
			return false;
		} else {
			return true;
		}

	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		TokenDAO dao = new TokenDAO();

		System.out.println("GERADOR DE TOKEN");
		System.out.println();
		System.out.println("Por favor, informe a frase para gerar o TOKEN...");
		String frase = in.nextLine();

		String hash = AutenticacaoAjudante.gerarHash(frase, "SHA-1");
		Token token = new Token(hash);

		dao.inserir(token);

		System.out.println();
		System.out.println("Token de hash " + hash + " gerado com sucesso!");

		in.close();

	}

	

}
