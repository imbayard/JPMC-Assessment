package com.jpmc.theater.exception;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomErrorResponseModel {

    private String source;
    private List<ErrorModel> errors;

    public CustomErrorResponseModel(String source, List<ErrorModel> errors) {
        this.source = source;
        this.errors = errors;
    }

    public CustomErrorResponseModel() {
    }

    @Override
    public String toString() {
        return "CustomErrorResponseModel{" +
                "source='" + source + '\'' +
                ", errors=" + errors +
                '}';
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorModel> errors) {
        this.errors = errors;
    }
}
