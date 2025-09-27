// Product Interface
interface GameCharacter {
    void attack();
    void defend();
}

// Concrete Products
class Warrior implements GameCharacter {
    @Override
    public void attack() {
        System.out.println("Warrior attacks with a sword!");
    }

    @Override
    public void defend() {
        System.out.println("Warrior defends with a shield!");
    }
}

class Mage implements GameCharacter {
    @Override
    public void attack() {
        System.out.println("Mage casts a fireball!");
    }

    @Override
    public void defend() {
        System.out.println("Mage creates a magic barrier!");
    }
}

class Archer implements GameCharacter {
    @Override
    public void attack() {
        System.out.println("Archer shoots an arrow!");
    }

    @Override
    public void defend() {
        System.out.println("Archer dodges quickly!");
    }
}

// Factory
class GameCharacterFactory {
    public GameCharacter createCharacter(String type) {
        return switch (type) {
            case "Warrior" -> new Warrior();
            case "Mage" -> new Mage();
            case "Archer" -> new Archer();
            default -> throw new IllegalArgumentException("Invalid character type.");
        };
    }
}

// Usage
public class GameCharacterDemo {
    public static void main(String[] args) {
        GameCharacterFactory factory = new GameCharacterFactory();
        GameCharacter warrior = factory.createCharacter("Warrior");
        GameCharacter mage = factory.createCharacter("Mage");
        GameCharacter archer = factory.createCharacter("Archer");

        warrior.attack();
        mage.defend();
        archer.attack();
    }
}
