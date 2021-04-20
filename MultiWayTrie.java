//
// Multi way trie
//
public class MultiWayTrie {
    // Private fields
    private MultiWayTrie    _parent;
    private MultiWayTrie    _rightSibling;
    private MultiWayTrie    _firstChild;
    private Byte            _mismatchedValue;
    private int             _phraseIndex;

    //
    // Create new MultiWayTrie
    //
    public MultiWayTrie() {
        _parent             = null;
        _rightSibling       = null;
        _firstChild         = null;
        _mismatchedValue    = null;
        _phraseIndex        = 0;
    }
}