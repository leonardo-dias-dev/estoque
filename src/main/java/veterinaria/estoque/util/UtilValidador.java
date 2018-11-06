package veterinaria.estoque.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UtilValidador {

	public static final long PRIMEIRO_JANEIRO_1900 = -2208972740000L;

	private UtilValidador() {

	}

	/**
	 * Método utilizado para validar se uma string é valida, ou seja, retorna
	 * true somente se for passado uma String não nula e diferente de vazio.
	 * 
	 * @param valorString
	 * @return boolean
	 */
	public static boolean isStringValida(String valorString) {
		if (valorString != null && !valorString.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método utilizado para validar e-mail. Garante que é um e-mail válido, mas
	 * não garante que o e-mail existe. Apenas confere se o formato é valido
	 * para um e-mail que pode ser cadastrado como um e-mail.
	 * 
	 * @param email
	 *            e-mail a ser validado
	 * @return boolean Retorna <b>true</b> somente se o e-mail informado for um
	 *         e-mail válido, caso contrário, retorna <b>false</b>.
	 */
	public static boolean isEmailValido(String email) {
		if (!isStringValida(email)) {
			return false;
		}

		if (email.indexOf("@") > 0 && email.indexOf(".") > -1 && email.indexOf("@.") == -1
				&& email.lastIndexOf(".") != (email.length() - 1) && email.indexOf(" ") == -1) {
			return true;
		}

		return false;
	}

	/**
	 * Método utilizado para validar se um objeto é nulo ou não.
	 * 
	 * @param object
	 *            Objeto a ser validado.
	 * @return boolean - <b>true</b> caso o objeto não seja null e <b>false</b>
	 *         caso o objeto seja null.
	 */
	public static boolean isObjetoValido(Object objeto) {
		if (objeto == null) {
			return false;
		}

		return true;
	}

	/**
	 * Método utilizado para validar se os objetos são nulos ou não.
	 * 
	 * @param object
	 *            Objetos a serem validados.
	 * @return boolean - <b>true</b> caso o objeto não seja null e <b>false</b>
	 *         caso o objeto seja null.
	 */
	public static boolean isObjetosValidos(Object... objeto) {
		for (Object obj : objeto) {
			if (isObjetoValido(obj) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Método utilizado para validar se um array é válido, ou seja, se contém
	 * algum objeto. Retorna true somente se o array for diferente de null e não
	 * estiver vazio.
	 * 
	 * @param array
	 * @return boolean
	 */
	public static boolean isArrayValido(Object[] array) {
		if (array != null && array.length >= 1) {
			return true;
		}

		return false;
	}

	/**
	 * Método utilizado para validar se uma collection é válida, ou seja, se
	 * contém algum objeto. Retorna true somente se a collection for diferente
	 * de null e não estiver vazia.
	 * 
	 * @param collection
	 * @return boolean
	 */
	public static boolean isCollectionValida(Collection<?> collection) {
		if (collection != null && !collection.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método utilizado para validar se uma lista é válida, ou seja, se contém
	 * algum objeto. Retorna true somente se a lista for diferente de null e não
	 * estiver vazia.
	 * 
	 * @param List
	 * @return boolean
	 */
	public static boolean isListaValida(List<?> list) {
		if (list == null) {
			return false;
		}

		if (list.isEmpty()) {
			return false;
		}

		return true;
	}

	/**
	 * Método utilizado para validar se um map é válido, ou seja, se contém
	 * algum objeto. Retorna true somente se o map for diferente de null e não
	 * estiver vazia.
	 * 
	 * @param map
	 * @return boolean
	 */
	public static boolean isMapValido(Map<?, ?> map) {
		if (map != null && !map.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método utilizado para validar se uma data é valida, ou seja, retorna true
	 * somente se for passado um Date não nula, que não tenha data vazia e que o
	 * tempo seja maior que 0 milissegundos.
	 * 
	 * @param data
	 * @return boolean
	 * 
	 */
	public static boolean isDataValida(Date data) {
		if (data != null && data.getTime() > PRIMEIRO_JANEIRO_1900) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	public static boolean isNumericoValido(byte numero) {
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	public static boolean isNumericoValido(short numero) {
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	public static boolean isNumericoValido(int numero) {
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	public static boolean isNumericoValido(long numero) {
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	public static boolean isNumericoValido(float numero) {
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	public static boolean isNumericoValido(double numero) {
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de nulo e diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	public static boolean isNumericoValido(Byte numero) {
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de nulo e diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	public static boolean isNumericoValido(Short numero) {
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de nulo e diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	public static boolean isNumericoValido(Integer numero) {
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de nulo e diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	public static boolean isNumericoValido(Long numero) {
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de nulo e diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	public static boolean isNumericoValido(Float numero) {
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de nulo e diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	public static boolean isNumericoValido(Double numero) {
		return validarNumero(numero);
	}

	public static boolean isNumericoValido(BigDecimal numero) {
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	private static boolean validarNumero(final double numero) {
		if (numero != 0.0d) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método utilizado para validar um número. Só retorna true se o número for
	 * diferente de nulo e diferente de zero
	 * 
	 * @param numero
	 * @return boolean
	 */
	private static boolean validarNumero(final Number numero) {
		if (numero != null && numero.doubleValue() != 0.0d) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método utilizado para validar CPF
	 * 
	 * @param cpf
	 * @return boolean - <b>true</b> se o documento for um CPF válido ou
	 *         <b>false</b> se o documento não for um CPF válido.
	 * 
	 */
	public static boolean isCPFValido(String cpf) {
		if ((cpf == null) || (!isSomenteDigito(cpf)) || (cpf.length() < 3)) {
			return false;
		}
		int dv1 = Character.digit(cpf.charAt(cpf.length() - 2), 10);
		int dv2 = Character.digit(cpf.charAt(cpf.length() - 1), 10);
		if ((cpf.length() <= 11) && (!isCadeiaRepetida(cpf)) && (dv1 == calcDvModulo11(cpf, cpf.length() - 2, 2, 11))
				&& (dv2 == calcDvModulo11(cpf, cpf.length() - 1, 2, 11))) {
			return true;
		}
		return false;
	}

	public static boolean isJaExisteObjetoNaLista(final List<?> list, final Object objeto) {
		if (isObjetoValido(objeto) && isListaValida(list)) {
			if (list.stream().anyMatch(objetoLista -> objetoLista.equals(objeto))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isByteArrayValido(byte[] array) {
		if (array != null && array.length > 0)
			return true;
		else
			return false;
	}

	public static boolean isSomenteDigito(String cadeia) {
		boolean ret = true;
		for (int ind = 0; (ret) && (ind < cadeia.length()); ind++) {
			if ((cadeia.charAt(ind) < '0') || (cadeia.charAt(ind) > '9')) {
				ret = false;
			}
		}
		return ret;
	}

	public static boolean isDvValidoMod11(String num) {
		boolean ret = false;
		if ((num != null) && (isSomenteDigito(num))) {
			ret = Character.digit(num.charAt(num.length() - 1), 10) == calcDvModulo11(num, num.length() - 1, 2, 9);
		}
		return ret;
	}

	public static int calcDvModulo11(String cadeia, int tam, int fatorInicial, int fatorFinal) {
		int fator = fatorInicial;
		int total = 0;

		if ((cadeia == null) || (!isSomenteDigito(cadeia))) {
			return -1;
		}

		for (int ind = 0; ind < tam; ind++) {
			total += Character.digit(cadeia.charAt(tam - ind - 1), 10) * fator;
			fator++;

			if (fator > fatorFinal) {
				fator = fatorInicial;
			}
		}

		total %= 11;
		if (total < 2) {
			return 0;
		}

		return 11 - total;
	}

	public static boolean isBooleanValido(String booleano) {
		if (isStringValida(booleano)) {
			if (booleano.equalsIgnoreCase("true") || booleano.equalsIgnoreCase("false")) {
				return true;
			}
		}

		return false;
	}

	public static boolean isCadeiaRepetida(String cadeia) {
		int ind = 1;
		char ch = cadeia.charAt(0);
		while (ind < cadeia.length()) {
			if (ch == cadeia.charAt(ind)) {
				ind++;
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean isSomenteLetra(String cadeia) {
		boolean ret = true;
		for (int ind = 0; (ret) && (ind < cadeia.length()); ind++) {
			if (!Character.isLetter(cadeia.charAt(ind))) {
				ret = false;
			}
		}
		return ret;
	}
}
