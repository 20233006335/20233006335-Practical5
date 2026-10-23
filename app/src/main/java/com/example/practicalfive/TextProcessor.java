package com.example.practicalfive;

public class TextProcessor {

    public String processMunicipality(String municipality) {
        if (municipality == null || municipality.trim().length() == 0) {
            return null;
        }

        String input = municipality.trim().toLowerCase();

        if (input.equals("beijing")) {
            return "Beijing";
        } else if (input.equals("shanghai")) {
            return "Shanghai";
        } else if (input.equals("tianjin")) {
            return "Tianjin";
        } else if (input.equals("chongqing")) {
            return "Chongqing";
        } else {
            return null;
        }
    }
}
