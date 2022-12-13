package com.analyst1.eval.model.darknet.pojo;

import com.analyst1.eval.model.Indicator;

import java.util.Objects;

public class RelatedIOC {
    private String value;
    private String type;


    public RelatedIOC(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public RelatedIOC(){}
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelatedIOC that = (RelatedIOC) o;
        return Objects.equals(value, that.value) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }
}
