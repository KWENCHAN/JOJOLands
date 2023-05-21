package JojoLand;

import java.util.*;
import java.util.Map;

class Customer {
    private String name;
    private int age;
    private String gender;
    private String order;

    public Customer(String name, int age, String gender, String order) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getOrder() {
        return order;
    }
}

class Restaurant {
    private String name;
    private List<Customer> waitingList;
    private List<Customer> orderProcessingList;
    private Map<String, Double> menu;

    public Restaurant(String name, Map<String, Double> menu) {
        this.name = name;
        this.waitingList = new ArrayList<>();
        this.orderProcessingList = new ArrayList<>();
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getMenu() {
        return menu;
    }

    public List<Customer> getWaitingList() {
        return waitingList;
    }

    public List<Customer> getOrderProcessingList() {
        return orderProcessingList;
    }

    public void addToWaitingList(Customer customer) {
        waitingList.add(customer);
    }

    public void addToOrderProcessingList(Customer customer) {
        orderProcessingList.add(customer);
    }

    public void processOrders() {
        switch (name) {
            case "Jade Garden":
                processOrdersJadeGarden();
                break;
            case "Cafe Deux Magots":
                processOrdersCafeDeuxMagots();
                break;
            case "Trattoria Trussardi":
                processOrdersTrattoriaTrussardi();
                break;
            case "Libeccio":
                processOrdersLibeccio();
                break;
            case "Savage Garden":
                processOrdersSavageGarden();
                break;
            default:
                System.out.println("Invalid restaurant name!");
                break;
        }
    }

    private void processOrdersJadeGarden() {
        int start = 0;
        int end = waitingList.size() - 1;

        while (start < end) {
            Customer firstCustomer = waitingList.get(start);
            Customer lastCustomer = waitingList.get(end);

            orderProcessingList.add(firstCustomer);
            orderProcessingList.add(lastCustomer);

            start++;
            end--;
        }

        if (start == end) {
            orderProcessingList.add(waitingList.get(start));
        }
    }

    private void processOrdersCafeDeuxMagots() {
        List<Customer> sortedList = new ArrayList<>(waitingList);
        sortedList.sort(Comparator.comparingInt(Customer::getAge));

        int start = 0;
        int end = sortedList.size() - 1;

        while (start < end) {
            Customer oldestCustomer = sortedList.get(start);
            Customer youngestCustomer = sortedList.get(end);

            orderProcessingList.add(oldestCustomer);
            orderProcessingList.add(youngestCustomer);

            start++;
            end--;
        }

        if (start == end) {
            orderProcessingList.add(sortedList.get(start));
        }
    }

    private void processOrdersTrattoriaTrussardi() {
        List<Customer> males = new ArrayList<>();
        List<Customer> females = new ArrayList<>();
        List<Customer> unspecified = new ArrayList<>();

        for (Customer customer : waitingList) {
            String gender = customer.getGender();
            if (gender.equals("Male")) {
                males.add(customer);
            } else if (gender.equals("Female")) {
                females.add(customer);
            } else {
                unspecified.add(customer);
            }
        }

        males.sort(Comparator.comparingInt(Customer::getAge));
        females.sort(Comparator.comparingInt(Customer::getAge).reversed());

        while (!males.isEmpty() && !females.isEmpty()) {
            orderProcessingList.add(males.remove(0));
            orderProcessingList.add(females.remove(0));
        }

        orderProcessingList.addAll(males);
        orderProcessingList.addAll(females);
        orderProcessingList.addAll(unspecified);
    }

    private void processOrdersLibeccio() {
        int dayNumber = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int currentIndex = dayNumber - 1;

        while (!waitingList.isEmpty()) {
            currentIndex %= waitingList.size();
            Customer customer = waitingList.remove(currentIndex);
            orderProcessingList.add(customer);
        }
    }

    private void processOrdersSavageGarden() {
        int dayNumber = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int currentIndex = dayNumber - 1;
        int reverseIndex = waitingList.size() - 1;

        while (!waitingList.isEmpty()) {
            if (currentIndex < waitingList.size()) {
                Customer customer = waitingList.remove(currentIndex);
                orderProcessingList.add(customer);
            } else {
                Customer customer = waitingList.remove(reverseIndex);
                orderProcessingList.add(customer);
                reverseIndex--;
            }
            currentIndex += 1;
        }
    }

    public void printWaitingList() {
        System.out.println("Waiting List for " + name + ":");
        for (Customer customer : waitingList) {
            System.out.println(customer.getName() + " - Age: " + customer.getAge() + " - Gender: " + customer.getGender() + " - Order: " + customer.getOrder());
        }
    }

    public void printOrderProcessingList() {
        System.out.println("Order Processing List for " + name + ":");
        for (Customer customer : orderProcessingList) {
            System.out.println(customer.getName() + " - Age: " + customer.getAge() + " - Gender: " + customer.getGender() + " - Order: " + customer.getOrder());
        }
    }
}