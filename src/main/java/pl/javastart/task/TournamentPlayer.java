package pl.javastart.task;

public class TournamentPlayer {
    private final String firstName;
    private final String lastName;
    private final Integer result;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getResult() {
        return result;
    }

    TournamentPlayer(String line) {
        String[] strings = line.split(" ");
        firstName = strings[0];
        lastName = strings[1];
        result = Integer.valueOf(strings[2]);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + result;
    }
}