package Lesson7_Project;

import java.io.IOException;

public abstract class WeatherModel {

    abstract void  getWeather(String city, Period period) throws IOException;   //Этому методу будем передовать название города и за какой период нужно получить погоду
}                                                            //Чтобы создался класс период на лампочке выбрали enum period
