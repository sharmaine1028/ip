package duke.commands;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

/**
 * FindCommand finds all matching tasks with given keyword
 */
public class FindCommand extends Command{
    /** Keyword to be searched for in TaskList*/
    private final String keyword;

    /**
     * Constructor for FindCommand
     *
     * @param description Description of Command
     * @throws DukeException If user did not type in keyword to search for
     */
    public FindCommand(String description) throws DukeException {
        String[] lst = description.split(" ", 2);
        if (lst.length < 2) {
            throw new DukeException("Keyword missing!");
        }
        this.keyword = lst[1];
    }

    /**
     * Finds matching tasks with keywords and prints out message based on whether
     * there are matching tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> filtered = tasks.find(this.keyword);
        if (filtered.size() == 0) {
            System.out.println("No matching tasks found");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < filtered.size(); i++) {
                System.out.println((i + 1) + "." + filtered.get(i).toString());
            }
        }
    }
}
