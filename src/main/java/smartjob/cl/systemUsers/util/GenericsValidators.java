package smartjob.cl.systemUsers.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Getter
@Setter
public class GenericsValidators {

    @Value("${regular.expression.mail.regexp}")
    private String mailRegexp;

    public Boolean mailValidate(String mail){

        Pattern pattern = Pattern.compile(mailRegexp);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();

    }

}
