package contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {
    private ContactService contactService;
    private Contact testContact;

    @BeforeEach
    void setUp() {
        contactService = new ContactService();
        testContact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
    }

    @Test
    void testAddContact() {
        contactService.addContact(testContact);
        assertEquals(testContact, contactService.getContact("1234567890"));
    }

    @Test
    void testAddDuplicateContact() {
        contactService.addContact(testContact);
        Contact duplicateContact = new Contact("1234567890", "Jane", "Doe", "9876543210", "456 Oak St");
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(duplicateContact);
        });
    }

    @Test
    void testAddNullContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(null);
        });
    }

    @Test
    void testDeleteContact() {
        contactService.addContact(testContact);
        contactService.deleteContact("1234567890");
        assertNull(contactService.getContact("1234567890"));
    }

    @Test
    void testDeleteNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.deleteContact("nonexistent");
        });
    }

    @Test
    void testUpdateContact() {
        contactService.addContact(testContact);
        contactService.updateContact("1234567890", "Jane", "Smith", "9876543210", "456 Oak St");
        
        Contact updatedContact = contactService.getContact("1234567890");
        assertEquals("Jane", updatedContact.getFirstName());
        assertEquals("Smith", updatedContact.getLastName());
        assertEquals("9876543210", updatedContact.getPhone());
        assertEquals("456 Oak St", updatedContact.getAddress());
    }

    @Test
    void testUpdateNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContact("nonexistent", "Jane", "Smith", "9876543210", "456 Oak St");
        });
    }

    @Test
    void testPartialUpdate() {
        contactService.addContact(testContact);
        contactService.updateContact("1234567890", "Jane", null, null, null);
        
        Contact updatedContact = contactService.getContact("1234567890");
        assertEquals("Jane", updatedContact.getFirstName());
        assertEquals("Doe", updatedContact.getLastName());
        assertEquals("1234567890", updatedContact.getPhone());
        assertEquals("123 Main St", updatedContact.getAddress());
    }
}