
TODO
===============================

* I think it'd be educative to make the target instructions more LLVM, possibly even in static single assignment form.
* The scopes thing is a bit ugly with repeated code; is there a way to have anything HasScope automatically manage that during compilation?
* Is it possible to support if-statement without else, if I want everything to be an expression? (or loop with 0 repetitions?)
* Make sure variable, function, block etc names don't collide (currently only checked for variables, and blocks are generated)

//

* rewrite: store the benchmark data outside the preliminary-compiled object (perhaps pass it in)
* how to connect preliminary compiled versions to final ones? is that even necessary? not really if the benchmark data is separate
* mark most AST nodes as Exec except the ones that are different in prelim/opt mode (e.g. If)
* make function definition keep back-references to invoke sites
* make sure invoke sites don't know about optimization level of callee
* make every prelim expression count it's calls (but not necessarily have the same cutoff)
* can I already do recursion? I think so...


Maybe not do
-------------------------------

* Note that only one data type (int) is fully implemented, with a few boolean operations.


