package hus.vuhso.lesson01.TuringGame;

import hus.vuhso.lesson01.TuringMachine.*;

import java.util.Scanner;

public class TuringGame {

    private static Scanner scanner = new Scanner(System.in);

    private static void runTuringMachineM1() {
        System.out.print("Enter the input string: ");
        String input = scanner.nextLine();
        TuringMachineM1 turingMachine = new TuringMachineM1(input);
        turingMachine.run();
        System.out.println("Result: " + turingMachine.getTapeContent());
    }

    private static void runTuringMachineM2() {
        System.out.print("Enter the input string: ");
        String input = scanner.nextLine();
        TuringMachineM2 turingMachine = new TuringMachineM2(input);
        turingMachine.run();
        System.out.println("Result: " + turingMachine.getTapeContent());
    }

    private static void runTuringMachineM3() {
        System.out.print("Enter the input string: ");
        String input = scanner.nextLine();
        TuringMachineM3 turingMachine = new TuringMachineM3(input);
        turingMachine.run();
        System.out.println("Result: " + turingMachine.getTapeContent());
    }

    private static void runTuringMachineM4() {
        System.out.print("Enter the input string: ");
        String input = scanner.nextLine();
        TuringMachineM4 turingMachine = new TuringMachineM4(input);
        turingMachine.run();
        System.out.println("Result: " + turingMachine.getTapeContent());
    }

    private static void runTuringMachineM5() {
        System.out.print("Enter the input string: ");
        String input = scanner.nextLine();
        TuringMachineM5 turingMachine = new TuringMachineM5(input);
        turingMachine.run();
        System.out.println("Result: " + turingMachine.getTapeContent());
    }


    public static void main(String[] args) {

        // Turing Machine M1
        runTuringMachineM1();


        // Turing Machine M2
        runTuringMachineM2();

        // Turing Machine M3
        runTuringMachineM3();

        // Turing Machine M4
        runTuringMachineM4();

        // Turing Machine M5
        runTuringMachineM5();
    }

}
