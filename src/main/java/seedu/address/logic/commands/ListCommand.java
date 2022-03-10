package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.address.model.Model;

/**
<<<<<<< HEAD
 * Lists all Clients in the address book to the user.
=======
 * Lists all clients in the address book to the user.
>>>>>>> branch-person-to-client
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all clients";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
