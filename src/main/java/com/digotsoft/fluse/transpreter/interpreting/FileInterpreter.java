package com.digotsoft.fluse.transpreter.interpreting;

import com.digotsoft.fluse.transpreter.Type;
import com.digotsoft.fluse.transpreter.component.AccessLevel;
import com.digotsoft.fluse.transpreter.component.ClassRegistry;
import com.digotsoft.fluse.transpreter.component.Component;
import com.digotsoft.fluse.transpreter.component.FluseClass;
import com.digotsoft.fluse.transpreter.error.SyntaxError;
import com.digotsoft.fluse.transpreter.processing.ServerProcessing;

import java.util.Arrays;
import java.util.List;

/**
 * @author Markus
 */
public class FileInterpreter {
    private static List<Character> LIMITERS = Arrays.asList('{','}', ';');
    private String fileContent;
    private Statement context;

    public FileInterpreter(byte[] fileBytes) {
        this.fileContent = new String(fileBytes);
        this.context = new Statement(null, StatementType.FILE);
    }

    public void run(){
        int cursor = 0;
        int length = this.fileContent.length();
        char[] contentCharArray = this.fileContent.toCharArray();

        StringBuilder currentContext = new StringBuilder();

        while(cursor < length - 1){
            char currentChar = contentCharArray[cursor];
            currentContext.append(currentChar);
            if(FileInterpreter.LIMITERS.contains(currentChar)){
                this.processStatement(currentContext.toString());
                currentContext.setLength(0);
            }

            cursor++;
        }
    }


    private void processStatement(String statement){
        statement = statement.trim().replace("\n", "").replace("\r", ""); // Remove new lines
        //statement = statement.replace(" ", ""); // Removes whitespaces TODO: Dont remove whitespaces in strings
        //statement = statement.replaceAll("(\"[^\"\\\\]*(?:\\\\.[^\"\\\\]*)*\")|(\\s+)", "");

        String[] spacedStatement = statement.split(" ");

        if(statement.startsWith("//")){
            // It is a comment,we can ignore it
            return;
        }

        if(this.context.getChilds().size() == 0){
            // No class has been declared yet - the first statement should be the class declaration
            if(statement.matches(Component.CLASS_MATCHER)){
                // Build the class
                String accessModifier = spacedStatement[0];
                String baseClass = spacedStatement[1];
                String className = spacedStatement[2];

                FluseClass fluseClass = new FluseClass(className, Type.HYBRID, new ServerProcessing(), AccessLevel.valueOf(accessModifier.toUpperCase()));
                if(!baseClass.equals("class") && !baseClass.equals("Class")){
                    fluseClass.setParent(ClassRegistry.findClass(baseClass));
                }

                // Register it in the class registry
                ClassRegistry.register(fluseClass);
                this.context.addChild(new Statement(this.context, StatementType.CLASS_DECLARATION));
            }
            else {
                throw new SyntaxError("Expected class declaration!");
            }
        }


        System.out.println(statement);
    }
}
