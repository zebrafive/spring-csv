package com.zebrafive.csv;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import au.com.bytecode.opencsv.CSVWriter;

public class CSVResponseConverter extends AbstractHttpMessageConverter<CSVResponse> {
    
    public CSVResponseConverter() {
        super(new MediaType("text", "comma-separated-values"));
    }

    @Override
    protected boolean supports(Class<?> realClass) {
        return CSVResponse.class.equals(realClass);
    }

    @Override
    protected CSVResponse readInternal(Class<? extends CSVResponse> realClass, HttpInputMessage inputMessage)
            throws IOException {
                
        return null;
    }

    @Override
    protected void writeInternal(CSVResponse csvResponse, HttpOutputMessage outputMessage)
            throws IOException {
                
        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(outputMessage.getBody(), StandardCharsets.UTF_8))) {
            writer.writeNext(csvResponse.getFieldNames());
            writer.writeAll(csvResponse.getAllRecords());
        }
    }
}
