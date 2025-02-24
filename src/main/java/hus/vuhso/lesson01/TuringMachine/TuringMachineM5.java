package hus.vuhso.lesson01.TuringMachine;

public class TuringMachineM5 {

    // Turing Machine M5
    // Máy Turing M5 thực hiện việc kiểm tra một dãy số nhị phân có đối xứng không. Ví dụ:
    //010010 =⇒ “yes”, 01100 =⇒ “no”.
    private static final char BLANK = ' ';
    private static final char ONE = '1';
    private static final char ZERO = '0';
    private static final char YES = 'y';
    private static final char NO = 'n';
    private static final char START = 's';
    private static final char CHECK_LEFT = 'l';
    private static final char CHECK_RIGHT = 'r';
    private static final char HALT = 'h';
    private static final char COMPARE = 'c';

    private char[] tape;
    private int headPosition;
    private char state;

    public TuringMachineM5(String input) {
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
                if (currentSymbol == ONE || currentSymbol == ZERO) {
                    state = CHECK_RIGHT;
                    headPosition++;
                } else if (currentSymbol == BLANK) {
                    tape[0] = YES;
                    state = HALT;
                }
                break;

            case CHECK_RIGHT:
                if (currentSymbol == ONE || currentSymbol == ZERO) {
                    headPosition++;
                } else if (currentSymbol == BLANK) {
                    headPosition--;
                    state = CHECK_LEFT;
                }
                break;

            case CHECK_LEFT:
                if (currentSymbol == ONE || currentSymbol == ZERO) {
                    headPosition--;
                } else if (currentSymbol == BLANK) {
                    headPosition++;
                    state = COMPARE;
                }
                break;

            case COMPARE:
                char leftSymbol = tape[headPosition];

                headPosition++;
                while (tape[headPosition] != BLANK) {
                    headPosition++;
                }
                headPosition--;

                char rightSymbol = tape[headPosition];
                if (leftSymbol == rightSymbol) {
                    tape[headPosition] = BLANK;
                    headPosition--;
                    while (tape[headPosition] != BLANK) {
                        headPosition--;
                    }
                    headPosition++;
                    tape[headPosition] = BLANK;
                    headPosition++;
                    state = START;
                } else {
                    tape[0] = NO;
                    state = HALT;
                }
                break;
        }
    }

    public String getTapeContent() {
        return new String(tape).trim();
    }
}
