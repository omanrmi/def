import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);//подрубаю сканнер
		String expresso = scanner.nextLine();//передаем значение из терминала в строку expresso

		String[] data;//массив для хранения полученных из терминала данных
		char operation; //символ для хранения символа оператора

			if (expresso.contains("+")){ //если выражение содержит данный знак то
				data = expresso.split(" \\+ ");//полученное выражение разделяем по знаку +
				operation = '+';                   //сохраняем знак операции
			} else if (expresso.contains("-")) {
				data = expresso.split(" - ");
				operation = '-';
			} else if (expresso.contains("*")) {
				data = expresso.split(" \\* ");
				operation = '*';
			} else if (expresso.contains("/")) {
				data = expresso.split(" / ");
				operation = '/';
			}else {
				throw new Exception("Это арифметическое действие не поддерживается программой");
				//исключение, если пользователь введет не тот символ или знак
			}

					if (!data[1].contains("\"")){
						if (Integer.parseInt(data[1])> 10 || Integer.parseInt(data[1]) < 1){
							throw new Exception("Выход из диапазона значений");
							//проверяю на выход из диапазона
						}
					}

					if (!data[0].contains("\"")) {
						throw new Exception("Число не может быть первым");
						//проверяю стоит ли число первым
					}

					if (data[0].length() > 10 || data[1].length() > 10){
						throw new Exception("Очень длинная строка");
						//проверяю допустимую длинну строки
					}

					if (operation == '*'||operation == '/'){//если деление или умножение
						if (data[1].contains("\""))//проверяем является ли числом операнд2
							throw new Exception("Делить и умножать можно только на число");
							//если содержуются ковычки, то выкидываем ошибку
					}



			for(int i = 0; i < data.length; i++){
				data[i] = data[i].replace("\"","");
				//прогон через цикл значений и удаление кавычек
			}


				if (operation == '+'){//если знак операции соответствует
				giveQuotes(data[0]+data[1]);//выводим в терминал сложение строк

			}   else if (operation == '*') {
				int multi = Integer.parseInt(data[1]);//вытаскиваем значение множителя и конвертируем
				String result = "";
				for (int i = 0; i < multi; i++){//считаем количество нужных строк
					result+=data[0];
				}
				if (result.length() > 40){ //если длинна символов строки больше 40
					result = result.substring(0,40) + "...";//ограничеваем количество
				}
				giveQuotes(result);

			}   else if (operation == '-') {
				int index = data[0].indexOf(data[1]);//проверяем на совпадение
				if(index == -1){
					giveQuotes(data[0]);// если совпадений нет то выводим первое значение
				} else {
					String result = data[0].substring(0,index);
					result += data[0].substring(index+data[1].length());
					giveQuotes(result);
				}

			}   else{
				int lenght = data[0].length()/Integer.parseInt(data[1]);
				String reeult = data[0].substring(0,lenght);
				giveQuotes(reeult);
			}
		}


	 static void giveQuotes(String text){ //метод для обертывания строк в кавычки
		 System.out.print("\""+text+"\"");

	}




}