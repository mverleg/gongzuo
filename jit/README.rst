
JIT
===============================

The input code, here represented as an abstract syntax tree (AST), is converted to executable bytes, here represented as executable statements that are interpreted by a processor class.

Compilation
-------------------------------

The unit of just-in-time compilation is a function, since this is a proof of concept.

The executable code exists in three stages:

1. A statements that compiles the AST in preliminary mode when executed (see next point), then runs that.
2. Debug code, which includes benchmarking code and which does not include slow optimizations. After being called often, it is optimized (see next point).
3. Optimized mode, which is compiled more slowly with many optimizations, based on the runtime benchmarking of the debug code.

The processor can only run a limited set of instructions.

Language
-------------------------------

The input language is intentionally simple. It only has expressions, which return either integers or booleans.

Features include:

* Variables (only integers), which are all in function-scope.
* Functions (which are compiled on-demand). Functions always return an integer, and take any number of integers as input. They are all top-level.
* If statements, which return an integer.
* Logical operations, which are only used for if statement conditions.
* Arithmetic operations.
* Constants.

Loops may be added in the future.

Notes
-------------------------------

LLVM IR is quite low-level.

* Possibly when compiling to LLVM, there should be another intermediary language (without if statements and loops), which is not necessary for Javascript.
* Some optimizations are easier or only possible at LLVM IR level and should be done by LLVM itself, but some safety guarantees are lost by converting to more expressive statements, potentially disabling other optimizations.


