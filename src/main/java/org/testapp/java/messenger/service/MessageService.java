package org.testapp.java.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.testapp.java.messenger.database.DatabaseClass;
import org.testapp.java.messenger.exception.DataNotFoundException;
import org.testapp.java.messenger.model.Message;

public class MessageService {
	
	// All instances of the messageService will point to the same
	// in-mem "messages" map object. So, we can add a message in 
	// one messageService instance and delete it in the other, still
	// pointing all messageservice instances to the same messages obj instance
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1L, "Hello", "John"));
		messages.put(2L, new Message(2L, "Hey", "Doe"));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message msg : messages.values()) {
			cal.setTime(msg.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(msg);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if (start+size > list.size()) 
			return new ArrayList<Message>();
		return list.subList(start, start+size);
	}	
	
	public Message getMessage(long id) {
		Message msg = messages.get(id);
		if (msg == null) {
			throw new DataNotFoundException("Message with id " + id + " does not exist");
		}
		return msg;
	}
	
	public Message addMessage(Message m1) {
		m1.setId(messages.size() + 1);
		messages.put(m1.getId(), m1);
		return m1;
	}
	
	public Message updateMessage(Message m1) {
		if (m1.getId() <= 0) {
			return null;
		} else {
			messages.put(m1.getId(), m1);
			return m1;
		}
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
