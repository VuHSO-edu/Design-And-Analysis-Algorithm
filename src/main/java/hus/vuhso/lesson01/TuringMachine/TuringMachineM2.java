package hus.vuhso.lesson01.TuringMachine;

public class TuringMachineM2 {

    // Turing Machine M2
    // Máy Turing M2 thực hiện phép trừ 1 của số nhị phân.

    private static final char BLANK = ' ';
    private static final char ONE = '1';
    private static final char ZERO = '0';
    private static final char START = 's';
    private static final char HALT = 'h';

    private char[] tape;
    private int headPosition;
    private char state;

    public TuringMachineM2(String input) {
        this.tape = (input + BLANK).toCharArray();
        this.headPosition = input.length() - 1;
        this.state = START;
    }

    public void run() {
        int stepCount = 0;
        while (state != HALT) {
            System.out.println("Step " + (++stepCount) + ": " + getTapeContent() + " (Head Position: " + headPosition + ", State: " + state + ")");
            step();
        }
        System.out.println("Final Output: " + getTapeContent());
    }

    private void step() {
        char currentSymbol = tape[headPosition];

        switch (state) {
            case START:
                if (currentSymbol == ONE) {
                    tape[headPosition] = ZERO;
                    state = HALT;
                } else if (currentSymbol == ZERO) {
                    tape[headPosition] = ONE;
                    headPosition--;
                    state = START;
                } else if (currentSymbol == BLANK) {
                    tape[headPosition] = ZERO;
                    state = HALT;
                }
                break;
        }
    }

    public String getTapeContent() {
        return new String(tape).trim();
    }

}
