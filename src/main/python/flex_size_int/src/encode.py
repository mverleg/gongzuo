
from math import log, floor, ceil
from struct import Struct
from utils import DATAPERBYTE, NEGATIVE, LASTBYTE, BitShift

tobit = Struct('!b')


def long_to_flex(nr):
	anr = abs(int(nr))
	if (anr > 2**63):
		raise NotImplementedError("numbers bigger than Long cannot be encoded yet, because bit-shift is not exact for such values (value: {})".format(nr))
	if anr == 0:
		return [LASTBYTE]
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
	bytes[-1] += LASTBYTE
	# Set the second bit of the first byte to 1 if negative
	if nr < 0:
		bytes[0] += NEGATIVE
	return bytes


def ulong_to_flex(nr, shift=None):
	assert shift is None or isinstance(shift, BitShift)
	assert nr >= 0
	if (nr > 2**63):
		raise NotImplementedError("numbers bigger than Long cannot be encoded yet, because bit-shift is not exact for such values (value: {})".format(nr))
	if nr == 0:
		return [LASTBYTE]
	bitoffset = shift.amt % 7 if shift is not None else 0
	bitcnt = int(floor(log(nr, 2))) + bitoffset + 1
	blockcnt = int(ceil(bitcnt / 7))
	bytes = [None] * blockcnt
	# Treat first blocks differently
	bytes[0] = tobit.pack(nr % (DATAPERBYTE - bitoffset))[0]
	nr >>= 7 - bitoffset
	# All blocks have 7 bits
	for blocknr in range(blockcnt - 1, -1, -1):
		bytes[blocknr] = tobit.pack(nr % DATAPERBYTE)[0]
		nr >>= 7
	# Set the first bit of last byte to 1
	bytes[-1] += LASTBYTE
	# Add the shift data to the left
	# TODO
	return bytes


