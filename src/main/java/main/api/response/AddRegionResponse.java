package main.api.response;

import java.util.LinkedHashMap;

public class AddRegionResponse {
    private boolean result;
    private LinkedHashMap<String, String> errors = new LinkedHashMap<>();

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public LinkedHashMap<String, String> getErrors() { return errors; }

    public void setErrors(String name, String shortName) {
        this.errors.put("Имя региона: ", name);
        this.errors.put("Короткое имя региона: ", shortName);
    }
}
