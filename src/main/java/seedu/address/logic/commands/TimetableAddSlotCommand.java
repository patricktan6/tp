package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Routine;
import seedu.address.model.person.Slot;

public class TimetableAddSlotCommand extends Command {
    public static final String COMMAND_WORD = "timetable_add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Slot to the timetable in fitNUS. "
            + "Parameters: "
            + PREFIX_ROUTINE + "ROUTINE_NAME "
            + PREFIX_DAY + "DAY"
            + PREFIX_TIME + "TIME"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROUTINE + "Leg Day Session "
            + PREFIX_DAY + "Monday "
            + PREFIX_TIME + "1600-1800";

    public static final String MESSAGE_SUCCESS = "Slot added to Timetable: %1$s";
    public static final String MESSAGE_DUPLICATE_SLOT = "This slot already exists in your timetable";
    public static final String MESSAGE_OVERLAP_SLOT = "This slot overlaps with another slot in your timetable";

    private final Slot slotToAdd;

    /**
     * Creates an RoutineAddCommand to add the specified {@code Routine}
     */
    public TimetableAddSlotCommand(Slot slot) {
        requireNonNull(slot);
        slotToAdd = slot;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasSlot(slotToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SLOT);
        } else if (model.hasOverlappingSlot(slotToAdd)) {
            throw new CommandException(MESSAGE_OVERLAP_SLOT);
        }

        model.addSlotToTimetable(slotToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, slotToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimetableAddSlotCommand // instanceof handles nulls
                && slotToAdd.equals(((TimetableAddSlotCommand) other).slotToAdd));
    }
}
