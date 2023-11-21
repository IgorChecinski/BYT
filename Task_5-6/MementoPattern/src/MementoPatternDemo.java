public class MementoPatternDemo {
    public static void main(String[] args) {

        Originator originator = new Originator(); // Enables to set state, save it to memento and get the state from memento
        Caretaker caretaker = new Caretaker(); // Enables saving mementos and list them

        originator.setState("State #1"); // set but not saved
        originator.setState("State #2"); // set state 2#
        caretaker.add(originator.saveStateToMemento()); // saved state #2

        originator.setState("State #3");
        caretaker.add(originator.saveStateToMemento());// saved state 3#

        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState());

        originator.getStateFromMemento(caretaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(caretaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }
}
