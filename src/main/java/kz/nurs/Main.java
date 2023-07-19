package kz.nurs;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Main {
    public static void main(String[] args) {
        Flux<Integer> integerFlux10 = Flux.range(100, 110);
        incrementIfMoreThenFive(integerFlux10);
        processFiveElements(integerFlux10);
        printOddNumbers(integerFlux10);
        printNumbers(integerFlux10);
    }

    public static void incrementIfMoreThenFive(Flux<Integer> flux) {
        flux.subscribe(s -> {
            if (s > 5) {
                System.out.println(++s);
            } else {
                throw new IllegalArgumentException("Error: " + s + " < than 5");
            }
        }, error -> System.out.println(error.getMessage()));
    }

    public static void processFiveElements(Flux<Integer> flux) {
        flux.subscribe(
                System.out::println,
                error -> System.out.println(error),
                () -> System.out.println("Done"),
                sub -> sub.request(5)
        );
    }

    public static void printOddNumbers(Flux<Integer> flux) {
        flux
                .filter(s -> s % 2 == 0)
                .subscribe(System.out::println);
    }

    public static void printNumbers(Flux<Integer> flux) {
        flux
                .subscribe(System.out::println)
                .dispose();
    }


}
