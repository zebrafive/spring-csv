package com.zebrafive.csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CSVResponse {

    private List<String> fieldNames;
    private List<CSVRecord> csvRecords;

    public CSVResponse() {
        fieldNames = new ArrayList<>();
        csvRecords = new ArrayList<>();
    }

    public String[] getFieldNames() {
        return fieldNames.toArray(new String[fieldNames.size()]);
    }

    public List<String[]> getAllRecords() {
        return csvRecords.stream()
                .map(CSVRecord::getFieldValues)
                .collect(Collectors.toList());
    }

    public CSVRecord addRecord() {
        CSVRecord record = new CSVRecord();
        csvRecords.add(record);
        return record;
    }

    public class CSVRecord {

        private Map<String, Object> fieldValues = new HashMap<>();

        public String[] getFieldValues() {
            return (String[]) fieldNames.stream()
                    .map(fieldName -> fieldValues.get(fieldName) != null ? fieldValues.get(fieldName).toString() : "")
                    .toArray();
        }

        public CSVRecord withField(String fieldName, Object fieldValue) {
            if (!fieldNames.contains(fieldName)) {
                fieldNames.add(fieldName);
            }

            fieldValues.put(fieldName, fieldValue);

            return this;
        }

        public CSVRecord andAddRecord() {
            return addRecord();
        }
    }
}
