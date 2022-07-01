package stdbtt.springcourse.util;

public class Search {
    private String request;

    private boolean isAnswer;

    private boolean isAnswerEmpty;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }

    public boolean isAnswerEmpty() {
        return isAnswerEmpty;
    }

    public void setAnswerEmpty(boolean answerEmpty) {
        isAnswerEmpty = answerEmpty;
    }
}
