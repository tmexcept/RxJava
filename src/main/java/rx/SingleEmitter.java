/*
 * Copyright 2016 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rx;

import rx.functions.Cancellable;

/**
 * Abstraction over a {@link SingleSubscriber} that gets either an onSuccess or onError
 * signal and allows registering an cancellation/unsubscription callback.
 * <p>
 * All methods are thread-safe; calling onSuccess or onError twice or one after the other has
 * no effect.
 * <p>History: 1.2.3 - experimental
 * @param <T> the success value type
 * @since 1.3
 */
public interface SingleEmitter<T> {

    /**
     * Notifies the SingleSubscriber that the {@link Single} has completed successfully with
     * the given value.
     * <p>
     * If the {@link Single} calls this method, it will not thereafter call
     * {@link #onError}.
     *
     * @param t the success value
     */
    void onSuccess(T t);

    /**
     * Notifies the SingleSubscriber that the {@link Single} has experienced an error condition.
     * <p>
     * If the {@link Single} calls this method, it will not thereafter call
     * {@link #onSuccess}.
     *
     * @param t
     *          the exception encountered by the Observable
     */
    void onError(Throwable t);

    /**
     * Sets a Subscription on this emitter; any previous Subscription
     * or Cancellation will be unsubscribed/cancelled.
     * @param s the subscription, null is allowed
     */
    void setSubscription(Subscription s);

    /**
     * Sets a Cancellable on this emitter; any previous Subscription
     * or Cancellation will be unsubscribed/cancelled.
     * @param c the cancellable resource, null is allowed
     */
    void setCancellation(Cancellable c);

}
