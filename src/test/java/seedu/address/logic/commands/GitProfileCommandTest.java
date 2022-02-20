package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Github;
import seedu.address.model.person.Person;

/**
 * Contains integration tests for GitProfileCommand#execute()
 */
public class GitProfileCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_updateGithubProfileFilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Github newUsername = new Github("testuser");

        Person editedPerson = new Person(firstPerson.getName(), firstPerson.getPhone(), firstPerson.getEmail(),
                firstPerson.getAddress(), newUsername, firstPerson.getTags());

        GitProfileCommand command = new GitProfileCommand(INDEX_FIRST_PERSON, newUsername);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        String expectedMessage = String.format(GitProfileCommand.MESSAGE_ADD_GITHUB_SUCCESS, editedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidIndexFilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        GitProfileCommand command = new GitProfileCommand(outOfBoundIndex, new Github("test_user"));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
