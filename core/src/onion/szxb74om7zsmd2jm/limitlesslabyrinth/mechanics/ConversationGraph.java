package onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics;

import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * Created by 226864 on 4/18/2017.
 */
public class ConversationGraph {
    private Hashtable<String, Conversation> conversations;
    private Hashtable<String, ArrayList<ConversationChoice>> associatedChoices;
    private String currentConversationID = null;

    public ConversationGraph(Hashtable<String, Conversation> conversations, String rootID)
    {
        setConversations(conversations);
        setCurrentConversation(rootID);
    }

    public void setConversations(Hashtable<String, Conversation>
                                         conversations) {
        if (conversations.size() < 0) {
            throw new IllegalArgumentException(
                    "Can't have a negative amount of conversations");
        }
        this.conversations = conversations;
        this.associatedChoices = new Hashtable<
                String,
                ArrayList<ConversationChoice>>(conversations.size());
        for (Conversation conversation : conversations.values()) {
            associatedChoices.put(
                    conversation.getId(),
                    new ArrayList<ConversationChoice>());
        }
        this.conversations = conversations;
    }



}
