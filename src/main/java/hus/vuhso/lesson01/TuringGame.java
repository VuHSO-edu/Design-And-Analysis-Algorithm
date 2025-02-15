package hus.vuhso.lesson01;

import java.util.Scanner;

public class TuringGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số nhị phân: ");
        String binaryNumber = scanner.nextLine(); // Số nhị phân cần cộng thêm 1
        TuringMachine machine = new TuringMachine(binaryNumber);
        machine.performAddition();
        System.out.println("Kết quả sau khi cộng 1: " + machine.getResult());
    }

}
