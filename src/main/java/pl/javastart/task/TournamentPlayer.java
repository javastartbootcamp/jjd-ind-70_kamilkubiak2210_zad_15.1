package pl.javastart.task;

public class TournamentPlayer {
    private String firstName;
    private String lastName;
    private Integer result;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getResult() {
        return result;
    }

    public TournamentPlayer(String firstName, String lastName, Integer result) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.result = result;
    }

    public TournamentPlayer() {
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + result;
    }

    public TournamentPlayer createPerson(String line) {
        String[] strings = line.split(" ");
        String firstName = strings[0];
        String lastName = strings[1];
        Integer result = Integer.valueOf(strings[2]);
        return new TournamentPlayer(firstName, lastName, result);
    }
}


