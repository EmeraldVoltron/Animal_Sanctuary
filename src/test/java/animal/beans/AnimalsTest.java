/**
 * @author xbitt = twilkens
 * CIS171 - Spring 2023
 * Apr 23, 2023
 */
package animal.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author xbitt
 *
 */
class AnimalsTest {

private Animals animal;
	
	@Before
	public void setUp() {
		animal = new Animals("Rex", "Dog", "Male", "Labrador", "Golden", "Large");
	}
	
	@Test
	public void testGetters() {
		assertEquals("Rex", animal.getName());
		assertEquals("Dog", animal.getSpecies());
		assertEquals("Male", animal.getGender());
		assertEquals("Labrador", animal.getBreed());
		assertEquals("Golden", animal.getColor());
		assertEquals("Large", animal.getSize());
	}
	
	@Test
	public void testSetName() {
		animal.setName("Max");
		assertEquals("Max", animal.getName());
	}
	
	@Test
	public void testSetSpecies() {
		animal.setSpecies("Cat");
		assertEquals("Cat", animal.getSpecies());
	}
	
	@Test
	public void testSetGender() {
		animal.setGender("Female");
		assertEquals("Female", animal.getGender());
	}
	
	@Test
	public void testSetBreed() {
		animal.setBreed("Poodle");
		assertEquals("Poodle", animal.getBreed());
	}
	
	@Test
	public void testSetColor() {
		animal.setColor("Black");
		assertEquals("Black", animal.getColor());
	}
	
	@Test
	public void testSetSize() {
		animal.setSize("Small");
		assertEquals("Small", animal.getSize());
	}
	
	@Test
	public void testIsAvailable() {
		assertTrue(animal.isAvailable());
	}
	
	@Test
	public void testSetAvailable() {
		animal.setAvailable(false);
		assertFalse(animal.isAvailable());
	}

}
