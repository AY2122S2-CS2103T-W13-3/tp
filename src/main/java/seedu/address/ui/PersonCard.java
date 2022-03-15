package seedu.address.ui;

import java.util.Comparator;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.MainApp;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Hyperlink githubUsername;
    @FXML
    private Label email;
    @FXML
    private FlowPane teams;
    @FXML
    private FlowPane skillSet;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);

        GithubUsername username = person.getGithubUsername();
        githubUsername.setText(username.getGithubHandle());
        HostServices hs = MainApp.getHS();
        githubUsername.setOnAction(e -> {
            hs.showDocument(username.getGithubProfileLink());
        });

        person.getTeams().stream()
            .sorted(Comparator.comparing(team -> team.teamName))
            .forEach(team -> teams.getChildren().add(new Label(team.teamName)));
        person.getSkillSet().stream()
            .sorted(Comparator.comparing(skill -> skill.skillName))
            .forEach(skill -> skillSet.getChildren().add(new Label(skill.skillName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
            && person.equals(card.person);
    }
}
