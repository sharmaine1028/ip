package commands;

import exception.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import tasks.*;
import main.*;

/**
 * DeadlineCommand which handles creation and message of DeadlineTask
 */
public class DeadlineTaskCommand extends TaskCommand {

  private LocalDate by;

  /**
   * Constructor for DeadlineCommand which includes description of task and when
   * the task needed to be completed by
   * 
   * @param description Description of task
   * @param by          When the task is required by
   */
  public DeadlineTaskCommand(String description) throws DukeException {
    super(description);
    String[] eventlst = description.split("/by ", 2);
    if (eventlst.length < 2 || eventlst[1].equals("")) {

      throw new DukeException("Alamak! Fill in when the deadline is by...");
    }
    this.description = eventlst[0];
    try {
      LocalDate d1 = LocalDate.parse(eventlst[1]);
      this.by = d1;
    } catch (DateTimeParseException e) {
      throw new DukeException("Please fill in the date in this format yyyy-mm-dd");
    }
  }

  /**
   * Creates a new DeadlineTask object to tasklist and prints message to user
   */
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    DeadlineTask task = new DeadlineTask(this.description, this.by);
    tasks.add(task);
    storage.save(tasks);
    super.printMessage(tasks, task);
  }
}