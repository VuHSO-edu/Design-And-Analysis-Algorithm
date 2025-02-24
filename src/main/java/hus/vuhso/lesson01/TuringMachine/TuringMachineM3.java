package hus.vuhso.lesson01.TuringMachine;

public class TuringMachineM3 {
    // Turing Machine M3
    // Máy Turing M3 thực hiện việc thay tất cả các số 0 trong một dãy nhị phân thành các số
    //1 và ngược lại. Ví dụ: 01001 =⇒ 10110.

    private static final char BLANK = ' ';
    private static final char ONE = '1';
    private static final char ZERO = '0';
    private static final char START = 's';
    private static final char HALT = 'h';

    private char[] tape;
    private int headPosition;
    private char state;

    public TuringMachineM3(String input) {
        this.tape = (input + BLANK).toCharArray();
        this.headPosition = 0;
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
                    headPosition++;
                    state = START;
                } else if (currentSymbol == ZERO) {
                    tape[headPosition] = ONE;
                    headPosition++;
                    state = START;
                } else if (currentSymbol == BLANK) {
                    state = HALT;
                }
                break;
        }
    }

    public String getTapeContent() {
        return new String(tape).trim();
    }

}
