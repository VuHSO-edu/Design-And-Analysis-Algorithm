package hus.vuhso.lesson01;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
public class TuringMachine {
    private String tape;
    private int headPosition;
    private String state;
    private Map<String, Transition> transitions;

    public TuringMachine(String input) {
        this.tape = input + " "; // Thêm khoảng trắng để đánh dấu kết thúc
        this.headPosition = input.length() - 1; // Bắt đầu từ cuối của chuỗi nhị phân
        this.state = "q0"; // Trạng thái ban đầu
        this.transitions = new HashMap<>();

        // Định nghĩa các chuyển tiếp của máy Turing
        transitions.put("q0,0", new Transition('0', "q0", -1));
        transitions.put("q0,1", new Transition('1', "q0", -1));
        transitions.put("q0, ", new Transition(' ', "q1", 1));
        transitions.put("q1,0", new Transition('1', "qf", 0));
        transitions.put("q1,1", new Transition('0', "q1", 1));
        transitions.put("q1, ", new Transition('1', "q1", 0));
    }

    public void performAddition() {
        while (state != "qf") {
            if (headPosition < 0 || headPosition >= tape.length()) {
                throw new IllegalStateException("Head position out of bounds: " + headPosition);
            }

            char currentSymbol = tape.charAt(headPosition);
            String key = state + "," + currentSymbol;
            Transition transition = transitions.get(key);

            if (transition == null) {
                throw new IllegalStateException("Không có chuyển tiếp cho trạng thái " + state + " và ký hiệu " + currentSymbol);
            }

            tape = tape.substring(0, headPosition) + transition.writeSymbol + tape.substring(headPosition + 1);
            state = transition.nextState;
            headPosition += transition.moveDirection;
        }
    }

    public String getResult() {
        return tape.trim();
    }

    private static class Transition {
        char writeSymbol;
        String nextState;
        int moveDirection;

        public Transition(char writeSymbol, String nextState, int moveDirection) {
            this.writeSymbol = writeSymbol;
            this.nextState = nextState;
            this.moveDirection = moveDirection;
        }
    }

}
