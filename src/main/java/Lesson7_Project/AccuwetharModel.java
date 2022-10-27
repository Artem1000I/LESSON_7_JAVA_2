package Lesson7_Project;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static Lesson7_Project.Period.FIV_DAYS;

public class AccuwetharModel extends WeatherModel {


    //http://dataservice.accuweather.com/forecasts/v1/daily/1day /349727  //URL с сайта на котррый будем отправлять запрос
    //http://dataservice.accuweather.com/forecasts/v1/daily/5day /349727
    private static final String PROTOCOL = "http";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";

    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "kgoIoLeYhCwSPhBbayB4GqYrEAAD9zYD";  //ключ апи с сайта
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomlete";


    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    void getWeather(String city, Period period) throws IOException { //этот метод будет подключаться к серверу с погодой


        switch (period) {
            case NOW://Адрес для одного дня
                HttpUrl url = new HttpUrl.Builder()
                        .scheme(PROTOCOL)   //схема предаем сюдя переменную ПРОТОКОЛ
                        .host(BASE_HOST)// передаем хост
                        //передаём элементы пути
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(city))  // элемент города // создаем метод detectCityKey
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY) // Нужно указать два параметра ключ и его значение
                        //URL создали билдим его
                        .build();

                //Должны теперь кинуть туда запрос
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                //Теперь этот реквест нужно закинуть на наш URL
                Response onaDayresponse = okHttpClient.newCall(request).execute(); // execute нажимаем на лампочку выбираем add exeption to method signature
                String weatheResponse = onaDayresponse.body().string(); //Нужно тело ответа перевести в стрингу

                System.out.println(weatheResponse);
                break;

            case FIV_DAYS: // Для пяти дней
                HttpUrl urlFive = new HttpUrl.Builder()
                        .scheme(PROTOCOL)   //схема предаем сюдя переменную ПРОТОКОЛ
                        .host(BASE_HOST)// передаем хост
                        //передаём элементы пути
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIV_DAYS)
                        .addPathSegment(detectCityKey(city))  // элемент города // создаем метод detectCityKey
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY) // Нужно указать два параметра ключ и его значение
                        //URL создали билдим его
                        .build();

                //Должны теперь кинуть туда запрос
                Request requestFive= new Request.Builder()
                        .url(urlFive)
                        .build();

                //Теперь этот реквест нужно закинуть на наш URL
                Response fiveDayresponse = okHttpClient.newCall(requestFive).execute(); // execute нажимаем на лампочку выбираем add exeption to method signature
                String weathResponse = fiveDayresponse.body().string(); //Нужно тело ответа перевести в стрингу

                System.out.println(weathResponse);
                break;


        }
    }

    private String detectCityKey(String city) throws IOException { //принимает стринг и возвращает стринг
        //адрес берём из апи
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM,API_KEY)
                .addQueryParameter("q",city)
                .build();
// Собрали адрес теперь нужно собрать запрос
        Request request = new Request.Builder()
                .url(httpUrl)
                .get() //явно указываем get
                .addHeader("accept","application/json") // передаем heder что принимается формат json
                .build();

        //Теперб отправляем этот запрос с помощью OKhttpKlient
        Response response = okHttpClient.newCall(request).execute();
        //ПОлучаем ответ
        String responseString = response.body().string(); // берём с ответа боди преобразуем в стрингу
        //Там будет JSON его нужно распарсить
        String cityKey = objectMapper.readTree(responseString).get(0).at("/key").asText(); //берём корневой элемент обращаемся к пункту key
        System.out.println(responseString);

        return cityKey;
    }


}
