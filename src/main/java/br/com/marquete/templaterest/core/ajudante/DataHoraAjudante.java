package br.com.marquete.templaterest.core.ajudante;

import java.util.Calendar;
import java.util.Date;

public class DataHoraAjudante {

	public static boolean isDataHoraMenorQueAtual(Calendar data) {

		Calendar dataDeHoje = Calendar.getInstance();
		return data.before(dataDeHoje);
	}

	public static boolean isDataHoraMenorQueAtual(Date data) {

		Calendar dataCalendar = Calendar.getInstance();
		dataCalendar.setTime(data);

		return isDataHoraMenorQueAtual(dataCalendar);
	}

	public static boolean isDataHoraMaiorQueAtual(Calendar data) {

		Calendar dataDeHoje = Calendar.getInstance();
		return data.after(dataDeHoje);
	}

	public static boolean isDataHoraMaiorQueAtual(Date data) {

		Calendar dataCalendar = Calendar.getInstance();
		dataCalendar.setTime(data);

		return isDataHoraMaiorQueAtual(dataCalendar);
	}

}
