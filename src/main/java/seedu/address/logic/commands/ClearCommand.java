package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears fitNUS.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "FitNUS has been cleared. You can start afresh now!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFitNus(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
