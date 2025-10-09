//Лабораторная работа №1 Вариант 12 студентов группы 24ВП2 Ковалева И.С., Сазнова Д.А.
import java.util.Scanner;

/**ЛР1 В12 Перевод из семеричной системы счисления в троичную
 * @author Ковалев
 * */
public class lab1{

	public static void main(String[] args){

		System.out.println("ЛР1 В12\nПеревод из семеричной системы счисления в троичную\nКовалев, Сазнов");

		Scanner in = new Scanner(System.in);
		while (true){
			System.out.print("Введите семеричное число, не превышающее по модулю 104134211160 (выход - \"exit\"): ");
			String userInput = in.nextLine();
			if (userInput.equals("exit")) return;
			try {
				String ternary = GeptToTer(userInput); 
				System.out.println(ternary);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Переводит число из семеричной системы счисления в троичную
	 * @param gept целое семеричное число
	 * @return троичное число
	 * @throws Exception
	 * <ul>
	 * 	<li>Строка пуста</li>
	 * 	<li>Символ не является семеричной цифрой</li>
	 * 	<li>Число больше 104134211160 по модулю</li>
	 * </ul> 
	 */
	private static String GeptToTer(String gept) throws Exception{
		return DecToTer(GeptToDec(gept));
	}

	/**
	 * Переводит число из семеричной системы счисления в десятичную
	 * @param gept целое семеричное число
	 * @return десятичное число
	 * @throws Exception
	 * <ul>
	 * 	<li>Строка пуста</li>
	 * 	<li>Символ не является семеричной цифрой</li>
	 * 	<li>Число больше 104134211160 по модулю</li>
	 * </ul> 
	 */
	private static int GeptToDec(String gept) throws Exception{
		if (gept.isEmpty()) throw new Exception("Строка пуста.");
		int res = 0;
		String absGept = gept;

		if (gept.startsWith("-")) {
			absGept = gept.replace("-", "");
			if (absGept.isEmpty()) throw new Exception("Символ \"-\" не является семеричной цифрой.");
		}

		for (int i=0; i<absGept.length(); i++){
			char geptChar = absGept.charAt(absGept.length()-1-i);
			int geptNum = Character.getNumericValue(geptChar);
			if (geptNum>6 || geptNum<0) throw new Exception("Символ \""+geptChar +"\" не является семеричной цифрой.");
			res += geptNum*Math.pow(7, i);
		}

		if (res>=Integer.MAX_VALUE) throw new Exception("Число больше 104134211160 по модулю.");

		if (gept.startsWith("-")) res = -res;

		return res;
	}
	
	/**
	 * Переводит число из десятичной системы в троичную
	 * @param dec десятичное число
	 * @return троичное число
	 */
	private static String DecToTer(int dec){
		String res = "";
		StringBuilder stringBuilder = new StringBuilder(res);
		if (dec==0) res = "0";
		else if(dec<0) {
			res = "-";
			dec = -dec;
		}

		while (dec>0){
			stringBuilder.append(String.valueOf(dec%3));
			dec /= 3;
		}
		stringBuilder.reverse();
		res += stringBuilder.toString();
		return res;
	}
}
