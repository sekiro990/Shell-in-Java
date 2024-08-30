import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> commandHistory = new ArrayList<>();
        String command;
        System.out.println("Simple Java Shell. Type 'exit' to quit.");
        while(true){
            try{
                System.out.print("jshell >> ");
                command = reader.readLine();

                if ("exit".equalsIgnoreCase(command)) {
                    System.out.println("Exiting shell...");
                    break;
                }

                executeCommand(command);
                commandHistory.add(command);

                if (command.equals("history")) {
                    for (int i = 0; i < commandHistory.size(); i++) {
                        System.out.println((i + 1) + ": " + commandHistory.get(i));
                    }
                }

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }

    private static void executeCommand(String command) {
        try{

            Process process = Runtime.getRuntime().exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }



            input.close();

            process.waitFor();

        }catch (Exception e){
            System.out.println("Command execution failed: " + e.getMessage());
        }
    }
}