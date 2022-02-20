package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Github;
import seedu.address.model.person.Person;

/**
 * Links a person to a GitHub profile.
 */
public class GitProfileCommand extends Command {

    public static final String COMMAND_WORD = "github";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Links a person to a GitHub profile. "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_GITHUB + " GITHUB USERNAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_GITHUB + " User-one";

    public static final String MESSAGE_ADD_GITHUB_SUCCESS = "The GitHub profile of the person was updated: %1$s";

    private final Index index;

    private final Github username;

    /**
     *
     * @param index of the person in the filtered person list to link
     * @param username GitHub username of the person
     */
    public GitProfileCommand(Index index, Github username) {
        this.index = index;
        this.username = username;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), username, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_ADD_GITHUB_SUCCESS, personToEdit);
    }
}
