package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.team.Skill;
import seedu.address.model.team.SkillSet;
import seedu.address.model.team.Team;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String username} into an {@code GithubUsername}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code username} is invalid.
     */
    public static GithubUsername parseGithubUsername(String username) throws ParseException {
        requireNonNull(username);
        String trimmedUsername = username.trim();
        if (!GithubUsername.isValidUsername(trimmedUsername)) {
            throw new ParseException(GithubUsername.MESSAGE_CONSTRAINTS);
        }
        return new GithubUsername(trimmedUsername);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String team} into a {@code Team}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code team} is invalid.
     */
    public static Team parseTeam(String team) throws ParseException {
        requireNonNull(team);
        String trimmed = team.trim();
        if (!Team.isValidTeamName(trimmed)) {
            throw new ParseException(Team.MESSAGE_CONSTRAINTS);
        }
        return new Team(trimmed);
    }

    /**
     * Parses a {@code String skill} into a {@code skill}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code skill} is invalid.
     */
    public static Skill parseSkill(String skill) throws ParseException {
        requireNonNull(skill);
        String[] trimmedSkill = skill.trim().split("_");
        if (trimmedSkill.length != 2) {
            throw new ParseException(Skill.SKILL_INPUT_CONSTRAINTS);
        }
        String skillName = trimmedSkill[0];
        if (!Skill.isValidSkillProficiencyInteger(trimmedSkill[1])) {
            throw new ParseException(Skill.PROFICIENCY_CONSTRAINTS_INTEGER);
        }
        int skillProficiency = Integer.parseInt(trimmedSkill[1]);
        if (!Skill.isValidSkillName(skillName)) {
            throw new ParseException(Skill.NAME_CONSTRAINTS);
        }
        if (!Skill.isValidSkillProficiencyRange(skillProficiency)) {
            throw new ParseException(Skill.PROFICIENCY_CONSTRAINTS_RANGE);
        }
        return new Skill(skillName, skillProficiency);
    }

    /**
     * Parses {@code Collection<String> teams} into a {@code Set<Team>}.
     */
    public static Set<Team> parseTeams(Collection<String> teams) throws ParseException {
        requireNonNull(teams);
        final Set<Team> teamSet = new HashSet<>();
        for (String teamName : teams) {
            teamSet.add(parseTeam(teamName));
        }
        return teamSet;
    }

    /**
     * Parses {@code Collection<String> skill} into a {@code Set<Skill>}.
     */
    public static SkillSet parseSkillSet(Collection<String> skills) throws ParseException {
        requireNonNull(skills);
        final SkillSet skillSet = new SkillSet();
        for (String skill : skills) {
            skillSet.add(parseSkill(skill));
        }
        return skillSet;
    }
}
