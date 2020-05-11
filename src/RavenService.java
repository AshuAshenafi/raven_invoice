import java.util.ArrayList;
import java.util.Random;

public class RavenService {

    // arraylist to store requests and user replys for latter display
    private ArrayList<String> requestReply = new ArrayList<>();

    public RavenService() {
    }

    public RavenService(ArrayList<String> requestReply) {
        this.requestReply = requestReply;
    }

    public ArrayList<String> getRequestReply() {
        return requestReply;
    }

    public void setRequestReply(ArrayList<String> requestReply) {
        this.requestReply = requestReply;
    }

    Random rnd = new Random();
    int low = 0;
    int high = 0;

    // vairables to count number of matches positive and/or negative
    int posCount = 0;
    int tempPosCount = 0;
    int negCount = 0;

    String[] positive = {"good", "glad", "happy", "relaxed", "accomplished", "alert", "creative", "satisfied"};
    String[] negative = {"bad", "sad", "tired", "angry", "anxious", "hungry", "moody", "afraid", "bored"};
    String[] ravenPositive = {"I am so happy for you.", "Yay.", "Please tell me what's bothering you.",
            "I understand. What do you think makes you feel that ?", "That is interesting. Please continue.", "I see."};
    String[] ravenNegative = {"Really! why, tell me more!", "Does that trouble you ?", "What does that suggest to you?", "Do you often feel bad ?",
            "Do you feel strongly about discussing such things ?"};
    String[] ravenNeutral = {"Meh", "Um.", "What are your feelings now ?", "Can you elaborate on that ?", "How do you feel right now ?"};
    String[] someBody = {"Brother", "Sister", "Mother", "Mom", "Father", "Dad", "Friend"};

    String[] replyForName = {"AKA Chat Therapist Eliza's cousin.", "My name is Chatterbot", "We were discussing you, not me.", "You can call me Chatterbot",
            "My name does not matter, may be I better stay anonymous."};
    String[] replyHowAreYou = {"I'm doing fine!", "I'm doing well and you?", "Why do want to know how am I doing?", "We were discussing you, not me."};
    String[] replyYouReal = {"Would you prefer if I were not real?", "Does that question really maters to you?", "What do you mean by that?",
            "I'm as real as I can be.", "We were discussing you, not me."};
    String[] replyHi = {"then tell me about yourself ?", "How are you today ?", "how are you feeling today ?"};
    String[] standardGreetings = {"hi", "hello", "hola", "ola", "howdy", "hey"};
    String[] doNotRepeat = {"Please do not repeat? let's just proceed ahead?", "Do you expect a different answer by repeating yourself ?",
            "why do you repeat yourself?"};

    String[] splitStr;        // split String array declared

    public void giveReply(String userInput) {

        String tempSomebody = "";
        int matchCount = 0;

        if (requestReply.size() == 0) {
            String firstRequest = "Good morning, how are you feeling today? (q/Q to quit)";
            requestReply.add(firstRequest);
        }
        // add user input to arraylist
        requestReply.add(userInput);

        // covert everything to lowercase to make fair comparison
        userInput = userInput.toLowerCase();

        // count if the input matches with saved list of positive word
        for (int i = 0; i < positive.length; i++) {
            String x = positive[i];
            if (userInput.contains(x)) {
                posCount++;
            }
        }
        // check if positive counter is not incremented
        // that means no match is found from the postive[], check if match is found with negative[]
        if (tempPosCount == posCount) {
            for (int i = 0; i < negative.length; i++) {
                String x = negative[i];
                if (userInput.contains(x)) {
                    negCount++;
                }
            }
        }
        // check if user just hit enter key with no input value
        if (userInput.length() == 0) {
            zeroInput();
            matchCount++;

        }
        // check if previous user input is same as current user input
        if (requestReply.size() > 3 && matchCount == 0) {
            boolean repeated;
            repeated = printWhyDoYouRepeat(userInput);
            if (repeated) {
                matchCount++;
            }
        }
        // if user asks "what is your name"
        if (userInput.contains("what is your name") && matchCount == 0) {
            printYourName();
            matchCount++;

        }
        // if user asks "how are you"
        if (userInput.contains("how are you") && matchCount == 0) {
            printHowYouAre();
            matchCount++;

        }
        // if user asks "are you real"
        if (userInput.contains("are you real") && matchCount == 0) {
            printYouAreReal();
            matchCount++;
        }

        if (matchCount <= 0) {

            // if user enters any of standard greetings echo him back with some additional request
            splitStr = userInput.split(" ");
            int k = 0;
            int m = 0;

            for (k = 0; k < splitStr.length; k++) {
                for (m = 0; m < standardGreetings.length; m++) {

                    // check for the match
                    if (splitStr[k].equalsIgnoreCase(standardGreetings[m])) {
                        printHi(splitStr[k]);
                        matchCount++;
                        k = splitStr.length;
                        m = standardGreetings.length;
                    }
                }
            }
        }

        // confirm if it didn't enter the someBody "for() loop" above
        if (matchCount == 0) {

            int countTemp = 0;
            for (countTemp = 0; countTemp < someBody.length; countTemp++) {
                if (userInput.contains(someBody[countTemp].toLowerCase())) {
                    // if user mentions either one of these words use it to request.
                    tempSomebody = someBody[countTemp];
                    //exit the loop
                    countTemp = (someBody.length);

                    printFromSomeBody(tempSomebody);            // like tell me more about your "brother"
                    matchCount++;

                }
            }
        }
        if (matchCount < 1) {
            // compare number of positive and negative matches
            if (posCount > negCount) {
                printFromPositive();
            } else if (posCount == negCount) {
                printFromNeutral();
            } else if (posCount < negCount) {
                printFromNegative();
            }
        }

        tempPosCount = posCount;

    }   // end of giveReply() method
    //////////////////////////////////////////////////////////////////////////////////////////////
    public void printFromNegative() {
        low = 0;
        high = ravenNegative.length;
        int index = low + rnd.nextInt(high - low);
        // add to the log list
        requestReply.add(ravenNegative[index]);
        // print request
        System.out.println(ravenNegative[index]);
    }

