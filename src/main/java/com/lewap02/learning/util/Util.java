package com.lewap02.learning.util;

import com.lewap02.learning.controller.ServiceController;
import com.lewap02.learning.util.annotations.Getter;
import jakarta.persistence.Column;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Util {

    private static Log log = LogFactory.getLog(Util.class);

    public static String makeResponse (String contentInside) {
        return "<body style=\"background-color: gray\">Created<br>" + contentInside + "</body>";
    }

    private static List<String> getColumnNames (Object inputObject) {

        return Arrays.stream(inputObject.getClass().getDeclaredFields())
                .filter(s -> s.isAnnotationPresent(Column.class))
                .map(Field::getName)
                .toList();
    }

    private static String getRowValue (Object inputObject, String fieldName) throws TooManyGettersForSameFileException, NoGetterForFieldException {

        HashMap<Integer,String> getters = new HashMap<>();
        int cnt = 0;
        String val = null;

        for (Method method : inputObject.getClass().getDeclaredMethods()) {
            Getter getter = method.getAnnotation(Getter.class); //throw exception if empty

            if(getter != null && fieldName.equals(getter.fieldName())){
                cnt++;
                if (cnt > 1) {
                    throw new TooManyGettersForSameFileException("There can only be one Getter annotated method for field " + fieldName + ". Check " + inputObject.getClass());
                }
                try {
                    val = method.invoke(inputObject).toString();
                } catch (InvocationTargetException | IllegalAccessException e) {
                    log.error(e);
                }
            }
        }

        if (cnt == 0) {
            throw new NoGetterForFieldException("No Getter annotated method for field " + fieldName + " found. Check " + inputObject.getClass());
        }

        //TODO add exception handling in case >1 Getter method for given fields declared
        return val;
    }

    public static String makeTable (List<?> inputList) throws TooManyGettersForSameFileException, NoGetterForFieldException {

        List<String> header = getColumnNames(inputList.get(0));

        String table = "<table><tbody><tr>";

        for (String headerVal : header) {
            table += "<td>" + headerVal + "</td>";
        }

        table += "</tr>";

        for (Object obj : inputList) {

            table += "<tr>";

            for (String fieldName : header) {
                table += "<td>" + getRowValue(obj, fieldName) + "</td>";
            }

            table += "</tr>";

        }

        table += "</tbody></table>";

        return table;

    }

}
