import java.util.Scanner;
public class testcalc {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		Main result = new Main();
		System.out.println("Введите запрос:");
		String expression = input.nextLine();
		String total;
		total = result.calc(expression);


		System.out.println("Итого:\n" + total);
	}
}

class Main{
	public static String calc(String input){
		boolean romaOrAraab = false;
		String exception = "throws Exception";
		Main romanExamination = new Main();
		Main araabToRoma = new Main();
		int result = 0;
		String[] inputSplit = input.split(" ");
		if (inputSplit.length != 3){
			return exception;
		}
		Integer a = 0;
		Integer b = 0;
		try {
			a = Integer.valueOf(inputSplit[0]);
			b = Integer.valueOf(inputSplit[2]);
		} catch (NumberFormatException e) {
			try {
				a = romanExamination.romanToArab(inputSplit[0]);
				b = romanExamination.romanToArab(inputSplit[2]);
				romaOrAraab = true;
			} catch (NumberFormatException ex) {
				return exception;
			}
		}

		if ((a < 1) || (a > 10) || (b < 1) || (b > 10)){
			return exception;
		}

		String sign = inputSplit[1];
		switch (sign) {
			case "+" -> result = a + b;
			case "-" -> result = a - b;
			case "*" -> result = a * b;
			case "/" -> result = a / b;
			default -> {
				return exception;
			}
		}

		String output;

		if (romaOrAraab){
			if(result < 1){
				return exception;
			} else {
				output = araabToRoma.arabToRome(result);
			}
		} else {
			output = Integer.toString(result);
		}

		return output;
	}

	Integer romanToArab(String romaInput){
		int result = 0;
		int[] arab = {10, 9, 5, 4, 1};
		String[] roman = {"X", "IX", "V", "IV", "I"};
		for (int i = 0; i < arab.length; i++ ) {
			while (romaInput.indexOf(roman[i]) == 0) {
				result += arab[i];
				romaInput = romaInput.substring(roman[i].length());
			}
		}

		return result;
	}

	String arabToRome(int araabInput){
		String result = "";
		int value = 0;
		int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		for (int i = 0; i < arab.length; i++){
			value = araabInput / arab[i];
			for (int j = 0; j < value; j++){
				result = result.concat(roman[i]);
			}
			araabInput = araabInput % arab[i];
		}
		return result;
	}
}