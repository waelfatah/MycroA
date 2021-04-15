package tn.saturn.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {
 
    private TemplateEngine templateEngine;
 
    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
 
    public String build(String message, String firstname, String lastname, String contractDescription, double InsuredPropertyValue, double ContractPremium) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("firstname", firstname);
        context.setVariable("lastname", lastname);
        context.setVariable("contractDescription", contractDescription);
        context.setVariable("InsuredPropertyValue", InsuredPropertyValue);
        context.setVariable("ContractPremium", ContractPremium);
        return templateEngine.process("MailWelcome", context);
    }
 
}
