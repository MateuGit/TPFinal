package com.utn.UTNphones;

import com.utn.UTNphones.Models.City;
import com.utn.UTNphones.Models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.support.NullValue;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class UtNphonesApplication {

    public static void main(String[] args) {

SpringApplication.run(UtNphonesApplication.class, args);


/*String mydata = "Validation failed for classes [com.utn.UTNphones.Models.User] during persist time for groups [javax.validation.groups.Default, ]List of constraint violations:[ConstraintViolationImpl{interpolatedMessage='Invalid identification!', propertyPath=identification," +
        " rootBeanClass=class com.utn.UTNphones.Models.User, messageTemplate='Invalid identification!'} ConstraintViolationImpl{interpolatedMessage='Invalid lastname!', propertyPath=lastname, rootBeanClass=class com.utn.UTNphones.Models.User, messageTemplate='Invalid lastname!'}]";
String a="aaaa 'Invalid identification!', propertyPath=identification,\" +\n" +
        "        \" rootBeanClass=class com.utn.UTNphones.Models.User, messageTemplate='Invalid identification!'}   'Invalid lastname!'";
       Pattern pattern = Pattern.compile("'.*?'");
        Matcher matcher = pattern.matcher(mydata);
      while (matcher.find())
        {
            System.out.println(matcher.group());

        }*/
    }
}
