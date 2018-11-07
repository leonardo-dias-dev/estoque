package veterinaria.estoque.util;

import java.security.MessageDigest;

import javax.swing.plaf.synth.SynthSeparatorUI;

import veterinaria.estoque.util.exceptions.ManipulationException;

public class Criptografia {

	public static String criptografiaSHA256(String senha) throws ManipulationException {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(senha.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);

				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new ManipulationException("Falha ao tentar criptograr a senha em SHA256", e);
		}
	}

	public static String criptografiaMD5(String senha) throws ManipulationException {

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] hash = digest.digest(senha.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);

				if (hex.length() == 1) {
					hexString.append('0');
				}

				hexString.append(hex);
			}

			return hexString.toString();
		} catch (Exception e) {
			throw new ManipulationException("Falha ao tentar criptograr a senha em MD5", e);
		}
	}
	
	public static void main(String[] args) {
		try {
			String criptografiaSHA256 = Criptografia.criptografiaSHA256("123123");
			
			System.out.println(criptografiaSHA256);
		} catch (ManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
