package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Remark {


    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param content Any remark about a person.
     */
    public Remark(String content) {
        requireNonNull(content);
        this.value = content;
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof seedu.address.model.person.Remark // instanceof handles nulls
            && value.equals(((seedu.address.model.person.Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
