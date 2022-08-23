package commands;

import exception.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import tasks.*;

/**
 * Event command has an at field for the timing of the event
 */
public class EventTaskCommand extends TaskCommand {

  protected LocalDate at;

  /**
   * Constructor for EventCommand with at field
   * 
   * @param description Description of event
   * @param at          When the event is at
   */
  public EventTaskCommand(String description) throws DukeException {
    super(description);
    String[] eventlst = description.split("/at ", 2);
    if (eventlst.length < 2) {
      throw new DukeException("Alamak! Fill in when the event is at...");
    }
    this.description = eventlst[0];
    try {
      LocalDate d1 = LocalDate.parse(eventlst[1]);
      this.at = d1;
    } catch (DateTimeParseException e) {
      throw new DukeException("Please fill in the date in this format yyyy-mm-dd");
    }
  }

  /**
   * Creates new Event and add to tasklist as well as print message to user
   */
  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {
    Event task = new Event(this.description, this.at);
    tasklist.add(task);
    super.printMessage(tasklist, task);
  }
}
