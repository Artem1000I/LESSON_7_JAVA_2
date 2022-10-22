package Lesson7_Project;

import java.io.IOException;
import java.util.Scanner;

public class UerInterfaceView {
    private Controller controller = new Controller();

    public void runInterface(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Введите название города...");
            String city = scanner.nextLine();

            System.out.println("Введите 1 для получение погоды на сегодня;" + "Введите 5 для прогноза погоды на 5 дней; Для выхода введите 0...:");
                    String command = scanner.nextLine();

                    if ("0".equals(command))break;


                    //Цифру 1 или 5 передать в контроллер
            try {
                controller.getWaather(command,city);
            } catch (IOException e) {
                System.out.println("Ошибка при получении погоды" + e.getMessage());
            }

        }
    }

    public static void main(String[] args) throws IOException {
        UerInterfaceView view = new UerInterfaceView();
        view.runInterface();

    }
}
