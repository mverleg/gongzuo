
Storage format
===============================

Header
-------------------------------

The header consists of this information:

* Some storage format identifier (``BJ`` for binary-json, ``SJ`` for seekable binary-json).
* Protocol version, currently ``1``; in sync for both formats, stored as unsigned flex int.
* Float compression: ``0`` is uncompressed, ``1`` removed 4 bits, ``2`` to``6`` remove ``4+8n`` bits for a maximum of 46 of 52, stored as unsigned flex int.
* An indication of whether seekable format is used is already in the storage format identifier.
* All strings, see section String.

Type overview
-------------------------------

The following types are used:

* ``0000_0000`` Null
* ``0000_0011`` True
* ``0000_0010`` False
* ``0001`` Float - possibly compressed by removing up to 6.5 bytes
* ``0110`` Float - stored as f32 precision
* ``0111`` Float-as-int - an integer stored with data type float
* ``001`` Int - stored as flexible int
* ``010`` String - stored as UTF8
* ``100`` Map
* ``101`` Inhomogeneous list
* ``11`` Homogeneous ND array
  * ``00`` of strings
  * ``01`` of booleans
  * ``10`` of ints
  * ``11`` of floats - #TODO: first store all first bytes, then all second bytes, etc, for better compression; or use the XOR between lines

True, false, null
-------------------------------

These are constants that have specific binary representations.

Int
-------------------------------

Integers are stored flexible sizes integers using package ???. The first bits are shifted by 3 so the type byte can be used.

Float
-------------------------------

Floats are usually stored as f64. When not in a homogeneous array, some substitutions can be made:

* Floats which correspond to exact integers and for which this would save space are stored as flex ints.
* [#TODO: maybe?] Floats with little precision and low exponent range are stored as f32.

Full-precision floats can be lossy-compressed by cutting of bytes from the right of the significand, which is a file-wide constant (including homogeneous arrays).

String
---------------------------------------------------

Strings are interned. Each string is given a sequential number (represented as unsigned flex int).

Interned in header
...................................................

This part of the header has the following format:

* The number of strings in this header (unsigned flex int).
* In seekable format: The length in bytes of this header (unsigned flex int).
* In seekable format: The offset to the start of this header of every ``N``th string, as i32.
* The list of strings (they are sequential, so no index). First their length (unsigned flex int), then the value with UTF-8 encoding (compatible with ASCII).

#TODO: UTF-8 does not include a length or null terminator, right?

Note that maximum compression is achieved if the strings are ordered from most to least frequent, but this has a cost at writing time and is not required.

Referenced in content
...................................................

In the document, any string is represented as the index of it's interned version.

Map
-------------------------------

#TODO

It is possible that object structures will be interned in the future, but this is not the case in version ``1``.

List (inhomogeneous)
-------------------------------

#TODO

Array (ND homogeneous list)
-------------------------------

Lists which contains only one data type are stored as homogeneous structures. This allows the type to be defined only once, and makes other optimizations possible.

Note that ``null`` values cause the data to not be homogeneous (but ``NaN`` is okay for float data).

This also applies to nested lists, as long as the dimensions are square (e.g. an outer lists which contains 4 inner lists with 3 integers each).

#TODO

Floats
...............................

To aid further compression by tools like gzip, homogeneous arrays of ``n`` floats are stored in a special way. Write the first byte of each ``n`` floats, then the ``n`` second bytes, etc.

For floats which form some series or are otherwise related, this leaves similar bytes as neighbours.

Note that cutting off least significant digits combines well with this, since those are the most noisy and thus least compressible, but do note that that is lossy.


