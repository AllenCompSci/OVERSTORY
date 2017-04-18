package onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics;

/**
 * Created by 226864 on 4/18/2017.
 */
public interface ConversationGraphObserver {

        public static enum ConversationCommandEvent {
            EXIT_CONVERSATION,
            ACCEPT_QUEST,
            RETURN_QUEST,
            NONE
        }

        void onNotify(final ConversationGraph graph, ConversationCommandEvent event);
}
