package com.epam.rd.autocode.assessment.basics.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Generator {
    public static void main(String[] args) {

    }
    private static void generateLicensePlates(Random r) {
        IntStream.range(1, 25)
                .mapToObj(i -> "" + (char) r.nextInt('A', 'Z' + 1))
                .map(p -> p + (char) r.nextInt('A', 'Z' + 1))
                .map(p -> p + ' ')
                .map(p -> p + r.nextInt(0, 9))
                .map(p -> p + r.nextInt(0, 9))
                .map(p -> p + '-')
                .map(p -> p + r.nextInt(0, 9))
                .map(p -> p + r.nextInt(0, 9))
                .map(p -> p + ' ')
                .map(p -> p + (char) r.nextInt('A', 'Z' + 1))
                .map(p -> p + (char) r.nextInt('A', 'Z' + 1))
                .forEach(System.out::println);
    }

    private static void generateClients(Random r) {
        IntStream.range(6, 25 + 6)
                .boxed()
                .map(p -> p + "," + "Client" + r.nextInt(1, 26) + '@')
                .map(p -> p + (char) r.nextInt('a', 'z' + 1))
                .map(p -> p + (char) r.nextInt('a', 'z' + 1))
                .map(p -> p + (char) r.nextInt('a', 'z' + 1))
                .map(p -> p + ".com")
                // pwd
                .map(p -> p + ',' + (char) r.nextInt('A', 'z' + 1))
                .map(p -> p + (char) r.nextInt('#', '&' + 1))
                .map(p -> p + r.nextInt(0, 9))
                .map(p -> p + r.nextInt(0, 9))
                .map(p -> p + (char) r.nextInt('#', '&' + 1))
                .map(p -> p + r.nextInt(0, 9))
                .map(p -> p + r.nextInt(0, 9))
                .map(p -> p + (char) r.nextInt('#', '&' + 1))
                .map(p -> p + (char) r.nextInt('A', 'z' + 1))
                .map(p -> p + (char) r.nextInt('A', 'z' + 1))
                // balance
                .map(p -> p + ',' + r.nextInt(500, 3000))
                .map(p -> p + ',' + genDriverLicenses(r))
                .forEach(System.out::println);
    }

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static void generateOrders(Random r) {
        List<Integer> e = new ArrayList<>(25);
        IntStream.range(1, 25)
                .boxed()
                .map(p -> p + "," + r.nextInt(1, 25))  // cid
                .map(p -> p + "," + r.nextInt(2, 6)) // eId
                .map(p -> p + "," + nextUniqueInt(r, e, 1, 25)) // vId
                .map(p -> {
                    Date sd = generateDate(r, "2022-09-01", "2022-12-31", sdf);
                    Date ed = generateDate(r, "2022-09-01", "2022-12-31", sdf);
                    return p + "," + sdf.format(minDate(sd, ed)) +
                            "," + sdf.format(maxDate(sd, ed));
                })
                .map(p -> p + "," + r.nextInt(50, 150))
                .forEach(System.out::println);
    }

    static Date minDate(Date d1, Date d2) {
        return d1.getTime() < d2.getTime() ? d1 : d2;
    }

    static Date maxDate(Date d1, Date d2) {
        return d1.getTime() >= d2.getTime() ? d1 : d2;
    }

    static int nextUniqueInt(Random r, List<Integer> values, int origin, int bound) {
        int v = r.nextInt(origin, bound);
        int c = 0;
        while (values.contains(v) && (c + 1) * 2 <= values.size()) {
            v = r.nextInt(0, 9);
            c++;
        }
        values.add(v);
        return v;
    }

    static String genDriverLicenses(Random r) {
        Map<Character, String> map = Map.of(
                'A', "A",
                'B', "B",
                'C', "B C",
                'D', "B C D",
                'E', "B C D E",
                'F', "A B",
                'G', "A B C",
                'H', "A B C D",
                'I', "A B C D E");

        return map.get((char) r.nextInt('A', 'I' + 1));
    }

    private static void generatePasswords(Random r) {
        IntStream.range(1, 25)
                .mapToObj(i -> "" + (char) r.nextInt('A', 'z' + 1))
                .map(p -> p + (char) r.nextInt('A', 'z' + 1))
                .map(p -> p + (char) r.nextInt('#', '&' + 1))
                .map(p -> p + r.nextInt(0, 9))
                .map(p -> p + r.nextInt(0, 9))
                .map(p -> p + (char) r.nextInt('#', '&' + 1))
                .map(p -> p + r.nextInt(0, 9))
                .map(p -> p + r.nextInt(0, 9))
                .map(p -> p + (char) r.nextInt('#', '&' + 1))
                .map(p -> p + (char) r.nextInt('A', 'z' + 1))
                .map(p -> p + (char) r.nextInt('A', 'z' + 1))
                .forEach(System.out::println);
    }

    private static void generateDates(Random r) {
        IntStream.range(1, 25)
                .mapToObj(l -> generateDate(r, "2022-09-01", "2022-12-31", sdf))
                .forEach(System.out::println);
    }

    private static Date generateDate(Random r, String start, String end, SimpleDateFormat sdf) {
        try {
            long sTime = sdf.parse(start).getTime();
            long eTime = sdf.parse(end).getTime();
            return new Date(r.nextLong(sTime, eTime));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generatePhones(Random r) {
        IntStream.range(1, 25)
                .mapToObj(i -> IntStream.range(0, 7)
                        .mapToObj(p -> r.nextInt(0, 10) + "")
                        .collect(Collectors.joining())
                ).forEach(System.out::println);
    }

}

class A {
    int a = 1;
    String s = "a string";

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                ", s='" + s + '\'' +
                '}';
    }
}

class B extends A {
    int b = 5;
    String t = "a text";
    char[] chars = {'a', 'b'};

    @Override
    public String toString() {
        return "B{" +
                "a=" + a +
                ", s='" + s + '\'' +
                ", b=" + b +
                ", t='" + t + '\'' +
                ", chars=" + Arrays.toString(chars) +
                '}';
    }
}
