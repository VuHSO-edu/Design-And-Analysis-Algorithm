package hus.vuhso.lesson01.TuringMachine;

public class TuringMachineM4 {

    // Turing Machine M4
    // Máy Turing M4 thực hiện việc loại bỏ các chữ số 0 trong dãy nhị phân. Sau khi bỏ cần
    //dồn dãy lại. Ví dụ: 01001 =⇒ 11.
    private static final char BLANK = ' ';
    private static final char ONE = '1';
    private static final char ZERO = '0';
    private static final char START = 's';
    private static final char HALT = 'h';

    private char[] tape;
    private int headPosition;
    private char state;

    public TuringMachineM4(String input) {
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
                if (currentSymbol == ZERO) {
                    // Shift all elements to the left
                    for (int i = headPosition; i < tape.length - 1; i++) {
                        tape[i] = tape[i + 1];
                    }
                    tape[tape.length - 1] = BLANK;
                } else if (currentSymbol == ONE || currentSymbol == BLANK) {
                    headPosition++;
                }

                if (currentSymbol == BLANK) {
                    state = HALT;
                }
                break;
        }
    }

    public String getTapeContent() {
        return new String(tape).trim();
    }
}
