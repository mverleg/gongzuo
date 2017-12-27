
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
	anr >>= 6  # TODO: doesn't actually get used, right?
	# Set the first bit of last byte to 1
	bytes[-1] += LASTBYTE
	# Set the second bit of the first byte to 1 if negative
	if nr < 0:
		bytes[0] += NEGATIVE
	return bytes


def ulong_to_flex(nr, shift=None):
	if shift is None:
		shift = BitShift(0, 0)
	assert isinstance(shift, BitShift)
	assert nr >= 0, "this function is for unsigned numbers"
	if (nr > 2**63):
		raise NotImplementedError("numbers bigger than Long cannot be encoded yet, because bit-shift is not exact for such values (value: {})".format(nr))
	# Find how many blocks are needed for the shift, data and bookkeeping
	bytecount = _determine_byte_count(1 + shift.amt, nr)
	bytes = [None] * bytecount
	# All except first flexnr blocks have 7 bits
	first_block_index = shift.amt // 8
	for blocknr in range(bytecount - 1, first_block_index, -1):
		bytes[blocknr] = tobit.pack(nr % DATAPERBYTE)[0]
		nr >>= 7
	# First flexnr block has 6 or fewer bits
	first_block_nondata = (shift.amt % 8) + 1  # TODO: +2 for signed
	assert nr < 2**(8 - first_block_nondata), "remaining data is too large for first byte's size (this is a bug)"
	# Shift the data to occupy the
	bytes[0] = tobit.pack(nr)[0] << (first_block_nondata - 1)
	# Set the first bit of last byte to 1
	bytes[-1] = LASTBYTE if bytes[-1] is None else bytes[-1] + LASTBYTE
	# Add the data from the shift to the bytes
	for bytenr, shiftdata in enumerate(shift.data):
		bytes[bytenr] = shiftdata if bytes[bytenr] is None else bytes[bytenr] + shiftdata
	return bytes


def _determine_byte_count(fixed_data_bits, numerical_value):
	"""
	Determine how many bytes exactly are needed to store a number along with some fixed/bookkeeping data,
	using 7 data bits and one continuation bit per byte for the numerical value, and 8 bits per byte for other data.
	
	:param fixed_data_bits: This includes the sign, and any shift data (but not continuation bits).
	:param numerical_value: The number to be stored in flexible size format.
	"""
	if (numerical_value == 0):
		number_bits = 0
	else:
		number_bits = int(ceil(log(abs(numerical_value), 2)))
	continuation_bits = int(ceil(number_bits / 7.))
	return int(ceil((number_bits + continuation_bits + fixed_data_bits) / 8.))