    public void printFromPositive() {
        low = 0;
        high = ravenPositive.length;
        int index = low + rnd.nextInt(high - low);
        requestReply.add(ravenPositive[index]);
        System.out.println(ravenPositive[index]);
    }

    public void printFromNeutral() {
        low = 0;
        high = ravenNeutral.length;
        int index = low + rnd.nextInt(high - low);
        requestReply.add(ravenNeutral[index]);
        System.out.println(ravenNeutral[index]);
    }

    public void printFromSomeBody(String str) {
        String someBody = "Interesting, Please tell me about your " + str + "?";
        requestReply.add(someBody);
        System.out.println("Interesting, Please tell me about your " + str + "?");
    }

    public void zeroInput(){
        System.out.println("I'm not sure if I understand what you are talking about.");
    }
    public void printHi(String str) {
        low = 0;
        high = replyHi.length;
        int index = low + rnd.nextInt(high - low);

        String reply = str + "!, " + replyHi[index];
        requestReply.add(reply);
        System.out.println(reply);
    }

    public void printYourName() {
        low = 0;
        high = replyForName.length;
        int index = low + rnd.nextInt(high - low);
        requestReply.add(replyForName[index]);
        System.out.println(replyForName[index]);
    }

    public void printHowYouAre() {
        low = 0;
        high = replyHowAreYou.length;
        int index = low + rnd.nextInt(high - low);
        requestReply.add(replyHowAreYou[index]);
        System.out.println(replyHowAreYou[index]);
    }

    public void printYouAreReal() {
        low = 0;
        high = replyYouReal.length;
        int index = low + rnd.nextInt(high - low);
        requestReply.add(replyYouReal[index]);
        System.out.println(replyYouReal[index]);
    }

    public boolean printWhyDoYouRepeat(String userInput) {
        int indexPrevious = (requestReply.size() - 3);
        boolean repeat = false;
        low = 0;
        high = doNotRepeat.length;
        int indexRandom =  low + rnd.nextInt(high - low);
        if (userInput.contains(requestReply.get(indexPrevious))) {
            requestReply.add(doNotRepeat[indexRandom]);
            System.out.println(doNotRepeat[indexRandom]);
            repeat = true;
        }
        return repeat;
    }

    public void printBye(String strBye) {
        String str = "It was nice talking to you user, see you next time!";
//        requestReply.add(str);
        System.out.println(str);
    }

    public void printAllLogs() {
        System.out.println("\n===============================CHAT HISTORY===============================\n");
        for (int i = 0; i < requestReply.size(); i++) {
            if (i % 2 != 0) {
                System.out.printf("%2s ME: %s\n", "", requestReply.get(i));
            } else {
                System.out.print("RAVEN: " + requestReply.get(i) + "\n");
            }
        }
        System.out.println("Thank you!");
    }
    public void copyArrayList(){
//        return requestReply.size();
    }
    public int copyArrayLength(){
         return requestReply.size();
//        return requestReply.size();
    }
}
