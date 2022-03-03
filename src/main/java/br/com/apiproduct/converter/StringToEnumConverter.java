package br.com.apiproduct.converter;

import br.com.apiproduct.enums.Status;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Status> {

    @Override
    public Status convert(String source) {
        try {
            return Enum.valueOf(Status.class, source.toUpperCase());
        }
        catch (Exception e) {
            return null;
        }
    }
}