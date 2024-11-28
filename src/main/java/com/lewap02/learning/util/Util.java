package com.lewap02.learning.util;

import com.lewap02.learning.util.annotations.Getter;
import jakarta.persistence.Column;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Util {

    private static final Log log = LogFactory.getLog(Util.class);

    public static String makeResponse (String contentInside) {
        return  "<head><style>table, th, td {border: 1px solid black;}</style></head>" +
                "<body style=\"background-color: gray\"><br>" + contentInside + "</body>";
    }

    private static List<String> getColumnNames (Object inputObject) {

        return Arrays.stream(inputObject.getClass().getDeclaredFields())
                .filter(s -> s.isAnnotationPresent(Column.class))
                .map(Field::getName)
                .toList();
    }

    private static String getRowValue (Object inputObject, String fieldName) throws TooManyGettersForSameFieldException, NoGetterForFieldException {

        int cnt = 0;
        String val = null;

        for (Method method : inputObject.getClass().getDeclaredMethods()) {
            Getter getter = method.getAnnotation(Getter.class);

            if(getter != null && fieldName.equals(getter.fieldName())){
                cnt++;
                if (cnt > 1) {
                    throw new TooManyGettersForSameFieldException("There can only be one Getter annotated method for field " + fieldName + ". Check " + inputObject.getClass());
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

        return val;
    }

    public static String makeTable (List<?> inputList) throws TooManyGettersForSameFieldException, NoGetterForFieldException {

        List<String> header = getColumnNames(inputList.get(0));

        StringBuilder table = new StringBuilder("<table><tbody><tr>");

        for (String headerVal : header) {
            table.append("<td>").append(headerVal).append("</td>");
        }

        table.append("</tr>");

        for (Object obj : inputList) {

            table.append("<tr>");

            for (String fieldName : header) {
                table.append("<td>").append(getRowValue(obj, fieldName)).append("</td>");
            }

            table.append("</tr>");

        }

        table.append("</tbody></table>");

        return table.toString();

    }

}
