package com.sonata36.petshop;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/** 支持顾客指定动物的宠物店；未指定时出售最先入库的动物。 */
public final class MyAnimalShop implements AnimalShop {
    private double balance;
    private final List<Animal> animals;
    private final List<Customer> customers;
    private final Map<LocalDate, Double> dailyProfits;
    private final Clock clock;
    private boolean open;

    public MyAnimalShop(double balance, List<Animal> initialAnimals) {
        this(balance, initialAnimals, new ArrayList<>(), Clock.systemDefaultZone());
    }

    public MyAnimalShop(double balance, List<Animal> initialAnimals,
            List<Customer> initialCustomers, Clock clock) {
        if (!Double.isFinite(balance) || balance < 0) {
            throw new IllegalArgumentException("初始余额必须是非负的有限数字");
        }
        Objects.requireNonNull(initialAnimals, "动物列表不能为空");
        Objects.requireNonNull(initialCustomers, "顾客列表不能为空");
        this.clock = Objects.requireNonNull(clock, "时钟不能为空");
        this.animals = new ArrayList<>(initialAnimals);
        this.customers = new ArrayList<>();
        for (Customer customer : initialCustomers) {
            this.customers.add(Objects.requireNonNull(customer, "顾客不能为 null").snapshot());
        }
        for (Animal animal : this.animals) {
            Objects.requireNonNull(animal, "动物不能为 null");
        }
        this.dailyProfits = new HashMap<>();
        this.balance = balance;
        this.open = true;
    }

    @Override
    public void buyAnimal(Animal animal, double costPrice) {
        ensureOpen();
        Objects.requireNonNull(animal, "动物不能为 null");
        if (!Double.isFinite(costPrice) || costPrice < 0
                || BigDecimal.valueOf(costPrice).scale() > 2 || costPrice >= animal.getPrice()) {
            throw new IllegalArgumentException(String.format(Locale.ROOT,
                    "成本价必须是低于售价 %.2f 元、最多两位小数的非负有限数字",
                    animal.getPrice()));
        }
        if (balance < costPrice) {
            throw new InsufficientBalanceException(String.format(Locale.ROOT,
                    "余额不足：现有 %.2f 元，买入 %s 需要 %.2f 元",
                    balance, animal.getName(), costPrice));
        }
        balance -= costPrice;
        animals.add(animal);
        dailyProfits.merge(LocalDate.now(clock), -costPrice, Double::sum);
    }

    @Override
    public Animal serveCustomer(Customer customer) {
        return serveCustomer(customer, 0);
    }

    @Override
    public Animal serveCustomer(Customer customer, int animalIndex) {
        ensureOpen();
        Objects.requireNonNull(customer, "顾客不能为 null");
        LocalDate today = LocalDate.now(clock);
        if (animals.isEmpty()) {
            recordVisit(customer, today);
            throw new AnimalNotFoundException("店内没有动物可买");
        }
        if (animalIndex < 0 || animalIndex >= animals.size()) {
            throw new IllegalArgumentException("所选动物不在库存中");
        }
        recordVisit(customer, today);
        Animal soldAnimal = animals.remove(animalIndex);
        balance += soldAnimal.getPrice();
        dailyProfits.merge(today, soldAnimal.getPrice(), Double::sum);
        System.out.println("售出动物：" + soldAnimal);
        return soldAnimal;
    }

    @Override
    public void close() {
        ensureOpen();
        open = false;
        LocalDate today = LocalDate.now(clock);
        System.out.println("歇业日期：" + today);
        System.out.println("今日到店顾客：");
        boolean hasVisitors = false;
        for (Customer customer : customers) {
            if (today.equals(customer.getLastVisitDate())) {
                System.out.println(customer);
                hasVisitors = true;
            }
        }
        if (!hasVisitors) {
            System.out.println("无");
        }
        System.out.printf(Locale.ROOT, "今日利润：%.2f 元%n", getProfit(today));
    }

    public double getBalance() {
        return balance;
    }

    public List<Animal> getAnimals() {
        return List.copyOf(animals);
    }

    public List<Customer> getCustomers() {
        List<Customer> snapshots = new ArrayList<>();
        for (Customer customer : customers) {
            snapshots.add(customer.snapshot());
        }
        return List.copyOf(snapshots);
    }

    public double getProfit(LocalDate date) {
        return dailyProfits.getOrDefault(Objects.requireNonNull(date), 0.0);
    }

    public boolean isOpen() {
        return open;
    }

    private void ensureOpen() {
        if (!open) {
            throw new IllegalStateException("宠物店已经歇业");
        }
    }

    private void recordVisit(Customer customer, LocalDate date) {
        customer.recordVisit(date);
        customers.add(customer.snapshot());
    }
}
