package org.example;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)//игнорирование для класса с помощью анатации
public class Car {
    private String color; //Цвет
    @JsonProperty("model")//Хотим сказать что в json называеться model а у нас type
    private String type;
    private Seat seat;


    //Нужнов явном виде обьявить дефолтный конструктор из за того что у нас есть параметры цвет и тип
            public Car(){}

    public Car(String color, String type) {
        this.color = color;
        this.type = type;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;

    }

    @Override
    public String toString() {       //Метод ту стринг чтобы все хорошо отображалось
        return "Car{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", seat='" + seat + '\'' +  //добавили сиат вручную
                '}';
    }
}
