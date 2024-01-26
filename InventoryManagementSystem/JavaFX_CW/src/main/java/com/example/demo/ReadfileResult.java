package com.example.demo;

import java.util.List;
import java.util.Map;

public class ReadfileResult {
    private Map<String, List<Object>> hashmap;
    private int NoOfLines;

    public ReadfileResult(Map<String, List<Object>> hashmap, int NoOfLines) {
        this.hashmap = hashmap;
        this.NoOfLines = NoOfLines;
    }

    public Map<String, List<Object>> getHashmap() {
        return hashmap;
    }

    public int getNoOfLines() {
        return NoOfLines;
    }
}
