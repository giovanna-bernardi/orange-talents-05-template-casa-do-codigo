package br.com.zupacademy.giovanna.casadocodigo.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> domainClass;

    @Autowired
    private EntityManager em;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        domainClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = em.createQuery("select 1 from " + domainClass.getName() + " where " + domainAttribute + " = :fieldName");
        query.setParameter("fieldName", o);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Foi encontrado mais de um "+domainAttribute+" = "+ o + " na tabela de "+ domainClass.getName().substring(domainClass.getName().lastIndexOf('.') + 1));

        return list.isEmpty();
    }
}
