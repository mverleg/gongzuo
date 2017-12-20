
from math import log, floor, ceil
from struct import Struct
from utils import DATAPERBYTE, NEGATIVE


tobit = Struct('!b')


def long_to_flex(nr):
	anr = abs(int(nr))
	if (anr > 2**63):
		raise NotImplementedError("numbers bigger than Long cannot be encoded yet, because bit-shift is not exact for such values (value: {})".format(nr))
	if anr == 0:
		blockcnt = 1
	else:
		bitcnt = int(floor(log(anr, 2))) + 2
		blockcnt = int(ceil(bitcnt / 7))
	bytes = [None] * blockcnt
	# All except first blocks have 7 bits
	for blocknr in range(blockcnt - 1, 0, -1):
		bytes[blocknr] = tobit.pack(anr % DATAPERBYTE)[0]
		anr >>= 7
	# First block has 6 or fewer bits
	bytes[0] = tobit.pack(anr % 64)[0]
	anr >>= 6
	# Set the first bit of last byte to 1
	bytes[-1] += DATAPERBYTE
	# Set the second bit of the first byte to 1 if negative
	if nr < 0:
		bytes[0] += NEGATIVE
	return bytes


