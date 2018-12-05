package veterinaria.estoque.util;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

public class UtilString {

	private static Random random = new Random((new Date()).getTime());

	public static String gerarCaracterAleatorio(int tamanho) {
		char[] valores = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
				's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		String out = "";
		for (int i = 0; i < tamanho; i++) {
			int idx = random.nextInt(valores.length);
			out += valores[idx];
		}

		return out;
	}

	public static String gerarSimboloAleatorio(int tamanho) {
		char[] values = { '@', '#', '$', '%', '&', '*', '(', ')' };

		String out = "";
		for (int i = 0; i < tamanho; i++) {
			int idx = random.nextInt(values.length);
			out += values[idx];
		}

		return out;
	}

	public static String gerarCaracterEspecial(int tamanho) {
		char[] values = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '@', 'Ç', '!' };

		String out = "";
		for (int i = 0; i < tamanho; i++) {
			int idx = random.nextInt(values.length);
			out += values[idx];
		}

		return out;
	}

	public static String gerarLetraAleatorio(int tamanho) {
		char[] values = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z' };

		String out = "";
		for (int i = 0; i < tamanho; i++) {
			int idx = random.nextInt(values.length);
			out += values[idx];
		}

		return out;
	}

	public static String gerarNumeroAleatorio(int tamanho) {
		char[] values = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		String out = "";
		for (int i = 0; i < tamanho; i++) {
			int idx = random.nextInt(values.length);
			out += values[idx];
		}

		return out;
	}

	public static String retirarCaracterEspecial(String informacao) {
		String passa = informacao;
		passa = passa.replaceAll("[ÂÀÁÄÃ]", "A");
		passa = passa.replaceAll("[âãàáä]", "a");
		passa = passa.replaceAll("[ÊÈÉË]", "E");
		passa = passa.replaceAll("[êèéë]", "e");
		passa = passa.replaceAll("[ÎÍÌÏ]", "I");
		passa = passa.replaceAll("[îíìï]", "i");
		passa = passa.replaceAll("[ÔÕÒÓÖ]", "O");
		passa = passa.replaceAll("[ôõòóö]", "o");
		passa = passa.replaceAll("[ÛÙÚÜ]", "U");
		passa = passa.replaceAll("[ûúùü]", "u");
		passa = passa.replaceAll("Ç", "C");
		passa = passa.replaceAll("ç", "c");
		passa = passa.replaceAll("[ýÿ]", "y");
		passa = passa.replaceAll("Ý", "Y");
		passa = passa.replaceAll("ñ", "n");
		passa = passa.replaceAll("Ñ", "N");
		
		//ÄáâãàÁÂÃÀéêÉÊíÍóôõÓÔÕúÚçÇüÜ°ºª¹²³§ï¿½¡åµ´ÿ¦
		
		return passa;
	}

	public static String aplicarCaracterDeEscape(String informacao) {
		informacao =informacao.replaceAll("&", "&amp;");
//		informacao = informacao.replaceAll(" < ", "&lt;");
//		informacao = informacao.replaceAll(">", "&gt;");
//		informacao = informacao.replaceAll("\"", "&quot;");
//		informacao = informacao.replaceAll("'", "&#39;");
		
		return informacao;
	}
	
	/**
	 * Método que preenche a esquerda com o caracter informado até o tamnho
	 * passado como parametro
	 * 
	 * @param valor
	 *            String inicial que será concatenado os demais caracteres
	 * @param tamanho
	 *            int com o tamanho dinal da String
	 * @param caracter
	 *            char que será concatenado a String resultante
	 * @return
	 */
	public static String preencheAEsquerdaComChar(String valor, int tamanho, char caracter) {
		
		if (valor.length() >= tamanho) {
			return valor;
		}

		StringBuilder builder = new StringBuilder();
		//String retorno = valor;

		for (int x = valor.length(); x < tamanho; x++) {
			//retorno = caracter + retorno;
			builder.append(caracter);
		}
		builder.append(valor);
		return builder.toString();
	}

	/**
	 * Retira máscara de formatação da string e também retira caracteres fora do
	 * intervalo 32-127
	 * 
	 * @param str
	 *            String que terá a máscara retirada
	 * @return String Sem máscara
	 */
	public static String retiraMascara(String str) {
		StringBuffer ret, sb = new StringBuffer(str);
		String aux, charMasc = "./-,:(){}[]%_";
		int i, indice, caracter;
		char c;
		aux = str;

		for (i = 0; i < charMasc.length(); i++) {
			c = charMasc.charAt(i);
			while ((indice = aux.indexOf(c)) != -1) {
				sb.deleteCharAt(indice);
				aux = sb.toString();
			}
		}

		ret = new StringBuffer();
		for (i = 0; i < aux.length(); i++) {
			caracter = (int) aux.charAt(i);
			if (caracter >= 32 && caracter <= 127)
				ret.append((char) caracter);
		}

		return ret.toString();
	}

	public static String formata(String numero, String mascara) {
		// Ex de mascara ###.###.###.###,##

		String retorno = "";
		String inicRetorno = "";
		numero = numero.trim();
		int contMasc = 0;

		if (mascara != null && mascara.length() > 0 && !mascara.substring(0, 1).equals("#")) {
			for (int contIniMasc = 0; contIniMasc < mascara.length(); contIniMasc++) {
				if (!mascara.substring(contIniMasc, contIniMasc + 1).equals("#")) {
					inicRetorno = inicRetorno + mascara.substring(contIniMasc, contIniMasc + 1);
				} else {
					break;
				}
			}
		}

		for (int cont = 0; cont < numero.length() && contMasc < mascara.length(); cont++) {
			if (mascara.substring((mascara.length() - 1) - contMasc, mascara.length() - contMasc).equals("#")) {
				retorno = numero.substring((numero.length() - 1) - cont, numero.length() - cont) + retorno;
			} else {
				retorno = numero.substring((numero.length() - 1) - cont, numero.length() - cont)
						+ mascara.substring((mascara.length() - 1) - contMasc, mascara.length() - contMasc) + retorno;
				contMasc++;
			}
			contMasc++;
		}

		return inicRetorno + retorno;
	}

	public static String formataValor(double valor, int casasDec, boolean mostraCifrao) {
		String retorno = "";

		String mascara = "###,###,###,###,##0.00";

		if (casasDec == 0) {
			mascara = "###,###,###,###,###";
		} else if (casasDec == 1) {
			mascara = "###,###,###,###,##0.0";
		} else if (casasDec == 2) {
			mascara = "###,###,###,###,##0.00";
		} else if (casasDec == 3) {
			mascara = "###,###,###,###,##0.000";
		} else if (casasDec == 4) {
			mascara = "###,###,###,###,##0.0000";
		}

		DecimalFormat df = new DecimalFormat(mascara);

		retorno = df.format(valor);

		if (mostraCifrao) {
			retorno = "R$ " + retorno;
		}

		return retorno;
	}
	
	public static String formataCpf(String cpf) {
		return formata(preencheEsqueraComZeroCPF(cpf), "###.###.###-##");
	}
	
	public static String formataCnpj(String cnpj) {
		return formata(preencheEsqueraComZeroCNPJ(cnpj), "##.###.###/####-##");
	}
	
	public static String formataCep(String cep) {
		return formata(cep, "#####-###");
	}
	
	public static String formataRg(String rg) {
		return formata(rg, "#######-#");
	}
	
	public static String preencheEsqueraComZeroCPF(String cpf) {
		if (cpf.length() < 11) {
			return preencheAEsquerdaComChar(cpf, 11, '0');
		}
		
		return cpf;
	}
	
	public static String preencheEsqueraComZeroCNPJ(String cnpj) {
		if (cnpj.length() < 14) {
			return preencheAEsquerdaComChar(cnpj, 14, '0');
		}
		
		return cnpj;
	}
	
	public static String preencheEsqueraComZeroRG(String rg) {
		if (rg.length() < 8) {
			return preencheAEsquerdaComChar(rg, 8, '0');
		}
		
		return rg;
	}
	
	public static String formataTelefone(String telefone) {
		if (telefone.length() > 10) {
			return formata(telefone, "(##) #####-####");
		} else {
			return formata(telefone, "(##) ####-####");
		}
	}
 	
}
