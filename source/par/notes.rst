
Asynchronicity & parallelism
===============================

I want to unify the syntax for async and parallel (even though they function vastly differently).

Would it be possible to treat async IO as a thread? If instead of putting things in event loop, we just spawn a thread for each, then we have IO automatically. Things stop being deterministic though. The IO 'thread' could actually queue the IO operation, stop, wait for callback and then start a new one, right? Or at least prevent itself from getting focus while it's busy. If fibers are used, there has to be a way to make the IO non-blocking.

Threads switch at any time or even run concurrently, which async doesn't. But if there are only fixed communication points, does it matter?

A yield stream could still be buffered, it'd just be useless.

Can working parallel programs automatically run non-parallel-concurrently, e.g. is there a case where both threads must be active simultaneously?

TODO:

* "Writing nontrivial multithreaded code using thread and mutex primitives is very hard to get right (not only due to data races, but also race conditions and deadlocks). Has your experience been different?"
* "OK, idiomatic Go and Erlang will never deadlock. Sure, you can use mutexes in Go, but unlike in Rust they aren't the only means of achieving high levels of concurrency (in fact, they are explicitly discouraged)."


yield (from) - one-way
-------------------------------

Compiles the yielding function to a state machine, which computes the next value each time it is called.

.. code-block:: python

    def yieldfun(upto):
        print('start')
        for k in range(upto):
            print('step-')
            yield k**2
        yield -1

    for k in yieldfun(3):
        print(k)

.. code-block:: python

    class yieldfun_():
        def __init__(self, upto):
            self.upto = upto
            self.step = 0
            print('start')

        def next():
            if self.step == 0:
                print('start')
                range_upto = range(upto)
                index = 0
                self.step = 1
            if self.step == 1:
                try:
                    k = next(range_upto)
                except StopIteration:
                    step.step = 2
                print('step')
                return k**2
            if step == 2:
                self.step = 3
                return -1
            if step == 3:
                raise Finished()

    inst = yieldfun_(3)
    try:
        while true:
            k = inst.next()
            print(k)
    except Finished:
        pass

(This is just an example. Admittedly I have no idea how to automatically do this transformation).

Or alternatively:

.. code-block:: python

    class yieldfun_():
        def __init__(self, upto):
            self.upto = upto
            self.visited1 = False
            self.visited2 = False

        def next(self):
            if not self.visited1:
                print('start')
                self._iter = iter(range(self.upto))
                self.visited1 = True
            for k in self._iter:
                print('step')
                return k**2
            if self.visited2:
                return -1
                self.visited1 = True
            raise Finished('end')

    inst = yieldfun_(3)
    try:
        while true:
            k = inst.next()
            print(k)
    except Finished:
        pass

But the problem remains finding out which statements should be visited once only...

For example, what happens if there's a statement after the loop yield? Basically, the loop has to be frozen and the position within in remembered.

yield (from) - two-way
-------------------------------

It seems like this should be fairly similar to the one-way example, by providing arguments. But I don't really like the Python syntax in this case.

#TODO

await (async)
-------------------------------

Compiles such that the function is split at the await point, with the second half being queued for the event loop.

.. code-block:: python

    async def asyncdo():
        print('before')
        q = await readfile('filename.txt')
        print('after - q = {}'.format(q))

    async def readfile(fname):


First, wrong, attempt:

.. code-block:: python

    queue = get_async_queue()

    def asyncdo():
        print('before')
        queue.add(lambda: asyncdo__2())

    def asyncdo__2():
        q = readfile()
        print('after - q = {}'.format(q))

This doesn't work for a few reasons:

* ``getthing()`` doesn't return ``q``, instead it returns a promise (but does it have to?).
* the parallel code inside ``getthing()`` should run separately, and the OS should say when it's ready
* ???

https://www.quora.com/How-is-asynchronous-IO-implemented-in-programming-languages

.. code-block:: python



#TODO

Parallel task
-------------------------------


Using parallel to make await
-------------------------------




