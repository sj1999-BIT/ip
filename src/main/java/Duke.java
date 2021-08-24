import java.util.*;

public class Duke {

    private String line = "__________________________________\n";
    // global varibale to keep track of all the task
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Task inner class.
     * Able to keep track of task type, done and date.
     */
    private class Task {
        private String done;
        private String[] dataOfAction;
        private String finalAction = "";

        /**
         * constructor for Task.
         * @param action
         */
        public Task(String action) {
            // separate using the / as its the point of the date
            dataOfAction = action.split("/")[0].split(" ");
            String type = dataOfAction[0];
            for (int i = 1; i < dataOfAction.length; i++) {
                finalAction += dataOfAction[i] + " ";
            }
            //decide the correct header
            if (type.equals("Todo")) {
                done = "[T][] ";
            } else {
                String day =  setDate(action.split("/")[1].split(" "));
                if (type.equals("Deadline")) {
                    done = "[D][] ";
                } else if  (type.equals("Events")) {
                    done = "[E][] ";
                } else {
                    System.out.println("Error type");
                }
                finalAction += day;
            }
        }

        /**
         * Create a tail made up of the deadline.
         * @param data
         * @return String
         */
        private String setDate(String[] data) {
            String output = " (" + data[0] + ": ";
            for (int i = 1 ;i < data.length; i++) {
                    output += data[i];
            }
            return output + " )";
        }

        /**
         * modify the task message if done.
         */
        public void done() {
            System.out.println("Nice! I've marked this task as done: ");
            done = done.substring(0, 3) + "[X] ";
            System.out.println("    " + this);
        }

        /**
         * Override the original toString method to generate the correct value.
         * @return String
         */
        public String toString() {
            return done + finalAction;
        }
    }

    /**
     * Generate the initiate message.
     */
    private void greet() {
        System.out.println(line);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * generate the end message.
     */
    private void bye() {
        String line = "__________________________________";
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    /**
     * The original method to simply generate an echo message.
     * Now only meant to decide if the user has decided to end
     * the program.
     * @return boolean
     */
    private boolean echo() {
        Scanner user = new Scanner(System.in);
        String input = user.nextLine();
        if (input.equals("bye")) {
            bye();
            return false;
        } else {
            return choiceOfAction(input);
        }
    }

    /**
     * To decide base on the input what is the next action.
     * The decision on whether to show list, set existing task to
     * done, or to create and record new task.
     * @param input
     * @return boolean
     */
    private boolean choiceOfAction(String input) {
        if (input.equals("list")) {
            showList();
            return true;
        } else {
            String[] newInput = input.split(" ");
            if (newInput[0].equals("done")) {
                int index = Integer.parseInt(newInput[1]);
                if (index <= taskList.size() && index > 0) {
                    taskList.get(index - 1).done();
                } else {
                    System.out.println("invalid index");
                }
            } else {
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println(line);
                System.out.println("Got it. I've added this task: ");
                System.out.println("    " + newTask);
                System.out.format("Now you have %d tasks in the list.\n", taskList.size());
            }
            System.out.println(line);
            return true;
        }
    }

    /**
     * create a message to show all the tasks and their states.
     */
    private void showList() {
        int count = 0;

        for (Task action : taskList ) {
            count++;
            System.out.format("%d. " + action + "\n", count);
        }
        System.out.println(line);
    }


    public static void main(String[] args) {
        Duke bot = new Duke();

        bot.greet();
        boolean end = true;
        while (end) {
            end = bot.echo();
        }
    }
}
