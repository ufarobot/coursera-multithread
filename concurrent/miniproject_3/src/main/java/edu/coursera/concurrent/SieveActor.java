package edu.coursera.concurrent;

import edu.rice.pcdp.Actor;

import static edu.rice.pcdp.PCDP.finish;

/**
 * An actor class that helps implement the Sieve of Eratosthenes in
 * parallel.
 */


/**
 * An actor-based implementation of the Sieve of Eratosthenes.
 * <p>
 * TODO Fill in the empty SieveActorActor actor class below and use it from
 * countPrimes to determin the number of primes <= limit.
 */
public final class SieveActor extends Sieve {
    /**
     * {@inheritDoc}
     * <p>
     * TODO Use the SieveActorActor class to calculate the number of primes <=
     * limit in parallel. You might consider how you can model the Sieve of
     * Eratosthenes as a pipeline of actors, each corresponding to a single
     * prime number.
     */
    @Override
    public int countPrimes(final int limit) {
        final SieveActorActor actor = new SieveActorActor(2);
        finish(() -> {
            for (int i = 3; i <= limit; i += 2) {
                actor.send(i);
            }
        });

        SieveActorActor iteratorActor = actor;
        int result = 0;
        while (iteratorActor != null) {
            result++;
            iteratorActor = iteratorActor.getNextActor();
        }
        return result;
    }
    public static final class SieveActorActor extends Actor {
        private final int localPrime;
        private SieveActorActor nextActor;

        public SieveActorActor(int localPrime) {
            this.localPrime = localPrime;
        }



        public SieveActorActor getNextActor() {
            return nextActor;
        }



        /**
         * Process a single message sent to this actor.
         * <p>
         * TODO complete this method.
         *
         * @param msg Received message
         */
        @Override
        public void process(final Object msg) {
            final int candidate = (int) msg;
            if (candidate <= 0) {
                if (nextActor != null) {
                    nextActor.send(msg);
                }
                return;
            }
            if (candidate % localPrime != 0) {
                if (nextActor == null) {
                    nextActor = new SieveActorActor(candidate);
                } else {
                    nextActor.send(candidate);
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(new SieveActor().countPrimes(100000));
    }
}
