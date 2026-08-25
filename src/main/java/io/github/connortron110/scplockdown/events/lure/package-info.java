/**
 * The Lure mechanic allows Blocks and Entities to lure in Entities.
 * <p>
 * {@link io.github.connortron110.scplockdown.events.lure.LureTracker} Handles the short term memory of entities that are currently Lure instances or Entities that should be effected with Lure
 * <p>
 * {@link io.github.connortron110.scplockdown.events.lure.LureStorage} Is the long term storage of Block Lure instances locations, it primarily interfaces with {@link io.github.connortron110.scplockdown.events.lure.LureTracker} to allow the tracker to know what entities to Lure.
 * <p>
 * {@link io.github.connortron110.scplockdown.events.lure.LureSavedData} This is the actual format and how Lure data is saved to a world, only {@link io.github.connortron110.scplockdown.events.lure.LureStorage} interfaces with this to load up data ready for run time use
 */
package io.github.connortron110.scplockdown.events.lure;