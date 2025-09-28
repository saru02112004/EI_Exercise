import java.util.ArrayList;
import java.util.List;

// Observer Interface
interface Investor {
    void update(String stockSymbol, double price);
}

// Subject (Stock Market)
class StockMarket {
    private String stockSymbol;
    private double price;
    private List<Investor> investors = new ArrayList<>();

    public StockMarket(String stockSymbol, double price) {
        this.stockSymbol = stockSymbol;
        this.price = price;
    }

    public void addInvestor(Investor investor) {
        investors.add(investor);
    }

    public void setPrice(double price) {
        this.price = price;
        notifyInvestors();
    }

    private void notifyInvestors() {
        for (Investor investor : investors) {
            investor.update(stockSymbol, price);
        }
    }
}

// Concrete Observers
class EmailInvestor implements Investor {
    private String email;

    public EmailInvestor(String email) {
        this.email = email;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.printf("Email to %s: %s price is now $%.2f%n", email, stockSymbol, price);
    }
}

class SMSInvestor implements Investor {
    private String phoneNumber;

    public SMSInvestor(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.printf("SMS to %s: %s price is now $%.2f%n", phoneNumber, stockSymbol, price);
    }
}

// Usage
public class StockMarketDemo {
    public static void main(String[] args) {
        StockMarket appleStock = new StockMarket("AAPL", 150.00);
        Investor emailInvestor = new EmailInvestor("investor@example.com");
        Investor smsInvestor = new SMSInvestor("+1234567890");

        appleStock.addInvestor(emailInvestor);
        appleStock.addInvestor(smsInvestor);

        appleStock.setPrice(152.50);
        appleStock.setPrice(155.75);
    }
}
