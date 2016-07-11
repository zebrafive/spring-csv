package com.zebrafive.csv;

import java.util.List;
import java.util.ArrayList;

public class CSVResponse {
    
    public String[] getFieldNames() {
        return new String[]{"test1", "test2"};
    }
    
    public List<String[]> getAllRecords() {
        List<String[]> allRecords = new ArrayList<>();
        allRecords.add(new String[]{"value1", "value2"});
        return allRecords;
    }
}
