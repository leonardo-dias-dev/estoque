package veterinaria.estoque.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import veterinaria.estoque.util.exceptions.ManipulationException;

public class UtilData {

	private static final String DATA_HORA_PT_BR = "dd/MM/yyyy HH:mm";
	private static final String DATA_PT_BR = "dd/MM/yyyy";

	private static SimpleDateFormat simpleDateFormatIngles = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Converte data informada em string no formato AAAA-MM-DDTHH:MM:DD TZD
	 * 
	 * @param StringSimetrya
	 *            formato AAAA-MM-DDTHH:MM:DD TZD
	 * @return Timestamp
	 */
	public static Timestamp toTimestamp(String dataHoraComFuso) {
		LocalDateTime localDateTimeComFuso = LocalDateTime.parse(dataHoraComFuso, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

		return Timestamp.valueOf(localDateTimeComFuso);
	}

	/**
	 * Converte data informada em string no formato AAAA-MM-DDTHH:MM:DD do tipo local
	 * 
	 * @param String formato AAAA-MM-DDTHH:MM:DD
	 * @return Timestamp
	 */
	public static Timestamp toTimestampLocal(String dataHoraLocal) {
		LocalDateTime localDateTimeLocal = LocalDateTime.parse(dataHoraLocal, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		return Timestamp.valueOf(localDateTimeLocal);
	}

	/**
	 * Converte data informada em string no formato AAAA-MM-DD
	 * 
	 * @param StringSimetrya
	 *            no formato AAAA-MM-DD
	 * @return Date
	 */
	public static Date toDate(String dataSemFuso) {
		LocalDate localDate = LocalDate.parse(dataSemFuso, DateTimeFormatter.ISO_DATE);
		Date dataObj = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

		return dataObj;
	}
	
	/**
	 * Converte data informada em string no formato AAAA-MM-DD
	 * 
	 * @param StringSimetrya
	 *            no formato AAAA-MM-DD
	 * @return Timestamp
	 */
	public static Timestamp toTimestampSemHora(String dataSemFuso) {
		LocalDate localDate = LocalDate.parse(dataSemFuso, DateTimeFormatter.ISO_DATE);
		Date dataObj = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Timestamp timeStamp = new Timestamp (dataObj.getTime ());
		
		return timeStamp;
	}
	
	/**
	 * Converte data informada em string no formato AAAA-MM-DDTHH:MM:DD
	 * 
	 * @param StringSimetrya
	 *            no formato AAAA-MM-DDTHH:MM:DD
	 * @return Date
	 */
	public static Timestamp toDateTime(String dataHoraSemFuso) {
		LocalDateTime localDateTime = LocalDateTime.parse(dataHoraSemFuso, DateTimeFormatter.ISO_DATE_TIME);

		return Timestamp.valueOf(localDateTime);
	}

	/**
	 * Converte hora informada em string no formato HH:MM:SS
	 * 
	 * @param StringSimetrya
	 *            no formato HH:MM:SS
	 * @return java.sql.Time
	 */
	public static Time toTime(String horario) {
		LocalTime localTime = LocalTime.parse(horario, DateTimeFormatter.ISO_TIME);
		Date timeObj = Date.from(localTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());

		return new Time(timeObj.getTime());
	}

	public static LocalDate converterDateParaLocalDate(Date date) throws ManipulationException {
		try {
			String dateString = simpleDateFormatIngles.format(date);
			LocalDate localDate = LocalDate.parse(dateString);
			
			return localDate;
		} catch (Exception e) {
			throw new ManipulationException("Não foi possível efetuar a conversão");
		}
	}

	public static int intervaloDeMesesEntreDuasDatas(LocalDate inicio, LocalDate fim) throws ManipulationException {
		try {
			Period periodo = Period.between(inicio, fim);
			int meses = periodo.getMonths();
			
			return meses;
		} catch (Exception e) {
			throw new ManipulationException("Não foi possível determinar o intervalo entre as datas");
		}
	}

	public static int intervaloDeMesesEntreDuasDatas(Date inicio, Date fim) throws ManipulationException {
		try {
			LocalDate begin = converterDateParaLocalDate(inicio);
			LocalDate end = converterDateParaLocalDate(fim);
			Period periodo = Period.between(begin, end);
			int meses = periodo.getMonths();
			
			return meses;
		} catch (Exception e) {
			throw new ManipulationException("Não foi possível determinar o intervalo entre as datas");
		}
	}

	public static Date retirarHora(Date data) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}

	public static Date adicionarUltimaHora(Date data) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		
		return calendar.getTime();
	}

	public static Date now() {
		return new Date();
	}
	
	public static Timestamp gerarDataAtualPelaZona(String zona) {
		ZoneId zoneId = ZoneId.of(zona);
		ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
		Timestamp timestamp = Timestamp.valueOf(zonedDateTime.toLocalDateTime());

		return timestamp;
	}
	
	public static Timestamp gerarDataAtualNaZonaPadrao() {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
		Timestamp timestamp = Timestamp.valueOf(zonedDateTime.toLocalDateTime());

		return timestamp;
	}

	public static String formatarData(Date data) {
		SimpleDateFormat simpleDateFormatPortuges = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		String format = simpleDateFormatPortuges.format(data);
		
		return format;
	}

	public static String formatarDataEHora(Date date) {
		SimpleDateFormat sdfDataHora = new SimpleDateFormat(DATA_HORA_PT_BR);
		String dataFormatada = sdfDataHora.format(date);
		return dataFormatada;
	}

	public static String formatar(Date data, String padrao) {
		try {
			if (padrao == null) {
				padrao = DATA_PT_BR;
			}
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(padrao);
			String dataFormatada = simpleDateFormat.format(data);
			
			return dataFormatada;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
}