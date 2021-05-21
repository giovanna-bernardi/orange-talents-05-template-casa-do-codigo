package br.com.zupacademy.giovanna.casadocodigo.validation;

import br.com.zupacademy.giovanna.casadocodigo.paisestado.Estado;
import br.com.zupacademy.giovanna.casadocodigo.paisestado.dto.EstadoRequest;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueStateInCountryValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueStateInCountry {

    String message() default "O estado deve ser único dentro de um país";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<Estado> entity();
}
