package com.uwutever.RegexApp.beans;

import org.apache.commons.collections4.Transformer;

import java.util.Date;
import java.util.Random;

/**
 * Transforms the input type into a random location within
 * the bounds of the Dimension property.
 * This is used as the backing Transformer for the LazyMap
 * for many Layouts,
 * and provides a random location for unmapped vertices
 * the first time they are accessed.
 *
 * @param <V> the type parameter
 * @author Tom Nelson
 */
public class RandomLocationTransformer<V> implements Transformer<V, Point2D> {

    private Dimension dimension;

    private Random random;

    public RandomLocationTransformer(Dimension d) {
        this(d, new Date().getTime());
    }

    private RandomLocationTransformer(final Dimension d, long seed) {
        this.dimension = d;
        this.random = new Random(seed);
    }

    public Point2D transform(V v) {
        int limit1 = dimension.getWidth() - 40;
        int limit2 = dimension.getHeight() - 40;

        double random1 = random.nextDouble() * dimension.getWidth();
        double random2 = random.nextDouble() * dimension.getHeight();

        while ( !((40<=random1) &&
                (random1<=limit1-40) &&
                (40<=random2) &&
                (random2<=limit2)))
        {
            random1 = random.nextDouble() * dimension.getWidth();
            random2 = random.nextDouble() * dimension.getHeight();
        }

        return new Point2D(random1, random2);
    }
}