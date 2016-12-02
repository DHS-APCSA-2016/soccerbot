------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: SoccerBot
PURPOSE OF PROJECT: Used for school soccer teams, the chatbot is for entertainment purposes 
and is used to converse with an user about the sport of soccer. 
VERSION or DATE: 1.0
HOW TO START THIS PROJECT: The chatbot will first greet someone by saying hello.
 Then after a response, it will ask a question such as who is your favorite team or player. 
Once you type the name of the team and player in all upper-case, it will recognize that you are 
talking about for ex. Lionel Messi and it will give a response that goes along the lines of
 “In my opinion, “(name of player)”is one of the greatest soccer players ever. For teams, 
the chatbot will respond in a similar manner and say that “In my opinion, “(name of team”)
 is my favorite.” This chatbot will be used for entertainment and it is only developed 
for conversation with the user.

AUTHORS: Cassandra Maier & Sumedh Vaidyanathan
PROGRAM HIGHLIGHTS: Our program is different since it is not the typical acidemic
chatbot that talks about school work. This chatbot talks about soccer and the target
audience is the school soccer team since those players will have the most experty
in the subject. My unique code is that I used hte program to analyze for keywords
and it can detect a keyword to give out a correct response. We have a response for
null input in the form of code: 
public String getGreeting()
    {
        return "Welcome to the SoccerBot Chatbot. \n I am here to converse with you about the topic of soccer. \n Please type your response below. \n Hi there, How are you doing?";
        //Random Response for a conversation starter
        // Additionally, this is a Context Starter Response
    }
The program also has context setting responses such as keywords like "goal". 
Additionally, our program has transposition responses that takes a statement
such as "I want to (something)' and transform it into "What would it mean
to (something)." We did use good Javadoc format for comments in our program for 
everyone's understanding of our program.