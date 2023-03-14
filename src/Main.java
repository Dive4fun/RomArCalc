import java.util.Scanner;

class CalcRomAr {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение для вычисления: ");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }
//разбиваю на операнды по знаку
    public static String parse(String expression) throws Exception {
        int n1;
        int n2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть два операнда и один оператор");
        oper = detectOperation(expression);
        if (oper == null) throw new Exception("строка не является математической операцией");
        //если рим (ковертирую в араб)
        if (Rom.isRom(operands[0]) && Rom.isRom(operands[1])) {
            n1 = Rom.convertToArabian(operands[0]);
            n2 = Rom.convertToArabian(operands[1]);
            isRoman = true;
        }
        //если араб
        else if (!Rom.isRom(operands[0]) && !Rom.isRom(operands[1])) {
            n1 = Integer.parseInt(operands[0]);
            n2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        //рим + араб
        else {
            throw new Exception("используются одновременно разные системы исчисления");
        }
        if (n1 > 10 || n2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(n1, n2, oper);
        if (isRoman) {
            //если римское число получилось меньше либо равно нулю, генерируем ошибку
            if (arabian <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            // из арабского в римское
            result = Rom.convertToRoman(arabian);
        } else {
            //арабское число в тип String
            result = String.valueOf(arabian);
        }
        //возвращаем результат
        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

}
// римские массив плюс кодировка в арабские
class Rom {
    static String[] romArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean isRom(String val) {
        for (int i = 0; i < romArray.length; i++) {
            if (val.equals(romArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romArray.length; i++) {
            if (roman.equals(romArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romArray[arabian];
    }

}
