package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GitProfileCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Github;

/**
 * Parses input arguments and creates a new GitProfileCommand
 */
public class GitProfileCommandParser implements Parser<GitProfileCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GitProfileCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GITHUB);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GitProfileCommand.MESSAGE_USAGE), pe);
        }

        String gitHubUsername = argMultimap.getValue(PREFIX_GITHUB).orElse("");
        return new GitProfileCommand(index, new Github(gitHubUsername));
    }
}
