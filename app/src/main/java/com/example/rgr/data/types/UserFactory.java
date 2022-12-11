package com.example.rgr.data.types;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class UserFactory {
    private final static ArrayList<UserType> builders = new ArrayList<>();

    static {
        Set<Class<? extends UserType>> buildersClasses =  new HashSet<>();
        buildersClasses.add(UserInteger.class);
        buildersClasses.add(Point.class);

        buildersClasses.forEach(bc -> {
            try {
                UserType objectBuilder = bc.getDeclaredConstructor().newInstance();
                builders.add(objectBuilder);
            } catch (Exception ignored) {
                throw new RuntimeException("Something went wrong...");
            }
        });
        System.out.printf("%d builders were added\n", builders.size());
    }

    public static Set<String> getTypeNameList(){
        return builders.stream().map(UserType::typeName).collect(Collectors.toSet());
    }

    public static UserType getBuilderByName(String name){
        if (name == null) throw new NullPointerException();
        for (UserType b : builders) {
            if (name.equals(b.typeName())) return b;
        }
        throw new IllegalArgumentException();
    }
}
