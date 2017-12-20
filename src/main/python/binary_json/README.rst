
Binary JSON
===============================

Encode a JSON-like primitive structure as binary data.

There are two storage formats:

* A small format, which saves space but is slow to seek.
* A seekable format, which stores more meta-data, making is larger but very fast to load a specific subset of data.

Loading times should be about the same.

TODO: Writing performance for seekable format may be slower, precise method not decided yet.


