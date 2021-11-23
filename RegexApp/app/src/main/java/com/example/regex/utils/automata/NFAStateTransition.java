package com.terraincognita.automata;

import com.terraincognita.automata.states.NFAState;
import com.terraincognita.errors.NotImplementedException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NFAStateTransition implements Map<String, Set<NFAState>> {

    public HashMap<String, Set<NFAState>> transitions;

    @Override
    public int size() {
        return this.transitions.size();
    }

    @Override
    public boolean isEmpty() {
        return this.transitions.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.transitions.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (Object i : this.values()) {
            Set<Object> set = (Set<Object>) i;
            if (set.contains(value)) {
                return  true;
            }
        }
        return false;
    }

    @Override
    public Set<NFAState> get(Object key) {
        return this.transitions.get(key);
    }

    @Override
    public Set<NFAState> put(String key, Set<NFAState> value) {
        if (this.containsKey(key)) {
            Set<NFAState> states = this.transitions.get(key);
            states.addAll(value);
            this.transitions.put(key, states);
        } else {
            this.transitions.put(key, value);
        }
        return null;
    }

    @Override
    public Set<NFAState> remove(Object key) {
        throw new NotImplementedException("StateTransition");
    }

    @Override
    public void putAll(Map m) {
        throw new NotImplementedException("StateTransition");
    }

    @Override
    public void clear() {
        throw new NotImplementedException("StateTransition");
    }

    @Override
    public Set<String> keySet() {
        throw new NotImplementedException("StateTransition");
    }

    @Override
    public Collection<Set<NFAState>> values() {
        throw new NotImplementedException("StateTransition");
    }

    @Override
    public Set<Entry<String, Set<NFAState>>> entrySet() {
        throw new NotImplementedException("StateTransition");
    }
}
