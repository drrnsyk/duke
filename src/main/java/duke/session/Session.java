package duke.session;

import duke.repository.Repository;
import duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * Encapsulate the properties, constructors and methods required to run the program.
 * Contains the program logic.
 * Contains the parser code to parse user inputs.
 */
public class Session {
    private final Repository sessionRepo;
    private TaskList sessionTaskList;

    public Session (Repository repo) {
        this.sessionRepo = repo;
    }

    /**
     * Encapsulates the program logic and parsing of user input.
     * @throws IOException If an I/O error occurred.
     */
    public void start() throws IOException {
        sessionRepo.loadDirectory();
        sessionRepo.loadFile();
        sessionTaskList = new TaskList();
        sessionTaskList.existingTaskList(sessionRepo.readFile());

        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("System booting up...\n" + logo);

        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Hello! I'm duke.Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println();

        String inputLine;
        String commandLine;
        Scanner input = new Scanner(System.in);

        while (true) {
            inputLine = input.nextLine();
            String[] lineArray = inputLine.split(" ");
            commandLine = lineArray[0];

            if (inputLine.equalsIgnoreCase("bye")) {
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t Bye. Hope to see you again soon!");
                System.out.println("\t-----------------------------------------------------------------");
                break;
            } else if (commandLine.equalsIgnoreCase("delete")) {
                sessionTaskList.deleteTask(lineArray);
                sessionTaskList.saveTaskList(sessionRepo.getFileDirectory());
            } else if (commandLine.equalsIgnoreCase("mark")) {
                sessionTaskList.markTask(lineArray);
                sessionTaskList.saveTaskList(sessionRepo.getFileDirectory());
            } else if (commandLine.equalsIgnoreCase("unmark")) {
                sessionTaskList.unmarkTask(lineArray);
                sessionTaskList.saveTaskList(sessionRepo.getFileDirectory());
            } else if (commandLine.equalsIgnoreCase("list")) {
                sessionTaskList.listTask();
            } else if (commandLine.equalsIgnoreCase("todo")) {
                if (lineArray.length < 2) {
                    System.out.println("\t-----------------------------------------------------------------");
                    System.out.println("\t " + "The description of a todo cannot be empty.");
                    System.out.println("\t-----------------------------------------------------------------");
                    continue;
                }
                sessionTaskList.addToDo(lineArray);
                sessionTaskList.saveTaskList(sessionRepo.getFileDirectory());
            } else if (commandLine.equalsIgnoreCase("deadline")) {
                if (lineArray.length < 2) {
                    System.out.println("\t-----------------------------------------------------------------");
                    System.out.println("\t " + "The description of a deadline cannot be empty.");
                    System.out.println("\t-----------------------------------------------------------------"); 
                    continue;
                }
                sessionTaskList.addDeadLine(lineArray);
                sessionTaskList.saveTaskList(sessionRepo.getFileDirectory());
            } else if (commandLine.equalsIgnoreCase("event")) {
                if (lineArray.length < 2) {
                    System.out.println("\t-----------------------------------------------------------------");
                    System.out.println("\t " + "The description of a event cannot be empty.");
                    System.out.println("\t-----------------------------------------------------------------"); 
                    continue;
                }
                sessionTaskList.addEvent(lineArray);
                sessionTaskList.saveTaskList(sessionRepo.getFileDirectory());
            } else {
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\t " + "Pleas enter a valid command");
                System.out.println("\t-----------------------------------------------------------------");
            }
        }
        input.close();
    }
}
