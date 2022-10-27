package Lesson7_Project;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



import static Lesson7_Project.Period.FIV_DAYS;
import static Lesson7_Project.Period.NOW;

public class Controller {
    private WeatherModel weatherModel = new AccuwetharModel(); //может отправлять запрос на эту модель

    private Map<Integer,Period> variants = new HashMap<>(); //от клиента будет приходить цифорка 1 или 5 а контроллер это преобразует в NOW или FIVE_DAYS.

    public Controller(){
        variants.put(1,NOW);
        variants.put(5,FIV_DAYS);
        variants.put(2, Period.IVSB);
    }

    //метод

    public  void  getWaather(String userInput, String cityName) throws IOException {//Будет принимать цифру и название города
        Integer command = Integer.parseInt(userInput);

        switch (variants.get(command)){
            case NOW:
                weatherModel.getWeather(cityName,NOW);
                break;

            case FIV_DAYS:
                weatherModel.getWeather(cityName,FIV_DAYS);
                break;

            case IVSB:
                weatherModel.getSavedToDBWeather();
        }
    }
}
