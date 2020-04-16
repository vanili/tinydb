package org.my.tinydb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.my.tinydb.MetaCommandResult.META_COMMAND_SUCCESS;
import static org.my.tinydb.MetaCommandResult.META_COMMAND_UNRECOGNIZED_COMMAND;
import static org.my.tinydb.StatementType.STATEMENT_INSERT;
import static org.my.tinydb.StatementType.STATEMENT_SELECT;

public class main {

    public static void main(String[] args) throws IOException {
        InputStreamReader is = new InputStreamReader(System.in);
        while (true){
            printPromt();
            String command = readInput(is);
            switch (do_meta_command(command)){
                case META_COMMAND_SUCCESS: break;
                case META_COMMAND_UNRECOGNIZED_COMMAND: System.out.println("Unrecognized command:"+command); continue;
            }
            Statement statement = new Statement();
            switch (prepare_statement(command, statement)){
                case PREPARE_SUCCESS:break;
                case PREPARE_UNRECOGNIZED_STATEMENT: System.out.println("Unrecognized command:"+command); continue;
            }

            execute_statement(statement);
            System.out.println("Executed.");
        }
    }

    private static void execute_statement(Statement statement){
        switch (statement.getType()){
            case STATEMENT_INSERT: System.out.println("insert will be execute"); break;
            case STATEMENT_SELECT: System.out.println("select will be execute"); break;
        }
    }

    private static PrepareResult prepare_statement(String command, Statement statement){
        if(command.startsWith("insert")){
            statement.setType(STATEMENT_INSERT);
            statement.setCommand(command);
            return PrepareResult.PREPARE_SUCCESS;
        }
        if(command.startsWith("select")){
            statement.setType(STATEMENT_SELECT);
            statement.setCommand(command);
            return PrepareResult.PREPARE_SUCCESS;
        }
        return PrepareResult.PREPARE_UNRECOGNIZED_STATEMENT;
    }

    private static MetaCommandResult do_meta_command(String command){
        if("exit".equalsIgnoreCase(command)){
            System.out.println("bye");
            System.exit(0);
            return null;
        }
        else if(command.contains("insert") || command.contains("select")){
            return META_COMMAND_SUCCESS;
        }
        else {
            return META_COMMAND_UNRECOGNIZED_COMMAND;
        }
    }

    public static String readInput(InputStreamReader is) throws IOException {
        BufferedReader br = new BufferedReader(is);
        String input = br.readLine();
        return input;
    }

    private static void printPromt(){
        System.out.print("db > ");
    }
}
