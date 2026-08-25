package io.github.connortron110.scplockdown.events.lure.interfaces;

/**
 * An Interface that can be used by entities, or types of blocks that are meant to lure and effect players
 */
public interface ILurable {

	//  The distance at which the lure starts/stops tracking a potential entity
	int MAX_DISTANCE = 20;

	void handleLure();

	void untrapEntity();

	/**
	 * Defines if the entity must look at this lure instance to be influenced
	 */
	default boolean mustLookAtToLure() {
		return false;
	}
}
