package com.zebrafive.csv;

import java.util.*;
import java.util.stream.Collectors;

public class CSVResponse {

    private List<String> fieldNames = new ArrayList<>();
    private List<CSVRecord> csvRecords = new ArrayList<>();

    public CSVResponse withFieldNames(String... fieldNames) {
        this.fieldNames.addAll(Arrays.asList(fieldNames));
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
            return fieldNames.stream()
                    .map(fieldName -> fieldValues.get(fieldName) != null ? fieldValues.get(fieldName).toString() : "")
                    .toArray(String[]::new);
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
