
public class Invoice {
    private String fname;
    private String lname;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    public Invoice() {
    }

    public Invoice(String fname, String lname, String streetAddress, String city, String state, String zipCode) {
        this.fname = fname;
        this.lname = lname;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {

        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void printNumberOfRequest(String[]arr, int length) {

        System.out.println("\nRaven's Invoice\nTo:\n" + arr[0] + " " + arr[1] + "\n" +
                arr[2] + "\n" + arr[3] + ", " + arr[4] + " " + arr[5] + "\n\nItems:\n");

        // calculate total price
        // number of questions user asked
        int qty = (length - 1)/2;
        // price for questions
        double price = (qty * 0.35);
        // add 100 for a session
        double tprice = (100 + price);

        System.out.printf("%10s %20s %10s %10s", "Quantity", "   Description", "Price", "Total");
        System.out.printf("\n%10s %-20s %10s %10s", "1    ", "  Session", "100.00", "100.00");
        System.out.printf("\n%10s %-20s %10s %10s", qty + "    ", "  Questions", 0.35, String.format("%(,.2f", price));
        System.out.printf("\n\nTotal Due: %s\n", String.format("$%(,.2f", tprice));
        System.out.printf("\nThank you for doing business with Raven.");
    }
}
