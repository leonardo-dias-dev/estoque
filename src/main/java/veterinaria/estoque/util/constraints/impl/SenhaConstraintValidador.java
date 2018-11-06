package veterinaria.estoque.util.constraints.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.LengthRule;
import org.passay.MessageResolver;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.Rule;
import org.passay.RuleResult;

import com.google.common.base.Joiner;

import veterinaria.estoque.util.constraints.Senha;

public class SenhaConstraintValidador implements ConstraintValidator<Senha, String> {

	@Override
	public void initialize(Senha constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(String senha, ConstraintValidatorContext constraintValidatorContext) {
		if (senha == null) {
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate("{javax.validation.constraints.NotNull.message}").addConstraintViolation();
		}
		
		List<Rule> listaRegra = Arrays.asList(new LengthRule(8, 30));
		MessageResolver messageResolver = newMessageResolver();
		PasswordValidator passwordValidator = new PasswordValidator(messageResolver, listaRegra);
		RuleResult ruleResult = passwordValidator.validate(new PasswordData(senha));
		
		if (ruleResult.isValid()) {
			return true;
		}
		
		String messageTemplate = Joiner.on("\n").join(passwordValidator.getMessages(ruleResult));
		
		constraintValidatorContext.disableDefaultConstraintViolation();
		constraintValidatorContext.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation();
		
		return false;
	}
	
	private MessageResolver newMessageResolver() {
		try {
			Properties properties = new Properties();
			InputStream inputStream = this.getClass().getResourceAsStream("/passwordValidation.properties");
		
			properties.load(inputStream);
			
			return new PropertiesMessageResolver(properties);
		} catch (IOException e) {
			return null;
		}
	}

}
