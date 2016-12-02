/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 *      Uses advanced search for keywords 
 *</li><li>
 *      Will transform statements as well as react to keywords
 *</li></ul>
 * @author Laurie White
 * @version April 2012
 *
 */
public class Magpie4
{
    /**
     * Get a default greeting   
     * @return a greeting
     */ 
    public String getGreeting()
    {
        return "Welcome to the SoccerBot Chatbot. \n I am here to converse with you about the topic of soccer. \n Please type your response below. \n Hi there, How are you doing?";
        //Random Response for a conversation starter
        // Additionally, this is a Context Starter Response
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (statement.length() == 0)
        {
            response = "Iḿ sorry but I wasn’t able to identify what you said";
            //Response for null input 
        }
        
        else if (findKeyword(statement, "great") >= 0)
        {
            response = "What are you feeling great about?";
            //Response when someone says keyword "Great" for describing players, games, and etc. 
        }
        else if (findKeyword(statement, "football") >= 0)
        {
            response = "Either you are confused with American football or soccer";
            //Response when someone says keyword "Football" and chatbot will only understand the term soccer so saying football will cause confusion to the chatbot
        }
        else if (findKeyword(statement, "goal") >= 0)
        {
            response = "Who scored the goal?";
            //Response when someone says keyword "goal" and chatbot will ask who scored a goal for context
        }
        else if (findKeyword(statement, "forward") >= 0)
        {
            response = "Aren’t Forwards the Players Closest to the Other Team’s Goals?";
            //Response when someone says keyword "forward" and chatbot will clarify its' doubts about soccer forwards
        }
        else if (findKeyword(statement, "midfielder") >= 0)
        {
            response = "Aren’t Midfielders the Players Who Defend and Attack?";
            //Response when someone says keyword "midfielder" and chatbot will clarify its' doubts about soccer midfielders
        }
        else if (findKeyword(statement, "defender") >= 0)
        {
            response = "Aren’t Defenders Protecting The Goal?";
            //Response when someone says keyword "defender" and chatbot will clarify its' doubts about soccer defenders
        }
        else if (findKeyword(statement, "goalkeeper") >= 0)
        {
            response = "Aren’t Goalkeepers Supposed to Stop the Ball from entering the Goal?";
            //Response when someone says keyword "goalkeeper" and chatbot will clarify its' doubts about soccer goalkeepers
        }
        else if (findKeyword(statement, "player") >= 0)
        {
            response = "What About the Player?";
            //Response when someone says keyword "player", the chatbot wants to know details about the player
        }
         else if (findKeyword(statement, "team") >= 0)
        {
            response = "What About the Team?";
            //Response when someone says keyword "team", the chatbot wants to know details about the team
        }
        else if (findKeyword(statement, "win") >= 0)
        {
            response = "That is great news!";
            //Response when someone says keyword "win", the chatbot will react positively and celebrate
        }
        else if (findKeyword(statement, "lose") >= 0)
        {
            response = "That’s too bad";
            //Response when someone says keyword "lose", the chatbot will react negatively and be sympathetic
        }
         else if (findKeyword(statement, "bad") >= 0)
        {
            response = "Really? Tell Me More About It. What is that bad?";
            //Response when someone says keyword "bad", the chatbot will be concerned and ask the user what is bad
        }
        else if (findKeyword(statement, "penalty box") >= 0)
        {
            response = "What happened in the penalty box?";
            //Response when someone says keyword "penatly box", the chatbot wil ask about the event occuring in the penalty box in a soceer field
        }
        else if (findKeyword(statement, "kick") >= 0)
        {
            response = "Did this result in a goal?";
            //Response when someone says keyword "kick", the chatbot wil ask about what happened after a kick.
        }
        else if (findKeyword(statement, "pass") >= 0)
        {
            response = "Who was the soccer ball passed to?";
            //Response when someone says keyword "pass", the chatbot wil ask about who was the soccer ball passed to in order to make the user explain what they are saying.
        }
         else if (findKeyword(statement, "dribble") >= 0)
        {
            response = "Was the player able to juke others?";
            //Response when someone says keyword "dribble", the chatbot wil ask about the event that occured after a player dribbled the soccer ball
        }
        else if (findKeyword(statement, "yellow card") >= 0)
        {
            response = "This means that the player got a warning card!";
            //Response when someone says keyword "yellow card", the chatbot will confirm about a yellow card
        }
        else if (findKeyword(statement, "red card") >= 0)
        {
            response = "Thatś too bad since the player is now ejected";
            //Response when someone says keyword "red card", the chatbot will confirm its' beliefs about a red card
        }
         else if (findKeyword(statement, "love") >= 0)
        {
            response = "You love it?";
            //Response when someone says keyword "love", the chatbot will confirm whether the user actually loves something such as a player
        }
        
        else if (findKeyword(statement, "F.C. Barcelona") >= 0
                || findKeyword(statement, "Real Madrid") >= 0
                || findKeyword(statement, "Bayern Munich") >= 0)
                // Context Setting Responses that Listens to names of the biggest soccer teams in the world
                //Response by asking about more information about this team and the user can talk about the team history and players
        {
            response = "Tell Me More About This Team";
        }

        // Responses which require transformations
        else if (findKeyword(statement, "I want ", 0) >= 0)
        {
            response = transformIWantStatement(statement);
        }

        else
        {
            // Look for a two word (you <something> me)
            // pattern
            int psn = findKeyword(statement, "you", 0);

            if (psn >= 0
                    && findKeyword(statement, "me", psn) >= 0)
            {
                response = transformYouMeStatement(statement);
            }
            else
            {
                response = getRandomResponse();
            }
        }
        return response;
    }
    
    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    private String transformIWantStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want ", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "Why would you want a " + restOfStatement + "In this amazing world of soccer?";
        //A transposition response
    }
    
    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    private String transformYouMeStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        
        int psnOfYou = findKeyword (statement, "you", 0);
        int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
        
        String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
        return "What makes you think that I " + restOfStatement + " you?";
        //Transposition response where it finds keywords you and me and then reads the rest of the statement to ask what makes you think that I .... you. This adds a feeling of humor to this program
    }
    
    

    
    
    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  
     * @param statement the string to search
     * @param goal the string to search for
     * @param startPos the character of the string to begin the search at
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal, int startPos)
    {
        String phrase = statement.trim();
        //  The only change to incorporate the startPos is in the line below
        int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
        
        //  Refinement--make sure the goal isn't part of a word 
        while (psn >= 0) 
        {
            //  Find the string of length 1 before and after the word
            String before = " ", after = " "; 
            if (psn > 0)
            {
                before = phrase.substring (psn - 1, psn).toLowerCase();
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
            }
            
            //  If before and after aren't letters, we've found the word
            if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
                    && ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
            {
                return psn;
            }
            
            //  The last position didn't work, so let's find the next, if there is one.
            psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
            
        }
        
        return -1;
    }
    
    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
     * @param statement the string to search
     * @param goal the string to search for
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal)
    {
        return findKeyword (statement, goal, 0);
    }
    


    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 8;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        
        if (whichResponse == 0)
        {
            response = "Who is your favorite player?";
            //This is a random response that asks the user for their favorite player
        }
        else if (whichResponse == 1)
        {
            response = "What is your favorite team?";
            //This is a random response that asks the user for their favorite team to converse with the robot and explain about the team. 
        }
        else if (whichResponse == 2)
        {
            response = "Thats Great!";
            //This is another random response where the program gives a response used in nearly every conversation: "That's Great"
        }
        else if (whichResponse == 3)
        {
            response = "Wow.";
            //This Random Response is used randomnly and since it is fitting in any conversation, the user will not feel awkward and walk away. 
        }
        else if (whichResponse == 4)
        {
            response = "Do You Love Soccer?";
            //This random response asks the user randomnly whether they love soccer and this will be used to keep the user engaged and ready to respond to the question.
        }
         else if (whichResponse == 5)
        {
            response = "Please Tell Me More";
            //This random response makes the chtbot appear as if it is interested in the topic of the user and asks the user to tell the bot more.
        }
         else if (whichResponse == 6)
        {
            response = "Hmm.. Interesting";
            //This random response also makes the chatbot seem like it is interested in the topic of the user and makes it appear that the chatbot is listening to a new topic and is commenting on how interesting the user's comment/ or topic is.
        }
        else if (whichResponse == 6)
        {
            response = "Do you want to know more about the sport?";
            //This random response also makes the chatbot ask whether the user wants to learn more about soccer
        }
        return response;
    }

}
