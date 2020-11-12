public class Q2 {
    private static int[] thread_output_number; // To store number with max divisors for each thread
    private static int[] thread_output_divisors; // To store max divisors for each thread

    public static void main(String[] args) {
        long startTime = System.nanoTime(); // Start time

        final int number_of_threads = 5; // Number of threads

        // Initialize arrays
        thread_output_number = new int[number_of_threads];
        thread_output_divisors = new int[number_of_threads];
        // To store thread objects
        threads[] thread_objects = new threads[number_of_threads];

        final int max_integer = 100000; // MAX Value

        System.out.println("Maximum Integer: " + max_integer + "\n"
                + "Total Number of Threads: " + number_of_threads);

        int difference = max_integer / number_of_threads; // Difference between start and end

        int start = 1, end = difference; // start, end for first thread

        for (int i = 0; i < number_of_threads; i++) {
            // Create new thread
            thread_objects[i] = new threads(start, end, i);

            // Move thread to runnable state
            thread_objects[i].start();

            // Calculate start, end for next thread
            start += difference;
            end += difference;

            // If max_integer is not divisible by number_of_threads
            end = Math.min(end, max_integer);
        }

        // Wait for all threads to complete
        for (threads object : thread_objects) {
            try {
                object.join();
            } catch (InterruptedException e) {
                System.out.println("Unable to wait for thread " + object.getName());
            }
        }

        // Find max from all threads
        int max_number_of_divisors = 0, integer_with_max_number_of_divisors = 0;
        for (int i = 0; i < number_of_threads; i++) {
            if (max_number_of_divisors < thread_output_divisors[i]) {
                max_number_of_divisors = thread_output_divisors[i];
                integer_with_max_number_of_divisors = thread_output_number[i];
            }
        }
        System.out.println("\nInteger "
                + integer_with_max_number_of_divisors
                + " has the max number of divisors "
                + max_number_of_divisors);

        long endTime = System.nanoTime(); // End time
        double timeElapsed = (endTime - startTime) / 1000000000.0; // Calculate time elapsed in seconds
        System.out.println("\nTime Elapsed: " + timeElapsed + " seconds");


    }

    public static class threads extends Thread {
        private final int start;
        private final int end;
        private final int id;
        private int max_number_of_divisors;

        public threads(int start, int end, int id) {
            this.start = start;
            this.end = end;
            this.id = id;
            this.max_number_of_divisors = 0;
        }

        private int number_of_divisors(int n) {
            int divisors = 0;
            for (int i = 1; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    if (n / i == i) {
                        divisors++;
                    } else {
                        divisors += 2;
                    }
                }
            }
            return divisors;
        }

        public void run() {
            System.out.println("\nThread: " + this.id + " started! with values:- "
                    + "Start: " + this.start
                    + "  End: " + this.end);
            for (int i = start; i <= end; i++) {
                int divisors = number_of_divisors(i);
                if (this.max_number_of_divisors < divisors) {
                    this.max_number_of_divisors = divisors;
                    thread_output_number[id] = i;
                    thread_output_divisors[id] = this.max_number_of_divisors;
                }
            }
        }
    }
}