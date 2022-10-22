package org.example;

public class Seat extends CarElement{ //наследник от кар Элемент
    private  Integer count;

    public Seat(){}

    public Seat(String name, Integer count) {
        super(name);
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "count=" + count +
                '}';
    }
}
