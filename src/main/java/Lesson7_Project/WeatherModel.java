package Lesson7_Project;

import java.io.IOException;
import java.util.List;

public abstract class WeatherModel {

    abstract void  getWeather(String city, Period period) throws IOException;   //Этому методу будем передовать название города и за какой период нужно получить погоду

    public List<Weather>  getSavedToDBWeather() {
        return null;
    }
}                                                            //Чтобы создался класс период на лампочке выбрали enum period
