
from math import log, ceil

def test_format_examples():
	val = 92233720368547758079418
	twoexp = 0
	for twoexp in range(ceil(log(val, 2)), 0, -1):
		twofac = ipow(2, twoexp)
		# TODO: unfinished


