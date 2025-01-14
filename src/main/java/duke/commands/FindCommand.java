package duke.commands;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * FindCommand finds all matching tasks with given keyword
 */
public class FindCommand extends Command {

    /**
     * Keyword to be searched for in TaskList
     */
    private final String keyword;

    /**
     * Constructor for FindCommand
     *
     * @param description Description of Command
     * @throws DukeException If user did not type in keyword to search for
     */
    public FindCommand(String description) throws DukeException {
        String[] lst = description.split(" ", 2);
        assert lst[0].equals("find") : "Keyword should be find for FindCommand";

        if (lst.length < 2) { // Guard clause
            throw new DukeException("Keyword missing!");
        }

        this.keyword = lst[1];
    }

    /**
     * Finds matching tasks with keywords and prints out message based on whether
     * there are matching tasks
     *
     * @return @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        ArrayList<Task> filtered = tasks.find(this.keyword);
        return getMessage(filtered);

    }

    public String getMessage(ArrayList<Task> filtered) {
        if (filtered.size() == 0) {
            return "Your excellency, no matching tasks found";
        } else {
            StringBuilder sb = new StringBuilder("Your excellency, here are the matching tasks: \n");
            for (int i = 0; i < filtered.size(); i++) {
                sb.append(i + 1).append(". ").append(filtered.get(i).toString()).append("\n");
            }
            return sb.toString();
        }
    }
}
