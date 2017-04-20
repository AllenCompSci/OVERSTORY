/*
Dialog classes used/adapted from patrickhoey's BludBourne
Title: BludBourne
Author: patrickhoey
Source: https://github.com/patrickhoey/BludBourne
 */

package onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Dialog;

import com.badlogic.gdx.utils.Array;

/**
 * Created by 226864 on 4/18/2017.
 */
public class ConversationGraphSubject {
    private Array<ConversationGraphObserver> _observers;

    public ConversationGraphSubject(){
        _observers = new Array<ConversationGraphObserver>();
    }

    public void addObserver(ConversationGraphObserver graphObserver) {
        _observers.add(graphObserver);
    }

    public void removeObserver(ConversationGraphObserver graphObserver) {
        _observers.removeValue(graphObserver, true);
    }

    public void removeAllObservers() {
        for(ConversationGraphObserver observer: _observers){
            _observers.removeValue(observer, true);
        }
    }

    public void notify(final ConversationGraph graph, ConversationGraphObserver.ConversationCommandEvent event) {
        for(ConversationGraphObserver observer: _observers){
            observer.onNotify(graph, event);
        }
    }
}
