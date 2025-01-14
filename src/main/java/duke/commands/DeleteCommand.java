package duke.commands;

import duke.exception.DukeException;
import duke.exception.TaskNotFoundDukeException;
import duke.main.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * DeleteCommand used to delete tasks
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Creates a DeleteCommand with index of task to be deleted
     *
     * @param description String representation of task number to be marked
     * @throws DukeException if user did not type in a correct task number
     */
    public DeleteCommand(String description) throws DukeException {
        try {

            assert description.split(" ")[0].equals("delete") : "Keyword should be delete for DeleteCommand";
            String taskNumber = description.split(" ")[1];
            this.index = Integer.parseInt(taskNumber);

        } catch (Exception e) {
            throw new DukeException("Invalid tasks");
        }
    }

    /**
     * Marks command and prints out message to users depending on whether the
     * command was successful
     *
     * @return @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new TaskNotFoundDukeException();
        }

        Task task = tasks.remove(index - 1);
        storage.save(tasks);
        return getMessage(tasks, task);

    }

    public String getMessage(TaskList tasks, Task task) {
        String str = "Your grace. I've removed this task:\n";
        str += task.toString() + '\n';
        str += "Now you have " + tasks.size() + " task(s) in the list";
        return str;
    }
}
