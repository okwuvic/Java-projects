public class Quiz {

    public static void main(String[] args) {

        Question question = new TrueFalseQuestion("Quizzes are similar to games!","y");
        question.check();

        question = new TrueFalseQuestion("The operations y >> 3 and y >>> 3 produce the same result " +
                "when y > 0","True");
        question.check();

        question = new TrueFalseQuestion("Which one starts with T?","t");
        question.check();

        question = new TrueFalseQuestion("The modulus operator (%) in Java can be used only " +
                "with variables of integer type?","f");
        question.check();

        question = new TrueFalseQuestion("Objects of a subclass can be assigned to a super " +
                "class reference?","true");
        question.check();

        question = new MultipleChoiceQuestion  (

                "Who is President of USA?",
                "Donald Trump",
                "Muhammadu Buhari",
                "Adama Barrow",
                "George Weah",
                "None of the above",
                "a");
        question.check();


        question = new MultipleChoiceQuestion(

                "Who is President of Nigeria?",
                "Donald Trump",
                "Muhammadu Buhari",
                "Adama Barrow",
                "George Weah",
                "None of the above",
                "b");
        question.check();


        question = new MultipleChoiceQuestion(

                "Who is President of The Gambia?",
                "Donald Trump",
                "Muhammadu Buhari",
                "Adama Barrow",
                "George Weah",
                "None of the above",
                "c");
        question.check();


        question = new MultipleChoiceQuestion(

                "Who is President of Liberia?",
                "Donald Trump",
                "Muhammadu Buhari",
                "Adama Barrow",
                "George Weah",
                "None of the above",
                "d");
        question.check();

        question = new MultipleChoiceQuestion(

                "Who is President of Ghana?",
                "Donald Trump",
                "Muhammadu Buhari",
                "Adama Barrow",
                "George Weah",
                "None of the above",
                "e");
        question.check();

        MultipleChoiceQuestion.showResults();
    }
}