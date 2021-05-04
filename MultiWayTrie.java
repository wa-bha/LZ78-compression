import java.util.ArrayList;
import java.util.List;

//
// Multi Way Trie
//
public class MultiWayTrie {
    // Private fields
    private static  List<Integer> trieCounts = new ArrayList<Integer>(); // Should allow multiple MultiWayTrie support
    private int             _trieIndex;
    private MultiWayTrie    _parent;
    private MultiWayTrie    _rightSibling;
    private MultiWayTrie    _firstChild;
    private int             _mismatchedValue;
    private int             _phraseIndex;

    //
    // Create new MultiWayTrie that is the root
    //
    public MultiWayTrie() {
        _trieIndex = trieCounts.size();
        trieCounts.add(0);

        // Initialize multi way trie to have null phrase
        _parent             = null;
        _rightSibling       = null;
        _firstChild         = null;
        _mismatchedValue    = 0;
        _phraseIndex        = 0;
    }
    //
    // Create new MultiWayTrie that is not  the root
    //
    private MultiWayTrie(MultiWayTrie parent, int mismatchedValue) {
        _trieIndex = parent._trieIndex;
        trieCounts.set(_trieIndex, trieCounts.get(_trieIndex)+1); // Increment trie count

        // Initialize multi way trie to have non null phrase
        _parent             = parent;
        _rightSibling       = null;
        _firstChild         = null;
        _mismatchedValue    = mismatchedValue;
        _phraseIndex        = trieCounts.get(_trieIndex);
    }

    //
    // Get mismatched value
    //
    public int getMismatchedValue() {
        return _mismatchedValue;
    }

    //
    // Get parentPhraseIndex
    //
    public int getPhraseIndex() {
        return _phraseIndex;
    }

    //
    // Insert a new value or return the node that is equal to the value
    //
    public MultiWayTrie insert(int value) {
        MultiWayTrie currentChild = _firstChild;
        MultiWayTrie prevChild = currentChild;
        
        // Iterate through this node's children
        while (currentChild != null) {

            // If value is already in phrase, return node
            if (currentChild._mismatchedValue == value) {
                moveToFront(currentChild);
                return currentChild;
            }
            // Else keep looking
            prevChild = currentChild;
            currentChild = currentChild._rightSibling;
        }

        // Value not yet in this path, so insert value after all other child nodes
        currentChild = new MultiWayTrie(this, value);
        if (_firstChild == null) {
            _firstChild = currentChild;
        } else {
            prevChild._rightSibling = currentChild;
        }
        moveToFront(currentChild);
        return null; // Returning null indicates value was inserted
    }

    //
    // Move multi way trie to front of all other siblings
    //
    private void moveToFront(MultiWayTrie node) {
        MultiWayTrie parent = node._parent;
        if (parent != null) {
            MultiWayTrie current = parent._firstChild;
            MultiWayTrie previous = current;
            // Find the left sibling of 'node' and store in 'previous' variable
            while (current._rightSibling != null) {
                previous = current;
                current = current._rightSibling;
                if (current == node) {
                    break;
                }
            }

            MultiWayTrie after = current._rightSibling;

            // Move current to front on all siblings
            current._rightSibling = parent._firstChild;
            parent._firstChild = current;

            // Dereference old current position in Trie without losing anything that comes after current
            previous._rightSibling = after;
        }
    }
}