/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.rx.eureka.server.registry;

/**
 * @author Tomasz Bak
 */
public final class EvictionStrategies {

    private EvictionStrategies() {
    }

    public static EvictionStrategy percentageDrop(final int allowedPercentageDrop) {
        final double dropRatio = allowedPercentageDrop / 100D;
        return new EvictionStrategy() {
            @Override
            public int allowedToEvict(int expectedRegistrySize, int actualRegistrySize) {
                int maxAllowed = (int) (dropRatio * expectedRegistrySize);
                int currentDif = expectedRegistrySize - actualRegistrySize;
                int delta = maxAllowed - currentDif;
                return delta <= 0 ? 0 : delta;
            }
        };
    }
}
