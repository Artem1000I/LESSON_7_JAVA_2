package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();//Из обьекта JAVA делоть Json и обратно
        Car car = new Car("White","Lada");   //Конструктор с двумя параметрами поэтому задаём два параметра цвет и тип

       String jsonFromCar = objectMapper.writeValueAsString(car); //Метод ля преобразования в строчку

        System.out.println(jsonFromCar);

        Car carFromJSON = objectMapper.readValue(jsonFromCar, Car.class); //Передаем откуда читаем JsonFromCar реобразуем обратно
            System.out.println(carFromJSON); //распечатаем что получилось


        //СПИСОК МАШИН
        List<Car> cars = new ArrayList<>(Arrays.asList(
                new Car("Black","Mersedes"), new Car("Red","Renault"))); // Создали лист из двух машин добавили чререз запятую

        //Нужно записать этот саписок в виде JSON

        String carlistJSON = objectMapper.writeValueAsString(cars); // преобразуем
        System.out.println(carlistJSON);                            // распечатываем на выводе вфигурных скобках потомучто множество обьектов

        //Преобразуем обратно
               List<Car> carsFromJSON = objectMapper.readValue(carlistJSON, new TypeReference<ArrayList<Car>>() {}); // нужно явно указать что мы востанавливаем  new TypeReference<ArrayList<Car>>
                    System.out.println(carsFromJSON);

                    //Дана строка JSON из которой нужно собрать обьект
                        String jsonCarAfterUpdate = "{\"color\":\"Белый\",\"type\":\"Lada\",\"year\":\"Lada\"}";
                        /*objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);*/ //Чтобы игнорироватьь неизвестные проперти
                            Car carAfterUpdate = objectMapper.readValue(jsonCarAfterUpdate,Car.class);
                            System.out.println(carAfterUpdate);


                        //Еще одна стринга но type заменили еа модель
                                String jsonCarAfterRefactoring ="{\"Color\":\"Белый\",\"model\":\"Lada\"}";
                                Car carAfterRefactoring = objectMapper.readValue(jsonCarAfterRefactoring,Car.class);
                                System.out.println(carAfterRefactoring);


        //Машина с сиденьями
                Car CarWithSeat = new Car("Yellow","Peugeot");
                car.setSeat(new Seat("seat", Integer.valueOf(5)));

               //Записываем стринг в JSON
        String carWithSeatJSON = objectMapper.writeValueAsString(CarWithSeat);
        System.out.println(carWithSeatJSON);

        Car carWithSeatFromJSON = objectMapper.readValue(carWithSeatJSON, Car.class);
        System.out.println(carWithSeatFromJSON);
    }
}